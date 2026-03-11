package edu.ucne.eddian_vasquez_ap2_p2.domain.usecase

import edu.ucne.eddian_vasquez_ap2_p2.domain.repository.JugadorRepository
import javax.inject.Inject

class GetJugadorByIdUseCase @Inject constructor(private val repository: JugadorRepository) {
    operator fun invoke(id: Int) = repository.getJugadorById(id)
}