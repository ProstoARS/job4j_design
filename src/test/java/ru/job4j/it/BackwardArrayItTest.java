package ru.job4j.it;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.NoSuchElementException;

public class BackwardArrayItTest {


    @Test
    public void whenMultiCallhasNextThenTrue() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[] {1, 2, 3}
        );
        assertThat(it.hasNext()).isTrue();
    }

    @Test
    public void whenReadSequence() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[] {1, 2, 3}
        );
        assertThat(it.next()).isEqualTo(3);
        assertThat(it.next()).isEqualTo(2);
        assertThat(it.next()).isEqualTo(1);
    }

    @Test
    public void whenNextFromEmpty() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[] {}
        );
        assertThatThrownBy(it::next)
                .isInstanceOf(NoSuchElementException.class);
    }
}