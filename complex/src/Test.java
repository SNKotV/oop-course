import com.github.snkotv.Complex;

import java.util.zip.CheckedOutputStream;

public class Test {
    public static void main(String[] args) {

        Complex z1 = new Complex(0, 1);
        Complex z2 = new Complex(1, -2);

        Complex.setPrecision(4);

        System.out.println("z1 = " + z1);
        System.out.println("z2 = " + z2);
        System.out.println("z1 + z2 = " + Complex.sum(z1, z2));
        System.out.println("z1 - z2 = " + Complex.diff(z1, z2));
        System.out.println("z1 * z2 = " + Complex.mul(z1, z2));
        System.out.println("z1 / x2 = " +Complex.div(z1, z2));

        Complex z = new Complex(Math.E, -Math.PI);
        Complex a = new Complex(-3, 2);
        double k = 3.71;

        System.out.println("z = " + z);
        System.out.println("k * z = " + Complex.mul(k, z));
        System.out.println("e^z = " + Complex.exp(z));
        System.out.println("sin(z) = " + Complex.sin(z));
        System.out.println("cos(z) = " + Complex.cos(z));
        System.out.println("sh(z) = " + Complex.sh(z));
        System.out.println("ch(z) = " + Complex.ch(z));
        System.out.println("Ln(z) = " + Complex.Ln(z));
        System.out.println("a = " + a);
        System.out.println("z^a = " + Complex.pow(z, a));
    }
}
