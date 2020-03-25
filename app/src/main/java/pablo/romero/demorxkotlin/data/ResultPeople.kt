package pablo.romero.demorxkotlin.data

import People
import com.google.gson.annotations.SerializedName

data class ResultPeople (

    @SerializedName("count") val count : Int,
    @SerializedName("next") val next : String,
    @SerializedName("previous") val previous : String,
    @SerializedName("results") val results : List<People>
)