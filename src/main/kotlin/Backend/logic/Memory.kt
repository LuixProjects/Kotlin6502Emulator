package Backend.logic

import Backend.RepresentacionInformacion.Byte

class Memory {
    //No estoy seguro del size de la memoria del procesador 6502, por el momento
    //Supongo que son 65536 x 8.
    val filas_memoria = 1024
    val memory = Array(filas_memoria) { Byte() }


    fun reset(){
        for (byte in memory){
            byte.resetByte()
        }
    }

}