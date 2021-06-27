# Kotlin adapters
Simple type conversion using Kotlin extension functions

Converting objects of type `CoolType` to `EvenCoolerType` can be as simple as that:

```kotlin
val coolObject: CoolType = ...
val evenCoolerObject = coolObject.adaptTo(EvenCoolerType::class)
```

In [adapters.kt](https://github.com/mscharhag/kotlin-adapters/blob/master/src/main/kotlin/com.mscharhag.adapters/adapters.kt) you find all code required. In [example-usage.kt](https://github.com/mscharhag/kotlin-adapters/blob/master/src/main/kotlin/com.mscharhag.adapters/example-usage.kt) you can see some more usages and example adapter implementations.

See my blog post [Kotlin: Type conversion with adapters](https://www.mscharhag.com/kotlin/type-conversion-with-adapters)


