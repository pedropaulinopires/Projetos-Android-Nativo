//package com.example.colees
//
//class ExercicioAdapter {}
//
//interface Adapter {
//    fun quantidadeLinhas(): Int
//
//    fun exibirListagem();
//}
//
//class ExecutorListagemNumerica(var list: List<String>) : Adapter {
//    override fun quantidadeLinhas(): Int = list.size
//
//    override fun exibirListagem() {
//        var indice = 0
//        for (item in list){
//            println("${indice++} - ${item}")
//        }
//        println("Qtd de linhas = ${quantidadeLinhas()}")
//    }
//
//}
//
//class ExecutorListagem(var list: List<String>) : Adapter {
//    override fun quantidadeLinhas(): Int = list.size
//
//    override fun exibirListagem() {
//        for (item in list){
//            println("${item}")
//        }
//        println("Qtd de linhas = ${quantidadeLinhas()}")
//    }
//
//}
//
//class ComponenteListagem() {
//
//    var adapter: Adapter? = null
//
//    fun executarListagem() {
//        if (adapter == null) println("Passar o adapter")
//        adapter!!.exibirListagem();
//    }
//}
//
//fun main() {
//    var listaNome = listOf<String>("Pedro", "Joao", "Ana");
//
//    var componenteListagem = ComponenteListagem()
//    componenteListagem.adapter = ExecutorListagemNumerica(listaNome)
////    componenteListagem.adapter = ExecutorListagem(listaNome)
//    componenteListagem.executarListagem()
//}
//
//
//
//
