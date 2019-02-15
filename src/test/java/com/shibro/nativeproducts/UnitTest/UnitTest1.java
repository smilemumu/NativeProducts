package com.shibro.nativeproducts.UnitTest;

import java.util.ArrayList;
import java.util.List;

public class UnitTest1 {
    public static void main(String[] args) {
        List<? extends Number> foo4 = new ArrayList<Integer>();
        Number number = foo4.get(0);
    }
}
