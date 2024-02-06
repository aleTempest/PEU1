package upvictoria.pm_ene_abr_2024.iti_271164.peu1.DrawingWrapper

import android.content.Entity
import android.view.SurfaceHolder

class ThreadRender(
    private val surfaceHolder: SurfaceHolder,
    private var entities: MutableList<Entity>
) : Thread() {
    val isRunning = true
    init {
    }
}