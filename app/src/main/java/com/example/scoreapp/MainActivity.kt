package com.example.scoreapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val scoreViewModel: NumberViewModel by viewModels()

    private lateinit var tvScoreA: TextView
    private lateinit var tvScoreB: TextView
    private lateinit var btnPlus1A: Button
    private lateinit var btnPlus2A: Button
    private lateinit var btnPlus1B: Button
    private lateinit var btnPlus2B: Button
    private lateinit var btnReset: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi UI
        tvScoreA = findViewById(R.id.tv_score_a)
        tvScoreB = findViewById(R.id.tv_score_b)
        btnPlus1A = findViewById(R.id.btn_plus1_a)
        btnPlus2A = findViewById(R.id.btn_plus2_a)
        btnPlus1B = findViewById(R.id.btn_plus1_b)
        btnPlus2B = findViewById(R.id.btn_plus2_b)
        btnReset = findViewById(R.id.btn_reset)

        // Menampilkan skor terakhir saat Activity dibuat ulang
        updateScores()

        // Event Listener untuk tombol Team A
        btnPlus1A.setOnClickListener {
            scoreViewModel.scoreTeamA += 1
            updateScores()
        }

        btnPlus2A.setOnClickListener {
            scoreViewModel.scoreTeamA += 2
            updateScores()
        }

        // Event Listener untuk tombol Team B
        btnPlus1B.setOnClickListener {
            scoreViewModel.scoreTeamB += 1
            updateScores()
        }

        btnPlus2B.setOnClickListener {
            scoreViewModel.scoreTeamB += 2
            updateScores()
        }

        // Tombol Reset
        btnReset.setOnClickListener {
            scoreViewModel.scoreTeamA = 0
            scoreViewModel.scoreTeamB = 0
            updateScores()
        }

        // Mengatur padding sesuai dengan insets sistem (opsional)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Fungsi untuk memperbarui tampilan skor
    private fun updateScores() {
        tvScoreA.text = scoreViewModel.scoreTeamA.toString()
        tvScoreB.text = scoreViewModel.scoreTeamB.toString()
    }
}
