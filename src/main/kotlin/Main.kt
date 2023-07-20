import Backend.logic.CPU
import Backend.logic.Memory


fun main(args: Array<String>) {

    val memory = Memory()
    val cpu = CPU(memory)
    cpu.reset()
    cpu.execute(4)
}