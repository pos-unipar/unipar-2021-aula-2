package br.unipar.posback.aula2.domain.simulacao.services

import br.unipar.posback.aula2.domain.simulacao.model.IdSimulacao
import br.unipar.posback.aula2.domain.simulacao.model.ParametrosSimulacao
import br.unipar.posback.aula2.domain.simulacao.model.ResultadoSimulacao
import br.unipar.posback.aula2.domain.simulacao.model.Simulacao
import java.math.BigDecimal

private const val MESES_DO_ANO = 12
private val CEM = BigDecimal.valueOf(100.0)
private val TAXA_JUROS_PADRAO = BigDecimal.valueOf(9.0)
private val TAXAS_ADICIONAIS_PADRAO = BigDecimal.ONE

class SimuladorService {

    fun simular(parametros: ParametrosSimulacao): Simulacao {
        if (parametros.prazoFinanciamento < 10) {
            throw IllegalArgumentException("Prazo de financiamento nao pode ser menor que 10 anos")
        }

        val taxaJuros = TAXA_JUROS_PADRAO
        val taxasAdicionais = TAXAS_ADICIONAIS_PADRAO
        val custoEfetivoTotal = calculaCustoEfetivoTotal(taxaJuros, taxasAdicionais)

        return Simulacao(
            id = IdSimulacao(),
            parametros = parametros,
            resultado = ResultadoSimulacao(
                quantidadeMeses = parametros.prazoFinanciamento * MESES_DO_ANO,
                taxaJuros = taxaJuros,
                taxasAdicionais = taxasAdicionais,
                custoEfetivoTotal = custoEfetivoTotal,
                custoTotal = calculaCustoTotal(parametros, custoEfetivoTotal)
            )
        )
    }

    fun calculaCustoTotal(parametros: ParametrosSimulacao, custoEfetivoTotal: BigDecimal) = parametros.valorEmprestimo.divide(CEM)
        .multiply(BigDecimal(parametros.prazoFinanciamento))
        .multiply(custoEfetivoTotal)

    fun calculaCustoEfetivoTotal(taxaJuros: BigDecimal, taxasAdicionais: BigDecimal) = taxaJuros.add(taxasAdicionais)

}