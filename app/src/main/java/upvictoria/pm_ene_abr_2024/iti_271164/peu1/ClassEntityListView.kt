package upvictoria.pm_ene_abr_2024.iti_271164.pg1u1_eq02

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceHolder
import android.view.SurfaceView
import upvictoria.pm_ene_abr_2024.iti_271164.peu1.Assets.ClassEntity

class ClassEntityListView(context: Context, private val classEntities: List<ClassEntity>) :
    SurfaceView(context), SurfaceHolder.Callback {

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
        canvas.drawColor(Color.WHITE)
        val paint = Paint()
        for (classEntity in classEntities) {
            classEntity.draw(canvas, paint)
        }
    }
}
