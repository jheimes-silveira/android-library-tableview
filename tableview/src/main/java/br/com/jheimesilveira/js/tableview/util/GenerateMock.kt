package br.com.jheimesilveira.js.tableview.util

import br.com.jheimesilveira.js.tableview.model.Cell
import br.com.jheimesilveira.js.tableview.model.ColumnHeader
import br.com.jheimesilveira.js.tableview.model.Row

object GenerateMock {

    /**
     * Prepares dummy data
     */
    fun prepareData(rows: Int, column: Int? = null): ArrayList<Row> {
        val array = ArrayList<Row>()

        if (rows != null || column == null) {
            for (i in 0..rows!!) {
                val skRow = Row(getPrepareColumn())
                array.add(skRow)
            }
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

    private fun getPrepareColumn(): ArrayList<Cell> {
        val array = ArrayList<Cell>()

        var skCell = Cell(data = "Lorem")
        array.add(skCell)

        skCell = Cell(data = "355464543")
        array.add(skCell)

        skCell = Cell(data = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. ")
        array.add(skCell)

        skCell = Cell(data = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam elementum lacus mi, in ultricies eros imperdiet sit amet. Donec vel purus mauris.")
        array.add(skCell)

        skCell = Cell(data = "Lorem")
        array.add(skCell)

        skCell = Cell(data = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. ")
        array.add(skCell)

        skCell = Cell(data = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam elementum lacus mi, in ultricies eros imperdiet sit amet. Donec vel purus mauris.")
        array.add(skCell)

        skCell = Cell(data = "R$ 56.573,00")
        array.add(skCell)
//
//        skCell = Cell(data = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. ")
//        array.add(skCell)
//
//        skCell = Cell(data = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. ")
//        array.add(skCell)
//
//        skCell = Cell(data = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam elementum lacus mi, in ultricies eros imperdiet sit amet. Donec vel purus mauris.")
//        array.add(skCell)
//
//        skCell = Cell(data = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. ")
//        array.add(skCell)
//
//        skCell = Cell(data = "Lorem ipsum dolor sit amet, consectetur adipiscing.")
//        array.add(skCell)
//
//        skCell = Cell(data = "Lorem ipsum dolor sit amet, consectetur adipiscing elit adipiscing elit. ")
//        array.add(skCell)
//
//        skCell = Cell(data = "Lorem ipsum dolor sit amet, consectetur adipiscing elit adipiscing elit adipiscing elit. ")
//        array.add(skCell)
//
//        skCell = Cell(data = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. ")
//        array.add(skCell)
//
//        skCell = Cell(data = "Lorem ipsum dolor sit amet, consectetur elit.")
//        array.add(skCell)
//
//        skCell = Cell(data = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. ")
//        array.add(skCell)
//
//        skCell = Cell(data = "Lorem ipsum dolor sit amet.")
//        array.add(skCell)
//
//        skCell = Cell(data = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam elementum lacus mi, in ultricies eros imperdiet sit amet. Donec vel purus mauris.")
//        array.add(skCell)

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