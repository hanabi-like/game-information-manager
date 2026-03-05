package com.example.domain.util;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Collections;

public final class RegionDict {
    private RegionDict() {
    }

    public static final Map<String, Integer> GENSHIN_IMPACT;
    static {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("Genshin", 0);
        map.put("God", 1);
        map.put("Nod-Krai", 2);
        map.put("Mondstadt", 3);
        map.put("Liyue", 4);
        map.put("Inazuma", 5);
        map.put("Sumeru", 6);
        map.put("Fontaine", 7);
        map.put("Natlan", 8);
        GENSHIN_IMPACT = Collections.unmodifiableMap(map);
    }
}