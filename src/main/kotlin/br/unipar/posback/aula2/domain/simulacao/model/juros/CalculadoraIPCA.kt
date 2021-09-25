package br.unipar.posback.aula2.domain.simulacao.model.juros

import br.unipar.posback.aula2.domain.mercado.model.IndicadoresFinanceiros
import java.math.BigDecimal

private val TAXA_ADICAO_IPCA = BigDecimal.ONE

class CalculadoraIPCA(private val indicadoresFinanceiros: IndicadoresFinanceiros) : CalculadoraJuros {

    override fun calcular() = indicadoresFinanceiros.percentualIPCA.add(TAXA_ADICAO_IPCA)

}