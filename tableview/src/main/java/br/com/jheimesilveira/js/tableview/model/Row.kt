package br.com.jheimesilveira.js.tableview.model

import kotlin.collections.ArrayList

class Row(
        var cells: ArrayList<Cell> = ArrayList(),
        var height: Int = 0,
        var width: Int = 0)