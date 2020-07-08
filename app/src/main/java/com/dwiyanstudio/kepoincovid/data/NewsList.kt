package com.dwiyanstudio.kepoincovid.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsList(val title:String,val image :String,val link : String) : Parcelable