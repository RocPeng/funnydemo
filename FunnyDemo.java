package com.ambition.test;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by roc_peng on 2017/7/31.
 * Email 18817353729@163.com
 * Url https://github.com/RocPeng/
 * Description 这个世界每天都有太多遗憾,所以你好,再见!
 */

public class FunnyDemo {
    public static void main(String[] args) {
        //1.使用for循环、while循环和递归写出3个函数来计算给定数列的总和。
        int[] arr = {2,4,6,77,89,12};
        System.out.println("for:"+forTest(arr));
        System.out.println("while:"+whileTest(arr));
        System.out.println("recursion:"+recursionTest(arr));
        System.out.println("**************************************");
        //2.编写一个交错合并列表元素的函数。例如：给定的两个列表为[a，B，C]和[1，2，3]，函数返回[a，1，B，2，C，3]。
        Object[] arr1 = {'a','b','c'};
        Object[] arr2 = {1,2,3,4,5};
        merge(arr1,arr2);
        System.out.println("**************************************");
        //3.编写一个计算前100位斐波那契数的函数。根据定义，斐波那契序列的前两位数字是0和1，随后的每个数字是前两个数字的和。
        // 例如，前10位斐波那契数为：0，1，1，2，3，5，8，13，21，34。
//        System.out.println("fibonacci:"+fibonacci(100));
        System.out.println("**************************************");
        //4.编写一个能将给定非负整数列表中的数字排列成最大数字的函数。例如，给定[50，2，1,9]，最大数字为95021。
        int[] arr3 = {1,9,678,53};//9678531
        System.out.println("maxNum:"+maxNum(arr3));
        System.out.println("**************************************");
        //5.编写一个在1，2，…，9（顺序不能变）数字之间插入+或-或什么都不插入，使得计算结果总是100的程序，并输出所有的可能性。
        // 例如：1 + 2 + 34 – 5 + 67 – 8 + 9 = 100。
        hundred();
    }
    //1.
    public static int forTest(int[] arr){
        int sum=0;
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
        }
        return sum;
    }

    public static int whileTest(int[] arr){
        int sum=0;
        int i=0;
        while(i<arr.length){
            sum+=arr[i];
            i++;
        }
        return sum;
    }

    public static int recursionTest(int[] arr){
        if(arr.length==1){
            return arr[0];
        }
        int[] remove = ArrayUtils.remove(arr, 0);
        return arr[0]+recursionTest(remove);
    }

    //2.
    public static long fibonacci(int index){
        if(index==1){
            return 0;
        }
        if(index==2){
            return 1;
        }
        return fibonacci(index-1)+fibonacci(index-2);
    }

    //3
    public static Object[] merge(Object[] arr1,Object[] arr2){
        //构造3个数组  分别是 长度相同的arr1,arr2 以及最终剩下的数据
        Object[] cp1;
        Object[] cp2;
        Object[] merge;
        Object[] left;
        int minLength = arr1.length<arr2.length?arr1.length:arr2.length;
        cp1=Arrays.copyOf(arr1,minLength);
        cp2=Arrays.copyOf(arr2,minLength);
        merge = new Object[minLength*2];
        for(int i=0;i<minLength*2;i++){
            Object obj;
            if(i%2==0){
                obj = cp1[i/2];
            }else{
                obj = cp2[i/2];
            }
            merge[i]=obj;
        }
        System.out.println(merge);
        if(arr1.length>arr2.length){
            left = Arrays.copyOfRange(arr1,minLength,arr1.length);
        }else{
            left = Arrays.copyOfRange(arr2,minLength,arr2.length);
        }
        merge=ArrayUtils.addAll(merge,left);
        return merge;
    }
    //4
    public static String maxNum(int[] arr){
//        int[] arr3 = {1,9,678,53};//9678531
        String str="";
        int[] arr1 = Arrays.copyOf(arr,arr.length);
        toSingleNum(arr1);
        int[] arr2 = Arrays.copyOf(arr1,arr1.length);
        Arrays.sort(arr1);
        //arr1排序之后的数组  arr2未排序 index为排序序号
        for(int i=0;i<arr.length;i++){
            int index=ArrayUtils.indexOf(arr2,arr1[i]);
            str = String.valueOf(arr[index])+str;
        }
        return str;
    }
    //将整数数组的所有数据转换成 只有 最高位的新数组 数据
    public static void toSingleNum(int[] arr){
        for(int i=0;i<arr.length;i++){
            int num = arr[i];
            while(num>10){
                num/=10;
            }
            arr[i]=num;
        }
    }
    //5
    public static void hundred(){
        List<Integer> number = new ArrayList<>();
        Collections.addAll(number,1,2,3,4,5,6,7,8,9);
        List<Integer> temp = new ArrayList<>();
        List<List<Integer>> listAll = new ArrayList<>();
        tree(number,temp,listAll);
        //对所有情况进行
        //true代表+ false代表-
//        List<Integer> integers = listAll.get(0);
//        List<Boolean> operator = new ArrayList<>();
//        List<List<Boolean>> operAll = new ArrayList<>();
//        operation(integers,operator,operAll);
        //存储结果是100的数字list 和 运算符号list
        List<List<Integer>> finalInteger = new ArrayList<>();
        List<List<Boolean>> finalOperator = new ArrayList<>();
        for(List<Integer> list : listAll){
            //operAll : 这组integer所有的运算符号 可能性 集合
            List<Boolean> operator = new ArrayList<>();
            List<List<Boolean>> operAll = new ArrayList<>();
            operation(list,operator,operAll);
            //计算结果是100的运算法则
            for(List<Boolean> oper : operAll){
                int sum = list.get(0);
                for(int i=0;i<oper.size();i++){
                    if(oper.get(i)){
                        sum+=list.get(i+1);
                    }else{
                        sum-=list.get(i+1);
                    }
                }
                if(sum==100){
                    finalInteger.add(list);
                    finalOperator.add(oper);
                }
            }
        }
        //
        for(int i=0;i<finalInteger.size();i++){
            List<Integer> integers = finalInteger.get(i);
            List<Boolean> booleans = finalOperator.get(i);
            System.out.print(integers.get(0));
            for(int j=0;j<booleans.size();j++){
                System.out.print(booleans.get(j)?"+":"-");
                System.out.print(integers.get(j+1));
            }
            System.out.println();
        }
    }

    /**
     *
     * @param list 1到9
     * @param temp 新的数组 也就是所有的排列组合情况
     * @param listAll temp的集合
     * @return
     */
    public static void tree(List<Integer> list,List<Integer> temp,List<List<Integer>> listAll){
        if(list.size()==0){
            listAll.add(temp);
            return;
        }
        //倍率
        for(int i=0;i<list.size();i++){
            List<Integer> list1 = new ArrayList<>(list);
            List<Integer> temp1 = new ArrayList<>(temp);
            int sum=0;
            int power = 1;
            for(int j=0;j<=i;j++){
                sum+=list1.get(j)*Math.pow(10,(i-j));
            }
            temp1.add(sum);
            for(int j=0;j<=i;j++){
                list1.remove(0);
            }
            tree(list1,temp1,listAll);
        }
    }
    /*public static void tree(List<Integer> list,List<Integer> temp,List<List<Integer>> listAll){
        if(list.size()==1){
            temp.add(list.get(0));
            listAll.add(temp);
            return;
        }
        if(list.size()==0){
            listAll.add(temp);
            return;
        }
        List<Integer> list1 = new ArrayList<>(list);
        List<Integer> list2 = new ArrayList<>(list);
        List<Integer> temp1 = new ArrayList<>(temp);
        List<Integer> temp2 = new ArrayList<>(temp);

        temp1.add(list1.get(0));
        list1.remove(0);
        tree(list1,temp1,listAll);
        temp2.add(10*list2.get(0)+list2.get(1));
        list2.remove(1);
        list2.remove(0);
        tree(list2,temp2,listAll);
    }*/

    /**
     * 对于一个整形数组,生成所有可能的加减方法
     * @param list
     * @param operator
     * @param listAll
     */
    public static void operation(List<Integer> list,List<Boolean> operator,List<List<Boolean>> listAll){
        if(operator.size()==list.size()-1){
            listAll.add(operator);
            return;
        }
        List<Boolean> operator1 = new ArrayList<>(operator);
        List<Boolean> operator2 = new ArrayList<>(operator);
        operator1.add(true);
        operation(list,operator1,listAll);
        operator2.add(false);
        operation(list,operator2,listAll);
    }
}
