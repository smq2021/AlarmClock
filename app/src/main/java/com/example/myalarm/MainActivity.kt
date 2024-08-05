package com.example.myalarm

import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Bundle

import android.widget.TextClock
import android.widget.TimePicker
import androidx.activity.ComponentActivity
import com.example.myalarm.R

import java.util.Timer
import java.util.TimerTask

class MainActivity : ComponentActivity() {

    private lateinit var alarmTime: TimePicker
    private lateinit var currentTime: TextClock

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        alarmTime = findViewById(R.id.time_picker)
        currentTime = findViewById(R.id.textClock2)
        val r: Ringtone = RingtoneManager.getRingtone(applicationContext, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE))

        val t = Timer()
        t.schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    currentTime.format12Hour = "h:mm a"
                    if (currentTime.text.toString() == alarmTime() ) {
                        r.play()
                    } else {
                        r.stop()
                    }
                }
            }
        }, 0, 1000)
    }

    private fun alarmTime(): String {
        var alarmHours = alarmTime.hour
        val alarmMinutes = alarmTime.minute
        val stringAlarmMinutes: String
        val stringAlarmHours: String

        stringAlarmHours = if (alarmHours < 10) {
            "0$alarmHours"
        } else {
            alarmHours.toString()
        }

        stringAlarmMinutes = if (alarmMinutes < 10) {
            "0$alarmMinutes"
        } else {
            alarmMinutes.toString()
        }

        val stringAlarmTime: String = "$stringAlarmHours:$stringAlarmMinutes"

//        val stringAlarmTime: String = if (alarmHours > 12) {
//            alarmHours -= 12
//            "$stringAlarmHours:$stringAlarmMinutes PM"
//        } else {
//            "$stringAlarmHours:$stringAlarmMinutes AM"
//        }

        return stringAlarmTime
    }
}
