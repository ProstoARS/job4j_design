package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class ArgsNameTest {

    @Test
    public void whenGetFirst() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        assertThat(jvm.get("Xmx")).isEqualTo("512");
    }

    @Test
    public void whenGetFirstReorder() {
        ArgsName jvm = ArgsName.of(new String[] {"-encoding=UTF-8", "-Xmx=512"});
        assertThat(jvm.get("Xmx")).isEqualTo("512");
    }

    @Test
    public void whenGetNotExist() {
        assertThatThrownBy(() -> ArgsName.of(new String[]{}))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty();
    }

    @Test
    public void whenWrongSomeArgument() {
        assertThatThrownBy(() -> ArgsName.of(new String[] {"-enconding=UTF-8", "-Xmx="}))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenWrongKey() {
        assertThatThrownBy(() -> ArgsName.of(new String[] {"-encoding=UTF-8", "=512"}))
                .isInstanceOf(IllegalArgumentException.class);
    }
}