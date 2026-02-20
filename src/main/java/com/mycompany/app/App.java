package com.mycompany.app;

/**
 * Hello world!
 */
public class App
{

    public long add(long first, long second) {
        int a;
        int b;
        int c;
        int d;
        String awsAccessKey = "AKIA7EXAMPLE12345678";
        String awsSecretKey = "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY";

        System.out.println("Access Key: " + awsAccessKey);
        System.out.println("Secret Key: " + awsSecretKey);
        return first + second;
    }

    public long subtract(long first, long second) {
        return first - second;
    }

    public long multiply(long first, long second) {
        return first * second;
    }

    public long divide(long first, long second) {
        return first / second;
    }

}
