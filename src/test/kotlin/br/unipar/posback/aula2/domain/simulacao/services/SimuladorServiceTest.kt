package br.unipar.posback.aula2.domain.simulacao.services

import br.unipar.posback.aula2.domain.simulacao.model.ParametrosSimulacao
import br.unipar.posback.aula2.domain.simulacao.model.SituacaoImovel
import br.unipar.posback.aula2.domain.simulacao.model.TipoFinanciamento
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.lang.IllegalArgumentException
import java.math.BigDecimal

class SimuladorServiceTest {

    @ParameterizedTest
    @MethodSource("proverParametrosParaTaxaFixa")
    fun `deve simular um emprestimo de Taxa Fixa`(prazoFinanciamento: Int, quantidadeMesesEsperada: Int, custoTotalEsperado: Double) {
        val taxaJurosEsperada = BigDecimal.valueOf(9.0)
        val taxasAdicionaisEsperadas = BigDecimal.ONE
        val custoEfetivoTotalEsperado = BigDecimal.valueOf(10.0)

        val parametros = ParametrosSimulacao(
            tipoFinanciamento = TipoFinanciamento.TAXA_FIXA,
            cpf = "11111111111",
            situacaoImovel = SituacaoImovel.NOVO,
            valorEmprestimo = BigDecimal.valueOf(100000.0),
            prazoFinanciamento = prazoFinanciamento,
            jaTemFinanciamento = false,
        )

        val simulador = SimuladorService()
        val simulacao = simulador.simular(parametros)

        assertEquals(parametros, simulacao.parametros)
        assertEquals(quantidadeMesesEsperada, simulacao.resultado.quantidadeMeses)
        assertEquals(taxaJurosEsperada, simulacao.resultado.taxaJuros)
        assertEquals(taxasAdicionaisEsperadas, simulacao.resultado.taxasAdicionais)
        assertEquals(BigDecimal.valueOf(custoTotalEsperado), simulacao.resultado.custoTotal)
        assertEquals(custoEfetivoTotalEsperado, simulacao.resultado.custoEfetivoTotal)
    }

    @Test
    fun `o prazo de financiamento nao deve ser menor que 10 anos`() {
        val prazoInvalido = 9

        val parametros = ParametrosSimulacao(
            tipoFinanciamento = TipoFinanciamento.TAXA_FIXA,
            cpf = "11111111111",
            situacaoImovel = SituacaoImovel.NOVO,
            valorEmprestimo = BigDecimal.valueOf(100000.0),
            prazoFinanciamento = prazoInvalido,
            jaTemFinanciamento = false,
        )

        val simulador = SimuladorService()

        val exception = assertThrows(IllegalArgumentException::class.java) {
            simulador.simular(parametros)
        }

        assertEquals("Prazo de financiamento nao pode ser menor que 10 anos", exception.message)
    }

    companion object {

        @JvmStatic
        fun proverParametrosParaTaxaFixa() = listOf(
            Arguments.of(30, 360, 300000.0),
            Arguments.of(25, 300, 250000.0),
            Arguments.of(10, 120, 100000.0)
        )

    }

}