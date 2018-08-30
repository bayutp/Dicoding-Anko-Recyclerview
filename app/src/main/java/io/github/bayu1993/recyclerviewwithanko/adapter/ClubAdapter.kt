package io.github.bayu1993.recyclerviewwithanko.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import io.github.bayu1993.recyclerviewwithanko.R
import io.github.bayu1993.recyclerviewwithanko.model.Club
import kotlinx.android.extensions.LayoutContainer
import org.jetbrains.anko.*

/**
 * Created by Bayu teguh pamuji on 8/30/18.
 * email : bayuteguhpamuji@gmail.com.
 */

class ClubAdapter constructor(private val context: Context, private val items: List<Club>, private val listener: (Club) -> Unit) : RecyclerView.Adapter<ClubAdapter.ViewHolder>() {
    class ViewHolderUI : AnkoComponent<Context> {
        override fun createView(ui: AnkoContext<Context>): View = with(ui) {
            linearLayout {
                padding = dip(10)
                lparams(width = matchParent, height = wrapContent)
                imageView {
                    id = R.id.img_club
                    setImageResource(R.drawable.ic_launcher_background)
                }.lparams(width = dip(50), height = dip(50))

                textView {
                    id = R.id.tv_club_name
                    text = "Barcelona Fc"
                    textSize = 20f
                    textColor = Color.BLACK
                    topPadding = dip(10)
                    leftPadding = dip(10)
                }.lparams(width = wrapContent, height = wrapContent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ViewHolderUI().createView(AnkoContext.Companion.create(parent.context, false)))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer {
        private val name = containerView.find<TextView>(R.id.tv_club_name)
        private val image = containerView.find<ImageView>(R.id.img_club)

        fun bind(item: Club, listener: (Club) -> Unit) {
            name.text = item.name
            Glide.with(itemView.context).load(item.img_url)
                    .into(image)
            containerView.setOnClickListener {
                listener(item)
            }
        }
    }
}