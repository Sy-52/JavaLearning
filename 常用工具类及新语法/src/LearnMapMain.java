import java.util.HashMap;
import java.util.Map;

public class LearnMapMain {
    public static void main(String[] args) {
        // >> TODO 访问map中的元素
        Map<String, String> map = createMap(29);
        System.out.println(map.get("key20"));
        System.out.println(map.get(new Object()));
        System.out.println(map.get("key30"));
        System.out.println();

        // >> TODO 每一种map都有自己的规范。有些map的key/value为null就不行，但hashMap支持key、value为null。
        map.put(null , "null key的value");
        map.put("null值的key" , null);
        System.out.println("null key的值为：" + map.get(null));
        System.out.println("null值的key为：" + map.get("null值的key"));
        System.out.println();

        // >> TODO 删除map中的元素
        System.out.println("------------- 删除key ---------------");
        String keyToRemove = "key9";
        map.remove(keyToRemove);
        System.out.println("执行remove操作后，" + keyToRemove + "的值为：" + map.get(keyToRemove));
        System.out.println();

        // >> TODO map的遍历
        System.out.println("------------- 遍历keyvalue对 -------------");
        // >> TODO 详情用ctrl + f12.这里用Map中的内部接口Entry引用的指向实例。
        for(Map.Entry<String, String> entry : map.entrySet()){
            System.out.println("Key为：" + entry.getKey() + "，value为：" + entry.getValue());
        }
        System.out.println("------------- 遍历value -------------");
        for(String value : map.values()){
            System.out.println(value);
        }
        System.out.println("------------- 遍历key -------------");
        for(String key : map.keySet()){
            System.out.println(key);
        }
    }

    public static Map<String, String> createMap(int size){
        Map<String, String> ret = new HashMap<>();
        for(int i = 0; i < size; i++){
            ret.put("key" + i, String.valueOf(i));
        }
        return ret;
    }
}
