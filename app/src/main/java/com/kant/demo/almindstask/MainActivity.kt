package com.kant.demo.almindstask

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.Toast
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private lateinit var circleImage: ImageView

    private val bitmap = Bitmap.createBitmap(700, 1000, Bitmap.Config.ARGB_8888)

    private val canvas = Canvas(bitmap)

    private var paint = Paint()

    var x1 = 0F
    var x2 = 0F
    var x3 = 0F

    var y1 = 0F
    var y2 = 0F
    var y3 = 0F

    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        circleImage = findViewById(R.id.circleImage)

        canvas.drawARGB(255, 78, 168, 186)

        paint.color = Color.parseColor("#FFFFFF")
        paint.strokeWidth = 10F
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
        paint.isDither = true

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getRealMetrics(displayMetrics)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        Log.d("bamakant", "onTouchEvent: touced")

        if (event?.action == MotionEvent.ACTION_DOWN && counter == 2) {

            x1 = event.x
            y1 = event.y
            drawCircle(x1, y1, 2F)
            Log.d("bamakant", "onTouchEvent: $counter")
            findOutCenter()
        }

        if (event?.action == MotionEvent.ACTION_DOWN && counter == 1) {
            x2 = event.x
            y2 = event.y
            drawCircle(x2, y2, 2F)
            Log.d("bamakant", "onTouchEvent: $counter")
            counter++
        }

        if (event?.action == MotionEvent.ACTION_DOWN && counter == 0) {
            x3 = event.x
            y3 = event.y
            drawCircle(x3, y3, 2F)
            Log.d("bamakant", "onTouchEvent: $counter")
            counter++
        }

        return false
    }

    private fun findOutCenter() {
        val centerX = (x1 + x2 + x3) / 3
        val centerY = (y1 + y2 + y3) / 3

        val r = sqrt((centerX - x1).pow(2) + (centerY - y1).pow(2))

        if (r < 0) {
            Log.d("bamakant", "findOutCenter: $centerX $centerY $r")
            Toast.makeText(this, "Circle won't be possible", Toast.LENGTH_SHORT).show()
            counter = 0
        } else {
            Log.d("bamakant", "findOutCenter: $centerX $centerY $r")
            drawCircle(centerX, centerY, r)
            counter = 0
        }

    }

    private fun drawCircle(centerX: Float, centerY: Float, r: Float) {
        canvas.drawCircle(centerX, centerY, r, paint)
        circleImage.background = BitmapDrawable(resources, bitmap)
    }

}