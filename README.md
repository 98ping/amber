# <img width="253" alt="amberLogo" src="https://github.com/98ping/amber/assets/67809373/a039d8ae-bcde-4230-b1cc-efff45f18aff"> 
# Amber
Amber is an annotation-based YAML library that utilizes interfaces to make clean configuration files.

# Usage

To initialize amber, you need to use the function
```
AmberConfigurationService.make(path, debugMode)
```
The path would be the file location of where you want the YAML file to be stored. 

To accept a new configuration into the service, you must make an interface and annotate it properly. A properly-annotated interface looks something like this:
```
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
```
AmberConfigurationService.from(CustomersConfig::class.java, "yourFileName.yaml" )
```
This function returns the interface so you can pull your values directly from the config.
