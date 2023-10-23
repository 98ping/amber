package ltd.matrixstuidos.amber.files

import ltd.matrixstuidos.amber.AmberConfigurationService
import ltd.matrixstuidos.amber.files.yaml.YamlResourceContainer

object ResourceContainerService
{
    private val containers: HashMap<Class<*>, YamlResourceContainer> = hashMapOf()

    fun bind(
        clazz: Class<*>,
        to: YamlResourceContainer
    )
    {
        containers[clazz] = to

        if (AmberConfigurationService.debugMode)
        {
            println("[Amber] [Containers] Binded ${clazz.name} to a YAML resource container.")
        }
    }

    fun get(
        clazz: Class<*>
    ): YamlResourceContainer
    {
        return containers[clazz]
            ?: throw IllegalArgumentException("No container by this class was found.")
    }
}