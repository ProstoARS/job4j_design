package ru.job4j.it;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.NoSuchElementException;

public class MatrixItTest {
    @Test
    public void when4El() {
        int[][] in = {
                {1}
        };
        MatrixIt it = new MatrixIt(in);
        assertThat(it.next()).isEqualTo(1);
    }

    @Test
    public void whenFirstEmptyThenNext() {
        int[][] in = {
                {}, {1}
        };
        MatrixIt it = new MatrixIt(in);
        assertThat(it.next()).isEqualTo(1);
    }

    @Test
    public void whenFirstEmptyThenHashNext() {
        int[][] in = {
                {}, {1}
        };
        MatrixIt it = new MatrixIt(in);
        assertThat(it.hasNext()).isTrue();
    }

    @Test
    public void whenRowHasDiffSize() {
        int[][] in = {
                {1}, {}, {2, 3}, {}, {5},
        };
        MatrixIt it = new MatrixIt(in);
        assertThat(it.next()).isEqualTo(1);
        assertThat(it.next()).isEqualTo(2);
        assertThat(it.next()).isEqualTo(3);
        assertThat(it.next()).isEqualTo(5);
    }

    @Test
    public void whenFewEmpty() {
        int[][] in = {
                {1}, {}, {}, {}, {2}
        };
        MatrixIt it = new MatrixIt(in);
        assertThat(it.next()).isEqualTo(1);
        assertThat(it.next()).isEqualTo(2);
    }

    @Test
    public void whenEmpty() {
        int[][] in = {
                {}
        };
        MatrixIt it = new MatrixIt(in);
        assertThat(it.hasNext()).isFalse();
    }

    @Test
    public void whenEmptyThenNext() {
        int[][] in = {
                {}
        };
        MatrixIt it = new MatrixIt(in);
        assertThatThrownBy(it::next)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void whenMultiHashNext() {
        int[][] in = {
                {}, {1}
        };
        MatrixIt it = new MatrixIt(in);
        assertThat(it.hasNext()).isTrue();
        assertThat(it.hasNext()).isTrue();
    }

    @Test
    public void whenNoElements() {
        int[][] in = {{}, {}, {}};
        MatrixIt it = new MatrixIt(in);
        assertThat(it.hasNext()).isFalse();
    }
}