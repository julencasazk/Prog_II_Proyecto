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

    // A continuación las funciones que se ocupan de rotar en los ejes XY, XZ, YZ, y la función que se encarga de escalar


    /**
     * Rota el vector en el plano XY, o al rededor del eje Z
     * @param angulo Angulo deseado en grados sexagesimales (º)
     * @return Vector resultante después de la rotación
     */
    public Vector3D rotarXY(double angulo) {
        double angRad = Math.toRadians(angulo);
        double nuevoX = (Math.cos(angRad) * this.x) - (Math.sin(angRad) * this.y);
        double nuevoY = (Math.sin(angRad) * this.x) + (Math.cos(angRad) * this.y);
        Vector3D vectorRes = new Vector3D(nuevoX, nuevoY, this.z);
        return vectorRes;
    }

    /**
     * Rota el vector en el plano XZ, o al rededor del eje Y
     * @param angulo Angulo deseado en grados sexagesimales (º)
     * @return Vector reusultante después de la rotación
     */
    public Vector3D rotarXZ(double angulo) {
        double angRad = Math.toRadians(angulo);
        double nuevoX = (Math.cos(angRad) * this.x) + (Math.sin(angRad) * this.z);
        double nuevoZ = (-1 * (Math.sin(angRad) * this.x)) + (Math.cos(angRad) * this.z);
        Vector3D vectorRes = new Vector3D(nuevoX, this.y, nuevoZ);
        return vectorRes;
    }

    /**
     * Rota el vector en el plano YZ, o al rededor del eje X
     * @param angulo Angulo deseado en grados sexagesimales (º)
     * @return Vector reusltante después de la rotación
     */
    public Vector3D rotarYZ(double angulo) {
        double angRad = Math.toRadians(angulo);
        double nuevoY = (Math.cos(angRad) * this.y) - (Math.sin(angRad) * this.z);
        double nuevoZ = (Math.sin(angRad) * this.y) + (Math.cos(angRad) * this.z);
        Vector3D vectorRes = new Vector3D(this.x, nuevoY, nuevoZ);
        return vectorRes;
    }


    /**
     * Escala el vector por un factor definido en cada eje
     * @param x Factor a escalar en el eje x
     * @param y Factor a escalar en el eje y
     * @param z Factor a escalar en el eje z
     * @return Vector escalado
     */
    public Vector3D escalar(double x, double y, double z) {
        Vector3D vectorRes = new Vector3D((int)(x * this.x), (int)(y * this.y), (int)(z * this.z));
        return vectorRes;
    }

    /**
     * Calcula y devuelve el módulo del vector
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