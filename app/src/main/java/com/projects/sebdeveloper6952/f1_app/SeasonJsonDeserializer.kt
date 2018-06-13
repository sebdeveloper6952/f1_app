package com.projects.sebdeveloper6952.f1_app

import com.google.gson.*
import java.lang.reflect.Type

class SeasonJsonDeserializer: JsonDeserializer<Season> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?,
                             context: JsonDeserializationContext?): Season {
        val rootObject = json?.asJsonObject
        val raceTable = rootObject?.get("MRData")?.asJsonObject?.get("RaceTable") as JsonObject
        val year = raceTable.get("season").asString
        val racesJson = raceTable.get("Races") as JsonArray
        val races = ArrayList<Race>()
        for(i in 0..(racesJson.size())-1) {
            races.add(Race(racesJson.get(i).asJsonObject.get("raceName").asString))
        }
        return Season(year, races)
    }
}