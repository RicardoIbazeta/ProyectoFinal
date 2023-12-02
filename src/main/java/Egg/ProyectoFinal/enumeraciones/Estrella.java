
package Egg.ProyectoFinal.enumeraciones;

public enum Estrella {
    UNO(1+1),
    DOS(2+1),
    TRES(3+1),
    CUATRO(4+1),
    CINCO(5+1);

    final int cantidad;

    public int getCantidad() {
        return cantidad;
    }


    Estrella(int cantidad) {
        this.cantidad = cantidad;
    } 
}
