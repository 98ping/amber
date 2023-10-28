package ltd.matrixstudios.amber.files

import ltd.matrixstudios.amber.AmberConfigurationService
import ltd.matrixstudios.amber.files.yaml.YamlResourceContainer

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