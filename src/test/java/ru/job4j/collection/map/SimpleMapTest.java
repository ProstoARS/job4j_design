package ru.job4j.collection.map;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class SimpleMapTest {

    @Test
    public void whenPutWhenIt() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("one", 1);
        String rsl = map.iterator().next();
        assertThat(rsl).isEqualTo("one");
    }

    @Test
    public void whenPutTwiceWhenFalse() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("one", 1);
        map.put("two", 2);
        assertThat(map.put("two", 2)).isFalse();
    }

    @Test
    public void whenPutWhenExpand() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three ", 3);
        map.put("four ", 4);
        map.put("five ", 5);
        map.put("six ", 6);
        map.put("seven ", 7);
        assertThat(16).isEqualTo(map.tableSize());
    }

    @Test
    public void whenGet() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("one", 1);
        Integer rsl = map.get("one");
        assertThat(rsl).isEqualTo(1);
    }

    @Test
    public void remove() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("one", 1);
        map.put("two", 2);
        assertThat(map.remove("one")).isTrue();
    }

    @Test
    public void whenRemoveWenNull() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.remove("one");
        assertThat(map.get("one")).isNull();
    }

    @Test
    public void whenRemoveWenSize() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.remove("one");
        assertThat(1).isEqualTo(map.size());
    }

    @Test
    public void whenRemoveIfNoElement() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("one", 1);
        map.put("two", 2);
        assertThat(map.remove("three")).isFalse();
    }

    @Test
    public void whenPutIfSameBucket() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("six ", 6);
        assertThat(map.put("four", 4)).isFalse();
    }

    @Test
    public void whenPutAndRemoveAndPut() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(2, "two");
        map.remove(2);
        map.put(2, "два");
        String rsl = "два";
        assertThat(rsl).isEqualTo(map.get(2));
    }
}