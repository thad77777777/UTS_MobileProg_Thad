package com.example.uts_mobileprogramming_naufal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var hero: RecyclerView
    private val list = ArrayList<Hero>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hero = findViewById(R.id.hero)
        hero.setHasFixedSize(true)

        list.addAll(getListFilm())
        showRecyclerList()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
                val intent= Intent(this, AboutActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun getListFilm(): ArrayList<Hero> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataketerangan = resources.getStringArray(R.array.data_keterangan)
        val listFilm = arrayListOf<Hero>()
        for (i in  dataName.indices) {
            val film = Hero(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1), dataketerangan[i])
            listFilm.add(film)
        }
        return listFilm
    }

    private fun showRecyclerList() {
        hero.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListHeroAdapter(list)
        hero.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object : ListHeroAdapter.OnItemClickCallback{
            override fun onItemClicked(data : Hero){
                val detailIntent = Intent(this@MainActivity, DetailActivity::class.java)
                detailIntent.putExtra("photo", data.photo)
                detailIntent.putExtra("title", data.name)
                detailIntent.putExtra("description", data.description)
                detailIntent.putExtra("keterangan", data.keterangan)
                startActivity(detailIntent)
            }
        })
    }
}