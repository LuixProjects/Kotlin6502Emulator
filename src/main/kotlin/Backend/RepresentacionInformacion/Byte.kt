package Backend.RepresentacionInformacion



//Unidad de almacenamiento de bits. Contiene 8 bits.
class Byte {
    var byte = BooleanArray(8)


    constructor() {

    }
    constructor(ba:BooleanArray) {
        this.byte = ba
    }

    fun getIntValue(pesos: IntArray):Int{//[7,6,5,4,3,2,1,0]
        var valor:Int = 0
        for (i in 0 until 8){

            if (this.byte[i]){
                valor += Math.pow(2.0,pesos[i].toDouble()).toInt()
            }
        }
        return valor
    }

    fun resetByte() {
        this.byte.fill(false)
    }

    override fun toString():String{
        return this.byte.joinToString()
    }

    fun equalsByte(other: Byte): Boolean {
        return this.byte.contentEquals(other.byte)
    }

    fun clone(): Byte {
        return Byte(this.byte.clone())
    }
}