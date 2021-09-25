package br.unipar.posback.aula2.domain.simulacao.model.juros

import br.unipar.posback.aula2.domain.mercado.model.IndicadoresFinanceiros
import java.math.BigDecimal

private val PERCENTUAL_JUROS_MINIMO = BigDecimal.valueOf(5.0)

class CalculadoraTaxaFixa(private val indicadoresFinanceiros: IndicadoresFinanceiros) : CalculadoraJuros {

    override fun calcular() = if (indicadoresFinanceiros.percentualJurosTaxaFixa < PERCENTUAL_JUROS_MINIMO) {
        throw IllegalArgumentException("O percentual de juros nao deve ser menor que 5.0")
    } else {
        indicadoresFinanceiros.percentualJurosTaxaFixa
    }

}
