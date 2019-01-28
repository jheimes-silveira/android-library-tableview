package br.com.jheimesilveira.js.tableview

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.*
import br.com.jheimesilveira.js.tableview.adapter.RowAdapter
import br.com.jheimesilveira.js.tableview.model.ColumnHeader
import br.com.jheimesilveira.js.tableview.model.Row
import br.com.jheimesilveira.js.tableview.model.RowHeader
import br.com.jheimesilveira.js.tableview.util.FixedGridLayoutManager
import java.util.ArrayList

class TableView @JvmOverloads constructor(var mContext: Context, attrs: AttributeSet? = null) : LinearLayout(mContext, attrs) {

    internal var scrollX = 0
    internal var scrollY = 0

    lateinit var rows: ArrayList<Row>
    lateinit var columnsHeader: ArrayList<ColumnHeader>
    lateinit var rowsHeader: ArrayList<RowHeader>

    private lateinit var rvHeader: RecyclerView
    private lateinit var hsvHeaderColumn: HorizontalScrollView
    private lateinit var svHeaderRow: ScrollView
    private lateinit var llHeaderRow: LinearLayout
    private lateinit var llHeaderColumn: LinearLayout

    private lateinit var SKRowAdapter: RowAdapter
    private lateinit var tvCorner: TextView

    init {
//        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TableView, 0, 0)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.tableview, this, true)

        initViews()

//        mTitle = typedArray.getString(R.styleable.ItemSelectView_titleText)
//        mTitleDialog = typedArray.getString(R.styleable.ItemSelectView_titleTextDialog)

//        @SuppressWarnings("ResourceAsColor")
//        tvTitle.textColor = typedArray.getColor(R.styleable.ItemSelectView_titleColor, R.color.silver)

//        typedArray.recycle()
    }

    fun startAllItems(
            rows: ArrayList<Row>,
            columnsHeader: ArrayList<ColumnHeader> = ArrayList(),
            rowsHeader: ArrayList<RowHeader> = ArrayList()) {

        verifyIfRowsEqualsColumns(rows)
        setGenerateColumnsHeader(rows, columnsHeader)
        setGenerateRowsHeader(rows, rowsHeader)
        setGenerateMaxWidthPerColumn(rows, columnsHeader)
        setGenerateIndexRows(rows)

        this.rows = rows
        this.rowsHeader = rowsHeader
        this.columnsHeader = columnsHeader

        initHeaderColumns()
        initHeaderRows()
        initCorner(rowsHeader)
        setUpRecyclerView()
    }

    private fun initViews() {
        rvHeader = findViewById(R.id.rvHeader)
        hsvHeaderColumn = findViewById(R.id.hsvHeaderColumn)
        llHeaderColumn = findViewById(R.id.llHeaderColumn)
        llHeaderRow = findViewById(R.id.llHeaderRow)
        tvCorner = findViewById(R.id.tvCorner)
        svHeaderRow = findViewById(R.id.svHeaderRow)
    }


    private fun setGenerateIndexRows(rows: ArrayList<Row>) {
        var i = 0
        var j = 0

        rows.map { row ->
            row.cells.map { cell ->
                cell.i = i
                cell.j = j
                j++
            }
            i++
        }
    }

    private fun setGenerateRowsHeader(rows: ArrayList<Row>, rowsHeader: ArrayList<RowHeader>) {
        val sizeRows = rows.size
        val sizeRowsHeader = rowsHeader.size


        if (sizeRowsHeader >= sizeRows) {
            return
        }

        for (i in sizeRowsHeader until sizeRows) {
            rowsHeader.add(RowHeader(
                    index = i,
                    id = "${i + 1}",
                    data = "${i + 1}")
            )
        }
    }

    private fun setGenerateColumnsHeader(rows: ArrayList<Row>, columnsHeader: ArrayList<ColumnHeader>) {
        val sizeColumns = rows[0].cells.size
        val sizeColumnsHeader = columnsHeader.size


        if (sizeColumnsHeader >= sizeColumns) {
            return
        }

        for (i in sizeColumnsHeader until sizeColumns) {
            columnsHeader.add(ColumnHeader(
                    index = i,
                    id = "${i + 1}",
                    data = "${i + 1}")
            )
        }
    }

