package ru.job4j.io;


import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class ConfigTest {
    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Arseny Sudakov");
    }

    @Test
    public void whenPairWithoutCommentAndEmptyLine() {
        String path = "./data/pairWithoutCommentAndEmptyLine.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Arseny Sudakov");
    }

    @Test
    public void whenPairViolation() {
        String path = "./data/pairViolation.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenNoSeparator() {
        String path = "./data/noSeparator.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenKeyNull() {
        String path = "./data/keyNull.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenTwoSeparator() {
        String path = "./data/twoSeparator.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenTwoSeparatorAnotherPlace() {
        String path = "./data/twoSeparatorAnotherPlace.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }
}