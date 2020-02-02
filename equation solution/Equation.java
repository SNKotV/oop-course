
import java.util.LinkedList;
import java.lang.Math;

public class Equation {

    private static final double eps = 1e-12;

    private static LinkedList<Double> coefficients;
    private static LinkedList<Complex> roots;

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

        roots.add(new Complex(-b / a, 0));
    }

    private static void solveQuadratic() {
        if (isNull(coefficients))   {
            return;
        }

        double a = coefficients.get(0);
        double b = coefficients.get(1);
        double c = coefficients.get(2);

        double D = b * b - 4 * a * c;

        if (D > eps) {
            roots.add(new Complex((-b + Math.sqrt(D)) / (2 * a), 0.0));
            roots.add(new Complex((-b - Math.sqrt(D)) / (2 * a), 0.0));
        } else {
            roots.add(new Complex(-b / (2 * a), Math.sqrt(-D) / (2 * a)));
            roots.add(new Complex(-b / (2 * a), - Math.sqrt(-D) / (2 * a)));
        }
    }

    private static void solveCubic() {
        if (isNull(coefficients)) {
            return;
        }

        double a = coefficients.get(0);
        double b = coefficients.get(1);
        double c = coefficients.get(2);
        double d = coefficients.get(3);


    }


    public static void printSolution() {
        if (isNull(roots))  {
            return;
        }

        int index = 1;
        for (Complex x: roots) {
            System.out.print("x" + index++ + " = ");
            x.print();
            System.out.println();
        }

    }



    public static void main(String[] args) {
        setCoefficients(args);
        printEquation();
        solve();
        printSolution();
    }
}