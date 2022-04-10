package com.dicoding.myrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MoveActivity : AppCompatActivity() {

    private lateinit var rvHeroes: RecyclerView
    private var list: ArrayList<Province> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_row_prov)

//        rvHeroes = findViewById(R.id.rv_prov)
        rvHeroes.setHasFixedSize(true)

        list.addAll(ProvinceData.listData)

//        showRecyclerList()

    }

//    private fun showRecyclerList() {
//        rvHeroes.layoutManager = LinearLayoutManager(this)
//        val listProvinceAdapter = ListProvinceAdapter(list)
//        rvHeroes.adapter = listProvinceAdapter
//    }

    
}