package Backend.Register

//Clase que maneja las flags existentes en un procesador.
class ProcessorStatus {
    var carry_flag: Boolean = false;
    var zero_flag: Boolean = false;
    var interrupDisable_flag: Boolean = false;
    var decimalMode_flag:Boolean=false;
    var breakCommand_flag: Boolean = false;
    var overflow_flag: Boolean=false;
    var negative_flag: Boolean = false;
}