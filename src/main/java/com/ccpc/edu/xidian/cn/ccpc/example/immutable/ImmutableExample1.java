package com.ccpc.edu.xidian.cn.ccpc.example.immutable;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
@Slf4j
public class ImmutableExample1 {

    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 3);
        map.put(2, 4);
        map.put(3, 5);
    }

    public static void main(String[] args) {
        map.put(1, 4);
        log.info("{}", map.get(1));
    }
}
