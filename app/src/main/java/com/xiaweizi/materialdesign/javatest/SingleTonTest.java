package com.xiaweizi.materialdesign.javatest;

/**
 * Created by lwz on 2018/5/25.
 */

public class SingleTonTest {
    /**
     * 饿汉式单例
     */
   /* private final static SingleTonTest singleTonTest = new SingleTonTest();

    private SingleTonTest() {
    }

    public static SingleTonTest getSingleTonTest() {
        return singleTonTest;
    }*/

    /**
     * 懒汉式单例
     */
    /*private static SingleTonTest tonTest;
    private SingleTonTest(){}
    public static SingleTonTest getTonTest(){
        if (tonTest == null){
            tonTest = new SingleTonTest();
        }
        return tonTest;
    }
*/

    /**
     * 双重校验锁  推荐使用的单例模式
     */
//    private static volatile SingleTonTest singleTonTest;
//
//    private SingleTonTest() {
//    }
//
//    public static SingleTonTest getSingleTonTest() {
//        if (singleTonTest == null) {
//            synchronized (SingleTonTest.class) {
//                if (singleTonTest == null) {
//                    singleTonTest = new SingleTonTest();
//                }
//            }
//        }
//        return singleTonTest;
//    }

//    private static volatile SingleTonTest singleTonTest;
//    private SingleTonTest(){}
//    public static SingleTonTest getSingleTest(){
//        if (singleTonTest == null){
//            synchronized(SingleTonTest.class){
//                if (singleTonTest == null){
//                    singleTonTest = new SingleTonTest();
//                }
//            }
//        }
//        return singleTonTest;
//    }

    private static SingleTonTest singleTonTest = new SingleTonTest();
    private SingleTonTest(){}
    public static SingleTonTest getSingleTest(){
        return singleTonTest;
    }
}
