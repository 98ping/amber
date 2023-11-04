package ltd.matrixstudios.amber.writers

import ltd.matrixstudios.amber.files.yaml.YamlResourceContainer
import ltd.matrixstudios.amber.writers.components.Body
import ltd.matrixstudios.amber.writers.components.Key
import java.lang.Exception
import java.lang.reflect.Method

/**
 * Class created on 11/2/2023

 * @author 98ping
 * @project amber
 * @website https://solo.to/redis
 */
object WriterComposite {
    fun handle(
        method: Method,
        args: Array<out Any>,
        container: YamlResourceContainer
    ) {
        // Check existence of this annotation in node
        val writerDestination = method
            .getDeclaredAnnotation(Writer::class.java)

        // Discriminate parameters to ensure people don't
        // supply wrong arguments
        if (method.parameters.isEmpty()) {
            throw IllegalArgumentException(
                "You must have a parameter in order to set a value"
            )
        }

        if (method.parameters.size > 2) {
            throw IllegalArgumentException(
                "Please do not provide more than 2 arguments!"
            )
        }

        // Iterate through
        var keyIndex: Int = -1

        for (index in method.parameters.indices)
        {
            if (method.parameters[index].isAnnotationPresent(Key::class.java))
            {
                keyIndex = index
            }
        }

        var keyId: String = method.parameters.first().name

        if (keyIndex != -1) {
            keyId = args[keyIndex]
                .toString()
        }

        val bodyParameter = method.parameters
            .firstOrNull { it.isAnnotationPresent(Body::class.java) }
            ?: throw IllegalArgumentException(
                "You must provide a body to set inside of the config! Annotate this param with @Body."
            )

        val bodyLocation =
            method.parameters.indexOf(bodyParameter)

        container.set(
            if (writerDestination.intrinsic) keyId else "${writerDestination.path}.$keyId",
            args[bodyLocation]
        )

        println("[Amber] [Debug] Handled a set operation for the key $keyId.")
    }
}