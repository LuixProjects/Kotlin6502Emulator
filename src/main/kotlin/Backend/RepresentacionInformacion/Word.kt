package Backend.RepresentacionInformacion

class Word {
    var word:List<Byte> = listOf(Byte(),Byte())


    fun getInt():Int{
        val superior_byte = intArrayOf(15,14,13,12,11,10,9,8)
        val inferior_byte = intArrayOf(7,6,5,4,3,2,1,0)

        return (this.word[0].getIntValue(superior_byte) + this.word[0].getIntValue(inferior_byte))
    }

    fun cargarBytes(listaBytes :MutableList<Byte>){
        this.word = listOf(listaBytes[0],listaBytes[1])
    }
}