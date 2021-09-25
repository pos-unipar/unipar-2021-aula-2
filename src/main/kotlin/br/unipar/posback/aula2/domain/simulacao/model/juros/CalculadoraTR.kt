package br.unipar.posback.aula2.domain.simulacao.model.juros

import br.unipar.posback.aula2.domain.mercado.model.IndicadoresFinanceiros
import java.math.BigDecimal

private val PORCAO_IPCA_EFETIVA = BigDecimal.valueOf(0.7)

class CalculadoraTR(private val indicadoresFinanceiros: IndicadoresFinanceiros) : CalculadoraJuros {

    override fun calcular(): BigDecimal {
        val taxaIPCAEfetiva = indicadoresFinanceiros.percentualIPCA.multiply(PORCAO_IPCA_EFETIVA)
        return taxaIPCAEfetiva.add(indicadoresFinanceiros.percentualTR).setScale(1)
    }

}