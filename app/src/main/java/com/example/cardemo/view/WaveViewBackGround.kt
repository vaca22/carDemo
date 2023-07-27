package com.example.cardemo.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.example.cardemo.R


class WaveViewBackGround : View {

    interface Ga {
        fun yes(x: Int, y: Int)
    }

    fun setG(g: Ga) {
        ga = g
    }
    private val x1Paint = Paint()
    private val x2Paint = Paint()


    var ga: Ga? = null
    var canvas: Canvas? = null
    private val wavePaint = Paint()
    private val bgPaint = Paint()
    var currentHead = 0
    val headLen = 3
    var currentTail = 0
    val drawSize = 500
    var n1 = 0
    var n2 = 0

    private fun nn(k: Int): Int {
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

    var disp = false
    val data = IntArray(drawSize) {
        0
    }
    var drawFra: Int = 1

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
        x1Paint.apply {
            color = getColor(R.color.wavebg3)
            style = Paint.Style.STROKE
            strokeWidth = 1f
        }
        x2Paint.apply {
            color = getColor(R.color.wavebg4)
            style = Paint.Style.STROKE
            strokeWidth = 1f
        }





    }


    lateinit var w: Rect

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawARGB(255, 255, 255, 255)
        val gridSize=0.1f* WavePara.pixelsPerMv
        var tempX=0f
        var index=0
        do{
            tempX=index.toFloat()*gridSize
            canvas.drawLine(
                tempX,
                0f,
                tempX,
                height.toFloat(),
                x2Paint
            )
            index++
        }while (tempX<=width)

        index=0
        do{
            tempX=index*gridSize
            canvas.drawLine(
                0f,
                tempX,
                width.toFloat(),
                tempX,
                x2Paint
            )
            index++
        }while (tempX<=height)


        index=0
        do{
            tempX=index*gridSize
            canvas.drawLine(
                tempX,
                0f,
                tempX,
                height.toFloat(),
                x1Paint
            )
            index+=5
        }while (tempX<=width)

        index=0
        do{
            tempX=index*gridSize
            canvas.drawLine(
                0f,
                tempX,
                width.toFloat(),
                tempX,
                x1Paint
            )
            index+=5
        }while (tempX<=height)
    }


    private fun drawWave(canvas: Canvas) {
        canvas.drawColor(getColor(R.color.black))
    }


    private fun getColor(resource_id: Int): Int {
        return ContextCompat.getColor(context, resource_id)
    }
}