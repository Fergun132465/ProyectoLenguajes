package proyectolenguajes;

public class Butaca {
    private int fila;
    private int columna;
    private EstadoButaca estado;
    
    public Butaca(int fila, int columna) {
    this.fila = fila;
    this.columna = columna;
    this.estado = EstadoButaca.LIBRE;
    }

        public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public EstadoButaca getEstado() {
        return estado;
    }
    
    public boolean reservar() {
    if (estado == EstadoButaca.LIBRE) {
        estado = EstadoButaca.RESERVADA;
        return true;
    }

    return false;
    }
    
    public boolean ocupar() {
    if (estado == EstadoButaca.LIBRE || estado == EstadoButaca.RESERVADA) {

        estado = EstadoButaca.OCUPADA;
        return true;
    }
    return false;
    }
    
    public boolean liberar() {
    if (estado != EstadoButaca.LIBRE) {
        estado = EstadoButaca.LIBRE;
        return true;
    }

    return false;
    }
}
