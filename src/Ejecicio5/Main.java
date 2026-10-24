package Ejecicio5;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.util.List;

public class Main {
    public static void fillData(String ruta) {
        try {
            if (!Files.exists(Paths.get(ruta))) {
                Files.createFile(Paths.get(ruta));

                try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(ruta))) {
                    bw.write("1;Ana;Bilbao;Suite;3;180.00;desayuno,spa\n");
                    bw.write("2;Pedro;Madrid;Doble;2;95.00;desayuno\n");
                    bw.write("3;Ane;Bilbao;Individual;4;65.00;ninguno\n");
                    bw.write("4;Luis;Vitoria;Suite;2;180.00;spa,parking\n");
                    bw.write("5;Marta;Bilbao;Doble;5;95.00;desayuno,parking\n");
                    bw.write("6;Jon;Donostia;Individual;1;65.00;ninguno\n");
                    bw.write("7;Aitor;Bilbao;Suite;2;180.00;desayuno,spa,parking\n");
                    bw.write("8;Laura;Madrid;Doble;3;95.00;parking\n");
                    bw.write("9;Iker;Bilbao;Doble;1;95.00;desayuno\n");
                    bw.write("10;Nerea;Vitoria;Individual;6;65.00;spa\n");
                    bw.write("11;Maite;Donostia;Suite;4;180.00;desayuno\n");
                    bw.write("12;Unai;Madrid;Individual;2;65.00;ninguno\n");
                }

                System.out.println("Fichero creado correctamente.");
            } else {
                System.out.println("El fichero ya existe. Usándolo...");
            }

        } catch (IOException e) {
            System.out.println("Error gestionando el fichero.");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String ruta = "reservas.txt";

        fillData(ruta);//dentro delmetodo miro si existe 

        List<Reserva> reservas = HotelReservas.leerReservas(ruta);
        
        System.out.println("Reservas > 300€:");
        HotelReservas.mostrarImporteMayor300(reservas);

        System.out.println("\nClientes de Bilbao:");
        System.out.println(HotelReservas.clientesDeBilbao(reservas));

        System.out.println("\nClientes con ≥ 3 noches:");
        System.out.println(HotelReservas.clientesConMinTresNoches(reservas));

        System.out.println("\nFacturación total:");
        System.out.println(HotelReservas.facturacionTotal(reservas));

        System.out.println("\nImporte medio:");
        System.out.println(HotelReservas.calcularMediaImporte(reservas));

        System.out.println("\nReservas por habitación:");
        System.out.println(HotelReservas.reservasPorHabitacion(reservas));

        System.out.println("\nFacturación por ciudad:");
        System.out.println(HotelReservas.facturacionPorCiudad(reservas));

        System.out.println("\nReserva de mayor importe:");
        System.out.println(HotelReservas.reservaMayorImporte(reservas));

        System.out.println("\nPorcentaje > 300€:");
        System.out.println(HotelReservas.porcentajeMayor300(reservas));

        System.out.println("\nServicios únicos:");
        System.out.println(HotelReservas.serviciosSinRepetir(reservas));

        System.out.println("\nConteo de servicios:");
        System.out.println(HotelReservas.contarServicios(reservas));

        System.out.println("\nTop 3 reservas:");
        System.out.println(HotelReservas.top3(reservas));
        System.out.println("\nPeek:");
        HotelReservas.mostrarPeek(reservas);

        HotelReservas.escribirReservasCaras("reservas_caras.txt", reservas);
        System.out.println("\nEl fichero reservas_caras.txt creado correctamente");
    }
}
