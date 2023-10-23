package ltd.matrixstuidos.amber.configurations.extension

import ltd.matrixstuidos.amber.configurations.annotate.primitives.DefaultString
import ltd.matrixstuidos.amber.configurations.annotate.EntryName
import ltd.matrixstuidos.amber.configurations.annotate.Path
import ltd.matrixstuidos.amber.configurations.annotate.primitives.DefaultInteger
import java.lang.reflect.Method

fun Method.pathway(): String
{
    if (this.isAnnotationPresent(Path::class.java))
    {
        return this.getDeclaredAnnotation(Path::class.java).directory
    }

    return ""
}

fun Method.default(): Any
{
    if (this.isAnnotationPresent(DefaultString::class.java))
    {
        return this.getDeclaredAnnotation(DefaultString::class.java).content
    }

    if (this.isAnnotationPresent(DefaultInteger::class.java))
    {
        return this.getDeclaredAnnotation(DefaultInteger::class.java).content
    }

    return this.name
}



fun Method.key(): String
{
    if (this.isAnnotationPresent(EntryName::class.java))
    {
        return this.getDeclaredAnnotation(EntryName::class.java).name
    }

    return this.name
}