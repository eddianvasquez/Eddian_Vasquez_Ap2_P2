package edu.ucne.eddian_vasquez_ap2_p2.data.remote.api

import edu.ucne.eddian_vasquez_ap2_p2.data.remote.dto.JugadorDto
import retrofit2.Response
import retrofit2.http.*

interface JugadoresApi {
    @GET("api/Jugadores")
    suspend fun getJugadores(): Response<List<JugadorDto>>

    @GET("api/Jugadores/{id}")
    suspend fun getJugador(@Path("id") id: Int): Response<JugadorDto>

    @POST("api/Jugadores")
    suspend fun saveJugador(@Body jugador: JugadorDto): Response<JugadorDto>


    @PUT("api/Jugadores/{id}")
    suspend fun updateJugador(@Path("id") id: Int, @Body jugador: JugadorDto): Response<Void>


    @DELETE("api/Jugadores/{id}")
    suspend fun deleteJugador(@Path("id") id: Int): Response<Void>
}