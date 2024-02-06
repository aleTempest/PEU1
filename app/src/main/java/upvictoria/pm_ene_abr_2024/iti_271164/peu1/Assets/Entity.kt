package upvictoria.pm_ene_abr_2024.iti_271164.peu1.Assets

import android.graphics.Canvas
import android.graphics.Paint

abstract class Entity(
    val posX: Int,
    val posY: Int,
    val width: Int,
    val height: Int,
    val color: Int
) {
    abstract fun draw(canvas: Canvas, paint: Paint)
}