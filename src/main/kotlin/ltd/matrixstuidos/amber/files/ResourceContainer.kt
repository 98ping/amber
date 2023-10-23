package ltd.matrixstuidos.amber.files

interface ResourceContainer
{
    fun load() : Boolean
    fun set(path: String, value: Any)
    fun get(path: String, type: Class<*>) : Any?
}