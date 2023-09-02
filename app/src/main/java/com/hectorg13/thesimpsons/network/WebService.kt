package com.hectorg13.thesimpsons.network

import com.hectorg13.thesimpsons.models.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET("quotes?count=12")
    suspend fun getAllCharacters(): Response<List<Character>>

    @GET("quotes")
    suspend fun getCharacter(
        @Query("character") personaje: String
    ): Response<List<Character>>
}