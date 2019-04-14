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
        val tableDto = TableDto("titulo")
        tableDto.columns = arrayListOf()
        tableDto.rows = arrayListOf(
                arrayListOf("inputl loren test imed. tes lorem input", "inputl loren test imed. tes lorem input", "inputl loren test imed. tes lorem input", "inputl loren test imed. tes lorem input", "inputl loren test imed. tes lorem input"),
                arrayListOf("inputl loren test imed. tes lorem input", "inputl loren test imed. tes lorem input", "inputl loren test imed. tes lorem input", "inputl loren test imed. tes lorem input", "inputl loren test imed. tes lorem input"),
                arrayListOf("inputl loren test imed. tes lorem input", "inputl loren test imed. tes lorem input", "inputl loren test imed. tes lorem input", "inputl loren test imed. tes lorem input", "inputl loren test imed. tes lorem input"),
                arrayListOf("inputl loren test imed. tes lorem input", "inputl loren test imed. tes lorem input", "inputl loren test imed. tes lorem input", "inputl loren test imed. tes lorem input", "inputl loren test imed. tes lorem input"),
                arrayListOf("inputl loren test imed. tes lorem input", "inputl loren test imed. tes lorem input", "inputl loren test imed. tes lorem input", "inputl loren test imed. tes lorem input", "inputl loren test imed. tes lorem input"),
                arrayListOf("inputl loren test imed. tes lorem input", "inputl loren test imed. tes lorem input", "inputl loren test imed. tes lorem input", "inputl loren test imed. tes lorem input", "inputl loren test imed. tes lorem input")
        )
        val model = TableViewModelBia(tableDto)
        tableview.allItens(
                model.getCellList(),
                model.getColumnHeaderList(),
                model.getRowHeaderList())

        tableview.startDrawer()
    }

    private fun initBtnStart() {
        btnStart.setOnClickListener {
            startTable()
        }
    }

}
