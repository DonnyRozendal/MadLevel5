package nl.hva.madlevel5.features.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "gameTable")
data class Game(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val title: String,
    val platform: String,
    val status: String,
    val date: String
) : Parcelable