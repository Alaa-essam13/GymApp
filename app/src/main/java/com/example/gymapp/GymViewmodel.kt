package com.example.gymapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class GymViewmodel(
    private val stateHandle: SavedStateHandle
) : ViewModel() {
    var state by mutableStateOf(emptyList<Gym>())

    private var apiServices:GymApiService //execute network request

    private lateinit var gymCall:Call<List<Gym>>
    init { //to instantiate retrofit builder
        val retrofit:Retrofit=Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create()  //add json converter
            )
            .baseUrl("")
            .build()
        apiServices=retrofit.create(GymApiService::class.java)   //to create implementation of interface
        getGyms()
    }
    fun getGyms() {
        gymCall=apiServices.getGyms()
        gymCall.enqueue(object :Callback<List<Gym>>{
            override fun onResponse(p0: Call<List<Gym>>, p1: Response<List<Gym>>) {
               p1.body()?.let{
                   state=it.restoreSelectedGyms()
               }
            }

            override fun onFailure(p0: Call<List<Gym>>, p1: Throwable) {
                p1.printStackTrace()
            }

        })
//        apiServices.getGyms().execute().body()?.let { gyms: List<Gym> ->
//            state=gyms.restoreSelectedGyms()
//        }
    }

    override fun onCleared() {
        super.onCleared()
        gymCall.cancel()
    }
    fun toggleFavState(gymId: Int) {
        val gyms = state.toMutableList()
        val itemIndex = gyms.indexOfFirst { it.id == gymId }
        gyms[itemIndex] = gyms[itemIndex].copy(isFav = !gyms[itemIndex].isFav)
        storedSelectedGym(gyms[itemIndex])
        state = gyms
    }

    private fun storedSelectedGym(gym:Gym){
        val savedHandleList=stateHandle.get<List<Int>?>(FAV_IDS).orEmpty().toMutableList()
        if(gym.isFav) savedHandleList.add(gym.id)
        else savedHandleList.remove(gym.id)
        stateHandle[FAV_IDS]=savedHandleList
    }
    private fun List<Gym>.restoreSelectedGyms():List<Gym>{
//        val gym=this
        stateHandle.get<List<Int>?>(FAV_IDS)?.let { savedIds->
            savedIds.forEach {gymId->
                this.find{it.id==gymId}?.isFav=true
            }
        }
        return this
    }
    companion object{
        const val FAV_IDS="myKeyToSaveData"
    }
}