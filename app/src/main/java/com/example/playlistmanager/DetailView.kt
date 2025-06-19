package com.example.playlistmanager

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailView : AppCompatActivity() {

    private lateinit var lvSongEntries:ListView
    private lateinit var tvAvgRating:TextView
    private lateinit var btnList:Button
    private lateinit var btnAvgRating:Button
    private lateinit var btnReturn:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        lvSongEntries=findViewById(R.id.lvSongEntries)
        tvAvgRating=findViewById(R.id.tvAvgRating)
        btnList=findViewById(R.id.btnList)
        btnAvgRating=findViewById(R.id.btnAvgRating)
        btnReturn= findViewById(R.id.btnReturn)

        val playlistProcessor = intent.getParcelableExtra<PlaylistProcessor>("playlistProcessor")

        if (playlistProcessor != null) {
            val formattedEntries = playlistProcessor.generatedFormattedEntries()
            val adapter = null
            lvSongEntries.adapter = adapter

            val avgRating = String.format("%.2f",playlistProcessor.calculateAverageRatings())
            tvAvgRating.text = "The average rating: $avgRating"

        } else {
            tvAvgRating.text = "The average rating: N/A"
        }

        btnReturn.setOnClickListener {
            val intent = Intent (this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }

        setContentView(R.layout.activity_detail_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}