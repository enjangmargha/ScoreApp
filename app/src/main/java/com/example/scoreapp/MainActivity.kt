package com.example.scoreapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.scoreapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val scoreViewModel: ScoreViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.scoreViewModel = scoreViewModel

        // Menggunakan LiveData untuk update UI otomatis
        scoreViewModel.scoreTeamA.observe(this, Observer { score ->
            binding.tvScoreA.text = score.toString()
            checkWinner()
        })

        scoreViewModel.scoreTeamB.observe(this, Observer { score ->
            binding.tvScoreB.text = score.toString()
            checkWinner()
        })

        // Event klik tombol menggunakan binding
        binding.btnPlus1A.setOnClickListener { scoreViewModel.addScoreTeamA(1) }
        binding.btnPlus2A.setOnClickListener { scoreViewModel.addScoreTeamA(2) }
        binding.btnPlus1B.setOnClickListener { scoreViewModel.addScoreTeamB(1) }
        binding.btnPlus2B.setOnClickListener { scoreViewModel.addScoreTeamB(2) }
        binding.btnReset.setOnClickListener { scoreViewModel.resetScores() }

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun checkWinner() {
        val scoreA = scoreViewModel.scoreTeamA.value ?: 0
        val scoreB = scoreViewModel.scoreTeamB.value ?: 0
        if (scoreA >= 25) {
            showWinnerDialog("Team A Menang!")
        } else if (scoreB >= 25) {
            showWinnerDialog("Team B Menang!")
        }
    }

    private fun showWinnerDialog(winner: String) {
        AlertDialog.Builder(this)
            .setTitle("Permainan Selesai")
            .setMessage(winner)
            .setPositiveButton("OK") { _, _ -> scoreViewModel.resetScores() }
            .setCancelable(false)
            .show()
    }
}
