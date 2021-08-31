package ru.job4j.collection.map;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenPutWhenIt() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("one", 1);
        String rsl = map.iterator().next();
        assertThat(rsl, is("one"));
    }

    @Test
    public void whenPutTwiceWhenFalse() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        assertTrue(map.put("one", 1));
        assertTrue(map.put("two", 2));
        assertFalse(map.put("two", 2));
    }

    @Test
    public void whenPutWhenExpand() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four ", 4);
        map.put("five ", 5);
        map.put("six ", 6);
        map.put("seven ", 7);
        assertThat(16, is(map.tableSize()));
    }

    @Test
    public void whenGet() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("one", 1);
        Integer rsl = map.get("one");
        assertThat(rsl, is(1));
    }

    @Test
    public void remove() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("one", 1);
        map.put("two", 2);
        assertTrue(map.remove("one"));
        assertThat(null, is(map.get("one")));
        assertThat(1, is(map.size()));
    }
}