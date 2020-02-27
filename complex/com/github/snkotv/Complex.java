package com.github.snkotv;

import java.lang.Math;

public class Complex {
    private static final double eps = 1e-12;
    public static final Complex i = new Complex(0, 1);
    public static int precision = 6;

    public static void setPrecision(int precision) {
        if (precision < 0)  throw new IllegalArgumentException();
        Complex.precision = precision;
    }

    private double re;
    private double im;

    public double getRe() {
        return re;
    }

    public void setRe(double re) {
        this.re = re;
    }

    public double getIm() {
        return im;
    }

    public void setIm(double im) {
        this.im = im;
    }

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public Complex() {
        this(0,0);
    }

    public double mod() {
        return Math.sqrt(re * re + im * im);
    }

    public double arg() {
        if (equals(new Complex(0.0, 0.0))) {
            return 0.0;
        }

        double arg = 0;
        if (Math.abs(re) < eps && im > 0)        arg = Math.PI / 2;
        else if (Math.abs(re) < eps && im < 0)   arg = -Math.PI / 2;
        else if ((Math.abs(im) < eps && re > 0)) arg = 0;
        else if ((Math.abs(im) < eps && re < 0)) arg = Math.PI;
        else                                     arg = Math.atan(im / re);

        return arg;
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

    public static Complex mul(Complex z1, Complex z2) {
        return new Complex(z1.re * z2.re - z1.im * z2.im,
                z1.im * z2.re + z1.re * z2.im);
    }

    public static Complex mul(Complex z, double n)    {
        return new Complex(z.re * n, z.im * n);
    }

    public static Complex mul(double n, Complex z) {
        return Complex.mul(z, n);
    }

    public static Complex div(Complex z1, Complex z2) {
        double z2mod = z2.re * z2.re + z2.im * z2.im;
        return mul(mul(z1, conjugate(z2)), 1.0 / (z2mod));
    }

    public static Complex conjugate(Complex z) {
        return new Complex(z.re, -z.im);
    }

    public static Complex exp(Complex z) {
        return new Complex(Math.exp(z.re) * Math.cos(z.im), Math.exp(z.re) * Math.sin(z.im));
    }

    public static Complex sin(Complex z) {
        Complex e1 = Complex.exp(Complex.mul(i, z));
        Complex e2 = Complex.exp(Complex.mul(Complex.mul(i, z), -1));
        return Complex.div(Complex.diff(e1, e2), Complex.mul(i, 2));
    }

    public static Complex cos(Complex z) {
        Complex e1 = Complex.exp(Complex.mul(i, z));
        Complex e2 = Complex.exp(Complex.mul(Complex.mul(i, z), -1));
        return Complex.mul(Complex.sum(e1, e2), 1.0 / 2.0);
    }

    public static Complex sh(Complex z) {
        return Complex.mul(Complex.diff(Complex.exp(z), Complex.exp(Complex.mul(z, -1.0))), 1.0 / 2.0);
    }

    public static Complex ch(Complex z) {
        return Complex.mul(Complex.sum(Complex.exp(z), Complex.exp(Complex.mul(z, -1.0))), 1.0 / 2.0);
    }

    public static Complex Ln(Complex z) {
        Complex ln = new Complex(Math.log(z.mod()), 0.0);
        Complex arg = Complex.mul(i, z.arg());
        return Complex.sum(ln, arg);
    }

    public static Complex pow(Complex z, Complex a) {
        return Complex.exp(Complex.mul(a, Complex.Ln(z)));
    }

    public String toString() {
        String format = "%." + precision + "f";
        String str = String.format(format, 0.0);

        String res = String.format(format, re);
        String ims = String.format(format, im);


        if (res.equals(String.format(format, -0.0))) {
            res = String.format(format, 0.0);
        }

        if (ims.equals(String.format(format, -0.0))) {
            ims = String.format(format, 0.0);
        }

        ims += "i";

        if (res.equals(String.format(format, 0.0))) {
            if (!ims.equals(String.format(format, 0.0) + "i")) {
                str = ims;
            }
        } else {
            str = res;
            if (!ims.equals(String.format(format, 0.0) + "i")) {
                if (im > 0) str += " + " + ims;
                else        str += " " + ims;
            }
        }

        return str;
    }
}