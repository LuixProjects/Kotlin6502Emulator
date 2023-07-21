def getBinaryFromHex(hex_num):
    
    hex_dict = {'0': '0000', '1': '0001', '2': '0010', '3': '0011', '4': '0100', '5': '0101', '6': '0110', '7': '0111', '8': '1000', '9': '1001', 'a': '1010', 'b': '1011', 'c': '1100', 'd': '1101', 'e': '1110', 'f': '1111'}
    binary = ''
    for digit in hex_num:
        binary += hex_dict[digit]
    return binary


def main():
    nombre_archivo_entrada = "./code.txt"
    nombre_archivo_salida = "./compiled_code.txt"
    salida_fichero = []
    directorio_operaciones = {
        "LDA #" :   ["Inmediate","1\t0\t1\t0\t1\t0\t0\t1"],
        "LDX #" :   ["Inmediate","1\t0\t1\t0\t0\t0\t1\t0"],
        "LDY #" :   ["Inmediate","1\t0\t1\t0\t0\t0\t0\t0"],
        "STA $" :   ["Absolute","1\t0\t0\t0\t1\t1\t0\t1"],
        "STX $" :   ["Absolute","1\t0\t0\t0\t1\t1\t1\t0"],
        "STY $" :   ["Absolute","1\t0\t0\t0\t1\t1\t0\t0"],
        "TAX"   :   ["Implicit","1\t0\t1\t0\t1\t0\t1\t0"],
        "TAY"   :   ["Implicit","1\t0\t1\t0\t1\t0\t0\t0"],
        "TXA"   :   ["Implicit","1\t0\t0\t0\t1\t0\t1\t0"],
        "TYA"   :   ["Implicit","1\t0\t0\t1\t1\t0\t0\t0"]
    }

    try:
        with open(nombre_archivo_entrada, "r") as file:
            # Recorremos el archivo línea por línea utilizando un bucle for
            linea = 0
            for comando in file:
                for clave in directorio_operaciones:
                    #print(clave + " " + comando)
                    if clave in comando:
                        
                        salida_fichero.append(directorio_operaciones[clave][1])##cargado la instruccion.
                        if directorio_operaciones[clave][0] == "Inmediate":
                            resto_cadena = comando[len(clave):]
                            datos = (resto_cadena.replace(" ","").replace("\n",""))
                            binario = bin(int(datos))[2:]
                            binario = binario.zfill(8)
                            salida_fichero.append("\t".join(binario))
                        if directorio_operaciones[clave][0] == "Absolute":
                            resto_cadena = comando[len(clave):]
                            datos = (resto_cadena.replace(" ","").replace("\n",""))
                            hight_data = datos[0:2]
                            low_data = datos[2:]
                            hight_binario = getBinaryFromHex(hight_data)
                            salida_fichero.append("\t".join(hight_binario))

                            low_binario = getBinaryFromHex(low_data)
                            salida_fichero.append("\t".join(low_binario))


                    linea += 1
                

    except FileNotFoundError:
        print(f"El archivo '{nombre_archivo_entrada}' no fue encontrado.")
    except IOError as e:
        print(f"Error de E/S: {e}")
    
    with open(nombre_archivo_salida, 'w+') as archivo:
        for valor in salida_fichero:
            archivo.write(str(valor) + '\n')


if __name__ == "__main__":
    main()
