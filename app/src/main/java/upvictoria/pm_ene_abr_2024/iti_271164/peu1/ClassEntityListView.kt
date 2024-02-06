package upvictoria.pm_ene_abr_2024.iti_271164.pg1u1_eq02

import android.content.Context
import android.content.Entity
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceHolder
import android.view.SurfaceView
import upvictoria.pm_ene_abr_2024.iti_271164.peu1.Assets.ClassEntity
import java.util.Random
import java.util.Timer
import java.util.TimerTask
import java.util.logging.Handler

class ClassEntityListView(context: Context) : SurfaceView(context), SurfaceHolder.Callback {
    var entities: List<ClassEntity> = emptyList()
    constructor(context: Context, list: List<ClassEntity>) : this(context) {
        this.entities = list
    }
    private val drawThread: DrawThread

    init {
        holder.addCallback(this)
        drawThread = DrawThread(holder)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        drawThread.start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        // Implement if needed
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        var retry = true
        drawThread.setRunning(false)
        while (retry) {
            try {
                drawThread.join()
                retry = false
            } catch (e: InterruptedException) {
                // Retry
            }
        }
    }

    private inner class DrawThread(private val surfaceHolder: SurfaceHolder) : Thread() {
        private var isRunning = true

        fun setRunning(running: Boolean) {
            isRunning = running
        }

        override fun run() {
            while (isRunning) {
                val canvas = surfaceHolder.lockCanvas()
                if (canvas != null) {
                    drawEntities(canvas)
                    surfaceHolder.unlockCanvasAndPost(canvas)
                }
            }
        }
    }

    private fun drawEntities(canvas: Canvas) {
        Timer().scheduleAtFixedRate(object : TimerTask() {
            private fun getRandomRGB() : Int {
                return Color.rgb(Random().nextInt(),Random().nextInt(),Random().nextInt())
            }
            override fun run() {
                entities += ClassEntity(Random().nextInt(500),Random().nextInt(500),Random().nextInt(100),Random().nextInt(200),this.getRandomRGB(),"Class",null,null)
            }
        }, 2000,20000)
        canvas.drawColor(Color.WHITE)
        val paint = Paint()
        for (classEntity in entities) {
            classEntity.draw(canvas, paint)
        }
    }
}
