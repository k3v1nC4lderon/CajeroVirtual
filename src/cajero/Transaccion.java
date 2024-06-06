/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cajero;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ETHAN PIERCE
 */
public class Transaccion {

    final String rutaArchivo = "transacciones.txt";
    List<String> lineas;

    public Transaccion() {
        lineas = leerArchivo(rutaArchivo);
    }

    //Lee las lineas
    public List<String> leerArchivo(String rutaArchivo) {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return lineas;
    }

    //Crea el txt
    public void crearTransaccion(String nCuenta, String tipoTransaccion, double monto, String divisa, String nCuentaR) {
        if(tipoTransaccion.equals("Transfirio")){
            Cuentas c = new Cuentas();
            divisa = c.tipoCuenta(nCuentaR);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            writer.write("Desde: "+nCuenta + "," + tipoTransaccion + "," + monto + divisa + ", A: " + nCuentaR + "\n");
            writer.write("A: "+nCuentaR + "," + "Te " +tipoTransaccion + "," + monto+ " " + divisa + ", Desde: " + nCuenta + "\n");
            System.out.println("Datos agregados exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
        }else{
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            writer.write("Desde: "+nCuenta + "," + tipoTransaccion + "," + monto + divisa + ", A: " + nCuentaR + "\n");
            System.out.println("Datos agregados exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
        }
    }

    //Lee el txt
    public StringBuilder leerTransaccionCuenta(String nCuenta) {
        StringBuilder transacciones = new StringBuilder();
        for (String linea : lineas) {
            String[] partes = linea.split(",");
            String emisor = partes[0];
            if (emisor.equals("Desde: "+nCuenta)|| emisor.equals("A: "+nCuenta)) {
                // Construir la cadena de transacciones concatenando cada línea
                transacciones.append(linea).append("\n");
            }
        }
        return transacciones;
    }
}
