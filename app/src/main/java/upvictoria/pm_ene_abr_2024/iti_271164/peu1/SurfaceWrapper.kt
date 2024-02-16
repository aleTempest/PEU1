package upvictoria.pm_ene_abr_2024.iti_271164.peu1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceHolder
import android.view.SurfaceView
import upvictoria.pm_ene_abr_2024.iti_271164.peu1.entities.ClassDiagram

class SurfaceWrapper(context: Context) : SurfaceView(context), SurfaceHolder.Callback {
    private var drawThread: DrawThread? = null

    init {
        holder.addCallback(this)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        drawThread = DrawThread(holder)
        drawThread?.start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        var retry = true
        drawThread?.setRunning(false)
        while (retry) {
            try {
                drawThread?.join()
                retry = false
            } catch (e: InterruptedException) {
                // Retry
            }
        }
    }

    inner class DrawThread(private val surfaceHolder: SurfaceHolder) : Thread() {
        private var isRunning = true
        private val paint = Paint().apply {
            isAntiAlias = true
            color = Color.RED
            style = Paint.Style.FILL
        }

        override fun run() {
            while (isRunning) {
                val canvas = surfaceHolder.lockCanvas()
                if (canvas != null) {
                    draw(canvas)
                    surfaceHolder.unlockCanvasAndPost(canvas)
                }
            }
        }

        fun setRunning(running: Boolean) {
            isRunning = running
        }

        private fun draw(canvas: Canvas) {
            canvas.drawColor(Color.WHITE) // Clear canvas
            val x = 100f
            val y = 100f
            // canvas.drawRect(x,y,x+200f,y+200f,paint)
            val classData = ClassData("Test",null,null)
            val class1 = ClassDiagram(x,y,200f,200f,classData)
            class1.renderShape(canvas,paint)
        }
    }
}
