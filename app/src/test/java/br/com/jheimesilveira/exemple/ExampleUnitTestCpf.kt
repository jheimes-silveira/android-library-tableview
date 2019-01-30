package br.com.jheimesilveira.exemple

import junit.framework.Assert

import org.jetbrains.annotations.TestOnly

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTestCpf {

    @TestOnly
    fun addition_isCorrect() {
        val cpf = GeradorCPF().geraCPF()
        Assert.assertEquals(4, 2 + 2)
    }
}