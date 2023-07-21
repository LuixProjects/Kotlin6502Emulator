package Backend.Register

import Backend.RepresentacionInformacion.Byte
import Backend.RepresentacionInformacion.Word
import Backend.Utils.HexToBinary

class StackPointer {
    var direction = Byte(booleanArrayOf(true,true,true,true,true,true,true,true))//FF

        //Función que devuelve la dir de memoria y decrementa el SP.
        fun getPushMemory(): Byte {
            var new_dir = this.direction.getIntValue(intArrayOf(7,6,5,4,3,2,1,0)) -1
            val dir_copy = this.direction.clone()
            //Int -> Hex.
            var cadena = Integer.toHexString(new_dir)

            if (cadena.length < 2){
                val nec_ceros = 4 - cadena.length

                for (i in 0 until nec_ceros){
                    cadena = "0$cadena"
                }

            }
            //Hex -> Bin.
            this.direction = (HexToBinary.HexToBinary(cadena))[0]
            return dir_copy
        }

        //Devuelve la memoria real a la que apunta el SP
        fun getStackMemory(): Word {
            val up_byte = Byte(booleanArrayOf(false,false,false,false,true,true,true,true))
            val down_byte = this.direction.clone()
            return Word(listOf(up_byte,down_byte))
        }
    }