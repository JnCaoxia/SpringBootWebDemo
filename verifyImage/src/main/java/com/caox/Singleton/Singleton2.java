package com.caox.Singleton;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/9/2 9:45
 * 饿汉式
 * 在定义类的静态私有变量同时进行实例化
 */
public class Singleton2 {
    private static final Singleton2 singleton2 = new Singleton2();

    private Singleton2(){

    }

    public static Singleton2 getSingleton(){
        return singleton2;
    }

}
