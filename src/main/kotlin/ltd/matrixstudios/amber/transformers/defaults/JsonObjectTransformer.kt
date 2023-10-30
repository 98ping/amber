package ltd.matrixstudios.amber.transformers.defaults

import com.google.gson.JsonObject
import ltd.matrixstudios.amber.AmberConfigurationService
import ltd.matrixstudios.amber.transformers.Transformer

object JsonObjectTransformer : Transformer<JsonObject>
{
    override fun fromString(value: String): JsonObject?
    {
        return AmberConfigurationService.GSON.fromJson(
            value, JsonObject::class.java
        )
    }
}