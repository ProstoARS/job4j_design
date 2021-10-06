package io;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {
    @Test
    public void whenPairWithoutComment() {
        String path = "C:\\IdeaProjects\\job4j_design\\data\\pair_without_comment.properties.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Arseny Sudakov"));
    }

    @Test
    public void whenPairWithoutCommentAndEmtyLine() {
        String path = "C:\\IdeaProjects\\job4j_design\\data\\pairWithoutCommentAndEmtyLine.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Arseny Sudakov"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenPairViolation() {
        String path = "C:\\IdeaProjects\\job4j_design\\data\\pairViolation.properties";
        Config config = new Config(path);
        config.load();
    }
}