package br.com.jheimesilveira.js.tableview.util

import br.com.jheimesilveira.js.tableview.model.Cell
import br.com.jheimesilveira.js.tableview.model.ColumnHeader
import br.com.jheimesilveira.js.tableview.model.Row

object GenerateMock {

    /**
     * Prepares dummy data
     */
    fun prepareData(rows: Int, column: Int): ArrayList<Row> {
        val array = ArrayList<Row>()

        for (i in 0..rows) {
            val skRow = Row(getPrepareColumn(i, column))
            array.add(skRow)
        }
        return array
    }

    private fun getPrepareColumn(i: Int, column: Int): ArrayList<Cell> {
        val array = ArrayList<Cell>()
        for (j in 0..column) {
            val ref = "$i-$j"
            val skCell = Cell(data = "Item $ref ${if (i % 2 == 0) "" else "um novo texto maior para testar as dimencões"}")
            array.add(skCell)
        }
        return array
    }

    fun getPrepareColumnHeader(): ArrayList<ColumnHeader> {
        val array = ArrayList<ColumnHeader>()

        for (j in 0..10) {
            val cellColumn = ColumnHeader(data = "Nova columa $j")
            array.add(cellColumn)
        }
        return array
    }

}