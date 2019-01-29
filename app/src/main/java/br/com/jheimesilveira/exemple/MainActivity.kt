package br.com.jheimesilveira.exemple

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.jheimesilveira.js.tableview.util.GenerateMock
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tableview.allItens(
                rows = GenerateMock.prepareData(300, 20))
        tableview.striped = true
        tableview.startDrawer()
    }

}
