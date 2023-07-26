package com.example.cardemo.view

import androidx.lifecycle.MutableLiveData
import java.util.LinkedList

object WavePara {
    //每毫伏对应的像素点数
    val pixelsPerMv=274.283167f
    //心电图倍率
    var realTimeDoubler=1f


    val waveDataX = LinkedList<Float>()
    val updateSignal = MutableLiveData<Boolean>()


    var drawTask: WaveView.Companion.DrawTask? = null
    //模拟发送数据
    var offerTask: WaveView.Companion.OfferTask? = null
}