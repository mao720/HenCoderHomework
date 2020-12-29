package com.hencoder.mgenerics;

interface Eater<T extends Food> {
    void eat(T food);
}
