package com.example.colees

class ClasseAninhada {

}


//class Motorista(val nome: String){
//
//    inner class Caminhao(val modelo: String){
//        fun exibirDados() = println("Modelo: $modelo - Motorista $nome")
//    }
//}

fun main() {
//    val motorista = Motorista("Pedro")
//    var caminhao = motorista.Caminhao("F250")
//    caminhao.exibirDados()

    var carro1 = Carro("Jetta", 2021)
    var carro2 = Carro("Corolla", 2017)

    var (modelo, ano) = carro1

}

data class Carro(val modelo: String, val ano: Int)

