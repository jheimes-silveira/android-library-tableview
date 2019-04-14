package br.com.jheimesilveira.exemple

import java.util.ArrayList

class TableDto(title: String) {
    var columns = ArrayList<String>()
    var widths = ArrayList<Int>()
    var rows = ArrayList<ArrayList<String>>()
    var isAlreadyStarted = false
}
