package br.unipar.posback.aula2.domain.simulacao.model.juros

import java.math.BigDecimal

interface CalculadoraJuros {
    fun calcular(): BigDecimal
}