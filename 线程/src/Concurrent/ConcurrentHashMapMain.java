package Concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapMain {
    public static void main(String[] args) {
        // TODO ConcurrentHashMap也是Map接口的一个实现类。ConcurrentHashMap是线程安全的，且对线程并发做了优化。（将Map分为16份，提供16把锁给不同线程）
        Map<String, List<Integer>> map = new ConcurrentHashMap<>();

        // TODO ConcurrentHashMap的key、value都不允许为null。因为Doug Lea认为在线程并发环境下，key的值为null和key对应的值没有，这两者是模棱两可的，干脆不支持。
        String k1 = "k1";
        map.put(k1,new ArrayList<>());
        System.out.println("put:" + map.get(k1));

        // TODO putIfAbsent用于：若map中没有这个key时，才会放入这个key-value对。
        List<Integer> newList = new ArrayList<>();
        newList.add(999);
        map.putIfAbsent(k1,newList);
        System.out.println("putIfAbsent:" + map.get(k1));

        // TODO compute用于对传入的key的value做修改操作。
        map.compute(k1,(k,v) ->{
            v.add(1);
            return v;
        });
        System.out.println("compute:" + map.get(k1));

        // TODO computeIfPresent：若传入的key存在的话，则对其value做修改。
        map.computeIfPresent(k1,(k,v) ->{
            v.add(999);
            return v;
        });
        System.out.println("computeIfPresent:" + map.get(k1));

        // TODO computeIfAbsent：若传入的key不存在的话，执行后面的lambda语句。（先判断，为true则再创建、false则什么都不干）
        map.computeIfAbsent(k1,(k) -> new ArrayList<>(Integer.MAX_VALUE));
    }
}
