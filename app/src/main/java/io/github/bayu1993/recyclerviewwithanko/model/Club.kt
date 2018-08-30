package io.github.bayu1993.recyclerviewwithanko.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Bayu teguh pamuji on 8/30/18.
 * email : bayuteguhpamuji@gmail.com.
 */
@Parcelize
data class Club(val name: String? = "", val img_url: String? = "", val detail_club:String? = ""):Parcelable