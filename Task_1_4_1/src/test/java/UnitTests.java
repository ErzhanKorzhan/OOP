import org.example.Grade;
import org.example.StudentGradeBook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTests
{
    @Test
    public void CanTransferToBudgeTest() {
        StudentGradeBook student = new StudentGradeBook("Тиван Тиванов Тиванович", true);
        student.addGrade("Матанализ", "Экзамен", 5, 4);
        student.addGrade("Модели вычислений", "Дифференцированный зачет", 3, 3);
        assertTrue(student.canTransferToBudget());
        student.addGrade("ООП", "Экзамен", 3, 4);
        assertFalse(student.canTransferToBudget());
    }

    @Test
    public void CanGetRedDiplomaTest() {
        StudentGradeBook student = new StudentGradeBook("Тиван Тиванов Тиванович", true);
        student.addGrade("ОСи", "Экзамен", 5, 4);
        student.addGrade("ООП", "Экзамен", 5, 4);
        assertTrue(student.canGetRedDiploma());
        student.addGrade("Модели вычислений", "Дифференцированный зачет", 4, 3);
        assertFalse(student.canGetRedDiploma());
    }

    @Test
    public void CanGetIncreasedScholarshipTest() {
        StudentGradeBook student = new StudentGradeBook("Сиван Сиванов Сиванович", true);
        student.addGrade("Модели вычислений", "Дифференцированный зачет", 4, 3);
        student.addGrade("ООП", "Дифференцированный зачет", 4, 3);
        assertTrue(student.canGetIncreasedScholarship());
        student.addGrade("Матанализ", "Дифференцированный зачет", 3, 3);
        assertFalse(student.canGetIncreasedScholarship());
    }

    @Test
    public void ToStringTest() {
        StudentGradeBook student = new StudentGradeBook("Диван Диванов Диванович", true);
        student.addGrade("Матанализ", "Экзамен", 5, 3);
        student.addGrade("ОСи", "Экзамен", 4, 4);
        student.addGrade("ООП", "Экзамен", 3, 4);
        student.addGrade("Модели вычислений", "Дифференцированный зачет", 5, 3);
        student.addGrade("ООП", "Защита ВКР", 4, 8);
        String res = student.toString();
        assertTrue(res.contains("Зачетная книжка студента:    Диван Диванов Диванович"));
        assertTrue(res.contains("Форма обучения:              Платная"));
        assertTrue(res.contains("Средний балл:            4,20"));
        assertTrue(res.contains("Перевод на бюджет:       Да"));
        assertTrue(res.contains("Красный диплом:          Нет"));
        assertTrue(res.contains("Повышенная стипендия:    Да"));
    }

    @Test
    public void GradeToStringTest() {
        Grade grade = new Grade("Матанализ", "Экзамен", 5, 3);
        assert grade.toString().equals("Предмет: Матанализ    Тип: Экзамен    Семестр: 3    Оценка: 5");
    }

    @Test
    public void AverageGradeTest() {
        StudentGradeBook student = new StudentGradeBook("Биван Биванов Биванович", true);
        student.addGrade("Матанализ", "Экзамен", 5, 3);
        student.addGrade("ОСи", "Экзамен", 4, 4);
        assertEquals(4.5, student.AverageGrade());
    }
}