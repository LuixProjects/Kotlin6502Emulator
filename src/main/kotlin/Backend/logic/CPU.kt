package Backend.logic

import Backend.Register.*
import Backend.Utils.HexToBinary
import Backend.RepresentacionInformacion.Byte

class CPU(private val memory: Memory) {
    val acumulator = Acumulator();
    val register_A =InformationRegisters()
    val register_X =InformationRegisters()
    val register_Y =InformationRegisters()
    val processorStatus=ProcessorStatus()
    val programCounter = ProgramCounter()
    val stackPointer = StackPointer()

    fun reset(){
        this.programCounter.direction.cargarBytes(HexToBinary.HexToBinary("0000"))//FFFC
        this.stackPointer.direction = HexToBinary.HexToBinary("0000")[0]
        this.processorStatus.decimalMode_flag = false
        this.register_A.content = false
        this.register_X.content = false
        this.register_Y.content = false
        this.memory.reset()
   }

    fun execute(_cicles:Int){
        var cicles = _cicles

        while(cicles > 0){

            //Leo byte en memoria.
            var contenido:Byte = this.programCounter.FetchByte(this.memory)
            cicles -=2 //Cantidad de ciclos usados por la acción de leer de memoria.

            when {
                contenido.equalsByte(HexToBinary.HexToBinary("A9")[0]) -> {
                    val valor:Byte = this.programCounter.FetchByte(this.memory)//Tener en cuenta que no he
                    //incrementado el PC porque no se si deberia de incrementar el valor del word.
                    this.acumulator.data = valor

                    if (valor.equalsByte(HexToBinary.HexToBinary("00")[0])){//Si el reg acumulador es 0 entonces flag zero a 1.
                        this.processorStatus.zero_flag = true
                    }
                    if (valor.byte[1]){//Si está activado el bit 7 del registro acumulador se activa flag negative
                        this.processorStatus.negative_flag = true
                    }
                }

            }

            cicles -=1;
        }
    }
}
