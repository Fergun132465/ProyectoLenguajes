package proyectolenguajes;

public class SalaCine {
    private int filas;
    private int columnas;
    private int numero;
    private Butaca[][] butacas;
    
    public SalaCine(int numero, int filas, int columnas) {
    this.numero = numero;
    this.filas = filas;
    this.columnas = columnas;

    butacas = new Butaca[filas][columnas];

    inicializarButacas();
    }
    
    private void inicializarButacas() {
    for (int fila = 0; fila < filas; fila++) {
        for (int columna = 0; columna < columnas; columna++) {
            butacas[fila][columna] = new Butaca(fila, columna);
        }
    }
    }
    
    public int getNumero() {
    return numero;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }
    
    public Butaca getButaca(int fila, int columna) {
    return butacas[fila][columna];
    }
}
