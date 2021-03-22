package com.tccspring.test;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class HashMapTest {
    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("Le Anh Duc", "Mang MT 14");
        hashMap.put("Cao Xuan Tuan", "Mang MT 16");
        hashMap.put("Vu Minh Tuan", "Mang MT 14");
        hashMap.put("Cao Hong Nhung", "Mang MT 15");
        System.out.println(hashMap.containsKey("Le Anh Duc"));
        System.out.println(hashMap.containsValue("Mang MT 16"));

        // duyet cac phan tu trng hashmap
        Set<String> set = hashMap.keySet();
        for (String key : set) {
            System.out.println(hashMap.get(key));
        }

        Collection<String> co = hashMap.values();
        
    }
}
