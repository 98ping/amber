package ltd.matrixstudios.amber.transformers

import ltd.matrixstudios.amber.transformers.defaults.JsonObjectTransformer
import ltd.matrixstudios.amber.transformers.defaults.URLTransformer

object TransformerService
{
    private val transformers: MutableMap<Class<*>, Transformer<*>> = mutableMapOf()

    fun loadDefaults()
    {
        listOf(
            JsonObjectTransformer,
            URLTransformer
        ).forEach {
            register(it)
        }
    }

    fun register(
        transformer: Transformer<*>
    )
    {
        transformers[transformer::class.java] = transformer
    }

    fun exists(clazz: Class<*>) : Boolean =
        transformers.containsKey(clazz)

    fun getTransformer(clazz: Class<*>) : Transformer<*> =
        transformers[clazz]
            ?: throw IllegalArgumentException(
                "Transformer for this class was not found. In order to use custom return types, you need a transformer!"
            )
}