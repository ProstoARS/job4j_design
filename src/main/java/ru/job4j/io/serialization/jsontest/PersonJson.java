package ru.job4j.io.serialization.jsontest;


public class PersonJson {
    private String name;
    private int age;
    private boolean sex;

    public PersonJson(String name, int age, boolean sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isSex() {
        return sex;
    }

    @Override
    public String toString() {
        return "Person{"
                + "name='" + name + '\''
                + ", age=" + age
                + ", sex=" + sex
                + '}';
    }
}
