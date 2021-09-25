package br.unipar.posback.aula2.domain.simulacao.model.juros

import br.unipar.posback.aula2.domain.mercado.service.ProvedorIndicadoresService
import br.unipar.posback.aula2.domain.simulacao.model.TipoFinanciamento

class FabricaCalculadoraJuros(private val provedorIndicadoresService: ProvedorIndicadoresService) {

    fun retornaCalculadora(tipoFinanciamento: TipoFinanciamento): CalculadoraJuros {
        val indicadoresFinanceiros = provedorIndicadoresService.retornaIndicadores()

        return when (tipoFinanciamento) {
            TipoFinanciamento.IPCA -> CalculadoraIPCA(indicadoresFinanceiros)
            TipoFinanciamento.TR -> throw NotImplementedError()
            TipoFinanciamento.TAXA_FIXA -> CalculadoraTaxaFixa(indicadoresFinanceiros)
        }
    }

}