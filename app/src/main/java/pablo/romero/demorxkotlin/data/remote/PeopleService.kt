package pablo.romero.demorxkotlin.data.remote

import People
import pablo.romero.demorxkotlin.data.ResultPeople
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Query

interface PeopleService {


    //No estoy seguro de si se hace asi
    @GET("people")
    fun getCurrentPeopleData(@Query("search") name: String): Call<ResultPeople>
}


//https://swapi.co/api/people/?search=lu