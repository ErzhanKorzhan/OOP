package org.example;

public class Main {
    public static void main(String[] args) {
        StudentGradeBook student = new StudentGradeBook("Диван Диванов Диванович", true);
        student.addGrade("Матанализ", "Экзамен", 5, 3);
        student.addGrade("ОСи", "Экзамен", 4, 4);
        student.addGrade("ООП", "Экзамен", 3, 4);
        student.addGrade("Модели вычислений", "Дифференцированный зачет", 5, 3);
        student.addGrade("ООП", "Защита ВКР", 4, 8);
        System.out.println(student);
    }
}
