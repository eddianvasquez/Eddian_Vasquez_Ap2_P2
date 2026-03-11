package edu.ucne.eddian_vasquez_ap2_p2.data.repository

import edu.ucne.eddian_vasquez_ap2_p2.data.remote.dto.Resource
import edu.ucne.eddian_vasquez_ap2_p2.data.remote.dto.toDto
import edu.ucne.eddian_vasquez_ap2_p2.data.remote.remotedatasource.JugadorRemoteDataSource
import edu.ucne.eddian_vasquez_ap2_p2.domain.models.Jugador
import edu.ucne.eddian_vasquez_ap2_p2.domain.repository.JugadorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class JugadorRepositoryImpl @Inject constructor(
    private val remoteDataSource: JugadorRemoteDataSource
) : JugadorRepository {

    override fun getJugadores(): Flow<Resource<List<Jugador>>> = flow {
        emit(Resource.Loading())
        remoteDataSource.getJugadores()
            .onSuccess { dtos -> emit(Resource.Success(dtos.map { it.toDomain() })) }
            .onFailure { emit(Resource.Error(it.message ?: "Error desconocido")) }
    }

    override fun getJugadorById(id: Int): Flow<Resource<Jugador>> = flow {
        emit(Resource.Loading())
        remoteDataSource.getJugadorById(id)
            .onSuccess { dto -> emit(Resource.Success(dto.toDomain())) }
            .onFailure { emit(Resource.Error(it.message ?: "Error desconocido")) }
    }

    override suspend fun saveJugador(jugador: Jugador): Resource<Jugador> {
        return remoteDataSource.saveJugador(jugador.toDto()).fold(
            onSuccess = { Resource.Success(it.toDomain()) },
            onFailure = { Resource.Error(it.message ?: "Error al guardar") }
        )
    }

    override suspend fun updateJugador(id: Int, jugador: Jugador): Resource<Unit> {
        return remoteDataSource.updateJugador(id, jugador.toDto()).fold(
            onSuccess = { Resource.Success(Unit) },
            onFailure = { Resource.Error(it.message ?: "Error al actualizar") }
        )
    }

    override suspend fun deleteJugador(id: Int): Resource<Unit> {
        return remoteDataSource.deleteJugador(id).fold(
            onSuccess = { Resource.Success(Unit) },
            onFailure = { Resource.Error(it.message ?: "Error al eliminar") }
        )
    }
}