package edu.ucne.eddian_vasquez_ap2_p2.domain.usecase

import edu.ucne.eddian_vasquez_ap2_p2.domain.models.Jugador
import edu.ucne.eddian_vasquez_ap2_p2.domain.repository.JugadorRepository
import javax.inject.Inject

class SaveJugadorUseCase @Inject constructor(private val repository: JugadorRepository) {
    suspend operator fun invoke(jugador: Jugador) = repository.saveJugador(jugador)
}