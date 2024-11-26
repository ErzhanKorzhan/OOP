package org.example;

public class Grade {
    private final String subject;
    private final String type; // Тип контроля (exam, differentiated credit, practice, etc.)
    private int grade; // Оценка: 5 - отлично, 4 - хорошо, 3 - удовлетворительно
    private final int semester; // Семестр, в котором получена оценка

    public Grade(String subject, String type, int grade, int semester) {
        this.subject = subject;
        this.type = type;
        this.grade = grade;
        this.semester = semester;
    }

    public int getGrade() {
        return grade;
    }

    public String getType() {
        return type;
    }

    public int getSemester() {
        return semester;
    }

    public String getSubject() {
        return subject;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return String.format("Предмет: %-15s Тип: %-15s Семестр: %-2d Оценка: %-2d",
                subject, type, semester, grade);
    }
}
