package br.com.jheimesilveira.exemple

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.item_tableview.view.*

@Suppress("UNCHECKED_CAST")
class RecyclerViewAdapter(var mContext: Context, var dataSet: ArrayList<TableViewModel> = ArrayList(),
                          val maxLines: Int? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_tableview, viewGroup, false))
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        init(holder as MyViewHolder, position)
    }

    private fun init(holder: MyViewHolder, position: Int) {
        val tableModel = dataSet[position]

        holder.itemView.table.allItens(
                tableModel.rows,
                tableModel.columnsHeader)

        val height = if (holder.itemView.table.rows[0].height * holder.itemView.table.rows.size > 800)
            800
        else
            ViewGroup.LayoutParams.MATCH_PARENT
        val lp = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height)
        holder.itemView.table.layoutParams = lp
        holder.itemView.table.llHeaderColumn.gravity = Gravity.CENTER
        holder.itemView.table.rvRows.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                v.parent.requestDisallowInterceptTouchEvent(true)
            } else if (event.action == MotionEvent.ACTION_UP) {
                v.parent.requestDisallowInterceptTouchEvent(false)
            }
            false
        }
        android.os.Handler().postDelayed({
            holder.itemView.table.startDrawer()
        }, 10)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}