package ltd.matrixstuidos.amber.reflections

import ltd.matrixstuidos.amber.AmberConfigurationService
import ltd.matrixstuidos.amber.configurations.AmberConfiguration
import ltd.matrixstuidos.amber.registry.AutoRegister
import org.reflections.Reflections
import org.reflections.scanners.Scanners
import org.reflections.util.ConfigurationBuilder

/**
 * Class created on 10/28/2023

 * @author 98ping
 * @project amber
 * @website https://solo.to/redis
 */
object AnnotationScanner {
    private val reflections: Reflections = Reflections(
        ConfigurationBuilder()
            .forPackage(
                AmberConfigurationService.parentConfiguration.parentPackage,
                this::class.java.classLoader
            )
            .addScanners(
                Scanners.MethodsAnnotated,
                Scanners.TypesAnnotated,
                Scanners.SubTypes
            )
    )

    fun target(
        annotation: Class<out Annotation>
    ): MutableList<Class<*>> = reflections
        .getTypesAnnotatedWith(annotation)
        .filter {
            it.isInterface
        }.toMutableList()
}