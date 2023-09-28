package ru.job4j.iterator;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3)).isEqualTo(input);
    }

    @Test
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(Arrays.asList(0, 1, 2, 3)).isEqualTo(input);
    }

    @Test
    public void whenPrelicateAndRemove() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
        ListUtils.removeIf(input, a -> a > 2 && a < 5);
        assertThat(Arrays.asList(0, 1, 2, 5)).isEqualTo(input);
    }

    @Test
    public void whenPrelicateAndReplace() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
        ListUtils.replaceIf(input, a -> a > 2 && a < 5, 22);
        assertThat(Arrays.asList(0, 1, 2, 22, 22, 5)).isEqualTo(input);
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
        List<Integer> el = new ArrayList<>(Arrays.asList(2, 3, 4));
        ListUtils.removeAll(input, el);
        assertThat(Arrays.asList(0, 1, 5)).isEqualTo(input);
    }
}