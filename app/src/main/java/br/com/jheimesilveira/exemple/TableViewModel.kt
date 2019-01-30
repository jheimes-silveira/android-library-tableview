package br.com.jheimesilveira.exemple

import br.com.jheimesilveira.js.tableview.model.ColumnHeader
import br.com.jheimesilveira.js.tableview.model.Row
import br.com.jheimesilveira.js.tableview.model.RowHeader

class TableViewModel {
    var rows = ArrayList<Row>()
    var rowsHeader = ArrayList<RowHeader>()
    var columnsHeader = ArrayList<ColumnHeader>()
}
