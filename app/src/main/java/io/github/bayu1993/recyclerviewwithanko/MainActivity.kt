package io.github.bayu1993.recyclerviewwithanko

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import io.github.bayu1993.recyclerviewwithanko.adapter.ClubAdapter
import io.github.bayu1993.recyclerviewwithanko.model.Club
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {
    private val items: MutableList<Club> = mutableListOf()

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

        val recyclerClub = find<RecyclerView>(R.id.recycler_club)
        initClub()
        recyclerClub.layoutManager = LinearLayoutManager(this)
        recyclerClub.adapter = ClubAdapter(items) {
            startActivity<DetailActivity>(CLUB to it)
        }
    }

    private fun initClub() {
        val clubName = resources.getStringArray(R.array.clubs)
        val clubUrl = resources.getStringArray(R.array.url_clubs)
        val detailClub = resources.getStringArray(R.array.detail_club)
        items.clear()

        for (i in clubName.indices) {
            items.add(Club(clubName[i], clubUrl[i], detailClub[i]))
        }
    }
}
