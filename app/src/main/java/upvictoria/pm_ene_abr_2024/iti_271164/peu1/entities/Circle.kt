package upvictoria.pm_ene_abr_2024.iti_271164.peu1.entities

import android.graphics.Canvas
import android.graphics.Paint

open class Circle(
    val centerX: Float,
    val centerY: Float,
    var radius: Float,
    val color: Int
) {
    fun render(canvas: Canvas, paint: Paint) {
        paint.color = color
        canvas.drawCircle(centerX, centerY, radius, paint)
    }
}
