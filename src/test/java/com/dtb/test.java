package com.dtb;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
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

        Scanner sc = new Scanner(System.in);
        int n =sc.nextInt();
        char arr[][]=new char[n][];
        int sum[]=new int[n];
        for(int i=0;i<n;i++)
        {
            sum[i]=0;
            String s=sc.next();
            if (s.length()>6)
                s = s.substring(0,6);
            arr[i]=s.toCharArray();
            for(int a=0;a<arr[i].length;a++)
            {
                if((int)arr[i][a]-48>9)
                {
                    if(arr[i][a]=='A')
                    {
                        sum[i]+=10*Math.pow(16, arr[i].length-a-1);
                    }
                    else if(arr[i][a]=='B')
                    {
                        sum[i]+=11*Math.pow(16, arr[i].length-a-1);
                    }
                    else if(arr[i][a]=='C')
                    {
                        sum[i]+=12*Math.pow(16, arr[i].length-a-1);
                    }
                    else if(arr[i][a]=='D')
                    {
                        sum[i]+=13*Math.pow(16, arr[i].length-a-1);
                    }
                    else if(arr[i][a]=='E')
                    {
                        sum[i]+=14*Math.pow(16, arr[i].length-a-1);
                    }
                    else
                    {
                        sum[i]+=15*Math.pow(16, arr[i].length-a-1);
                    }
                }
                else
                {
                    sum[i]+=(arr[i][a]-48)*Math.pow(16,arr[i].length-a-1);
                }
            }
        }
        for(int i=0;i<n;i++)
        {
            //System.out.println(sum[i]);
            long b=0;
            int a=0;
            while(true)
            {
                b+=(long) ((sum[i]%8)*Math.pow(10,a));
                sum[i]=sum[i]/8;
                a=a+1;
                if(sum[i]==8)
                {
                    b+=(long) ((sum[i]/8)*Math.pow(10,a));
                    break;
                }
                else if(sum[i]<8)
                {
                    b+=(long) ((sum[i]%8)*Math.pow(10,a));
                    break;
                }
            }

            System.out.println(b);
        }



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



    @Test
    public void sixTeenToTen(){
        String text = "甲乙两个小朋友各有一袋糖，没带糖，不到20颗，如果甲给乙一定数量的糖后，甲的糖是乙的糖的2倍，如果乙给甲，同样数量的糖后，甲的糖就是乙的糖颗数的三倍。那么甲乙两个小朋友共有多少颗糖？";

        //短语提取
        List<String> phraseList = HanLP.extractPhrase(text, 100);
        System.out.println(phraseList);

        //关键词提取
        List<String> keywordList = HanLP.extractKeyword(text, 100);
        System.out.println(keywordList);

        //中文分词
        List<Term> termList = HanLP.segment(text);
        //System.out.println(termList.get(0).word);
        System.out.println(termList);

//        Suggester suggester = new Suggester();
//        String[] titleArray =
//                (
//                        "威廉王子发表演说 呼吁保护野生动物\n" +
//                                "《时代》年度人物最终入围名单出炉 普京马云入选\n" +
//                                "“黑格比”横扫菲：菲吸取“海燕”经验及早疏散\n" +
//                                "日本保密法将正式生效 日媒指其损害国民知情权\n" +
//                                "英报告说空气污染带来“公共健康危机”"
//                ).split("\\n");
//        for (String title : titleArray)
//        {
//            suggester.addSentence(title);
//        }
//
//        System.out.println(suggester.suggest("发言", 1));       // 语义
//        System.out.println(suggester.suggest("危机公共", 1));   // 字符
//        System.out.println(suggester.suggest("mayun", 1));      // 拼音
    }

    public int quickSelect(int a[], int l, int r, int k) {
        Random rand = new Random();
        int p = rand.nextInt(r - l + 1) + l;
        int x = a[p];
        int tmp = a[p];
        a[p] = a[r];
        a[r] = tmp;
        int i = l, j = r;
        while (i < j) {
            while (i < j && a[i] < x) i++;
            if (i < j) {
                a[j] = a[i];
                j--;
            }
            while (i < j && a[j] > x) j--;
            if (i < j) {
                a[i] = a[j];
                i++;
            }
        }
        a[i] = x;
        p = i;
        if (i - l + 1 == k) return a[i];
        if (i - l + 1 < k) return quickSelect(a, i + 1, r, k); //填空
        else return quickSelect(a, l, i - 1, k);
    }

    @Test
    public void quick() {
        int[] a = {1, 4, 2, 8, 5, 7};
        System.out.println(quickSelect(a, 0, 5, 4));
    }
}
