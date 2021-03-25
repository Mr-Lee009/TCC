package com.tccspring.helper;

import java.io.File;

public class StringUtil {
    //
    // Java Regex hoặc Regular Expression (biểu thức chính quy) là một API để định nghĩa một mẫu
    // để tìm kiếm hoặc thao tác với chuỗi. Nó được sử dụng rộng rãi để
    // xác định ràng buộc trên các chuỗi như xác thực mật khẩu, email, kiểu dữ liệu datetime, .../
    public static Boolean validateMail(String email) {

        String[] strings = email.split(",");
        for (String x : strings) {
            System.out.println("chuoi " + x + "co chua chu cai=>" + x.matches(".*[a-zA-Z].*"));
        }

        return false;
    }

    public static Boolean checkNameFileExit(String str) {
        String pattern = "viettell_\\d{4}_\\d{2}_\\d{2}.txt";
        return str.matches(pattern);
    }

    public static void main(String[] args) {
        //String str = "1515,6969,leanhduc,ahihi,152cc";
        //validateMail(str);
        File file = new File("D:\\win10Ghost");
        if (file.isDirectory()) {
            System.out.println(file.getName() + "la thu muc");
            String[] files = file.list();
            for (String x : files) {
                if (checkNameFileExit(x) == true)
                    System.out.println(x);
            }
        }

    }
}
