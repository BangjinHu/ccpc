package com.ccpc.edu.xidian.cn.ccpc.example.immutable;

import com.ccpc.edu.xidian.cn.ccpc.annoations.ThreadSafe;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Map;
@Slf4j
@ThreadSafe
public class ImmutableExample2 {
    private static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 3);
        map.put(2, 4);
        map.put(3, 5);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        map.put(1, 4);
        log.info("{}", map.get(1));
    }
}
