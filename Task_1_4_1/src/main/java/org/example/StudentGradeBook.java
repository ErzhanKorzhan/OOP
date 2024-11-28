package org.example;

import java.util.ArrayList;
import java.util.List;

// Класс для зачетной книжки
public class StudentGradeBook {
    private final String name;
    private final boolean isPaidForm;
    private final List<Grade> grades;

    public StudentGradeBook(String name, boolean isPaidForm) {
        this.name = name;
        this.isPaidForm = isPaidForm;
        this.grades = new ArrayList<>();
    }

    public void addGrade(String subject, String type, int grade, int semester_num) {
        if (semester_num < 1 || semester_num > 8) {
            throw new IllegalArgumentException("Номер семестра должен быть в диапазоне от 2 до 5");
        }
        if (grade < 2 || grade > 5) {
            throw new IllegalArgumentException("Оценка должна быть в диапазоне от 2 до 5");
        }
        grades.add(new Grade(subject, type, grade, semester_num));
    }

    @Override
    public String toString() {
        int subject_len = 21;
        int type_len = 12;
        for (Grade grade :grades){
            subject_len = Math.max(subject_len, grade.subject().replace(" ", "  ").length()/2);
            type_len = Math.max(type_len, grade.type().replace(" ", "  ").length()/2);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Зачетная книжка студента:    ").append(name).append("\n");
        sb.append("Форма обучения:              ").append(isPaidForm ? "Платная" : "Бюджетная").append("\n");
        sb.append("-".repeat(subject_len + type_len + 28)).append('\n');
        sb.append(String.format("%s%s    %s%s    %s    %s\n", "Предмет", " ".repeat(subject_len - 7), "Вид контроля", " ".repeat(type_len - 12), "Семестр", "Оценка"));
        sb.append("-".repeat(subject_len + type_len + 28)).append('\n');
        for (Grade grade : grades) {
            sb.append(String.format("%s%s    %s%s       %d          %d\n", grade.subject(), " ".repeat(subject_len - grade.subject().replace(" ", "  ").length()/2), grade.type(), " ".repeat(type_len - grade.type().replace(" ", "  ").length()/2), grade.semester_num(), grade.grade()));
        }
        sb.append("-".repeat(subject_len + type_len + 28)).append('\n');
        sb.append(String.format("Средний балл:%s%.2f%n", " ".repeat(subject_len - 9), AverageGrade()));
        sb.append("Перевод на бюджет:").append(" ".repeat(subject_len - 14)).append(isPaidForm ? (canTransferToBudget() ? "Да" : "Нет") : "Уже на бюджете").append("\n");
        sb.append("Красный диплом:").append(" ".repeat(subject_len - 11)).append(canGetRedDiploma() ? "Да" : "Нет").append("\n");
        sb.append("Повышенная стипендия:").append(" ".repeat(subject_len - 17)).append(canGetIncreasedScholarship() ? "Да" : "Нет").append("\n");
        return sb.toString();
    }

    public double AverageGrade() {
        if (grades.isEmpty()) return 0;
        double total = 0;
        for (Grade grade : grades) {
            total += grade.grade();
        }
        return total / (double) grades.size();
    }

    public boolean canTransferToBudget() {
        int currentSemester = grades.stream().mapToInt(Grade::semester_num).max().orElse(0);
        for (Grade grade : grades) {
            if (grade.semester_num() >= currentSemester - 1 && ((grade.type().equals("Экзамен") && grade.grade() <= 3) || (grade.type().equals("Дифференцированный зачет") && grade.grade() <= 2))) {
                return false;
            }
        }
        return isPaidForm;
    }

    public boolean canGetRedDiploma() {
        double cnt_excellent_grade = 0;
        for (Grade grade : grades) {
            if ((grade.type().equals("Защита ВКР") && grade.grade() != 5) || grade.grade() <= 3) {
                return false;
            }
            if (grade.grade() == 5) {
                cnt_excellent_grade++;
            }
        }
        return cnt_excellent_grade / grades.size() >= 0.75;
    }

    public boolean canGetIncreasedScholarship() {
        int currentSemester = grades.stream().mapToInt(Grade::semester_num).max().orElse(0);
        for (Grade grade : grades) {
            if (grade.semester_num() == currentSemester && grade.grade() <= 3) {
                return false;
            }
        }
        return true;
    }
}