    private fun verifyIfRowsEqualsColumns(rows: ArrayList<Row>) {
        val size = rows[0].cells.size
        rows.map { row ->
            if (row.cells.size != size) {
                throw IllegalArgumentException("A quantidade de itens por colunas em todas as linhas devem ser iguais")
            }
        }
    }

    private fun setGenerateMaxWidthPerColumn(
            rows: ArrayList<Row>,
            columnsHeader: ArrayList<ColumnHeader>) {

        val sizeI = rows.size
        val sizeJ = rows[0].cells.size

        for (j: Int in 0 until sizeJ) {

            // Caso já tenha algum valor, não ira procurar uma largura maxima para a linha corrente
            var maxWidth = 0

            for (i: Int in 0 until sizeI) {
                val width = getConverStringToWidth(rows[i].cells[j].data)
                if (maxWidth < width) {
                    maxWidth = width
                }
            }

            for (i: Int in 0 until sizeI) {
                rows[i].cells[j].width = maxWidth
            }

            columnsHeader[j].width = maxWidth
        }
    }

    private fun getConverStringToWidth(text: String): Int {
        val textViewCell = TextView(mContext)
        textViewCell.text = text
//        val lp = RelativeLayout.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT)
//        textViewCell.layoutParams = lp

        val rect = Rect()
        textViewCell.paint.getTextBounds(textViewCell.text.toString(), 0, textViewCell.text.length, rect)
        return rect.width()
    }

    private fun getMaxCaracter(rows: ArrayList<Row>): Int {
        var maxCaracter = 0
        rows.map { row ->
            row.cells.map { cell ->
                if (maxCaracter < cell.data.length) {
                    maxCaracter = cell.data.length
                }
            }
        }
        return maxCaracter
    }

    private fun initCorner(rowsHeader: ArrayList<RowHeader>) {
        tvCorner.layoutParams = RelativeLayout.LayoutParams(rowsHeader[0].width, rowsHeader[0].height)
    }

    private fun initHeaderColumns() {
        llHeaderColumn.removeAllViews()
//        if (skColumnHeader == null) {
//            hsvHeaderColumn.visibility = View.GONE
//            return
//        }
//        hsvHeaderColumn.visibility = View.VISIBLE
//

        for (i in columnsHeader) {
            val textViewCell = TextView(mContext)
            textViewCell.text = i.data
            val lp = RelativeLayout.LayoutParams(i.width, i.height)
            textViewCell.layoutParams = lp
            textViewCell.gravity = Gravity.CENTER
            textViewCell.maxLines = 1
            textViewCell.setBackgroundResource(R.drawable.border_contorn_column_header)
            llHeaderColumn.addView(textViewCell)
        }
    }

    private fun initHeaderRows() {
        llHeaderRow.removeAllViews()
//        if (skColumnHeader == null) {
//            hsvHeaderColumn.visibility = View.GONE
//            return
//        }
//        hsvHeaderColumn.visibility = View.VISIBLE

        for (i in rowsHeader) {
            val textViewCell = TextView(mContext)
            textViewCell.text = i.data
            val lp = RelativeLayout.LayoutParams(i.width, i.height)
            textViewCell.layoutParams = lp
            textViewCell.gravity = Gravity.CENTER
            textViewCell.maxLines = 1
            textViewCell.setBackgroundResource(R.drawable.border_contorn_row_header)
            llHeaderRow.addView(textViewCell)
        }
    }

    /**
     * Handles RecyclerView for the action
     */
    private fun setUpRecyclerView() {
        SKRowAdapter = RowAdapter(mContext, rows)

        val manager = FixedGridLayoutManager()
        manager.setTotalColumnCount(1)
        rvHeader.layoutManager = manager
        rvHeader.adapter = SKRowAdapter
//        rvHeader.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))


        rvHeader.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                scrollX += dx

                hsvHeaderColumn.scrollTo(scrollX, 0)
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })

        rvHeader.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                scrollY += dy

                svHeaderRow.scrollTo(0, scrollY)
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })

        svHeaderRow.setOnTouchListener { _, _ -> true }
        hsvHeaderColumn.setOnTouchListener { _, _ -> true }
    }
}