package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import static org.hamcrest.MatcherAssert.assertThat;
import java.io.*;
import static org.hamcrest.core.Is.is;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void unavailable() throws IOException {
        Analizy an = new Analizy();
        File source = folder.newFile("log.txt");
        File target = folder.newFile("unavailable.csv");
        try (PrintWriter out = new PrintWriter(new FileOutputStream(source))) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
        }
        an.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target.getAbsolutePath()))) {
            in.lines().forEach(result::append);
        }
        assertThat(result.toString(), is("10:57:01;10:59:01;"));
    }
}