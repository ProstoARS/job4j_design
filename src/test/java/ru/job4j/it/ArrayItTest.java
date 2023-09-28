package ru.job4j.it;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;


public class ArrayItTest {

    @Test
    public void whenMultiCallHasNextThenTrue() {
        ArrayIt it = new ArrayIt(
                new int[] {1, 2, 3}
        );
        boolean result = it.hasNext();
        assertThat(result).isTrue();
        assertThat(it.hasNext()).isTrue();
    }

    @Test
    public void whenReadSequence() {
        ArrayIt it = new ArrayIt(
                new int[] {1, 2, 3}
        );
        assertThat(it.next()).isEqualTo(1);
        assertThat(it.next()).isEqualTo(2);
        assertThat(it.next()).isEqualTo(3);
    }
}