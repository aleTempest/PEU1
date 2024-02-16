package upvictoria.pm_ene_abr_2024.iti_271164.peu1

class ClassData(
    val name: String,
    val attributes: ArrayList<Attribute>?,
    val methods: ArrayList<Method>?
) {
    enum class Encapsulation {
        PRIVATE,PUBLIC,PROTECTED
    }
    open class Property ( val name: String, val type: String, val enc: Encapsulation) {
        protected fun encType() : String {
            return when(enc) {
                Encapsulation.PRIVATE -> "-"
                Encapsulation.PUBLIC -> "+"
                Encapsulation.PROTECTED -> "#"
            }
        }
        /** Estos métodos sirven como fábricas para las clases internas que extienden de la clase
         * Property, se utiliza 'compaion object' para no crear clases nuevas solo para esos métodos
         * **/
        companion object {
            fun createMethod(name: String, type: String, enc: Encapsulation): Method {
                return Method(name, type, enc)
            }

            fun createAttribute(name: String, type: String, enc: Encapsulation): Attribute {
                return Attribute(name, type, enc)
            }
        }
    }
    class Method(name: String, type: String, enc: Encapsulation) : Property(name,type,enc) {
        override fun toString(): String {
            return "${encType()} $name(): $type"
        }
    }
    class Attribute(name: String, type: String, enc: Encapsulation) : Property(name,type,enc) {
        override fun toString(): String {
            return "${encType()} $name: $type"
        }
    }
}