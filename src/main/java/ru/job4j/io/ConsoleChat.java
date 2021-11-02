package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ConsoleChat {

    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() throws IOException {
        List<String> log = new ArrayList<>();
        List<String> bot = readPhrases();
        Random random = new Random();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String userQuest = reader.readLine();
        boolean check = true;
        while (!OUT.equals(userQuest)) {
            System.out.println(userQuest);
            log.add(userQuest);
            if (check) {
                String botAnswer = bot.get(random.nextInt(bot.size()));
                System.out.println(botAnswer);
                log.add(botAnswer);
            }
            userQuest = reader.readLine();
            if (STOP.equals(userQuest)) {
                check = false;
            }
            if (CONTINUE.equals(userQuest)) {
                check = true;
            }
        }
        String end = "Чат завершён";
        System.out.println(end);
        log.add(end);
        saveLog(log);
    }

    private List<String> readPhrases() throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(botAnswers));
        return br.lines().collect(Collectors.toList());
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(
                new FileWriter(path, StandardCharsets.UTF_8, true))) {
            for (String str : log) {
                pw.println(str);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ConsoleChat cc = new ConsoleChat("ConsoleChatLog.txt", "BotChat.txt");
        cc.run();
    }
}