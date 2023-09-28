package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class CSVReaderTest {

    @Test
    public void whenFilterTwoColumns(@TempDir Path tempDir) throws Exception {
        String data = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
        File file = tempDir.resolve("source.csv").toFile();
        File target = tempDir.resolve("target.csv").toFile();
        String[] arg = new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=name,age"
        };
        ArgsName argsName = ArgsName.of(arg);
        Files.writeString(file.toPath(), data);
        String expected = String.join(
                System.lineSeparator(),
                "name;age",
                "Tom;20",
                "Jack;25",
                "William;30"
        ).concat(System.lineSeparator());
        CSVReader csv = new CSVReader(arg);
                csv.handle(argsName);
        assertThat(expected).isEqualTo(Files.readString(target.toPath()));
    }
}