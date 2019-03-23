package com.gupao;

public class Test {

    public static void main(String[] args) {
        String str = "99999999999";

        long l = superDegit(str, Integer.MAX_VALUE);

        System.out.println(l);
    }

    private static long superDegit(String str, long k) {
        char c = str.charAt(0);
        int first = Character.getNumericValue(c);

        long sum = first;
        for (int i = 1 ; i < str.length();i++){
            char at = str.charAt(i);
            int index = Character.getNumericValue(at);
            sum = sumCount(sum,index);
        }

        if (sum > 9){
            sum = sumCount(sum/10,sum%10);
        }
        long temp = sum;
        for(long i = 1 ; i<k ; i++){
            sum += temp;
            if(sum > 9){
                sum = sumCount(sum/10,sum%10);
            }
        }
        return sum;
    }

    private static long sumCount(long sum, long index) {

        long count = sum+index;
        if(count > 9){
            return sumCount(count/10,count%10);
        }
        return count;
    }
}
