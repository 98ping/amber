package test

import ltd.matrixstuidos.amber.configurations.annotate.EntryName
import ltd.matrixstuidos.amber.configurations.annotate.Path
import ltd.matrixstuidos.amber.configurations.annotate.primitives.DefaultString
import ltd.matrixstuidos.amber.registry.AutoRegister

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