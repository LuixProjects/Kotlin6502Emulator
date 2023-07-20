package Backend.logic

import Backend.RepresentacionInformacion.Byte
import java.io.File

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
    //Esta funcion se debe mejorar para crear la memoria según el tamaño del archivo.
    fun loadMemoryFile(route:String){
        var linea :Int= 0
        File(route).forEachLine {

            val aux_byte = Byte()
            val cadenaSinEspacios = it.replace("\\s+".toRegex(), "")

            println((cadenaSinEspacios.toCharArray().map { it == '1' }))

            aux_byte.byte = ( (cadenaSinEspacios.toCharArray().map { it == '1' }.toBooleanArray()) )
            this.memory[linea] = aux_byte
            linea ++
        }

    }

}