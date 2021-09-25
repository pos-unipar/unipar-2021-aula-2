package br.unipar.posback.aula2.domain.simulacao.model.juros

import br.unipar.posback.aula2.domain.mercado.model.IndicadoresFinanceiros
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.lang.IllegalArgumentException
import java.math.BigDecimal

class CalculadoraTaxaFixaTest {

    @ParameterizedTest
    @MethodSource("proverParametros")
    fun `deve calcular os juros de taxa fixa`(percentualJuros: Double) {
        val percentualJurosEsperado = BigDecimal.valueOf(percentualJuros)

        val indicadoresFinanceiros = IndicadoresFinanceiros(
            percentualIPCA = BigDecimal.ZERO,
            percentualJurosTaxaFixa = BigDecimal.valueOf(percentualJuros)
        )

        val calculadora = CalculadoraTaxaFixa(indicadoresFinanceiros)
        val percentualJuros = calculadora.calcular()

        Assertions.assertEquals(percentualJurosEsperado, percentualJuros)
    }

    @Test
    fun `os percentual de juros nao pode ser menor que 5 por cento`() {
        val percentualJurosInvalido = BigDecimal.valueOf(4.999)

        val indicadoresFinanceiros = IndicadoresFinanceiros(
            percentualIPCA = BigDecimal.ZERO,
            percentualJurosTaxaFixa = percentualJurosInvalido
        )

        val calculadora = CalculadoraTaxaFixa(indicadoresFinanceiros)

        val exception = Assertions.assertThrows(IllegalArgumentException::class.java) {
            calculadora.calcular()
        }

        Assertions.assertEquals("O percentual de juros nao deve ser menor que 5.0", exception.message)
    }

    companion object {

        @JvmStatic
        fun proverParametros() = listOf(
            Arguments.of(9.0),
            Arguments.of(7.5),
            Arguments.of(5.0)
        )

    }

}