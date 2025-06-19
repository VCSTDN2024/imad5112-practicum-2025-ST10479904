package com.example.playlistmanager

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PlaylistProcessor() : Parcelable {

    private val MAX_ENTRIES = 4
    private val songName: Array<String?> = arrayOfNulls(MAX_ENTRIES)
    private val artistName: Array<String?> = arrayOfNulls(MAX_ENTRIES)
    private val ratings: Array<Int?> = arrayOfNulls(MAX_ENTRIES)
    private val comments: Array<String?> = arrayOfNulls(MAX_ENTRIES)

    var entryCount: Int = 0

    constructor(parcel: Parcel) : this() {
        parcel.readStringArray(songName)
        parcel.readStringArray(artistName)
        parcel.readStringArray(comments)
        parcel.readArray(ratings::class.java.classLoader)?.let {
            for (i in it.indices) ratings[i] = it[i] as? Int
        }
        entryCount = parcel.readInt()
    }

    fun addEntry(song: String, artist: String, comment: String, rating: Int): Boolean {
        if (entryCount < MAX_ENTRIES) {
            songName[entryCount] = song
            artistName[entryCount] = artist
            comments[entryCount] = comment
            ratings[entryCount] = rating
            entryCount++
            return true
        }
        return false
    }

    fun calculateAverageRatings(): Double {
        if (entryCount == 0) return 0.0
        var totalRatings = 0
        var actualEntries = 0
        for (i in 0 until entryCount) {
            ratings[i]?.let {
                totalRatings += it
                actualEntries++
            }
        }
    }

    fun generatedFormattedEntries() {
        val formattedList = mutableListOf<String>()
        if (entryCount == 0) {
            formattedList.add("No songs recorded yet.")
            return
        }

        for (i in 0 until entryCount) {
            val song = songName[i] ?: "N/A"
            val artist = artistName[i] ?: "N/A"
            val comment = comments[i] ?: "N/A"
            val rating = ratings[i]?.toString() ?: "N/A"
        }

        formattedList.add(
            "Song: $song\n" +
            "Artist: $artist\n" +
            "Comment: $comment\n" +
            "Rating: $rating/5\n" +
            "-----------------------"
        )
    }
    return

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeStringArray(songName as Array<String?>)
        parcel.writeStringArray(artistName as Array<String?>)
        parcel.writeStringArray(comments as Array<String?>)
        parcel.writeArray(ratings as Array<out Any?>)
        parcel.writeInt(entryCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PlaylistProcessor> {
        override fun createFromParcel(parcel: Parcel): PlaylistProcessor {
            return 0
        }

        override fun newArray(size: Int): Array<PlaylistProcessor?> {
            return arrayOfNulls(size)
        }
        }
    }
}