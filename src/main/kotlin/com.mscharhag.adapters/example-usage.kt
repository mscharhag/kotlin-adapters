package com.mscharhag.adapters

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import kotlin.reflect.KClass

data class A(val a: String)
data class B(val b: String)
data class Person(val name: String, val age: Int)

// converts instances of A to B
class AtoBAdapter : Adapter {
    override fun <T : Any> canAdapt(from: Any, to: KClass<T>) = from is A && to == B::class
    override fun <T : Any> adaptTo(from: Any, to: KClass<T>): T {
        require(canAdapt(from, to))
        val a = from as A
        return B(a.a) as T
    }
}

// converts json strings to objects using Jackson
class JsonToObjectAdapter : Adapter {
    private val objectMapper = ObjectMapper().registerModule(KotlinModule())

    override fun <T : Any> canAdapt(from: Any, to: KClass<T>) = from is String

    override fun <T : Any> adaptTo(from: Any, to: KClass<T>): T {
        require(canAdapt(from, to))
        return objectMapper.readValue(from as String, to.java)
    }
}

fun main() {

    // register adapters
    adapters.add(AtoBAdapter())
    adapters.add(JsonToObjectAdapter())

    // convert A to B
    val a = A("foo")
    val b = a.adaptTo<B>()
    println("converted $a to $b")

    // convert json string to Person object
    val json = """
        {
            "name": "John",
            "age" : 42
        }
    """.trimIndent()

    val person = json.adaptTo<Person>()
    println("converted $json to $person")
}

