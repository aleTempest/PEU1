package upvictoria.pm_ene_abr_2024.iti_271164.peu1.entities

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import upvictoria.pm_ene_abr_2024.iti_271164.peu1.ClassData

open class ClassDiagram(
    val x: Float,
    val y: Float,
    var width: Float,
    var height: Float,
    val classData: ClassData
) : Shape() {
    private val color = Color.WHITE
    init {
        width += x
        height += y
    }

    private var isBorderVisible = true

    override fun renderShape(canvas: Canvas, paint: Paint) {
        // Dibujar primero el cuadrado
        paint.color = color
        canvas.drawRect(x, y, width, height, paint)

        // Dibujar el borde de la figura
        renderBorder(canvas, paint)
        // Dibujar el nombre de la clase
        renderHeader(paint, canvas)

    }

    private fun renderHeader(paint: Paint, canvas: Canvas) {
        val header = "Class: ${classData.name}"
        val headerWidth = paint.measureText(header)
        val headerHeight = paint.descent() - paint.ascent()
        val headerX = (x + (width / 2)) - headerWidth
        val headerY = y + headerHeight
        paint.color = Color.BLACK
        canvas.drawText(header, headerX, headerY, paint)
    }

    private fun renderBorder(canvas: Canvas, paint: Paint) {
        if (isBorderVisible) {
            paint.color = Color.BLACK
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = 2f
            canvas.drawRect(x, y, width, height, paint)
        }
    }

    fun toggleBorderVisibility() {
        isBorderVisible = !isBorderVisible
    }
}