package com.mscharhag.adapters

import kotlin.reflect.KClass

interface Adapter {
    fun <T : Any> canAdapt(from: Any, to: KClass<T>): Boolean
    fun <T : Any> adaptTo(from: Any, to: KClass<T>): T
}

val adapters = mutableListOf<Adapter>()

fun <F : Any, T : Any> F.adaptTo(targetType: KClass<T>): T {
    val adapter = adapters.find { it.canAdapt(this, targetType) }
            ?: throw NoSuitableAdapterFoundException(this, targetType)
    return adapter.adaptTo(this, targetType)
}

class NoSuitableAdapterFoundException(from: Any, to: KClass<*>)
    : Exception("No suitable adapter found to convert $from to type $to")
