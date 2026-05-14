package com.example.myfirstapp

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val playButton: Button = findViewById(R.id.play_button)
        val pauseButton: Button = findViewById(R.id.pause_button)
        val stopButton: Button = findViewById(R.id.stop_button)
        val songDurationText: TextView = findViewById(R.id.song_duration_text)

        handler = Handler(Looper.getMainLooper())

        fun setDefaultButtonColors() {
            playButton.setBackgroundColor(ContextCompat.getColor(this, R.color.button_default_color))
            pauseButton.setBackgroundColor(ContextCompat.getColor(this, R.color.button_default_color))
            stopButton.setBackgroundColor(ContextCompat.getColor(this, R.color.button_default_color))
        }

        fun formatTime(ms: Int): String {
            val minutes = (ms / 1000) / 60
            val seconds = (ms / 1000) % 60
            return String.format("%d:%02d", minutes, seconds)
        }

        setDefaultButtonColors()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        playButton.setOnClickListener {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this, R.raw.music)
                mediaPlayer?.setOnPreparedListener { mp ->
                    songDurationText.text = "0:00 / ${formatTime(mp.duration)}"
                }
            }

            mediaPlayer?.start()
            setDefaultButtonColors()
            playButton.setBackgroundColor(ContextCompat.getColor(this, R.color.play_color))

            runnable = Runnable {
                mediaPlayer?.let {
                    val currentPosition = it.currentPosition
                    songDurationText.text = "${formatTime(currentPosition)} / ${formatTime(it.duration)}"
                }
                handler.postDelayed(runnable, 1000)
            }
            handler.post(runnable)
        }

        pauseButton.setOnClickListener {
            mediaPlayer?.pause()
            handler.removeCallbacks(runnable)
            setDefaultButtonColors()
            pauseButton.setBackgroundColor(ContextCompat.getColor(this, R.color.pause_color))
        }

        stopButton.setOnClickListener {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
            handler.removeCallbacks(runnable)
            songDurationText.text = "0:00 / 0:00"
            setDefaultButtonColors()
            stopButton.setBackgroundColor(ContextCompat.getColor(this, R.color.stop_color))
        }
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer?.release()
        mediaPlayer = null
        if (this::handler.isInitialized && this::runnable.isInitialized) {
            handler.removeCallbacks(runnable)
        }
    }
}