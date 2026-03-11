package edu.ucne.eddian_vasquez_ap2_p2.data.remote.remotedatasource

import edu.ucne.eddian_vasquez_ap2_p2.data.remote.api.JugadoresApi
import edu.ucne.eddian_vasquez_ap2_p2.data.remote.dto.JugadorDto
import javax.inject.Inject

class JugadorRemoteDataSource @Inject constructor(
    private val api: JugadoresApi
) {
    suspend fun getJugadores(): Result<List<JugadorDto>> {
        return try {
            val response = api.getJugadores()
            if (response.isSuccessful) Result.success(response.body() ?: emptyList())
            else Result.failure(Exception("Error: ${response.code()}"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getJugadorById(id: Int): Result<JugadorDto> {
        return try {
            val response = api.getJugador(id)
            if (response.isSuccessful && response.body() != null) Result.success(response.body()!!)
            else Result.failure(Exception("Jugador no encontrado"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun saveJugador(dto: JugadorDto): Result<JugadorDto> {
        return try {
            val response = api.saveJugador(dto)
            if (response.isSuccessful && response.body() != null) Result.success(response.body()!!)
            else Result.failure(Exception("Error al guardar: ${response.code()}"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateJugador(id: Int, dto: JugadorDto): Result<Unit> {
        return try {
            val response = api.updateJugador(id, dto)
            if (response.isSuccessful) Result.success(Unit)
            else Result.failure(Exception("Error al actualizar: ${response.code()}"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteJugador(id: Int): Result<Unit> {
        return try {
            val response = api.deleteJugador(id)
            if (response.isSuccessful) Result.success(Unit)
            else Result.failure(Exception("Error al eliminar: ${response.code()}"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}