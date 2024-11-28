package org.example;

public record Grade(String subject, String type, int grade, int semester_num) {
    @Override
    public String toString() {
        return String.format("Предмет: %s Тип: %s Семестр: %d Оценка: %d", subject, type, semester_num, grade);
    }
}