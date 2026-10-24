package Ejecicio5;

import java.util.List;
import java.util.List;

public class Reserva {
    private int id;
    private String cliente;
    private String ciudadOrigen;
    private String tipoHabitacion;
    private int noches;
    private double precioNoche;
    private List<String> servicios;

    public Reserva(int id, String cliente, String ciudadOrigen, String tipoHabitacion,
                   int noches, double precioNoche, List<String> servicios) {
        this.id = id;
        this.cliente = cliente;
        this.ciudadOrigen = ciudadOrigen;
        this.tipoHabitacion = tipoHabitacion;
        this.noches = noches;
        this.precioNoche = precioNoche;
        this.servicios = servicios;
    }

    public double getImporte() {
        return noches * precioNoche;
    }

    public int getId() { return id; }
    public String getCliente() { return cliente; }
    public String getCiudadOrigen() { return ciudadOrigen; }
    public String getTipoHabitacion() { return tipoHabitacion; }
    public int getNoches() { return noches; }
    public double getPrecioNoche() { return precioNoche; }
    public List<String> getServicios() { return servicios; }

    @Override
    public String toString() {
        return id + ";" + cliente + ";" + ciudadOrigen + ";" + tipoHabitacion + ";" +
               noches + ";" + precioNoche + ";" + servicios;
    }
}
