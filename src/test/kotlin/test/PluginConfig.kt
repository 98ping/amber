package test

import ltd.matrixstudios.amber.configurations.annotate.primitives.DefaultString
import ltd.matrixstudios.amber.configurations.annotate.EntryName
import ltd.matrixstudios.amber.configurations.annotate.Intrinsic
import ltd.matrixstudios.amber.configurations.annotate.Path
import ltd.matrixstudios.amber.configurations.annotate.primitives.DefaultBoolean
import ltd.matrixstudios.amber.configurations.annotate.primitives.DefaultInteger
import ltd.matrixstudios.amber.configurations.annotate.section.Section
import ltd.matrixstudios.amber.writers.Writer
import ltd.matrixstudios.amber.writers.components.Body
import ltd.matrixstudios.amber.writers.components.Key
import java.net.URL

interface PluginConfig
{
    @EntryName("joe")
    @Path("customers")
    @DefaultString("awesome")
    fun onJoe() : String

    @EntryName("joeSatisfactions")
    @Path("customers")
    @DefaultInteger(100)
    fun onJoeSatisfaction() : Int

    @EntryName("joeIsCool")
    @Intrinsic
    @DefaultBoolean(true)
    fun onJoeIsCool() : Boolean

    @EntryName("joesWebsite")
    @Intrinsic
    @DefaultString("https://youtube.com")
    fun onJoeWebsite() : URL

    @Writer("customers.setOperation")
    @Intrinsic
    fun onSetJoeOperation(@Key key: String, @Body joeCount: Int) : Boolean

    // ensure amber understands the section.
    @Intrinsic
    @Section("reviews")
    fun onSection() : Collection<String>
}