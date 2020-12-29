package com.hencoder.mgenerics;

import java.util.ArrayList;
import java.util.Collections;

class ReversableArrayList<T> extends ArrayList<T> {
    public void reverse(){
        Collections.reverse(this);
    }
}
