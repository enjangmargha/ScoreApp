package com.example.scoreapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel : ViewModel() {
    private val _scoreTeamA = MutableLiveData(0)
    val scoreTeamA: LiveData<Int> get() = _scoreTeamA

    private val _scoreTeamB = MutableLiveData(0)
    val scoreTeamB: LiveData<Int> get() = _scoreTeamB

    fun addScoreTeamA(points: Int) {
        if ((_scoreTeamA.value ?: 0) < 25 && (_scoreTeamB.value ?: 0) < 25) {
            _scoreTeamA.value = (_scoreTeamA.value ?: 0) + points
        }
    }

    fun addScoreTeamB(points: Int) {
        if ((_scoreTeamA.value ?: 0) < 25 && (_scoreTeamB.value ?: 0) < 25) {
            _scoreTeamB.value = (_scoreTeamB.value ?: 0) + points
        }
    }

    fun resetScores() {
        _scoreTeamA.value = 0
        _scoreTeamB.value = 0
    }
}
