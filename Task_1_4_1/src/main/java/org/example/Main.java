package org.example;

public class Main {
    public static void main(String[] args) {
        StudentGradeBook student = new StudentGradeBook("иван иванов", true);

        // Добавляем оценки по семестрам
        student.addGrade("Математика", "exam", 5, 1);
        student.addGrade("Программирование", "exam", 4, 1);
        student.addGrade("Физика", "exam", 3, 2);
        student.addGrade("Практика", "practice", 5, 2);
        student.addGrade("Дипломная работа", "qualification work", 5, 8);

        // Выводим средний балл
        System.out.println("Средний балл: " + student.calculateAverageGrade());

        // Проверяем перевод на бюджет
        System.out.println("Можно перевестись на бюджет: " + student.canTransferToBudget());

        // Проверяем возможность красного диплома
        System.out.println("Можно получить красный диплом: " + student.canGetRedDiploma());

        // Проверяем повышенную стипендию в текущем семестре
        System.out.println("Можно получить повышенную стипендию: " + student.canGetIncreasedScholarship(2));
    }
}
