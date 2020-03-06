package proyecto;

/**
 * Punto
 */
public class Punto3D {

    private double x;
    private double y;
    private double z;

    public Punto3D() {

        this.x = 0;
        this.y = 0;
        this.z = 0;

    }

    public Punto3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    /**
     * Suma un vector al punto para obtener otro punto
     */
    public Punto3D sumaVectorAPunto(Vector3D v){

        double x = this.x + v.getX();
        double y = this.y + v.getY();
        double z = this.z + v.getZ();
        Punto3D pResultante = new Punto3D(x,y,z);

        return pResultante;
    }

    /**
     * Resta un vector al punto para obtener otro punto
     * @param v
     * @return
     */
    public Punto3D restaVectorAPunto(Vector3D v){

        double x = this.x - v.getX();
        double y = this.y - v.getY();
        double z = this.z - v.getZ();
        Punto3D pResultante = new Punto3D(x,y,z);

        return pResultante;
    }

    /**
     * Resta un punto a otro para obtener el vector que los une
     * @param p
     * @return
     */
    public Vector3D restaPuntoAPunto(Punto3D p) {
        double x = this.x - p.getX();
        double y = this.y - p.getY();
        double z = this.z - p.getZ();
        Vector3D vResultante = new Vector3D(x,y,z);

        return vResultante;
    }
    
    /**
     * Los puntos aparecen en consola de la siguiente forma: (x,y,z)
     */
    @Override
    public String toString() {

        return "("+x+","+y+","+z+")";
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