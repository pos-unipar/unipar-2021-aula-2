package br.unipar.posback.aula2.domain.simulacao.model.juros

import br.unipar.posback.aula2.domain.mercado.model.IndicadoresFinanceiros
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.math.BigDecimal

class CalculadoraIPCATest {

    @ParameterizedTest
    @MethodSource("proverParametros")
    fun `deve calcular os juros de IPCA`(percentualIPCA: Double, percentualJurosEsperado: Double) {
        val indicadoresFinanceiros = IndicadoresFinanceiros(
            percentualIPCA = BigDecimal.valueOf(percentualIPCA),
            percentualJurosTaxaFixa = BigDecimal.ZERO
        )

        val calculadora = CalculadoraIPCA(indicadoresFinanceiros)
        val percentualJuros = calculadora.calcular()

        Assertions.assertEquals(BigDecimal.valueOf(percentualJurosEsperado), percentualJuros)
    }


    companion object {

        @JvmStatic
        fun proverParametros() = listOf(
            Arguments.of(9.0, 10.0),
            Arguments.of(7.5, 8.5),
            Arguments.of(5.0, 6.0)
        )

    }

}