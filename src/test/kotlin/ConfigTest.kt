import ltd.matrixstuidos.amber.AmberConfigurationService
import ltd.matrixstuidos.amber.files.ResourceContainerService
import org.junit.jupiter.api.Test

class ConfigTest
{
    @Test
    fun test2()
    {
        val config = AmberConfigurationService.make(
            "C:\\Users\\User\\Desktop\\amber",
            true
        )

        val joeConfig = AmberConfigurationService.from(
            PluginConfig::class.java, "test.yaml"
        )
        val rawContainer = ResourceContainerService.get(PluginConfig::class.java)

        println("Raw fetch: ${rawContainer.get("customers.joeSatisfactions", Integer::class.java)}")

        println(joeConfig.onJoe())
        println(joeConfig.onJoeSatisfaction())
        println(joeConfig.onJoeIsCool())
    }
}