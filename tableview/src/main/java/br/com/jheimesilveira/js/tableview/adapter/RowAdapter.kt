package br.com.jheimesilveira.js.tableview.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import br.com.jheimesilveira.js.tableview.R
import br.com.jheimesilveira.js.tableview.model.Row
import java.util.*


class RowAdapter(private val context: Context, private var rows: ArrayList<Row>) : RecyclerView.Adapter<RowAdapter.ClubViewHolder>() {

    companion object {
        private val TYPE_ROW = 0
        private val TYPE_ROW_COLORFUL = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) {
            TYPE_ROW_COLORFUL
        } else TYPE_ROW
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ClubViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.row_layout, viewGroup, false)
        return ClubViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClubViewHolder, position: Int) {
        val skRow = this.rows[position]
        holder.llBody.removeAllViews()

        for (i in skRow.cells) {
            val textViewCell = TextView(context)
            val width = if (i.width > 0) i.width else skRow.width
            textViewCell.text = i.data
            val lp = RelativeLayout.LayoutParams(width, skRow.height)
            textViewCell.layoutParams = lp
            textViewCell.gravity = Gravity.CENTER_VERTICAL
            textViewCell.maxLines = 1
            textViewCell.setPadding(20, 0, 20, 0)
            textViewCell.setBackgroundResource(R.drawable.border_contorn_row)
            holder.llBody.addView(textViewCell)
        }
    }

    override fun getItemCount(): Int {
        return rows.size
    }

    inner class ClubViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var llBody: LinearLayout = view.findViewById(R.id.llBody)
    }

}