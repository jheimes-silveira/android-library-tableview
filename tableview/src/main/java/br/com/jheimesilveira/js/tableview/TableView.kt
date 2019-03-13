package br.com.jheimesilveira.js.tableview

import android.content.Context
import android.graphics.Rect
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.WindowManager
import android.widget.*
import br.com.jheimesilveira.js.tableview.adapter.RowAdapter
import br.com.jheimesilveira.js.tableview.model.ColumnHeader
import br.com.jheimesilveira.js.tableview.model.Row
import br.com.jheimesilveira.js.tableview.model.RowHeader
import br.com.jheimesilveira.js.tableview.util.FixedGridLayoutManager
import java.util.*


class TableView @JvmOverloads constructor(var mContext: Context, attrs: AttributeSet? = null) : LinearLayout(mContext, attrs), OnGlobalLayoutListener {
    override fun onGlobalLayout() {}

    internal var scrollX = 0
    internal var scrollY = 0

    lateinit var rows: ArrayList<Row>
    lateinit var columnsHeader: ArrayList<ColumnHeader>
    lateinit var rowsHeader: ArrayList<RowHeader>

    var striped: Boolean = false
    var showRowHeader: Boolean = true
    var showColumnHeader: Boolean = true
    var percentageProportion: Float = 0.09f

    lateinit var rvRows: RecyclerView
    lateinit var hsvHeaderColumn: HorizontalScrollView
    lateinit var svHeaderRow: ScrollView
    lateinit var llHeaderRow: LinearLayout
    lateinit var llHeaderColumn: LinearLayout

    lateinit var rowAdapter: RowAdapter
    lateinit var tvCorner: TextView

    private var heightPixelsDisplay: Int = 0
    private var widthPixelsDisplay: Int = 0
    private var isCanObserverWidth: Boolean = false
    private var recalcWidthCellsMaxDisplayListener: ((rows: ArrayList<Row>) -> Unit)? = null

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

    fun allItens(
            rows: ArrayList<Row>,
            columnsHeader: ArrayList<ColumnHeader> = ArrayList(),
            rowsHeader: ArrayList<RowHeader> = ArrayList()) {
        log("start allItens")
        getScreenResolution(context)
        verifyIfRowsEqualsColumns(rows)
        setGenerateColumnsHeader(rows, columnsHeader)
        setGenerateRowsHeader(rows, rowsHeader)
        setGenerateMaxWidthPerColumn(rows, columnsHeader)
        setGenerateIndexRows(rows)

        this.rows = rows
        this.rowsHeader = rowsHeader
        this.columnsHeader = columnsHeader
    }

    fun startDrawer() {
        log("startDrawer")
        initHeaderColumns()
        initHeaderRows()
        initCorner(rowsHeader)
        setUpRecyclerView()
        initStartEventsRecycler()
    }

    fun onRecalcWidthCellsMaxDisplayListener(recalcWidthCellsMaxDisplayListener: (rows: ArrayList<Row>) -> Unit) {
        this.recalcWidthCellsMaxDisplayListener = recalcWidthCellsMaxDisplayListener
    }

