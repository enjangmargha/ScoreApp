package com.example.scoreapp

import androidx.lifecycle.ViewModel

class NumberViewModel : ViewModel() {
    var scoreTeamA: Int = 0
    var scoreTeamB: Int = 0

    fun incrementScoreA() {
        scoreTeamA += 1
    }

    fun incrementScoreB() {
        scoreTeamB += 1
    }

    fun resetScore() {
        scoreTeamA = 0
        scoreTeamB = 0
    }
}