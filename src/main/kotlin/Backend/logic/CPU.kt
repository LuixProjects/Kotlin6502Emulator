package Backend.logic

import Backend.Register.*
import Backend.Utils.HexToBinary
import Backend.RepresentacionInformacion.Byte
import Backend.RepresentacionInformacion.Word

class CPU(private val memory: Memory) {
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
   }

    fun execute(_cicles:Int){
        var cicles = _cicles

        while(cicles > 0){

            //Leo byte en memoria.
            val contenido:Byte = this.programCounter.FetchByte(this.memory)
            //No tengo seguro si esto consume un ciclo.
            when {
                contenido.equalsByte(HexToBinary.HexToBinary("A9")[0]) -> { //LDA inmediato
                    loadData(this.register_A)
                    cicles -= 2
                }
                contenido.equalsByte(HexToBinary.HexToBinary("A2")[0]) ->{ //LDX inmediato
                    loadData(this.register_X)
                    cicles -= 2
                }
                contenido.equalsByte(HexToBinary.HexToBinary("A0")[0]) ->{ //LDY inmediato
                    loadData(this.register_Y)
                    cicles -= 2
                }
                contenido.equalsByte(HexToBinary.HexToBinary("8D")[0]) ->{ //STA Absolute
                    saveData(this.register_A)
                    cicles -= 4
                }
                contenido.equalsByte(HexToBinary.HexToBinary("8E")[0]) ->{ //STX Absolute
                    saveData(this.register_X)
                    cicles -= 4
                }
                contenido.equalsByte(HexToBinary.HexToBinary("8C")[0]) ->{ //STY Absolute
                    saveData(this.register_Y)
                    cicles -= 4
                }
                contenido.equalsByte(HexToBinary.HexToBinary("AA")[0]) ->{ //TAX A -> X
                    transferData(this.register_A,this.register_X)
                    cicles -= 2
                }
                contenido.equalsByte(HexToBinary.HexToBinary("A8")[0]) ->{ //TAY A -> Y
                    transferData(this.register_A,this.register_Y)
                    cicles -= 2
                }
                contenido.equalsByte(HexToBinary.HexToBinary("8A")[0]) ->{ //TXA X -> A
                    transferData(this.register_X,this.register_A)
                    cicles -= 2
                }
                contenido.equalsByte(HexToBinary.HexToBinary("98")[0]) ->{ //TYA Y -> A
                    transferData(this.register_Y,this.register_A)
                    cicles -= 2
                }

            }


        }
    }

    /*
    * Auxiliar function to help loading data to registers.
    * In the future it will necesary to have a extra paremeter to select the type of  Addressing Modes.
    * */
    fun loadData(register:InformationRegisters){
        val valor:Byte = this.programCounter.FetchByte(this.memory)
        register.data = valor

        if (valor.equalsByte(HexToBinary.HexToBinary("00")[0])){//Si el reg acumulador es 0 entonces flag zero a 1.
            this.processorStatus.zero_flag = true
        }
        if (valor.byte[1]){//Si está activado el bit 7 del registro acumulador se activa flag negative
            this.processorStatus.negative_flag = true
        }
    }
    /*PROBLEMA: NO APARECE EN LA DOCUMENTACION EL ORDEN DE PREFERENCIA DE LOS BYTES.
    * ESTO HACE QUE AL COGER DE LA MEMORIA LA DIRECCIÓN DE 16 BITS (2BYTES) NO SE CONOCE
    * CUAL DE ESTOS BYTES ES EL QUE CONTIENE LOS BITS SIGNIFICATIVOS, SE SUPONE A PARTIR DE ESTE PUNTO
    * QUE LA PARTE SIGNIFICATIVA DE LA DIRECCIÓN ES LA PRIMERA, POR LO QUE EL MENOS SIGNIFICATIVO ES EL SEGUNDO.
    * ESTO TIENE QUE ESTAR MAL*/
    fun saveData(register:InformationRegisters){
        //Read memory to know the destination of the data
        val up_direction:Byte = this.programCounter.FetchByte(this.memory)
        val down_direction:Byte = this.programCounter.FetchByte(this.memory)
        //Read the data
        val data:Byte = register.data.clone()

        //Write the data in the selected destination
        val direction = Word()
        direction.word = listOf(up_direction,down_direction)
        val int_direction = direction.getInt()

        this.memory.memory[int_direction] = data
    }


    fun transferData(registerOrigin:InformationRegisters,registerDestination:InformationRegisters){
        registerOrigin.data = registerDestination.data.clone()

        if (this.register_A.data.equalsByte(HexToBinary.HexToBinary("00")[0])){//Si el reg acumulador es 0 entonces flag zero a 1.
            this.processorStatus.zero_flag = true
        }
        if (this.register_A.data.byte[1]){//Si está activado el bit 7 del registro acumulador se activa flag negative
            this.processorStatus.negative_flag = true
        }
    }

}
