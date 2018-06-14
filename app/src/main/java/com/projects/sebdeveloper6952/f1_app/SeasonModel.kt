package com.projects.sebdeveloper6952.f1_app

import java.io.Serializable

data class SeasonResponse(var MRData: MRData): Serializable
data class MRData(var RaceTable: Season): Serializable
data class Season(var season: String, var Races: List<Race>): Serializable