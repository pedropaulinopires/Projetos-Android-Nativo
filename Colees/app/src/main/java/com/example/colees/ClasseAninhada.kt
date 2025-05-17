package com.example.colees

import android.R

class ClasseAninhada {

}

class Pessoa(){
    var nome: String = ""
        get() = field.uppercase()
    var idade: Int = 0
        set(value) {
            field = value
        }
    var telefones: List<String> = listOf("")

    constructor(nome:String, idade: Int, vararg telefones: String) : this() {
        this.nome = nome
        this.idade = idade
        this.telefones = telefones.toList()
    }

    var maiorIdade: Boolean = false
        get() = idade >= 18
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

//    var carro1 = Carro("Jetta", 2021)
//    var carro2 = Carro("Corolla", 2017)
//
//    var (modelo, ano) = carro1

    var pessoa = Pessoa("Pedro", 15, "123", "1423", "78954")
    print(pessoa.nome)
    print(pessoa.idade)
    print(pessoa.maiorIdade)
    print(pessoa.telefones)




}

data class Carro(val modelo: String, val ano: Int)

