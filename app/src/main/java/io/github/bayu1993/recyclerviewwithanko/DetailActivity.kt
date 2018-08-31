package io.github.bayu1993.recyclerviewwithanko

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import io.github.bayu1993.recyclerviewwithanko.model.Club
import org.jetbrains.anko.*

class DetailActivity : AppCompatActivity() {

    class DetailUI : AnkoComponent<DetailActivity> {
        @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        override fun createView(ui: AnkoContext<DetailActivity>): View = with(ui) {
            verticalLayout {
                padding = dip(16)
                imageView {
                    id = R.id.imgClub
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    setImageResource(R.drawable.ic_launcher_background)
                }.lparams(width = 100, height = 100)
                textView {
                    text = resources.getString(R.string.club_barca)
                    id = R.id.tvTitle
                    textSize = 20f
                    textColor = Color.BLACK
                }.lparams(matchParent, wrapContent)
                textView {
                    text = resources.getString(R.string.detail_barca)
                    id = R.id.tvDetail
                    textSize = 14f
                    textColor = Color.BLACK
                }.lparams(matchParent, wrapContent)

            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetailUI().setContentView(this)

        val intentClub: Club = intent.getParcelableExtra(CLUB)
        val imageClub = find<ImageView>(R.id.imgClub)
        val tvDetailClub = find<TextView>(R.id.tvDetail)
        val tvTitle = find<TextView>(R.id.tvTitle)

        Glide.with(this).load(intentClub.img_url).into(imageClub)
        tvTitle.text = intentClub.name
        tvDetailClub.text = intentClub.detail_club
    }
}
