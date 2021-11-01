package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ConsoleChat {

    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

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
        while (!userQuest.equals(OUT)) {
            System.out.println(userQuest);
            log.add(userQuest);
            if (check) {
                String botAnswer = bot.get(random.nextInt(bot.size()));
                System.out.println(botAnswer);
                log.add(botAnswer);
            }
            userQuest = reader.readLine();
            if (userQuest.equals(STOP)) {
                check = false;
            }
            if (userQuest.equals(CONTINUE)) {
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
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(path)
                ))) {
            for (String str : log) {
                out.println(str);
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