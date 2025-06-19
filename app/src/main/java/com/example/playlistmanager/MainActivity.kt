//Student Number: ST10479904
//Student name: Motheo Setloko Marotholi Marotholi
package com.example.playlistmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var etSong:EditText
    private lateinit var etArtist:EditText
    private lateinit var etRating:EditText
    private lateinit var etComments:EditText
    private lateinit var btnPlaylist:Button
    private lateinit var btnDetail:Button
    private lateinit var btnExit:Button

    companion object {
        var playlistProcessor: PlaylistProcessor = PlaylistProcessor()
    }

    private fun addPlaylistEntry() {
        val songNam = etSong.text.toString().trim()
        val artistNam = etArtist.text.toString().trim()
        val ratingStr = etRating.text.toString().trim()
        val commentz = etComments.text.toString().trim()

        if (songNam.isEmpty() || artistNam.isEmpty() || ratingStr.isEmpty() || commentz.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val rating: Int
        try {
            rating = ratingStr.toInt()
            if (rating < 1 || rating > 5) {
                Toast.makeText(this, "Please make the number between 1 and 5.", Toast.LENGTH_SHORT).show()
                return
            }
        } catch (e: NumberFormatException){
            Toast.makeText(this, "Invalid rating selected.", Toast.LENGTH_SHORT).show()
            return
        }

        val added = playlistProcessor.addEntry(songNam, artistNam, ratingStr, commentz)

        if (added) {
            Toast.makeText(this, "Entry added successfully! Total entries: ${playlistProcessor.entryCount}", Toast.LENGTH_SHORT).show()

        }

        fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //initialize the views
        etSong=findViewById(R.id.etSong)
        etArtist=findViewById(R.id.etArtist)
        etRating=findViewById(R.id.etRating)
        etComments=findViewById(R.id.etComments)
        btnPlaylist=findViewById(R.id.btnPlaylist)
        btnDetail=findViewById(R.id.btnDetail)
        btnExit=findViewById(R.id.btnExit)

        btnPlaylist.setOnClickListener {
            addPlaylistEntry()
        }

        //Setting up the button to go the detail view screen
        btnDetail.setOnClickListener {
                val intent: Intent(this;DetailView::class.java)
                startActivity(intent)

            }
        }

        //Setting up button to exit the app
        btnExit.setOnClickListener {
            finishAffinity()
        }
        }
    }
}