package me.star.utils;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Sets;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListUtils {
    /*
     * list to Map<key,object>
     */
    public static <T, K> Map<K, T> toMap(List<T> list, FieldSelector<T, K> selector) {
        if (list == null || list.isEmpty()) return Collections.emptyMap();
        Map<K, T> map = new HashMap<>(list.size());
        for (T t : list) {
            K key = selector.select(t);
            if (key != null) map.put(key, t);
        }
        return map;
    }

    /**
     * 将集合转换为另一种类型的列表
     *
     * @param sourceList 源集合
     * @param selector   元素转换函数
     * @param <T>        源元素类型
     * @param <R>        目标元素类型
     * @return 转换后的列表
     */
    public static <T, R> List<R> toList(Collection<T> sourceList, Function<T, R> selector) {
        if (sourceList == null || sourceList.isEmpty()) {
            return Collections.emptyList();
        }

        List<R> result = new ArrayList<>(sourceList.size());
        for (T item : sourceList) {
            R converted = selector.apply(item);
            if (converted != null) {
                result.add(converted);
            }
        }
        return result;
    }

    public static <T, K> Map<K, T> toLinkedHashMap(List<T> list, FieldSelector<T, K> selector) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<K, T> map = new LinkedHashMap<>(list.size());
        for (T t : list) {
            K key = selector.select(t);
            if (key != null) {
                map.put(key, t);
            }
        }
        return map;
    }

    public static <T, K> Map<K, T> toHashMap(List<T> list, FieldSelector<T, K> selector) {
        if (list == null || list.isEmpty()) {
            return new HashMap<>();
        }
        Map<K, T> map = new HashMap<>(list.size());
        for (T t : list) {
            K key = selector.select(t);
            if (key != null) {
                map.put(key, t);
            }
        }
        return map;
    }

    public static <T, K> LinkedHashMap<K, T> toLinkedMap(List<T> list, FieldSelector<T, K> selector) {
        if (list == null || list.isEmpty()) {
            return new LinkedHashMap<>();
        }
        LinkedHashMap<K, T> map = new LinkedHashMap<>(list.size());
        for (T t : list) {
            K key = selector.select(t);
            if (key != null) {
                map.put(key, t);
            }
        }
        return map;
    }

    public static Map<Object, Map<String, Object>> toMap(List<Map<String, Object>> list, String key) {
        if (list == null || list.isEmpty()) {
            return new HashMap<>();
        }
        Map<Object, Map<String, Object>> map = new HashMap<>(list.size());
        for (Map<String, Object> o : list) {
            Object value = o.get(key);
            if (value != null) {
                map.put(value, o);
            }
        }
        return map;
    }

    /*
     * list to Map<key,List<object>>
     */
    public static <T, K> Map<K, List<T>> groupBy(List<T> list, FieldSelector<T, K> selector) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<K, List<T>> map = new HashMap<>();
        for (T t : list) {
            K key = selector.select(t);
            if (key == null) {
                continue;
            }
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<T>());
            }
            map.get(key).add(t);
        }
        return map;
    }

    public static <T, K> Map<K, List<T>> linkedHashMapGroupBy(List<T> list, FieldSelector<T, K> selector) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<K, List<T>> map = new LinkedHashMap<>();
        for (T t : list) {
            K key = selector.select(t);
            if (key == null) {
                continue;
            }
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<T>());
            }
            map.get(key).add(t);
        }
        return map;
    }

    public static <T, K> Map<K, List<T>> orderGroupBy(List<T> list, FieldSelector<T, K> selector) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<K, List<T>> map = new LinkedHashMap<>();
        for (T t : list) {
            K key = selector.select(t);
            if (key == null) {
                continue;
            }
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<T>());
            }
            map.get(key).add(t);
        }
        return map;
    }

    public static Map<Object, List<Map<String, Object>>> groupBy(List<Map<String, Object>> list, String selector) {
        if (list == null || list.isEmpty()) {
            return new HashMap<>();
        }
        Map<Object, List<Map<String, Object>>> map = new HashMap<>(list.size());
        for (Map<String, Object> o : list) {
            Object key = o.get(selector);
            if (key == null) {
                continue;
            }
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(o);
        }
        return map;
    }

    /*
     * list<object> to select one field or convert to other object
     */
    public static <T, K> List<K> select(List<T> list, FieldSelector<T, K> selector) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        List<K> filedList = new ArrayList<>(list.size());
        for (T t : list) {
            K key = selector.select(t);
            if (key != null) {
                filedList.add(key);
            }
        }
        return filedList;
    }

    /*
     * list<object> 获取字段值(不重复)
     */
    public static <T, K> List<K> distinctSelect(List<T> list, FieldSelector<T, K> selector) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        Set<K> filedSet = new LinkedHashSet<>();
        for (T t : list) {
            K key = selector.select(t);
            if (key != null) {
                filedSet.add(key);
            }
        }
        return new ArrayList<>(filedSet);
    }

    /*
     * list<object> 获取字段值(不重复)
     */
    public static <T, K> Set<K> selectToSet(List<T> list, FieldSelector<T, K> selector) {
        if (list == null || list.isEmpty()) {
            return Collections.emptySet();
        }
        Set<K> filedSet = new LinkedHashSet<>();
        for (T t : list) {
            K key = selector.select(t);
            if (key != null) {
                filedSet.add(key);
            }
        }
        return filedSet;
    }

    public static <T> List<T> removeDuplicate(List<T> list) {
        return new ArrayList<>(new HashSet<>(list));
    }

    /*
    将格式为字符隔开的数字的字符串，根据字符切割，转为List对象
    e.g: stringToIntegerList("1,2,3,4,5,6", ",") => List<Integer>
     */
    public static List<Integer> stringToIntegerList(String str, String separator) {
        List<Integer> integerList = new ArrayList<>();
        if (StrUtil.isBlank(str) || StrUtil.isBlank(separator)) {
            return integerList;
        }
        String[] split = str.split(separator);
        for (String st : split) {
            if (isNumeric(st)) {
                integerList.add(Integer.valueOf(st));
            }
        }
        return integerList;
    }

    public static int[] stringToIntArray(String str, String separator) {
        List<Integer> integerList = new ArrayList<>();
        if (StrUtil.isBlank(str) || StrUtil.isBlank(separator)) {
            return new int[0];
        }
        String[] split = str.split(separator);
        for (String st : split) {
            if (isNumeric(st)) {
                integerList.add(Integer.valueOf(st));
            }
        }
        return integerList.stream().mapToInt(Integer::intValue).toArray();
    }

    /*
    将格式为字符隔开的数字的字符串，根据字符切割，转为List对象
    e.g: stringToIntegerList("1,2,3,4,5,6", ",") => Set<Integer>
     */
    public static Set<Integer> stringToIntegerSet(String str, String separator) {
        Set<Integer> integerList = Sets.newHashSet();
        if (StrUtil.isBlank(str) || StrUtil.isBlank(separator)) {
            return integerList;
        }
        String[] split = str.split(separator);
        for (String st : split) {
            if (isNumeric(st)) {
                integerList.add(Integer.valueOf(st));
            }
        }
        return integerList;
    }

    @SuppressWarnings("all")
    public static List<String> stringToList(String str, String separator) {
        List<String> stringList = new ArrayList<>();
        if (StrUtil.isBlank(str) || StrUtil.isBlank(separator)) {
            return stringList;
        }
        String[] split = str.split(separator);
        for (String st : split) {
            stringList.add(st);
        }
        return stringList;
    }

    @SuppressWarnings("all")
    public static List<String> stringToListByNull(String str, String separator) {
        List<String> stringList = new ArrayList<>();
        if (str == null || StrUtil.isBlank(separator)) {
            if (str != null) {
                stringList.add(str);
            }
            return stringList;
        }
        String[] split = str.split(separator);
        for (String st : split) {
            stringList.add(st);
        }
        return stringList;
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    /**
     * 根据指定条件将列表中符合条件的元素删除并返回删除元素集合
     * 注意，原列表元素也会被删除，若是从数据库直接取出的数据建议先copy一份List再传入
     *
     * @param collection 待删除元素列表
     * @param predicate  删除条件
     * @return 被删元素集合
     */
    public static <E> List<E> removeIf(Collection<? extends E> collection, Predicate<? super E> predicate) {
        List<E> removed = new ArrayList<>();
        for (Iterator<? extends E> i = collection.iterator(); i.hasNext(); ) {
            E e = i.next();
            if (predicate.test(e)) {
                removed.add(e);
                i.remove();
            }
        }
        return removed;
    }

    /**
     * 多集合取交集
     * 若T为自定义对象，需要重写equals与hashCode方法
     */
    @SafeVarargs
    public static <T> List<T> intersection(List<T>... paramLists) {
        List<List<T>> totalList = new ArrayList<>();
        for (List<T> paramList : paramLists) {
            if (paramList != null) {
                List<T> set = new ArrayList<>(new HashSet<>(paramList));
                totalList.add(set);
            }
        }
        if (totalList.isEmpty()) {
            return new ArrayList<>();
        }
        List<T> result = totalList.get(0);
        for (List<T> list : totalList) {
            if (list != null) {
                result.retainAll(list);
            }
        }
        return result;
    }

    /**
     * 把原始字符串分割成指定长度的字符串列表
     *
     * @param inputString 原始字符串
     * @param length      指定长度
     */
    public static List<String> splitStrToList(String inputString, int length) {
        int size = inputString.length() / length;
        if (inputString.length() % length != 0) {
            size += 1;
        }
        return splitStrToList(inputString, length, size);
    }

    /**
     * 把原始字符串分割成指定长度的字符串列表
     *
     * @param inputString 原始字符串
     * @param length      指定长度
     * @param size        指定列表大小
     */
    public static List<String> splitStrToList(String inputString, int length, int size) {
        List<String> list = new ArrayList<String>();
        for (int index = 0; index < size; index++) {
            String childStr = substring(inputString, index * length,
                    (index + 1) * length);
            list.add(childStr);
        }
        return list;
    }

    /**
     * 分割字符串，如果开始位置大于字符串长度，返回空
     *
     * @param str 原始字符串
     * @param f   开始位置
     * @param t   结束位置
     */
    public static String substring(String str, int f, int t) {
        if (f > str.length()) {
            return null;
        }
        if (t > str.length()) {
            return str.substring(f, str.length());
        } else {
            return str.substring(f, t);
        }
    }

    public static List<?> convertObjectToList(Object obj) {
        List<?> list = new ArrayList<>();
        if (obj.getClass().isArray()) {
            list = Arrays.asList((Object[]) obj);
        } else if (obj instanceof Collection) {
            list = new ArrayList<>((Collection<?>) obj);
        }
        return list;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    public static <T> List<List<T>> averageList(List<T> source, int n) {
        List<List<T>> result = new ArrayList<List<T>>();
        int remaider = source.size() % n;
        int number = source.size() / n;
        int offset = 0;//偏移量
        for (int i = 0; i < n; i++) {
            List<T> value = null;
            if (remaider > 0) {
                value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
                remaider--;
                offset++;
            } else {
                value = source.subList(i * number + offset, (i + 1) * number + offset);
            }
            result.add(value);
        }
        return result;
    }

}
