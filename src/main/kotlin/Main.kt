import Backend.logic.CPU
import Backend.logic.Memory


fun main(args: Array<String>) {

    val memory = Memory()
    memory.loadMemoryFile("./src/main/kotlin/Backend/logic/memoria.txt")
    val cpu = CPU(memory)
    cpu.reset()
    cpu.execute(4)
}