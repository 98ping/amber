package ltd.matrixstuidos.amber.files.yaml

import ltd.matrixstuidos.amber.files.ResourceContainer
import org.simpleyaml.configuration.file.YamlFile
import java.io.File

class YamlResourceContainer(
    destination: File,
) : ResourceContainer
{
    var mapping: YamlFile = YamlFile(destination)

    override fun load() : Boolean
    {
        var existed = true

        if (!mapping.exists())
        {
            mapping.createNewFile()
            existed = false
        }

        mapping.load()

        return existed
    }


    override fun set(path: String, value: Any)
    {
        mapping.set(
            path,
            value
        )

        mapping.save()
    }

    override fun get(path: String, type: Class<*>): Any?
    {
        when (type)
        {
            String::class.java ->
            {
                return mapping.getString(path)
            }

            Int::class.java ->
            {
                return mapping.getInt(path)
            }

            Integer::class.java ->
            {
                return mapping.getInt(path)
            }

            Double::class.java ->
            {
                return mapping.getDouble(path)
            }

            Long::class.java ->
            {
                return mapping.getLong(path)
            }

            Boolean::class.java ->
            {
                return mapping.getBoolean(path)
            }
        }

        return null
    }
}