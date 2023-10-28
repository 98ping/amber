package ltd.matrixstudios.amber

import ltd.matrixstudios.amber.configurations.AmberConfiguration
import ltd.matrixstudios.amber.files.ResourceContainerService
import ltd.matrixstudios.amber.files.yaml.YamlResourceContainer
import ltd.matrixstudios.amber.internals.InternalAmberConfiguration
import ltd.matrixstudios.amber.registry.AutomaticRegistrationService
import java.io.File
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Proxy
import kotlin.properties.Delegates

object AmberConfigurationService
{
    lateinit var parentConfiguration: InternalAmberConfiguration
    var debugMode by Delegates.notNull<Boolean>()

    fun make(
        path: String,
        parentPackage: String,
        debug: Boolean
    ) : InternalAmberConfiguration
    {
        parentConfiguration = InternalAmberConfiguration(
            path,
            parentPackage
        )

        debugMode = debug
        AutomaticRegistrationService.onInitialScan()

        return parentConfiguration
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> from(
        interfaceClass: Class<T>,
        fileName: String
    ) : T
    {
        val container = YamlResourceContainer(File(parentConfiguration.path + "/$fileName"))

        val config = AmberConfiguration(
            interfaceClass,
            container
        )

        val loaded = config.container.load()

        if (!loaded)
        {
            config.initiallyLoadAllResources()
        }

        return buildProxyInstance(
            interfaceClass,
            config
        ).apply {
            ResourceContainerService.bind(interfaceClass, container)
        } as T
    }

    private fun buildProxyInstance(clazz: Class<*>, invocationHandler: InvocationHandler) = Proxy
        .newProxyInstance(
            clazz.classLoader,
            arrayOf(clazz),
            invocationHandler
        )
}