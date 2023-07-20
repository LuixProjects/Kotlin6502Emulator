package Backend.Register

import Backend.RepresentacionInformacion.Byte
import Backend.RepresentacionInformacion.Word
import Backend.Utils.HexToBinary
import Backend.logic.Memory

class ProgramCounter {
    var direction = Word()


    fun FetchByte(memoria:Memory): Byte {
        val int_direction = this.direction.getInt()
        //Actualizamos posición del PC.
        val new_dir = this.direction.getInt() + 1
        var cadena = Integer.toHexString(new_dir)

        if (cadena.length < 4){
            val nec_ceros = 4 - cadena.length

            for (i in 0 until nec_ceros){
                cadena = "0$cadena"
            }

        }
        this.direction.cargarBytes(HexToBinary.HexToBinary(cadena))

        return memoria.memory[int_direction]
    }

}