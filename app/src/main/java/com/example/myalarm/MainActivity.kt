package com.example.myalarm

import android.media.RingtoneManager
import android.os.Bundle
import android.widget.TextClock
import android.widget.TimePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myalarm.ui.theme.MyAlarmTheme
import java.util.Timer
import java.util.TimerTask

class MainActivity : ComponentActivity() {
    private lateinit var alarmTime: TimePicker
    private lateinit var currentTime: TextClock

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        enableEdgeToEdge()
        alarmTime = findViewById(R.id.time_picker)
        currentTime = findViewById(R.id.textClock2)

        val r = RingtoneManager.getRingtone(applicationContext, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE))

        val t = Timer()
        t.schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    currentTime.text = currentTime.text


                    val w = currentTime.text.toString()
                    val z = alarmTime()
                    if(currentTime.text.toString() == alarmTime()) {
                        r.play()
                    } else {
                        r.stop()
                    }
                }

            }
        }, 0, 1000)


    }

    fun alarmTime(): String {
        val alarmHours = alarmTime.hour
        val alarmMinutes = alarmTime.minute
        var stringAlarmMinutes:String

                if (alarmMinutes < 10) {
                    stringAlarmMinutes = "0"
                    stringAlarmMinutes = stringAlarmMinutes+(alarmMinutes.toString())
                } else {
                    stringAlarmMinutes = alarmMinutes.toString()
                }
        val stringAlarmTime: String
        if (alarmHours > 12) {
            val adjustedHours = alarmHours -12
            stringAlarmTime = "0"+adjustedHours.toString()+(":")+(stringAlarmMinutes)
        } else {
            stringAlarmTime ="0"+ alarmHours.toString()+(":")+(stringAlarmMinutes)
        }
        return stringAlarmTime
    }
}

/*@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyAlarmTheme {
        Greeting("Android")
    }
}*/