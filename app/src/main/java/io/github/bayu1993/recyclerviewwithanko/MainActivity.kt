package io.github.bayu1993.recyclerviewwithanko

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import io.github.bayu1993.recyclerviewwithanko.adapter.ClubAdapter
import io.github.bayu1993.recyclerviewwithanko.model.Club
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {
    private val items: MutableList<Club> = mutableListOf()
    private val TAG = "MainActivity"

    class MainActivityUI : AnkoComponent<MainActivity> {
        override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
            verticalLayout {
                lparams(matchParent, matchParent)
                recyclerView {
                    id = R.id.recycler_club
                }.lparams(width = matchParent, height = wrapContent)
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)

        val recyclerView = find<RecyclerView>(R.id.recycler_club)
        initClub()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ClubAdapter(this, items) {
            startActivity<DetailActivity>(CLUB to it)
        }
    }

    private fun initClub() {
        val name = resources.getStringArray(R.array.clubs)
        val urlClub = resources.getStringArray(R.array.url_clubs)
        val detailClub = resources.getStringArray(R.array.detail_club)
        items.clear()

        for (i in name.indices) {
            items.add(Club(name[i], urlClub[i], detailClub[i]))
        }
        Log.d(TAG, "$items")
    }
}
