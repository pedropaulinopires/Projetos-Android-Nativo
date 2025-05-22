package com.example.colees

class colecoes {
}


fun main() {

    var map = mapOf<String, String>("1" to "Pedro", "2" to "José")

    map.forEach { m ->
        println(m.key)
    }

    var teste = map.filter { x -> x.key == "1" }

    println(teste)

}