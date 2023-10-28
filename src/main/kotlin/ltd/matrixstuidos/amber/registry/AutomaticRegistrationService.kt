package ltd.matrixstuidos.amber.registry

import ltd.matrixstuidos.amber.AmberConfigurationService
import ltd.matrixstuidos.amber.reflections.AnnotationScanner

/**
 * Class created on 10/28/2023

 * @author 98ping
 * @project amber
 * @website https://solo.to/redis
 */
object AutomaticRegistrationService {
    private val registeredConfigurations: MutableMap<Class<*>, Any> = mutableMapOf()

    fun onInitialScan()
    {
        val classes = AnnotationScanner.target(AutoRegister::class.java)

        for (clazz in classes)
        {
            val createdConfiguration =
                    AmberConfigurationService.from(
                        clazz, "${clazz.simpleName}.yaml"
                    )

            registeredConfigurations[clazz] = createdConfiguration
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> get(
        clazz: Class<*>
    ) : T
    {
        val mappedValue = registeredConfigurations[clazz]
            ?: throw IllegalArgumentException(
                "Unable to fetch this configuration because it does not exist!"
            )

        return mappedValue as T
    }
}