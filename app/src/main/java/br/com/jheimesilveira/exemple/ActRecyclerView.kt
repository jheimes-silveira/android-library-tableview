package br.com.jheimesilveira.exemple

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.ViewGroup
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.act_recycler_view.*

class ActRecyclerView : AppCompatActivity() {

    lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_recycler_view)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val tablesViewModel = getGenerateMock()
        adapter = RecyclerViewAdapter(this@ActRecyclerView, tablesViewModel)
        rv.adapter = adapter

        val linearLayoutManager = LinearLayoutManager(this@ActRecyclerView)
        rv.layoutManager = linearLayoutManager
    }

    private fun getGenerateMock(): ArrayList<TableViewModel> {
        val tablesViewModel = ArrayList<TableViewModel>()
        var model = TableViewModel()

        model.rows = GenerateMock.prepareData(20, 0)
        model.columnsHeader = GenerateMock.getPrepareColumnHeader( 0)
        tablesViewModel.add(model)

        model = TableViewModel()
        model.rows = GenerateMock.prepareData(20, 1)
        model.columnsHeader = GenerateMock.getPrepareColumnHeader( 1)
        tablesViewModel.add(model)

        model = TableViewModel()
        model.rows = GenerateMock.prepareData(20, 2)
        model.columnsHeader = GenerateMock.getPrepareColumnHeader( 2)
        tablesViewModel.add(model)

        model = TableViewModel()
        model.rows = GenerateMock.prepareData(20, 3)
        model.columnsHeader = GenerateMock.getPrepareColumnHeader( 3)
        tablesViewModel.add(model)

        model = TableViewModel()
        model.rows = GenerateMock.prepareData(20, 3)
        model.columnsHeader = GenerateMock.getPrepareColumnHeader( 3)
        tablesViewModel.add(model)

        model = TableViewModel()
        model.rows = GenerateMock.prepareData(3, 2)
        model.columnsHeader = GenerateMock.getPrepareColumnHeader( 2)
        tablesViewModel.add(model)

        model = TableViewModel()
        model.rows = GenerateMock.prepareData(30, 1)
        model.columnsHeader = GenerateMock.getPrepareColumnHeader( 1)
        tablesViewModel.add(model)

        return tablesViewModel
    }
}
