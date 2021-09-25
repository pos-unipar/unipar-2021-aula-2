package br.unipar.posback.aula2.domain.simulacao.model

import java.math.BigDecimal

data class ParametrosSimulacao(
    val tipoFinanciamento: TipoFinanciamento,
    val cpf: String,
    val situacaoImovel: SituacaoImovel,
    val valorEmprestimo: BigDecimal,
    val prazoFinanciamento: Int,
    val jaTemFinanciamento: Boolean,
)
