
import java.util.LinkedList;
import java.lang.Math;

public class Equation {

    private static final double eps = 1e-12;

    private static LinkedList<Double> coefficients;
    private static LinkedList<Double> roots;

    public static void setCoefficients(String []buffer)    {
        if (buffer.length < 1)    {
            System.out.println("Invalid number of coefficients.");
            return;
        }

        coefficients = new LinkedList<>();

        int i = 0;
        while (i < buffer.length) {
            if (Double.parseDouble(buffer[i]) > eps)
                break;
            i++;
        }

        if (i == buffer.length)
            coefficients.add(0.0);

        while (i < buffer.length) {
            coefficients.add(Double.parseDouble(buffer[i++]));
        }
    }

    public static void printEquation() {
        if (isNull(coefficients)) {
            return;
        }

        int deg = coefficients.size() - 1;
        for (Double coeff: coefficients) {
            if (deg != 0)
                System.out.print(" (" + coeff + ") * x^" + deg-- + " +");
            else
                System.out.print(" (" + coeff + ") = 0");
        }
        System.out.println();
    }

    private static boolean isNull(Object obj)    {
        return obj == null;
    }


    public static void solve() {
        if (isNull(coefficients)) {
            return;
        }

        if (coefficients.size() > 4) {
            System.out.println("Can't solve equation more than 3 degree.");
            return;
        }

        if (coefficients.size() == 1)   {
            if (coefficients.get(0) < eps)
                System.out.println("Infinite number of solutions");
            else
                System.out.println("No solutions");
        }

        roots = new LinkedList<>();

        if (coefficients.size() == 2)
            solveLinear();
        else if (coefficients.size() == 3)
            solveQuadratic();
        else
            solveCubic();
    }

    private static void solveLinear() {
        if (isNull(coefficients))   {
            return;
        }

        double a = coefficients.get(0);
        double b = coefficients.get(1);

        roots.add(-b / a);
    }

    private static void solveQuadratic() {

    }

    private static void solveCubic() {

    }


    public static void printSolution() {
        if (isNull(roots))  {
            return;
        }

        int index = 1;
        for (Double x: roots) {
            System.out.println("x" + index++ + " = " + x);
        }

    }



    public static void main(String[] args) {
        setCoefficients(args);
        printEquation();
        solve();
        printSolution();
    }
}