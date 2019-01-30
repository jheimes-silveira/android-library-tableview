package br.com.jheimesilveira.exemple

import java.util.Random

class GeradorCPF {
    @Throws(Exception::class)
    fun geraCPF(): String {
        var digito1 = 0
        var digito2 = 0
        var resto = 0
        val nDigResult: String
        val numerosContatenados: String
        val numeroGerado: String
        val numeroAleatorio = Random()
        //numeros gerados
        val n1 = numeroAleatorio.nextInt(10)
        val n2 = numeroAleatorio.nextInt(10)
        val n3 = numeroAleatorio.nextInt(10)
        val n4 = numeroAleatorio.nextInt(10)
        val n5 = numeroAleatorio.nextInt(10)
        val n6 = numeroAleatorio.nextInt(10)
        val n7 = numeroAleatorio.nextInt(10)
        val n8 = numeroAleatorio.nextInt(10)
        val n9 = numeroAleatorio.nextInt(10)
        val soma = n9 * 2 + n8 * 3 + n7 * 4 + n6 * 5 + n5 * 6 + n4 * 7 + n3 * 8 + n2 * 9 + n1 * 10
        val valor = soma / 11 * 11
        digito1 = soma - valor
        //Primeiro resto da divisão por 11.
        resto = digito1 % 11
        if (digito1 < 2) {
            digito1 = 0
        } else {
            digito1 = 11 - resto
        }
        val soma2 = digito1 * 2 + n9 * 3 + n8 * 4 + n7 * 5 + n6 * 6 + n5 * 7 + n4 * 8 + n3 * 9 + n2 * 10 + n1 * 11
        val valor2 = soma2 / 11 * 11
        digito2 = soma2 - valor2
        //Primeiro resto da divisão por 11.
        resto = digito2 % 11
        if (digito2 < 2) {
            digito2 = 0
        } else {
            digito2 = 11 - resto
        }
        //Conctenando os numeros
        numerosContatenados = n1.toString() + n2.toString() + n3.toString() + "." + n4.toString() +
                n5.toString() + n6.toString() + "." + n7.toString() + n8.toString() +
                n9.toString() + "-"
        //Concatenando o primeiro resto com o segundo.
        nDigResult = digito1.toString() + digito2.toString()
        numeroGerado = numerosContatenados + nDigResult
        println("CPF Gerado $numeroGerado")
        return numeroGerado
    }//fim do metodo geraCPF

    @Throws(Exception::class)
    fun mostraResultado(): String {
        return geraCPF()
    }
}//fim da classe