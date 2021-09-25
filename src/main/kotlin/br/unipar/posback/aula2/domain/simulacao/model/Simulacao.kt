package br.unipar.posback.aula2.domain.simulacao.model

import java.util.*

class Simulacao(
    val id: IdSimulacao,
    val parametros: ParametrosSimulacao,
    val resultado: ResultadoSimulacao
)

data class IdSimulacao(val id: UUID) {
    constructor() : this(UUID.randomUUID())
}
