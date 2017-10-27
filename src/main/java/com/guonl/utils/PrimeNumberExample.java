package com.guonl.utils;

public class PrimeNumberExample {

    public static boolean isPrime(long n) {
       
        if(n > 2 && (n & 1) == 0)
           return false;
        /* 运用试除法:
         * 1.只有奇数需要被测试
         * 2.测试范围从2与根号{n},反之亦然 */
        for(int i = 3; i * i <= n; i += 2)
            if (n % i == 0) 
                return false;
        return true;
    }
    
    // 20171024
    public static void main(String[] args) {
        int which=0;
        for(int i=20171000;i<=20171024;i++){
            if(isPrime(i)){        
                which++;
                if(which % 10 == 0 ){System.out.println();}
                System.out.print(i+", ");
            }
        }
        System.out.println();
        System.out.print("共有"+which+"个质数.");
    }

}
