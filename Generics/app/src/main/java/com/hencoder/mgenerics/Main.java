package com.hencoder.mgenerics;

import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Main {
    public static void main(String[] args) {
        ArrayList<? super AppCompatTextView> list = new ArrayList<TextView>();
    }

    public <T extends Runnable & Serializable> void someMethod(T param) {

    }

    public <T> void merge(T item, List<T> list) {

    }

}
