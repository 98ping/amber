package ltd.matrixstudios.amber.transformers

import com.google.gson.JsonObject
import ltd.matrixstudios.amber.transformers.defaults.JsonObjectTransformer
import ltd.matrixstudios.amber.transformers.defaults.URLTransformer
import java.net.URL

object TransformerService
{
    private val transformers: MutableMap<Class<*>, Transformer<*>> = mutableMapOf()

    fun loadDefaults()
    {
        register(URL::class.java, URLTransformer)
        register(JsonObject::class.java, JsonObjectTransformer)
    }

    fun register(
        clazz: Class<*>,
        transformer: Transformer<*>
    )
    {
        transformers[clazz] = transformer
    }

    fun exists(clazz: Class<*>) : Boolean =
        transformers.containsKey(clazz)

    fun getTransformer(clazz: Class<*>) : Transformer<*> =
        transformers[clazz]
            ?: throw IllegalArgumentException(
                "Transformer for this class was not found. In order to use custom return types, you need a transformer!"
            )
}