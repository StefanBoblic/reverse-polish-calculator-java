import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;


public class Util {

    void printCalculations() {
        BufferedReader expression = new BufferedReader(new InputStreamReader(System.in));
        String inputString;

        try {
            inputString = expression.readLine();
            inputString = polishNotation(inputString);
            System.out.println(calculate(inputString));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String polishNotation(String inputString) throws Exception {
        StringBuilder sbStack = new StringBuilder(""), sbOut = new StringBuilder("");
        char cIn, cTmp;

        for (int i = 0; i < inputString.length(); i++) {
            cIn = inputString.charAt(i);
            if (isOperator(cIn)) {
                while (sbStack.length() > 0) {
                    cTmp = sbStack.substring(sbStack.length() - 1).charAt(0);
                    if (isOperator(cTmp) && (operationPriority(cIn) <= operationPriority(cTmp))) {
                        sbOut.append(" ").append(cTmp).append(" ");
                        sbStack.setLength(sbStack.length() - 1);
                    } else {
                        sbOut.append(" ");
                        break;
                    }
                }
                sbOut.append(" ");
                sbStack.append(cIn);
            } else if ('(' == cIn) {
                sbStack.append(cIn);
            } else if (')' == cIn) {
                cTmp = sbStack.substring(sbStack.length() - 1).charAt(0);
                while ('(' != cTmp) {
                    if (sbStack.length() < 1) {
                        throw new Exception("Ошибка разбора скобок. Проверьте правильность выражения.");
                    }
                    sbOut.append(" ").append(cTmp);
                    sbStack.setLength(sbStack.length() - 1);
                    cTmp = sbStack.substring(sbStack.length() - 1).charAt(0);
                }
                sbStack.setLength(sbStack.length() - 1);
            } else {
                sbOut.append(cIn);
            }
        }

        while (sbStack.length() > 0) {
            sbOut.append(" ").append(sbStack.substring(sbStack.length() - 1));
            sbStack.setLength(sbStack.length() - 1);
        }

        return sbOut.toString();
    }

    Double calculate(String inputString) throws Exception {
        double x, y;
        Deque<Double> stack = new ArrayDeque<>();
        StringTokenizer tokenizer = new StringTokenizer(inputString);
        while (tokenizer.hasMoreTokens()) {
            try {
                String temp = tokenizer.nextToken().trim();

                if (1 == temp.length() && isOperator(temp.charAt(0))) {
                    if (stack.size() < 2) {
                        throw new Exception("Неверное количество данных в стеке для операции " + temp);
                    }

                    y = stack.pop();
                    x = stack.pop();

                    switch (temp.charAt(0)) {
                        case '+':
                            x += y;
                            break;
                        case '-':
                            x -= y;
                            break;
                        case '/':
                            x /= y;
                            break;
                        case '*':
                            x *= y;
                            break;
                        default:
                            throw new Exception("Недопустимая операция " + temp);
                    }
                    stack.push(x);
                } else {
                    x = Double.parseDouble(temp);
                    stack.push(x);
                }
            } catch (Exception e) {
                throw new Exception("Недопустимый символ в выражении");
            }
        }
        if (stack.size() > 1) {
            throw new Exception("Количество операторов не соответствует количеству операндов");
        }

        return stack.pop();
    }

    boolean isOperator(char operator) {
        switch (operator) {
            case '-':
            case '+':
            case '*':
            case '/':
                return true;
        }
        return false;
    }

    byte operationPriority(char operation) {
        switch (operation) {
            case '*':
            case '/':
                return 2;
        }
        return 1; // Тут остается + и -
    }
}
