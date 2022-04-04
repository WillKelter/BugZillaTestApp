package com.willkelter.bugzillatestapp.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class BugModel(
    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    val id: Int,
    @SerializedName("creator")
    @ColumnInfo(name = "creator")
    val creator: String,
    @SerializedName("product")
    @ColumnInfo(name = "product")
    val product: String,
    @SerializedName("creation_time")
    @ColumnInfo(name = "creation_time")
    val creation_time : String,
    @SerializedName("classification")
    @ColumnInfo(name = "classification")
    var classification: String,
    @SerializedName("status")
    @ColumnInfo(name = "status")
    val status: String,
    @SerializedName("is_confirmed")
    @ColumnInfo(name = "is_confirmed")
    val is_confirmed: Boolean,
    @SerializedName("summary")
    @ColumnInfo(name = "summary")
    val summary: String,
    @SerializedName("priority")
    @ColumnInfo(name = "priority")
    var priority: String,
    @SerializedName("component")
    @ColumnInfo(name = "component")
    var component: String
): Parcelable
