import ltd.matrixstuidos.amber.configurations.annotate.primitives.DefaultString
import ltd.matrixstuidos.amber.configurations.annotate.EntryName
import ltd.matrixstuidos.amber.configurations.annotate.Path
import ltd.matrixstuidos.amber.configurations.annotate.primitives.DefaultInteger

interface PluginConfig
{
    @EntryName("joe")
    @Path("customers")
    @DefaultString("awesome")
    fun onJoe() : String

    @EntryName("joeSatisfactions")
    @Path("customers")
    @DefaultInteger(100)
    fun onJoeSatisfaction() : Int
}