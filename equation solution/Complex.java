import java.lang.Math;

public class Complex {
    private static final double eps = 1e-12;

    private double re;
    private double im;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public void print() {
        System.out.print(re + " ");
        if (Math.abs(im) < eps) return;
        if (im > eps)
            System.out.print("+ " + im + " * i");
        else
            System.out.print("- " + (-im) + " * i");
    }
}