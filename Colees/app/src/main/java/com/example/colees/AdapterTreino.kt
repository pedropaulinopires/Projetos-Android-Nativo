package com.example.colees

class TomadaAntiga(val conector: Conector){
    fun passarEnergia() {
        var qtdPinos = conector.quantidadePinos()
        println("Passando")
        println(qtdPinos)
    }
}

class ConectorAdaptador(): Conector{
    override fun quantidadePinos(): Int {
        return 2
    }

}

interface Conector{
    fun quantidadePinos(): Int
}

class ConectorNovoPadrao(): Conector{
    override fun quantidadePinos(): Int = 3

    fun ligarAparelho() = println("Ligando aparelho")
}

class AdapterTreino {
}


fun main() {
    var conectorAdaptador = ConectorAdaptador()
    var tomadaAntiga = TomadaAntiga(conectorAdaptador)
}