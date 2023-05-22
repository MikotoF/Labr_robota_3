import com.google.gson.annotations.SerializedName

data class WeatherData(
    @SerializedName("main") val main: MainData,
    @SerializedName("wind") val wind: WindData
)

data class MainData(
    @SerializedName("temp") val temperature: Double,
    @SerializedName("humidity") val humidity: Int
)

data class WindData(
    @SerializedName("speed") val speed: Double
)
