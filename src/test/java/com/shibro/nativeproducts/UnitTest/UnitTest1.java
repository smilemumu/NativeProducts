package com.shibro.nativeproducts.UnitTest;


import java.util.*;

public class UnitTest1 {
    public static void main(String[] args) {
        test2();
    }
    public void test1(){
        List<? extends Number> foo4 = new ArrayList<Integer>();
        Number number = foo4.get(0);
    }

    /**
     * map  value排序
     */
    public static void test2(){
        HashMap<String,Integer> map = new HashMap<>();
        map.put("a",1);
        map.put("b",5);
        map.put("c",7);
        map.put("d",6);
        List<Map.Entry<String,Integer>> list = new ArrayList<>(map.entrySet());
//        Collections.sort(list, new Comparator<Map.Entry<String,Integer>>() {
//            public int compare(Map.Entry<String,Integer> o1,
//                               Map.Entry<String,Integer> o2) {
//                return o2.getValue()-o1.getValue();
//            }
//        });
        Collections.sort(list, (o1, o2) -> o2.getValue()-o1.getValue());
        System.out.println("max="+list.get(0).getValue());
        System.out.println("maxKey="+list.get(0).getKey());
    }

}
