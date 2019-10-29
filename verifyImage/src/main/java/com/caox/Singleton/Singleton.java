package com.caox.Singleton;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/9/2 9:43
 * 懒汉式
 */
public class Singleton {
    private static Singleton singleton = null;

    private Singleton() {
    }

    public static Singleton getSingleton(){
        if(singleton == null){
            singleton = new Singleton();
        }
        return  singleton;
    }

}
