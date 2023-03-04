package com.xl.demo.utils;

import java.util.Arrays;
import java.util.List;

/**
 * @author XL
 * @date 2022/1/15 14:33
 */
public class TestUtils<T> {

    public static void main(String[] args) {
//        int[] arr = {23,1,34,2,6};
//        sort(arr);
        StringBuffer str = new StringBuffer("3342");
        str.append("121212");
        System.out.println(str);
    }

    public static void test1(){
        Arrays.asList(1,34,6).forEach(e -> System.out.println(e));
    }

    public static <T> void show(List<T> list){
        for(T t:list){
            System.out.println(t);
        }
    }

    public static <T> void test2(T data){
        System.out.println(data);

    }

    public static void sort(int[] arr){
        for (int i=0;i<arr.length;i++){
            for (int j=0;j<arr.length-1-i;j++){
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        for (int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }


}
