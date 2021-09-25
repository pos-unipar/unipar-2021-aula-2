package br.unipar.posback.aula2.domain.mercado.model

import java.math.BigDecimal

data class IndicadoresFinanceiros(
    val percentualIPCA: BigDecimal,
    val percentualJurosTaxaFixa: BigDecimal,
    val percentualTR: BigDecimal
)