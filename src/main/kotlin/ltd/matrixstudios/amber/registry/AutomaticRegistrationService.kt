package ltd.matrixstudios.amber.registry

/**
 * Class created on 10/28/2023

 * @author 98ping
 * @project amber
 * @website https://solo.to/redis
 */
object AutomaticRegistrationService {
    private val registeredConfigurations: MutableMap<Class<*>, Any> = mutableMapOf()

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