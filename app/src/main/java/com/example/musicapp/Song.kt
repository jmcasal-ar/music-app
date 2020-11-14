package com.example.musicapp

import android.os.Parcelable
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import kotlinx.android.parcel.Parcelize

@DatabaseTable(tableName = "Songs")
@Parcelize
class Song(
    @DatabaseField
    val name: String,
    @DatabaseField
    val artist: String,
    @DatabaseField
    val language: Boolean,
    @DatabaseField(id = true)
    val id: Int? = null
) : Parcelable {

    constructor() : this("", "", false, 0)

}