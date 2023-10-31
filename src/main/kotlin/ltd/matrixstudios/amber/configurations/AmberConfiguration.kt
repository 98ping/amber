package ltd.matrixstudios.amber.configurations

import ltd.matrixstudios.amber.configurations.annotate.Intrinsic
import ltd.matrixstudios.amber.configurations.annotate.Path
import ltd.matrixstudios.amber.configurations.annotate.section.Section
import ltd.matrixstudios.amber.configurations.extension.default
import ltd.matrixstudios.amber.configurations.extension.key
import ltd.matrixstudios.amber.configurations.extension.pathway
import ltd.matrixstudios.amber.configurations.node.ConfigurationNode
import ltd.matrixstudios.amber.files.ResourceContainer
import ltd.matrixstudios.amber.files.yaml.YamlResourceContainer
import ltd.matrixstudios.amber.transformers.TransformerService
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class AmberConfiguration(
    parent: Class<*>,
    val container: ResourceContainer
) : InvocationHandler
{
    private val nodes: MutableMap<Method, ConfigurationNode> = mutableMapOf()

    init
    {
        val allMethods = parent.methods
            .filter {
                it.isAnnotationPresent(
                    Intrinsic::class.java
                ) || it.isAnnotationPresent(
                    Path::class.java
                )
            }

        if (allMethods.isEmpty())
        {
            println("[Amber] [Debug] Could not find any annotated methods in ${parent.simpleName}.")
        } else
        {
            for (method in allMethods)
            {
                nodes[method] =
                    ConfigurationNode(
                        method.pathway(),
                        method.default(),
                        method.pathway() == "",
                        method.key(),
                    )
            }
        }
    }

    fun initiallyLoadAllResources()
    {
        for (node in nodes)
        {
            val config = node.value

            container.set(
                config.getQualifiedPath(),
                config.default
            )
        }

        println("[Amber] [Debug] Loaded all resources because the file did not exist!")
    }

    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any?
    {
        val node = nodes[method]
            ?: throw IllegalArgumentException(
                "Unable to find a node for this method. Make sure that it is annotated!"
            )

        if (method == null)
        {
            throw IllegalArgumentException(
                "Unable to proceed because of a null method"
            )
        }

        val returnType = method.returnType

        if (method.isAnnotationPresent(Section::class.java))
        {
            val returnIsList = Collection::class.java.isAssignableFrom(returnType)

            if (!returnIsList)
            {
                throw IllegalArgumentException(
                    "In order to interpret sections, you need to return Collection<String>"
                )
            }

            val path = method.getDeclaredAnnotation(Section::class.java).sectionPath

            if (container !is YamlResourceContainer)
            {
                throw IllegalArgumentException(
                    "Cannot interpret a configuration section on a Non-YAML container."
                )
            }

            return container.mapping.getConfigurationSection(path).getKeys(false)
        }

        if (returnType.simpleName == String::class.simpleName)
        {
            return container.get(node.getQualifiedPath(), String::class.java) ?: method.name
        } else
        {
            if (TransformerService.exists(returnType))
            {
                val transformer = TransformerService.getTransformer(returnType)

                val obj = transformer.fromString(
                    container.get(
                        node.getQualifiedPath(),
                        String::class.java
                    ) as String
                )

                if (obj != null)
                {
                    return obj
                }
            }

            if (returnType.isPrimitive)
            {
                return container.get(node.getQualifiedPath(), returnType)
            }
        }

        return null
    }
}