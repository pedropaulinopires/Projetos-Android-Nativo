package com.example.colees

class FuncaoEscopo {
}

data class Produto(
    var nome: String,
    var preco: Double){

    fun desativar(){
        println("Desativado")
    }
}

fun salvarProduto(produto: Produto){
    println(produto)
}

class AlertaMensagem(){
    fun configurarTitulo(titulo: String){
        println(titulo)
    }
}


fun main() {

    var produto: Produto? = null
//
    produto = Produto("Notebook", 2000.00)
//
//    produto?.let { item ->
//        item.preco = 100.100
////        salvarProduto(produto)
//    }
//
//    println(produto)

//    produto?.run {
//        preco = 100.200
//    }

//    with (produto){
//        desativar()
//    }

//    var alerta = AlertaMensagem();
//
////    alerta.configurarTitulo("Teste")
//
//    alerta.apply {
//        configurarTitulo("teste")
//    }

//    val listaNomes = listOf<String>("Pedro", "Ana")
//
//    listaNomes.map { x ->
//        x.uppercase()
//    }.also {x ->
//        println(x)
//    }
}