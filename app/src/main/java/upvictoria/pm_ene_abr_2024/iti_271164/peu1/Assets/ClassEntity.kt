package upvictoria.pm_ene_abr_2024.iti_271164.peu1.Assets

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import upvictoria.pm_ene_abr_2024.iti_271164.peu1.Exceptions.InvalidClassAttributeException

class ClassEntity(
    posX: Int,
    posY: Int,
    width: Int,
    height: Int,
    color: Int,
    private val className: String,
    private val classAttributes: List<ClassAttribute>?,
    private val classMethods: List<ClassMethod>?
) : Entity(posX, posY, width, height, color) {

    override fun draw(canvas: Canvas, paint: Paint) {
        val x = posX.toFloat()
        val y = posX.toFloat()
        val right = (posX + width).toFloat()
        val bottom = (posY + height).toFloat()
        val centerX = x + (width / 2).toFloat() // calcular el centro de la figura

        // Dibujar primero la figura
        paint.color = this.color
        canvas.drawRect(x,y,right,bottom,paint)

        // Dibujar el nombre de la clase en el centro superior de la figura
        val text = this.className
        val textWidth = paint.measureText(text)
        val textHeight = paint.descent() - paint.ascent()
        val textX = centerX - (textWidth / 2)
        val textY = y + textHeight
        paint.color = Color.BLACK
        canvas.drawText(text, textX, textY, paint)
    }

    class ClassAttribute(
        encType: EncapsulationType,
        name: String,
        attributeType: String,
        isAbstract: Boolean
    ) : ClassProperty(encType, name, attributeType, isAbstract) {
        override fun toString(): String {
            return super.toString() + " : ${this.attributeType}"
        }
    }

    class ClassMethod(
        encType: EncapsulationType,
        name: String,
        attributeType: String,
        isAbstract: Boolean
    ) : ClassProperty(encType, name, attributeType, isAbstract) {
        override fun toString(): String {
            return super.toString() + "() : ${this.attributeType}"
        }
    }

    abstract class ClassProperty (
        val encType: EncapsulationType,
        val name: String,
        val attributeType: String,
        val isAbstract: Boolean,
    ) {

        private fun getEncType() : String =
            when (encType) {
                EncapsulationType.PRIVATE -> "-"
                EncapsulationType.PUBLIC -> "+"
                EncapsulationType.PROTECTED -> "#"
                else -> ""
            }

        override fun toString(): String {
            var str = "${this.getEncType()}"
            if (isAbstract) {
                str += "abstract"
            }
            str += " $name"
            return str
        }

        init {
            if (name.isEmpty()) {
                throw InvalidClassAttributeException("Attribute name is empty")
            } else if (attributeType.isEmpty()) {
                throw InvalidClassAttributeException("Attribute type is empty")
            }
        }
    }
}