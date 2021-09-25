package br.unipar.posback.aula2.domain.simulacao.model

import java.math.BigDecimal

data class ResultadoSimulacao(
    val quantidadeMeses: Int,
    val taxaJuros: BigDecimal,
    val taxasAdicionais: BigDecimal,
    val custoTotal: BigDecimal,
    val custoEfetivoTotal: BigDecimal
)
