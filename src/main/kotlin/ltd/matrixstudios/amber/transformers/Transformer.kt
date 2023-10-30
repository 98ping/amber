package ltd.matrixstudios.amber.transformers

interface Transformer<T>
{
    fun fromString(value: String) : T?
}