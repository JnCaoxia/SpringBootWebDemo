package com.caox.concurrent;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/11/21 17:32
 */
public class StaticVariableTest1 {
    private static StaticVariableTest1 obj = new StaticVariableTest1();
    private static int counter1 = 2;
    private static int counter2 = 0;

    private StaticVariableTest1(){
        counter1++;
        counter2++;
    }

    public static StaticVariableTest1 getInstance(){
        return obj;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        StaticVariableTest1 obj = StaticVariableTest1.getInstance();
        System.out.println("obj.counter1=="+obj.counter1);
        System.out.println("obj.counter2=="+obj.counter2);
    }

}
