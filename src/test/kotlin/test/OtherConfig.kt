package test

import ltd.matrixstudios.amber.configurations.annotate.EntryName
import ltd.matrixstudios.amber.configurations.annotate.Path
import ltd.matrixstudios.amber.configurations.annotate.primitives.DefaultString
import ltd.matrixstudios.amber.registry.AutoRegister

/**
 * Class created on 10/28/2023

 * @author 98ping
 * @project amber
 * @website https://solo.to/redis
 */
@AutoRegister
interface OtherConfig
{
    @EntryName("bar")
    @Path("foo")
    @DefaultString("howdy")
    fun onFooBar() : String
}