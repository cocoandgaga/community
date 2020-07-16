package com.zxj.community;

import org.apache.tomcat.util.buf.CharsetUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class CommunityApplicationTests {
    public static void main(String[] args) {
int sum=0;
        int m = 6; 
        int n = 11;
        int k = 9;
        int temp = 0;
        for (int i = 0; i < m; i++) {
            int s1 = 0;
            temp = i;
            while (temp != 0) {
                s1 += temp % 10;
                temp /= 10;
            }
            for (int j = 0; j < n; j++) {
                int s2 = 0;
                temp = j;
                while (temp != 0) {
                    s2 += temp % 10;
                    temp /= 10;

                }

                if(s1+s2<=k)
                    sum++;
            }

        }
        System.out.println("数："+sum);
        System.out.println("数："+m*n);

    }

}
