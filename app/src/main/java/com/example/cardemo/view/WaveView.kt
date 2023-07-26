package com.example.cardemo.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.example.cardemo.R
import com.example.cardemo.view.WavePara.co
import com.example.cardemo.view.WavePara.er2Graph
import com.example.cardemo.view.WavePara.realTimeDoubler
import com.example.cardemo.view.WavePara.waveDataX
import java.util.TimerTask


class WaveView : View {

    companion object {
        var disp = false
        var drawSize = 862
        var nd = 1280f / drawSize
        var data = IntArray(drawSize) {
            0
        }

        val pkgsize=4

        var currentHead = 0
        val headLen = 3
        var currentTail = 0


        val g = FloatArray(pkgsize)
        var gIndex = 0;
        var currentUpdateIndex = 0

        fun reset() {
            waveDataX.clear()
            for (k in 0 until drawSize) {
                data[k] = 0
            }
            gIndex = 0
            disp = false
            currentUpdateIndex = 0
            currentHead = 0
            currentTail = 0
        }

        fun poss(it: Er1Draw) {
            for (k in 0 until pkgsize) {
                data[currentUpdateIndex] = (it.data[k] * co).toInt()
                currentUpdateIndex++
                if (currentUpdateIndex >= drawSize) {
                    currentUpdateIndex -= drawSize
                }
            }

            currentHead = currentUpdateIndex - 1
            var t = currentUpdateIndex + headLen
            if (t > drawSize - 1) {
                t -= drawSize
            }
            currentTail = t
        }


        class DrawTask() : TimerTask() {
            override fun run() {
                Log.e("vaca","drawTask22")
                try {
                    do {
                        val gx = waveDataX.poll()
                        if (gx == null) {
                            return
                        } else {
                            g[gIndex] = gx
                        }
                        gIndex++
                    } while (gIndex < pkgsize);
                    gIndex = 0;
                    poss(Er1Draw(g))
                    Log.e("vaca","drawTask")
                    er2Graph.postValue(true)
                } catch (e: java.lang.Exception) {
                    waveDataX.clear()
                    gIndex = 0;
                    e.printStackTrace()
                }


            }
        }

        class OfferTask() : TimerTask() {
            var k=0;
            override fun run() {
                try {
                    waveDataX.offer(kotlin.math.sin(k.toDouble()/30).toFloat())
                    k++
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }
        }


    }

    private val timePaint = Paint()
    private val linePaint = Paint()
    private val wavePaint = Paint()
    private val bgPaint = Paint()


    var n1 = 0
    var n2 = 0

    private fun judgePoint(k: Int): Int {
        if (currentHead < currentTail) {
            if ((k > currentHead) && (k <= currentTail)) {
                return 0
            } else {
                return 1
            }
        } else {
            if ((k > currentHead) || (k < currentTail)) {
                return 0
            } else {
                return 1
            }
        }
    }


    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init()
    }

    private fun init() {
        wavePaint.apply {
            color = getColor(R.color.wave_color)
            style = Paint.Style.STROKE
            strokeWidth = 4.0f
        }

        bgPaint.apply {
            color = getColor(R.color.gray)
            style = Paint.Style.STROKE
            strokeWidth = 2.0f
        }
        timePaint.apply {
            color = getColor(R.color.report_wave_time)
            textSize = 24f
            style = Paint.Style.STROKE
            isAntiAlias = true
        }

        linePaint.apply {
            color = getColor(R.color.report_wave_hint_line)
            style = Paint.Style.STROKE
            strokeWidth = 4.0f
        }
    }

    val gauu=ArrayList<PointF>()


    fun Canvas.dr2(){
        for(k in 0 until gauu.size-1){
            this.drawLine(gauu[k].x,gauu[k].y,gauu[k+1].x,gauu[k+1].y,wavePaint)
            Log.e("vaca","drawLine ${gauu[k].x},${gauu[k].y},${gauu[k+1].x},${gauu[k+1].y}")
        }
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        nd=width/drawSize.toFloat()
        canvas.drawARGB(0, 0, 0, 0)
        val baseY = 2.5f*co
        canvas.drawLine(30f, co * 2.5f - realTimeDoubler * co, 30f, co * 2.5f, linePaint)
        canvas.drawText("1mV", 35f, baseY + 35f, timePaint)
        if (disp) {
            for ((index, h) in data.withIndex()) {
                val h1 = h * realTimeDoubler
                n2 = judgePoint(index)
                if ((n2 == 1) && (index == data.size - 1)) {
                    canvas.dr2()
                    n1 = 0
                    break
                }
                if (n2 != n1) {
                    if (n1 > n2) {
                        canvas.dr2()
                        n1 = 0
                    } else {
                        gauu.clear()
                        gauu.add(
                            PointF(nd * index.toFloat(),
                                baseY - h1.toFloat()))
                        n1 = 1
                    }
                } else {
                    gauu.add(
                        PointF(
                        nd * index.toFloat(),
                        baseY - h1.toFloat()
                    ))
                }

            }
        }

    }


    private fun getColor(resource_id: Int): Int {
        return ContextCompat.getColor(context, resource_id)
    }
}