public class Vector {

    private double x;
    private double y;
    private double z;

    public Vector (double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector() {
        this(0,0,0);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public static Vector sum(Vector v1, Vector v2) {
        return new Vector(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
    }

    public static Vector difference(Vector v1, Vector v2) {
        return new Vector(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
    }

    public static Vector scalarProduct(Vector v, double k) {
        return new Vector(k * v.x, k * v.y, k * v.z);
    }

    public static Vector scalarProduct(double k, Vector v) {
        return scalarProduct(v, k);
    }

    public static double dotProduct(Vector v1, Vector v2) {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }

    public static Vector product(Vector v1, Vector v2) {
        double x = v1.y * v2.z - v1.z * v2.y;
        double y = v1.x * v2.z - v1.z * v2.x;
        double z = v1.x * v2.y - v1.y *v2.x;
        return new Vector(x, y, z);
    }

    public String toString() {
        return "( " + x + " ; " + y + " ; " + z + " )";
    }

    public static void main(String[] args) {
    }
}