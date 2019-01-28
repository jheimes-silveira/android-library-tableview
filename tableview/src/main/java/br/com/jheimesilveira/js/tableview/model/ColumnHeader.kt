package br.com.jheimesilveira.js.tableview.model

class ColumnHeader(
        index: Int = 0,
        id: String? = null,
        data: String,
        width: Int = 0,
        height: Int = 100) : Cell(id = id, data = data, width = width, height = height)