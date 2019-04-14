package br.com.jheimesilveira.exemple

import br.com.jheimesilveira.js.tableview.model.Cell
import br.com.jheimesilveira.js.tableview.model.ColumnHeader
import br.com.jheimesilveira.js.tableview.model.Row
import br.com.jheimesilveira.js.tableview.model.RowHeader
import java.lang.Exception
import kotlin.collections.ArrayList

/**
 * Created by evrencoskun on 4.02.2018.
 */

class TableViewModelBia(private val tableDto: TableDto) {

    fun getCellList(): ArrayList<Row> {
        val list = ArrayList<Row>()

        for (row in tableDto.rows.withIndex())  {
            val skRow = Row()
            skRow.height = 85

            for (column in row.value.withIndex())  {
                val width = try { tableDto.widths[column.index] } catch (e: Exception) { 0 }
                skRow.cells.add(Cell(data = column.value, height = 85, width = width))
            }
            list.add(skRow)
        }

        return list
    }

    fun getColumnHeaderList(): ArrayList<ColumnHeader> {
        val list = ArrayList<ColumnHeader>()

        tableDto.columns.map { column ->
            list.add(ColumnHeader(data = column, height = 85))
        }

        return list
    }

    fun getRowHeaderList(): ArrayList<RowHeader> {
        val list = ArrayList<RowHeader>()

        for (column in tableDto.rows.withIndex()) {
            list.add(RowHeader(data = "${column.index + 1}", height = 85))
        }

        return list
    }

    fun isPlot(): Boolean {
        return tableDto.rows.size > 0
    }
}