    private fun initStartEventsRecycler() {
        log("initStartEventsRecycler")
        rvRows.clearOnScrollListeners()
        scrollY = 0
        scrollX = 0
        rvRows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                scrollY += dy
                scrollX += dx

                svHeaderRow.scrollTo(0, scrollY)
                hsvHeaderColumn.scrollTo(scrollX, 0)
            }
        })

        if (rvRows.viewTreeObserver.isAlive) {
            rvRows.viewTreeObserver.addOnGlobalLayoutListener {
                rvRows.viewTreeObserver.removeOnGlobalLayoutListener {}
                isCanObserverWidth = true
                val countTotalWidth = countTotalWidth()
                if (rvRows.width > countTotalWidth) {
                    log("rvRows.width: ${rvRows.width} countTotalWidth: $countTotalWidth")
                    recalcWidthCellsMaxDisplay(countTotalWidth)
                    startDrawer()
                }
            }
        }

        if (isCanObserverWidth) {
            val countTotalWidth = countTotalWidth()
            if (rvRows.width > countTotalWidth) {
                log("rvRows.width: ${rvRows.width} countTotalWidth: $countTotalWidth")
                recalcWidthCellsMaxDisplay(countTotalWidth)
                startDrawer()
            }
        }
    }

    private fun initViews() {
        rvRows = findViewById(R.id.rvRows)
        hsvHeaderColumn = findViewById(R.id.hsvHeaderColumn)
        llHeaderColumn = findViewById(R.id.llHeaderColumn)
        llHeaderRow = findViewById(R.id.llHeaderRow)
        tvCorner = findViewById(R.id.tvCorner)
        svHeaderRow = findViewById(R.id.svHeaderRow)
    }

    private fun getScreenResolution(context: Context) {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val metrics = DisplayMetrics()
        display.getMetrics(metrics)
        widthPixelsDisplay = metrics.widthPixels
        heightPixelsDisplay = metrics.heightPixels
        log("start getScreenResolution : height=$heightPixelsDisplay")
    }

    private fun setGenerateIndexRows(rows: ArrayList<Row>) {
        var i = 0
        var j = 0

        rows.map { row ->
            if (row.height == 0) row.height = generateDefaultHeigthByPorcent()
            row.cells.map { cell ->
                if (cell.height == 0) cell.height = generateDefaultHeigthByPorcent()
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

        if (sizeRowsHeader < sizeRows) {
            for (i in sizeRowsHeader until sizeRows) {
                rowsHeader.add(RowHeader(
                        index = i,
                        data = "${i + 1}")
                )
            }
        }

        rowsHeader.map { row ->
            if (row.height == 0) row.height = generateDefaultHeigthByPorcent()
            if (row.width == 0) row.width = generateDefaultHeigthByPorcent()
        }

        log("setGenerateRowsHeader")
    }

    private fun setGenerateColumnsHeader(rows: ArrayList<Row>, columnsHeader: ArrayList<ColumnHeader>) {
        val sizeColumns = rows[0].cells.size
        val sizeColumnsHeader = columnsHeader.size

        // criar de forma ordenada elementos de header que não tenha sido passado
        if (sizeColumnsHeader < sizeColumns) {
            for (i in sizeColumnsHeader until sizeColumns) {
                columnsHeader.add(ColumnHeader(
                        index = i,
                        data = "${i + 1}")
                )
            }
        }

        columnsHeader.map { column ->
            if (column.height == 0) column.height = generateDefaultHeigthByPorcent()
            if (column.width == 0) column.width = generateDefaultHeigthByPorcent()
        }
        log("setGenerateColumnsHeader ${columnsHeader}")
    }

    private fun generateDefaultHeigthByPorcent(): Int {
        return (widthPixelsDisplay.toFloat() * percentageProportion).toInt()
    }

    private fun verifyIfRowsEqualsColumns(rows: ArrayList<Row>) {
        val size = rows[0].cells.size
        rows.map { row ->
            if (row.cells.size != size) {
                throw IllegalArgumentException("A quantidade de itens por colunas em todas as linhas devem ser iguais")
            }
        }
        log("verifyIfRowsEqualsColumns true")
    }

    private fun setGenerateMaxWidthPerColumn(
            rows: ArrayList<Row>,
            columnsHeader: ArrayList<ColumnHeader>) {

        val sizeI = rows.size
        val sizeJ = rows[0].cells.size

        for (j: Int in 0 until sizeJ) {

            // Caso já tenha algum valor, não ira procurar uma largura maxima para a linha corrente
            var maxWidth = 0
            log("setGenerateMaxWidthPerColumn index j= $j")
            for (i: Int in 0 until sizeI) {
                val width = if (rows[i].cells[j].width > 0) rows[i].cells[j].width else getConverStringToWidth(rows[i].cells[j].data)
                log("setGenerateMaxWidthPerColumn index i= $i width= $width")
                if (maxWidth < width) {
                    maxWidth = width
                }
            }

            columnsHeader[j].width = if (columnsHeader[j].width > 0) columnsHeader[j].width else getConverStringToWidth(columnsHeader[j].data)

            if (columnsHeader[j].width > maxWidth) {
                 maxWidth = columnsHeader[j].width
            }

            for (i: Int in 0 until sizeI) {
                rows[i].cells[j].width = maxWidth
            }

            log("setGenerateMaxWidthPerColumn max width= $maxWidth")
            columnsHeader[j].width = maxWidth
        }
    }

    private fun getConverStringToWidth(text: String): Int {
        log("getConverStringToWidth")
        val textViewCell = TextView(mContext)
        textViewCell.text = text
//        val lp = RelativeLayout.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT)
//        textViewCell.layoutParams = lp

        val rect = Rect()
        textViewCell.paint.getTextBounds(textViewCell.text.toString(), 0, textViewCell.text.length, rect)
        return rect.width() + 50
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
        log("initHeaderColumns")
        llHeaderColumn.removeAllViews()

        for (i in columnsHeader) {
            val textViewCell = TextView(mContext)
            textViewCell.text = i.data
            val lp = RelativeLayout.LayoutParams(i.width, i.height)
            textViewCell.layoutParams = lp
            textViewCell.maxLines = 1
            textViewCell.setTypeface(null, Typeface.BOLD)
            textViewCell.setBackgroundResource(R.drawable.border_contorn_column_header)
            textViewCell.gravity = Gravity.CENTER_VERTICAL
            textViewCell.setPadding(26, 0, 0, 0)
            llHeaderColumn.addView(textViewCell)
        }

        hsvHeaderColumn.setOnTouchListener { _, _ -> true }
    }

    private fun initHeaderRows() {
        log("initHeaderRows")
        llHeaderRow.removeAllViews()

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

        svHeaderRow.setOnTouchListener { _, _ -> true }
    }

    /**
     * Handles RecyclerView for the action
     */
    private fun setUpRecyclerView() {
        log("setUpRecyclerView")
        rowAdapter = RowAdapter(mContext, rows, striped)
        val manager = FixedGridLayoutManager()
        manager.setTotalColumnCount(1)
        rvRows.layoutManager = manager
        rvRows.adapter = rowAdapter
    }

    private fun countTotalWidth(): Int {
        log("countTotalWidth")
        var maxWithColumn = 0
        rows[0].cells.map { c ->
            maxWithColumn += c.width
        }
        return maxWithColumn
    }

    private fun recalcWidthCellsMaxDisplay(countTotalWidth: Int) {
        log("recalcWidthCellsMaxDisplay")
        var diff = (rvRows.width - countTotalWidth) / rows[0].cells.size

        while ((diff * rows[0].cells.size) + countTotalWidth < rvRows.width) {
            diff++
        }
        columnsHeader.map { column ->
            column.width += diff
        }

        rows.map { row ->
            row.width += diff
            row.cells.map { cell ->
                cell.width += diff
            }
        }

        recalcWidthCellsMaxDisplayListener?.invoke(rows)
    }

    private fun log(log: String) {
        Log.d("lib-TableView", log)
    }
}