package com.savenkoff.study.task1;

public class MethodsClass {

    @Test
    void method1() {
        System.out.println("Method 1 called");
    }
    @Test(priority = 10)
    void method2() {
        System.out.println("Method 2 called");
    }

    static void staticMethod1() {
        System.out.println("Static Method 1 called");
    }

    @BeforeSuite
    static void staticMethod2() {
        System.out.println("Static method 2 called");
    }

    @AfterSuite
    static void staticMethod3() {
        System.out.println("Static method 3 called");
    }
}
