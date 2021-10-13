package io;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {
    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Arseny Sudakov"));
    }

    @Test
    public void whenPairWithoutCommentAndEmptyLine() {
        String path = "./data/pairWithoutCommentAndEmptyLine.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Arseny Sudakov"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairViolation() {
        String path = "./data/pairViolation.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoSeparator() {
        String path = "./data/noSeparator.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenKeyNull() {
        String path = "./data/keyNull.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenTwoSeparator() {
        String path = "./data/twoSeparator.properties";
        Config config = new Config(path);
        config.load();
    }
}