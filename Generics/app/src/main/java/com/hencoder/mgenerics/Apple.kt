package com.hencoder.mgenerics

class Apple <T> {
    fun get(): Apple<T>{
        return this
    }

    fun <E> getAny(any:E):Apple<E>{
        return Apple()
    }
}