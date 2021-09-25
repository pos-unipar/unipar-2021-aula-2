package br.unipar.posback.aula2.domain.simulacao.model.juros

import br.unipar.posback.aula2.domain.mercado.model.IndicadoresFinanceiros
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.lang.IllegalArgumentException
import java.math.BigDecimal

class CalculadoraTRTest {

    @ParameterizedTest
    @MethodSource("proverParametros")
    fun `deve calcular os juros baseado na TR`(percentualIPCA: Double, percentualTR: Double, percentualJurosEsperado: Double) {
        val percentualJurosEsperado = BigDecimal.valueOf(percentualJurosEsperado)

        val indicadoresFinanceiros = IndicadoresFinanceiros(
            percentualIPCA = BigDecimal.valueOf(percentualIPCA),
            percentualTR = BigDecimal.valueOf(percentualTR),
            percentualJurosTaxaFixa = BigDecimal.ZERO
        )

        val calculadora = CalculadoraTR(indicadoresFinanceiros)
        val percentualJuros = calculadora.calcular()

        Assertions.assertEquals(percentualJurosEsperado, percentualJuros)
    }

    companion object {

        @JvmStatic
        fun proverParametros() = listOf(
            Arguments.of(5.0, 0.0, 3.5),
            Arguments.of(5.0, 1.2, 4.7),
            Arguments.of(7.0, 0.0, 4.9),
            Arguments.of(7.0, 1.0, 5.9)
        )

    }

}