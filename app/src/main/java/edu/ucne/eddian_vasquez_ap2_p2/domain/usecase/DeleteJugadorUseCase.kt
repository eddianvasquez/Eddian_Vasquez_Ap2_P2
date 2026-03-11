package edu.ucne.eddian_vasquez_ap2_p2.domain.usecase

import edu.ucne.eddian_vasquez_ap2_p2.domain.repository.JugadorRepository
import javax.inject.Inject

class DeleteJugadorUseCase @Inject constructor(private val repository: JugadorRepository) {
    suspend operator fun invoke(id: Int) = repository.deleteJugador(id)
}