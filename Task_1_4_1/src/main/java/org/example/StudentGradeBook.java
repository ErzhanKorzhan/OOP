package org.example;

import java.util.ArrayList;
import java.util.List;

// Класс для зачетной книжки
public class StudentGradeBook {
    private final String name; // Имя студента
    private final boolean isPaidForm; // true - платная форма, false - бюджетная
    private final List<Grade> grades; // Список всех оценок

    public StudentGradeBook(String name, boolean isPaidForm) {
        this.name = name;
        this.isPaidForm = isPaidForm;
        this.grades = new ArrayList<>();
    }

    // Добавление оценки
    public void addGrade(String subject, String type, int grade, int semester) {
        grades.add(new Grade(subject, type, grade, semester));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Зачетная книжка студента: ").append(name).append("\n");
        sb.append("Форма обучения: ").append(isPaidForm ? "Платная" : "Бюджетная").append("\n");
        sb.append("----------------------------------------------------------\n");
        sb.append(String.format("%-15s %-20s %-10s %-10s%n", "Предмет", "Тип контроля", "Семестр", "Оценка"));
        sb.append("----------------------------------------------------------\n");

        for (Grade grade : grades) {
            sb.append(String.format("%-15s %-20s %-10d %-10d%n",
                    grade.getSubject(), grade.getType(), grade.getSemester(), grade.getGrade()));
        }

        sb.append("----------------------------------------------------------\n");
        sb.append(String.format("Средний балл: %.2f%n", calculateAverageGrade()));
        sb.append("Перевод на бюджет: ").append(isPaidForm ? (canTransferToBudget() ? "Да" : "Нет") : "Уже на бюджете").append("\n");
        sb.append("Красный диплом: ").append(canGetRedDiploma() ? "Да" : "Нет").append("\n");
        return sb.toString();
    }


    // 1. Вычисление среднего балла
    public double calculateAverageGrade() {
        if (grades.isEmpty()) return 0.0;
        int total = 0;
        for (Grade grade : grades) {
            total += grade.getGrade();
        }
        return total / (double) grades.size();
    }

    // 2. Возможность перевода на бюджет
    public boolean canTransferToBudget() {
        if (!isPaidForm) return false;

        int satisfactoryCount = 0;

        // Проверяем оценки за последние два семестра
        int lastSemester = grades.stream().mapToInt(Grade::getSemester).max().orElse(0);
        for (Grade grade : grades) {
            if (grade.getSemester() >= lastSemester - 1 && grade.getGrade() == 3) {
                satisfactoryCount++;
            }
        }

        return satisfactoryCount == 0;
    }

    // 3. Возможность получить красный диплом
    public boolean canGetRedDiploma() {
        int excellentCount = 0;
        int totalGrades = 0;

        for (Grade grade : grades) {
            if ("qualification work".equals(grade.getType()) && grade.getGrade() != 5) {
                return false; // Дипломная работа не на "отлично"
            }
            if (grade.getGrade() == 3) {
                return false; // Есть "удовлетворительно"
            }
            totalGrades++;
            if (grade.getGrade() == 5) excellentCount++;
        }

        // Проверяем, что >= 75% оценок "отлично"
        return totalGrades > 0 && (excellentCount / (double) totalGrades) >= 0.75;
    }

    // 4. Возможность получения повышенной стипендии
    public boolean canGetIncreasedScholarship(int currentSemester) {
        int satisfactoryCount = 0;
        int excellentCount = 0;

        // Проверяем оценки текущего семестра
        for (Grade grade : grades) {
            if (grade.getSemester() == currentSemester) {
                if (grade.getGrade() == 3) satisfactoryCount++;
                if (grade.getGrade() == 5) excellentCount++;
            }
        }

        // Нет "удовлетворительных" и хотя бы одна "отлично"
        return satisfactoryCount == 0 && excellentCount > 0;
    }
}

