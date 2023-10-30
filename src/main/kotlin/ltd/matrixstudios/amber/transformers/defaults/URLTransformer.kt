package ltd.matrixstudios.amber.transformers.defaults

import ltd.matrixstudios.amber.transformers.Transformer
import java.net.URL

object URLTransformer : Transformer<URL>
{
    override fun fromString(value: String): URL
    {
       return URL(value)
    }
}