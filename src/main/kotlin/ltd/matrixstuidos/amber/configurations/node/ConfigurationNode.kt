package ltd.matrixstuidos.amber.configurations.node

data class ConfigurationNode(
    val path: String,
    val default: Any,
    val intrinsic: Boolean,
    val key: String,
)
{
    fun getQualifiedPath() : String
    {
        return if (intrinsic)
        {
            key
        } else
        {
            "$path.$key"
        }
    }
}