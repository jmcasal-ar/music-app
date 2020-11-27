package com.example.musicapp

import android.os.Parcelable
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import kotlinx.android.parcel.Parcelize

@DatabaseTable(tableName = "Songs")
@Parcelize
class Song(
    @DatabaseField
    var name: String,
    @DatabaseField
    var artist: String,
    @DatabaseField
    var spotify: Boolean,
    @DatabaseField(id = true)
    val id: Int? = null
) : Parcelable {

    constructor() : this("", "", false, 0)

}