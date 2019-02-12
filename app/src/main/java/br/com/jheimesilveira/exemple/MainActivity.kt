package br.com.jheimesilveira.exemple

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startTable()
        initBtnStart()
    }

    private fun startTable() {

        var model = TableViewModel()
        model.rows = GenerateMock.prepareData(etRows.text.toString().toInt(), etColumn.text.toString().toInt())
        model.columnsHeader = GenerateMock.getPrepareColumnHeader( etColumn.text.toString().toInt())
        tableview.allItens(
                columnsHeader = model.columnsHeader,
                rows = model.rows)
//        tableview.striped = true
        tableview.startDrawer()
    }

    private fun initBtnStart() {
        btnStart.setOnClickListener {
            startTable()
        }
    }

}
