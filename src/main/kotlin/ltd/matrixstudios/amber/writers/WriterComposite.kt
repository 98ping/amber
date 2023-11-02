package ltd.matrixstudios.amber.writers

import ltd.matrixstudios.amber.files.yaml.YamlResourceContainer
import ltd.matrixstudios.amber.writers.components.Key
import java.lang.Exception
import java.lang.reflect.Method

/**
 * Class created on 11/2/2023

 * @author 98ping
 * @project amber
 * @website https://solo.to/redis
 */
object WriterComposite
{
    fun handle(
        method: Method,
        container: YamlResourceContainer
    )
    {
        // Check existance of this annotation in node
        val writerDestination = method
            .getDeclaredAnnotation(Writer::class.java)
            .path
        
    }
}