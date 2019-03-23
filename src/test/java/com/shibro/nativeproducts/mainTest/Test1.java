package com.shibro.nativeproducts.mainTest;

public class Test1 {
    public static void main(String[] args) {
        test3();
    }
    public static void test3(){
        String s = "abcd";
        char [] characters = s.toCharArray();
        print(characters,0);
    }
    public static  void print(char [] chars,Integer index ){
        if(index<chars.length-1){
            for(int j = index+1;j<chars.length;j++){
                char temp = chars[index];
                chars[index] = chars[j];
                chars[j] = temp;
                print(chars,index+1);
                char temp2 = chars[index];
                chars[index] = chars[j];
                chars[j] = temp2;
            }
        }else if(index == chars.length-1){
            System.out.println(String.valueOf(chars));
        }else{
            return;
        }


    }
}
