package com.Blog.Util;

import org.apache.shiro.crypto.hash.Md5Hash;

public class PasswordUtil {
     public static String md5(String password,String salt)
     {
         return  new Md5Hash(password,salt).toString();
     }

    public static void main(String[] args) {
        System.out.println(PasswordUtil.md5("admin","admin"));
     }
}
