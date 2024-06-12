package com.example.breathtaker.data.repository

import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import kotlin.math.sin

class AudioDataSource {
    private lateinit var audioTrack: AudioTrack
    private val sampleRate = 44100
    private var frequency = 440.0  // Initial frequency in Hz
    private var duration = 1  // Duration of each tone in seconds

    fun generateAudio() {
        val bufferSize = AudioTrack.getMinBufferSize(sampleRate,
            AudioFormat.CHANNEL_OUT_MONO,
            AudioFormat.ENCODING_PCM_16BIT)

        audioTrack = AudioTrack(
            AudioManager.STREAM_MUSIC,
            sampleRate,
            AudioFormat.CHANNEL_OUT_MONO,
            AudioFormat.ENCODING_PCM_16BIT,
            bufferSize,
            AudioTrack.MODE_STREAM
        )

        // Start playback
        audioTrack.play()
    }

    fun generateTone(freq: Double, durationMillis: Long) {
        val numSamples = (durationMillis * sampleRate / 1000).toInt()
        val samples = ShortArray(numSamples)
        val maxAmplitude = 32767 // 16-bit PCM

        for (i in samples.indices) {
            val angle = 2.0 * Math.PI * i / (sampleRate / freq)
            samples[i] = (Math.sin(angle) * maxAmplitude).toInt().toShort()
        }

        // Write the samples to the audio track
        audioTrack.write(samples, 0, numSamples)
    }
}