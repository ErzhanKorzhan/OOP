package org.example;

public class Grade {
    private final String subject;
    private final String type;
    private final int semester_num;
    private final int grade;

    public Grade(String subject, String type, int grade, int semester_num) {
        this.subject = subject;
        this.type = type;
        this.semester_num = semester_num;
        this.grade = grade;
    }

    public String subject() {
        return subject;
    }

    public int semester_num() {
        return semester_num;
    }

    public int grade() {
        return grade;
    }

    public String type() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("Предмет: %s    Тип: %s    Семестр: %d    Оценка: %d", subject, type, semester_num, grade);
    }
}