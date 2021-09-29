package ru.job4j.question;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        int add = 0;
        int origins = 0;
        int change = 0;
        if (previous.containsAll(current) && previous.size() == current.size()) {
            return info;
        } else {
            Set<User> temp = new HashSet<>(previous);
            temp.addAll(current);
            Map<Integer, String> map = new HashMap<>();
            for (User rsl : previous) {
                map.put(rsl.getId(), rsl.getName());
            }
            for (User rsl : temp) {
                if (!map.containsKey(rsl.getId())) {
                    add++;
                } else if (!map.get(rsl.getId()).equals(rsl.getName())) {
                    change++;
                } else if (!current.contains(rsl)) {
                    origins++;
                }
            }
            info.setDeleted(origins - change);
            info.setAdded(add);
            info.setChanged(change);
        }
        return info;
    }
}