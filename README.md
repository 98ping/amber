# <img width="253" alt="amberLogo" src="https://github.com/98ping/amber/assets/67809373/1693ad30-1cdd-49fd-88e2-70c384391a91">
# Amber
Amber is an annotation-based YAML library that utilizes interfaces to make clean configuration files.

# Usage

To initialize amber, you need to use the function
```kotlin
AmberConfigurationService.make(path, debugMode)
```
The path would be the file location of where you want the YAML file to be stored. 

To accept a new configuration into the service, you must make an interface and annotate it properly. A properly-annotated interface looks something like this:
```kotlin
interface CustomersConfig
{
    @EntryName("bob")
    @Path("customers")
    @DefaultString("Awesome Guy")
    fun onCustomerBob() : String

    @EntryName("bobVisits")
    @Path("customers")
    @DefaultInteger(100)
    fun onBobVisitCheck() : Int
}
```

Once you have setup this portion of the config, you can use the function:
```kotlin
AmberConfigurationService.from(CustomersConfig::class.java, "yourFileName.yaml" )
```
This function returns the interface so you can pull your values directly from the config.
