import java.lang.Math;
import java.util.LinkedList;

public class Complex {
    private static final double eps = 1e-12;

    private double re;
    private double im;

    public Complex() {
        re = 0;
        im = 0;
    }

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public boolean equals(Complex z) {
        return (Math.abs(re - z.re) < eps) && (Math.abs(im - z.im) < eps);
    }

    public static Complex sum(Complex z1, Complex z2) {
        return new Complex(z1.re + z2.re, z1.im + z2.im);
    }

    public static Complex diff(Complex z1, Complex z2) {
        return new Complex(z1.re - z2.re, z1.im - z2.im);
    }

    public static Complex mult(Complex z1, Complex z2) {
        return new Complex(z1.re * z2.re - z1.im * z2.im,
                z1.im * z2.re + z1.re * z2.im);
    }

    public static Complex mult(Complex z, double n)    {
        return new Complex(z.re * n, z.im * n);
    }

    public static Complex div(Complex z1, Complex z2) {
        double z2mod = z2.re * z2.re + z2.im * z2.im;
        return mult(mult(z1, z2.conjugate()), 1.0 / (z2mod));
    }

    public Complex conjugate() {
        return new Complex(re, -im);
    }

    public static LinkedList<Complex> sqrt(Complex z) {
        if (z.equals(new Complex(0.0, 0.0)))    return null;

        double mod = Math.sqrt(z.re * z.re + z.im * z.im);
        double arg = 0;
        if (Math.abs(z.re) < eps && z.im > 0)        arg = Math.PI / 2;
        else if (Math.abs(z.re) < eps && z.im < 0)   arg = -Math.PI / 2;
        else if ((Math.abs(z.im) < eps && z.re > 0)) arg = 0;
        else if ((Math.abs(z.im) < eps && z.re < 0)) arg = Math.PI;
        else                                         arg = Math.atan(z.im / z.re);

        LinkedList<Complex> roots = new LinkedList<>();

        for (int k = 0; k < 2; k++)
            roots.add(new Complex(Math.sqrt(mod) * Math.cos((arg + 2 * Math.PI * k) / 2),
                    Math.sqrt(mod) * Math.sin((arg + 2 * Math.PI * k) / 2)));
        return roots;
    }

    public static LinkedList<Complex> cbrt(Complex z) {
        if (z.equals(new Complex(0.0, 0.0)))    return null;

        double mod = Math.sqrt(z.re * z.re + z.im * z.im);
        double arg = 0;
        if (Math.abs(z.re) < eps && z.im > 0)        arg = Math.PI / 2;
        else if (Math.abs(z.re) < eps && z.im < 0)   arg = -Math.PI / 2;
        else if ((Math.abs(z.im) < eps && z.re > 0)) arg = 0;
        else if ((Math.abs(z.im) < eps && z.re < 0)) arg = Math.PI;
        else                                         arg = Math.atan(z.im / z.re);

        LinkedList<Complex> roots = new LinkedList<>();

        for (int k = 0; k < 3; k++)
            roots.add(new Complex(Math.cbrt(mod) * Math.cos((arg + 2 * Math.PI * k) / 3),
                    Math.cbrt(mod) * Math.sin((arg + 2 * Math.PI * k) / 3)));
        return roots;
    }

    public void print() {
        if (Math.abs(re) < eps && re < 0) re *= -1;
        System.out.printf("%.4f ", re);
        if (Math.abs(im) < eps) return;
        if (im > eps)
            System.out.printf("+ %.4f * i", im);
        else
            System.out.printf("- %.4f * i", (-im));
    }
}