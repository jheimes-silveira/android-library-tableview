package br.com.jheimesilveira.exemple

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.act_main.*

class ActMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)
        initBtnActivity()
        initBtnRecyclerView()
    }

    private fun initBtnRecyclerView() {
        btnRecycleView.setOnClickListener {
            startActivity(Intent(this@ActMain, ActRecyclerView::class.java))
        }
    }

    private fun initBtnActivity() {
        btnActivity.setOnClickListener {
            startActivity(Intent(this@ActMain, MainActivity::class.java))
        }
    }
}
