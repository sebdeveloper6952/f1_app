package com.projects.sebdeveloper6952.f1_app.models

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class SeasonScheduleResponse(var MRData: SeasonScheduleResponse.MRDATA): Serializable {
    data class MRDATA(var RaceTable: SeasonSchedule): Serializable
    data class SeasonSchedule(var season: String, var Races: List<Race>): Serializable
    data class Race(var season: String, var raceName: String): Serializable
}

data class SeasonStandingsResponse(var MRData: SeasonStandingsResponse.MRDATA): Serializable {
    data class MRDATA(var StandingsTable: SeasonStanding): Serializable
    data class SeasonStanding(var season: String, var StandingsLists: List<RoundStanding>): Serializable
    data class RoundStanding(var round: Int, var DriverStandings: List<DriverResult>)
    data class DriverResult(var position: Int, var Driver: Driver): Parcelable {

        constructor(parcel: Parcel) : this(
                parcel.readInt(), parcel.readSerializable() as Driver)

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(position)
            parcel.writeSerializable(Driver)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<DriverResult> {
            override fun createFromParcel(parcel: Parcel): DriverResult {
                return DriverResult(parcel)
            }

            override fun newArray(size: Int): Array<DriverResult?> {
                return arrayOfNulls(size)
            }
        }

    }
    data class Driver(var driverId: String, var givenName: String, var familyName: String): Serializable
}