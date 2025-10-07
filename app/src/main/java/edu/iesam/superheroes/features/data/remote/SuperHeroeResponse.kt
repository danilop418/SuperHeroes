package edu.iesam.superheroes.features.data.remote

import androidx.annotation.ColorRes
import com.example.superherocards.R
import com.google.gson.TypeAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

data class SuperheroResponse (
    @SerializedName ("response") val response: String,
    @SerializedName ("results-for") val resultsFor: String,
    @SerializedName ("results") val results: List<Superhero>
)

data class Superhero (
    @SerializedName ("id") val id: String,
    @SerializedName ("name") val name: String,
    @SerializedName ("image") val image: Image,
    @SerializedName ("biography") val biography: Biography,
    @SerializedName("appearance") val appearance:Appearance,
    @SerializedName ("powerstats") val power: Power,
    @SerializedName ("work") val work: Work,
) {
    @ColorRes
    fun getAlignmentColor() : Int {
        return when (biography.alignment) {
            "good" -> R.color.alignment_color_good
            "bad" -> R.color.alignment_color_bad
            else -> R.color.alignment_color_neutral
        }
    }
}

data class Image (
    @SerializedName ("url") val url: String
)

data class Biography (
    @SerializedName ("full-name") val fullName: String,
    @SerializedName ("alter-egos") val alterEgo: String,
    @SerializedName ("aliases") val aliases: List<String>?,
    @SerializedName ("place-of-birth") val birthPlace: String,
    @SerializedName("alignment") val alignment:String,
    @SerializedName ("first-appearance") val firstAppearance: String,
    @SerializedName ("publisher") val publisher: String,
)

data class Appearance (
    @SerializedName("race") val race:String,
)

data class Power (
    @JsonAdapter(IntegerAdapter::class) @SerializedName("intelligence") val int: Int,
    @JsonAdapter(IntegerAdapter::class) @SerializedName("strength") val str: Int,
    @JsonAdapter(IntegerAdapter::class) @SerializedName("speed") val sp: Int,
    @JsonAdapter(IntegerAdapter::class) @SerializedName("durability") val dur: Int,
    @JsonAdapter(IntegerAdapter::class) @SerializedName("power") val pow: Int,
    @JsonAdapter(IntegerAdapter::class) @SerializedName("combat") val com: Int,
)

data class Work (
    @SerializedName ("occupation") val occupation: String,
    @SerializedName ("base") val base: String,
)

class IntegerAdapter : TypeAdapter<Int>() {
    override fun write(out: JsonWriter?, value: Int) {
        out?.value(value)
    }

    override fun read(`in`: JsonReader?): Int {
        if (`in` != null) {
            val value: String = `in`.nextString()
            if (value != "null") {
                return value.toInt()
            }
        }
        return 0
    }
}

