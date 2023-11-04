package test

import ltd.matrixstudios.amber.AmberConfigurationService
import ltd.matrixstudios.amber.files.ResourceContainerService
import ltd.matrixstudios.amber.registry.AutomaticRegistrationService
import org.junit.jupiter.api.Test

class ConfigTest
{
    @Test
    fun test2()
    {
        val config = AmberConfigurationService.make(
            "C:\\Users\\User\\Desktop\\amber",
            "test",
            true
        )

        val joeConfig = AmberConfigurationService.from(
            PluginConfig::class.java, "test.yaml"
        )
        val rawContainer = ResourceContainerService.get(PluginConfig::class.java)
        val fetched = AutomaticRegistrationService.get<OtherConfig>(OtherConfig::class.java)

        println(fetched.onFooBar())

        println("Raw fetch: ${rawContainer.get("customers.joeSatisfactions", Integer::class.java)}")

        println(joeConfig.onJoe())
        println(joeConfig.onJoeSatisfaction())
        println(joeConfig.onJoeIsCool())
        println(joeConfig.onJoeWebsite().host)

        joeConfig.onSetJoeOperation("awesomeJoe", 122)

        val reviews = joeConfig.onSection()

        reviews.forEach {
            println(it)
        }
    }
}