package br.unipar.posback.aula2.domain.mercado.service

import br.unipar.posback.aula2.domain.mercado.model.IndicadoresFinanceiros
import java.math.BigDecimal

class ProvedorIndicadoresService {

    fun retornaIndicadores() = IndicadoresFinanceiros(
        percentualIPCA = BigDecimal.valueOf(5.5),
        percentualTR = BigDecimal.ZERO,
        percentualJurosTaxaFixa = BigDecimal.valueOf(9.0)
    )

}