package br.com.jheimesilveira.js.tableview.model

class RowHeader(
        index: Int = 0,
        id: String? = null,
        data: String = "",
        width: Int = 100,
        height: Int = 100) : Cell(id = id, data = data, width = width, height = height)