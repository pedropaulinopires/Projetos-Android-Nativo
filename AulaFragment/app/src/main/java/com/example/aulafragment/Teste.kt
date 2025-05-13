package com.example.aulafragment


fun somar(n1: Int, n2: Int):Int{
    return  n1 + n2
}

fun subtrair(n1: Int, n2: Int):Int{
    return  n1 - n2
}

fun calcular(funcao: (Int, Int) -> Int){
    println(funcao(10, 10))
}

fun main() {

//    calcular( ::subtrair)
    teste("Pedro")

}

var teste = { nome: String ->
    print(nome)
}