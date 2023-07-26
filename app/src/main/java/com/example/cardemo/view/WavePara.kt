package com.example.cardemo.view

import androidx.lifecycle.MutableLiveData
import java.util.LinkedList

object WavePara {
    val co=274.283167f
    var realTimeDoubler=1f

    val coi=0.013462f

    val waveDataX = LinkedList<Float>()
    val er2Graph = MutableLiveData<Boolean>()


    var drawTask: WaveView.Companion.DrawTask? = null
    var offerTask: WaveView.Companion.OfferTask? = null
}