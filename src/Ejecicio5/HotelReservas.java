package Ejecicio5;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.*;

public class HotelReservas {

    // Leer el fichero y cada linea una reserva
    public static List<Reserva> leerReservas(String ruta) {
        try (Stream<String> lines = Files.lines(Paths.get(ruta))) {
            return lines
                .map(linea -> {
                    String[] p = linea.split(";");
                    List<String> servicios;
                    if (p[6].trim().equals("ninguno")) {
                        servicios = new ArrayList<>();
                    } else {
                        servicios = Arrays.asList(p[6].trim().split(","));
                    }
                    return new Reserva(
                        Integer.parseInt(p[0].trim()),
                        p[1].trim(),
                        p[2].trim(),
                        p[3].trim(),
                        Integer.parseInt(p[4].trim()),
                        Double.parseDouble(p[5].trim()),
                        servicios
                    );
                })
                .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // BLOQUE 1------------------------------
    public static void mostrarImporteMayor300(List<Reserva> reservas) {
        reservas.stream()
            .filter(r -> r.getImporte() > 300)
            .forEach(System.out::println);
    }

    public static List<String> clientesDeBilbao(List<Reserva> reservas) {
        return reservas.stream()
            .filter(r -> r.getCiudadOrigen().equals("Bilbao"))
            .map(Reserva::getCliente)
            .collect(Collectors.toList());
    }

    public static List<String> clientesConMinTresNoches(List<Reserva> reservas) {
        return reservas.stream()
            .filter(r -> r.getNoches() >= 3)
            .map(Reserva::getCliente)
            .sorted()
            .collect(Collectors.toList());
    }

    // BLOQUE 2---------------------------
    public static double facturacionTotal(List<Reserva> reservas) {
        return reservas.stream()
            .mapToDouble(Reserva::getImporte)
            .sum();
    }

    public static double calcularMediaImporte(List<Reserva> reservas) {
        return reservas.stream()
            .mapToDouble(Reserva::getImporte)
            .average()
            .orElse(0);
    }

    public static Map<String, Long> reservasPorHabitacion(List<Reserva> reservas) {
        return reservas.stream()
            .collect(Collectors.groupingBy(
                Reserva::getTipoHabitacion,
                Collectors.counting()
            ));
    }

    public static Map<String, Double> facturacionPorCiudad(List<Reserva> reservas) {
        return reservas.stream()
            .collect(Collectors.groupingBy(
                Reserva::getCiudadOrigen,
                Collectors.summingDouble(Reserva::getImporte)
            ));
    }

    public static Reserva reservaMayorImporte(List<Reserva> reservas) {
        return reservas.stream()
            .max(Comparator.comparingDouble(Reserva::getImporte))
            .orElse(null);
    }

    public static double porcentajeMayor300(List<Reserva> reservas) {
        long total = reservas.size();
        long caras = reservas.stream()
            .filter(r -> r.getImporte() > 300)
            .count();

        return (caras * 100.0) / total;
    }

    // BLOQUE 3------------------------------
    public static List<String> serviciosSinRepetir(List<Reserva> reservas) {
        return reservas.stream()
            .flatMap(r -> r.getServicios().stream())
            .filter(s -> !s.equals("ninguno"))
            .distinct()
            .sorted()
            .collect(Collectors.toList());
    }

    public static Map<String, Long> contarServicios(List<Reserva> reservas) {
        return reservas.stream()
            .flatMap(r -> r.getServicios().stream())
            .filter(s -> !s.equals("ninguno"))
            .collect(Collectors.groupingBy(
                s -> s,
                Collectors.counting()
            ));
    }

    public static Map<Boolean, List<Reserva>> separarPorPrecio(List<Reserva> reservas) {
        return reservas.stream()
            .collect(Collectors.partitioningBy(r -> r.getImporte() > 300));
    }

    public static List<Reserva> top3(List<Reserva> reservas) {
        return reservas.stream()
            .sorted(Comparator.comparingDouble(Reserva::getImporte).reversed())
            .limit(3)
            .collect(Collectors.toList());
    }

    public static void mostrarPeek(List<Reserva> reservas) {
        reservas.stream()
            .peek(r -> System.out.println("Antes: " + r))
            .filter(r -> r.getImporte() > 300)
            .peek(r -> System.out.println("Después: " + r))
            .forEach(r -> {});
    }

    // BLOQUE 4------------------------------
   public static void escribirReservasCaras(String rutaSalida, List<Reserva> reservas) {
    try {
        // Mensaje por si ya existe el fichero 
        if (Files.exists(Paths.get(rutaSalida))) {
            System.out.println("El fichero " + rutaSalida + " ya existe. Se va a sobrescribir.");
        }
        
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(rutaSalida))) { //crea o sobreescribe el fichero 
            reservas.stream()
                .filter(r -> r.getImporte() > 300)
                .forEach(r -> {
                    try {
                        bw.write(r.toString());
                        bw.newLine();
                    } catch (IOException e) {
                        System.out.println("Error escribiendo en el fichero.");
                    }
                });

            System.out.println("Fichero creado y reservas caras guardadas.");

        }

    } catch (IOException e) {
        System.out.println("Error gestionando el fichero.");
        e.printStackTrace();
    }
}

}
