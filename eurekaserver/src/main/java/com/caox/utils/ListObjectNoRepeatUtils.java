package com.caox.utils;

import com.caox.model.UserInfo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/7/5 17:54
 * 去重List<对象>
 */
public class ListObjectNoRepeatUtils {


    /**
     * 去重字符串重复
     * @param stringList  字符串List
     * @return            返回去重字符串
     */
    public static List<String> removeStringListDupli(List<String> stringList) {
        Set<String> set = new LinkedHashSet<>();
        set.addAll(stringList);
        stringList.clear();
        stringList.addAll(set);
        return stringList;
    }

    /**
     * 根据对象属性去重  属性：userId
     * @param persons
     * @return
     */
    public static List<UserInfo> removeDupliById(List<UserInfo> persons) {
        Set<UserInfo> personSet = new TreeSet<>((o1, o2) -> o1.getUserId().compareTo(o2.getUserId()));
        personSet.addAll(persons);
        return new ArrayList<>(personSet);
    }

    /**
     * 根据对象多个属性去重 属性：userId + userName
     * @param persons
     * @return
     */
    public static List<UserInfo> removeDupliByMorePro(List<UserInfo> persons){
        List<UserInfo> personList = persons.stream().collect(Collectors
                .collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> {
                    // 根据useId和userName进行去重
                    return o.getUserId() + "," + o.getUsername() +"," + o.getAge();
                }))), ArrayList::new));
        return  personList;
    }

    /**
     * 根据对象单个属性去重 属性：userId
     * @param persons
     * @return
     */
    public static List<UserInfo> removeDupliByUserId(List<UserInfo> persons){
        List<UserInfo> unique = persons.stream().collect(
                collectingAndThen(
                        toCollection(() -> new TreeSet<>(comparingLong(UserInfo::getUserId))), ArrayList::new)
        );
        return unique;
    }

    /**
     * 根据对象单个属性去重 属性：userId
     * @param persons
     * @return
     */
    public static List<UserInfo> removeDupliByUserIdNew(List<UserInfo> persons){

        List<UserInfo> personList = new ArrayList<>();
         persons.stream().filter(distinctByKey(p -> p.getUserId()))
                .forEach(p -> personList.add(p));
         return personList;
    }

    /**
     * 根据key去重重复
     * @param keyExtractor key执行器
     * @param <T>          泛型
     * @return
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}
