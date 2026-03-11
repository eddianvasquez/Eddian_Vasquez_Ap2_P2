package edu.ucne.eddian_vasquez_ap2_p2.domain.repository

import edu.ucne.eddian_vasquez_ap2_p2.data.remote.dto.Resource
import edu.ucne.eddian_vasquez_ap2_p2.domain.models.Jugador
import kotlinx.coroutines.flow.Flow

interface JugadorRepository {
    fun getJugadores(): Flow<Resource<List<Jugador>>>
    fun getJugadorById(id: Int): Flow<Resource<Jugador>>
    suspend fun saveJugador(jugador: Jugador): Resource<Jugador>
    suspend fun updateJugador(id: Int, jugador: Jugador): Resource<Unit>
    suspend fun deleteJugador(id: Int): Resource<Unit>
}