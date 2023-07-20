package Backend.Utils

import Backend.RepresentacionInformacion.Byte

class HexToBinary {
    companion object{
    private val hashmap: HashMap<Char,BooleanArray>

    init{
        hashmap = hashMapOf(
            '0' to booleanArrayOf(false,false,false,false),
            '1' to booleanArrayOf(false,false,false,true),
            '2' to booleanArrayOf(false,false,true,false),
            '3' to booleanArrayOf(false,false,true,true),
            '4' to booleanArrayOf(false,true,false,false),
            '5' to booleanArrayOf(false,true,false,true),
            '6' to booleanArrayOf(false,true,true,false),
            '7' to booleanArrayOf(false,true,true,true),
            '8' to booleanArrayOf(true,false,false,false),
            '9' to booleanArrayOf(true,false,false,true),
            'A' to booleanArrayOf(true,false,true,false),
            'B' to booleanArrayOf(true,false,true,true),
            'C' to booleanArrayOf(true,true,false,false),
            'D' to booleanArrayOf(true,true,false,true),
            'E' to booleanArrayOf(true,true,true,false),
            'F' to booleanArrayOf(true,true,true,true),
        )
    }

    fun HexToBinary(cadenaHex:String): MutableList<Byte> {
        val mutableList: MutableList<Byte> = mutableListOf()
        val aux: MutableList<BooleanArray> = mutableListOf()

        for (letra in cadenaHex){

            aux.add(this.hashmap[letra]!!)

            if (aux.size >= 2){
                val byte = Byte()
                byte.byte = aux[0] + aux[1]
                mutableList.add(byte)
                aux.clear()
            }
        }
        return mutableList
    }
    }
}