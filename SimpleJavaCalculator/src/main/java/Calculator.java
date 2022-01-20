import java.io.*;
import java.util.*;

public class Calculator {
    public static void main(String[] args) {
        Util util = new Util();
        System.out.println("Введте выражение для расчета. Поддерживаются цифры, операции +,-,*,/ и приоритеты в виде скобок ( и ):");
        util.printCalculations();
    }
}
