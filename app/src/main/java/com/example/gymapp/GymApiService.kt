package com.example.gymapp

import retrofit2.Call
import retrofit2.http.GET

interface GymApiService {

    @GET("gyms.json")
    fun getGyms():Call<List<Gym>>
}