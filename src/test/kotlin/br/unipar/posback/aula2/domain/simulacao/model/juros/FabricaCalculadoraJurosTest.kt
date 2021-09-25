package br.unipar.posback.aula2.domain.simulacao.model.juros

import br.unipar.posback.aula2.domain.mercado.service.ProvedorIndicadoresService
import br.unipar.posback.aula2.domain.simulacao.model.TipoFinanciamento
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class FabricaCalculadoraJurosTest {

    @ParameterizedTest
    @MethodSource("proverInstanciasPorTipoDeFinanciamento")
    fun `deve retornar a instancia correta da calculadora de acordo com o tipo de financiamento`(tipoFinanciamento: TipoFinanciamento, classeCalculadora: Any) {
        val provedorIndicadoresService = ProvedorIndicadoresService()
        val fabrica = FabricaCalculadoraJuros(provedorIndicadoresService)

        val calculadora = fabrica.retornaCalculadora(tipoFinanciamento)
        assertEquals(classeCalculadora, calculadora.javaClass)
    }

    companion object {

        @JvmStatic
        fun proverInstanciasPorTipoDeFinanciamento() = listOf(
            Arguments.of(TipoFinanciamento.TAXA_FIXA, CalculadoraTaxaFixa::class.java),
//            Arguments.of(TipoFinanciamento.TR, CalculadoraTaxaFixa::class.java),
            Arguments.of(TipoFinanciamento.IPCA, CalculadoraIPCA::class.java)
        )

    }

}