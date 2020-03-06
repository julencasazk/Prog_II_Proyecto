package proyecto;

import java.lang.Math;

/**
 * Vector3D
 */
public class Vector3D {

    private double x;
    private double y;
    private double z;

    /**
     * Contructor por defecto
     */
    public Vector3D() {

        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    /**
     * Contructor con parametros
     * @param x
     * @param y
     * @param z
     */
    public Vector3D(double x, double y, double z) { 

        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Los vectores aparecen en consola de la siguiente forma: [x,y,z]
     */
    @Override
    public String toString() {
        return "["+x+","+y+","+z+"]";
    }

    /**
     * Genera un nuevo vector sumando un vector parametro al vector actual
     */
    public Vector3D sumaVectorAVector(Vector3D v) {

        double x = this.x + v.getX();
        double y = this.y + v.getY();
        double z = this.z + v.getZ();
        Vector3D vResultante = new Vector3D(x,y,z);

        return vResultante;
    }

     /**
     * Genera un nuevo vector restando un vector parametro al vector actual
     */
    public Vector3D restaVectorAVector(Vector3D v) {

        double x = this.x - v.getX();
        double y = this.y - v.getY();
        double z = this.z - v.getZ();
        Vector3D vResultante = new Vector3D(x,y,z);

        return vResultante;
    }

    /**
     * Calcula y devuelve el modulo del vector
     * @return
     */
    public double getModulo() {
        return Math.sqrt((this.x * this.x) + (this.y * this.y) + (this.z * this.z));
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

    
}