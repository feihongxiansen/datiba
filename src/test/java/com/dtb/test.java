package com.dtb;

import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author：lmx
 * @Description：
 * @Date：Created on 21:40 2019/2/28.
 * @ModifyBy：
 */
public class test {

    public static void main(String args[]) throws Exception{
//        long f[] = new long[1000001];
//
//        Scanner sc = new Scanner(System.in);
//        int input = sc.nextInt();
//
//        f[1] = 1;
//        f[2] = 1;
//
//        for (int i=3;i<=input;i++){
//            f[i] = (f[i-1]+f[i-2])%10007;
//        }
//
//
//        System.out.println(f[input]);
//
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int i = 0;
//        int arr[] = new int[n+1];
//        while (i<=n){
//            arr[i++] = sc.nextInt();
//        }
//
//
//        System.out.println(Arrays.toString(arr));


    }


    @Test
    public void area(){
        Scanner sc = new Scanner(System.in);
        double r = sc.nextDouble();
        double PI = Math.atan(1.0)*4;
        System.out.printf("%.7f",PI*r*r);
    }

    @Test
    public void sum(){
        long res = 0;
        long i = 1000000000;
        res = i*(1+i)/2;
        System.out.println(res);
    }

    @Test
    public void scanner(){
        int n = 5;
        int arr[] = {8,3,6,4,9};
        int min;
        for (int i=0; i<n; i++){
            min = i;
            for (int j=i; j<n;j++){
                if (arr[min] >arr[j]){
                    int temp = arr[j];
                    arr[j] = arr[min];
                    arr[min] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
