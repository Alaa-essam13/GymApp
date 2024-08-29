package com.example.gymapp

import com.google.gson.annotations.SerializedName

//val listOfGyms= listOf<Gym>(
//    Gym(1,"Gym1 prapra","20 place pla pla"),
//    Gym(2,"Gym2 prapra","20 place pla pla"),
//    Gym(3,"Gym3 prapra","20 place pla pla"),
//    Gym(4,"Gym4 prapra","20 place pla pla"),
//    Gym(5,"Gym5 prapra","20 place pla pla"),
//    Gym(6,"Gym6 prapra","20 place pla pla"),
//    Gym(7,"Gym7 prapra","20 place pla pla"),
//    Gym(8,"Gym8 prapra","20 place pla pla"),
//    Gym(1,"Gym1 prapra","20 place pla pla"),
//    Gym(2,"Gym2 prapra","20 place pla pla"),
//    Gym(3,"Gym3 prapra","20 place pla pla"),
//    Gym(4,"Gym4 prapra","20 place pla pla"),
//    Gym(5,"Gym5 prapra","20 place pla pla"),
//    Gym(6,"Gym6 prapra","20 place pla pla"),
//    Gym(7,"Gym7 prapra","20 place pla pla"),
//    Gym(8,"Gym8 prapra","20 place pla pla"),
//)

data class Gym(
    val id:Int,
    @SerializedName("gym_name")
    val name:String,
    @SerializedName("gym_location")
    val place:String,
    var isFav:Boolean=false
)