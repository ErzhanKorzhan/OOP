package org.example;

import org.expr.*;
import org.expr.Number;

public class ExpressionParser {
    private static int pos; // Текущая позиция в строке
    private static String input; // Входная строка для разбора

    public static Expression parse(String str) {
        input = str.replaceAll(" ", ""); // Убираем пробелы для удобства парсинга
        pos = 0;
        return parseExpression();
    }

    private static Expression parseExpression() {
        Expression result = parseTerm(); // Сначала разберем все `+` и `-`
        while (pos < input.length()) {
            char op = input.charAt(pos);
            if (op == '+' || op == '-') {
                pos++;
                Expression right = parseTerm(); // Разбираем следующий операнд
                if (op == '+') {
                    result = new Add(result, right);
                } else {
                    result = new Sub(result, right);
                }
            } else {
                break;
            }
        }
        return result;
    }

    private static Expression parseTerm() {
        Expression result = parseFactor(); // Сначала разберем все `*` и `/`
        while (pos < input.length()) {
            char op = input.charAt(pos);
            if (op == '*' || op == '/') {
                pos++;
                Expression right = parseFactor(); // Разбираем следующий операнд
                if (op == '*') {
                    result = new Mul(result, right);
                } else {
                    result = new Div(result, right);
                }
            } else {
                break;
            }
        }
        return result;
    }

    private static Expression parseFactor() {
        if (pos < input.length() && input.charAt(pos) == '(') {
            pos++; // Пропускаем открывающую скобку
            Expression result = parseExpression(); // Рекурсивный вызов для вложенных выражений
            if (pos < input.length() && input.charAt(pos) == ')') {
                pos++; // Пропускаем закрывающую скобку
            }
            return result;
        }
        return parseAtom();
    }

    private static Expression parseAtom() {
        StringBuilder sb = new StringBuilder();
        while (pos < input.length() && (Character.isDigit(input.charAt(pos)) || Character.isLetter(input.charAt(pos)))) {
            sb.append(input.charAt(pos++));
        }
        String token = sb.toString();
        if (Character.isDigit(token.charAt(0))) {
            return new Number(Integer.parseInt(token));
        }
        return new Variable(token);
    }
}