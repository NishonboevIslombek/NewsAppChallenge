package com.example.newsapplicationchallenge.presentation.utils

import android.os.CountDownTimer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun CoroutineScope.countDownForAction(
    millis: Long,
    interval: Long,
    action: suspend () -> Unit,
): CountDownTimer = object : CountDownTimer(millis, interval) {
    override fun onTick(p0: Long) {}
    override fun onFinish() {
        this@countDownForAction.launch { action() }
    }
}
