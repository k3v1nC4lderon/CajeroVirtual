/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cajero;

/**
 *
 * @author ETHAN PIERCE
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class Cuentas {

    String nombre, Ap, Am, tipoCuenta, usuario;
    int ci, dia, mes, anio, pin;
    final String rutaArchivo = "usuario.txt"; // Ruta relativa desde el directorio raíz del proyecto

    List<String> lineas;

    public Cuentas(String nombre, String Ap, String Am, int ci, int dia, int mes, int anio, String tipoCuenta) {
        this.nombre = nombre;
        this.Ap = Ap;
        this.Am = Am;
        this.tipoCuenta = tipoCuenta;
        this.ci = ci;
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        lineas = leerArchivo("usuario.txt");
    }

    public Cuentas(String nombre, String Ap, String Am, int ci, int dia, int mes, int anio, String tipoCuenta, String usuario, int pin) {
        this.nombre = nombre;
        this.Ap = Ap;
        this.Am = Am;
        this.tipoCuenta = tipoCuenta;
        this.ci = ci;
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.usuario = usuario;
        this.pin = pin;
    }

    public Cuentas() {
        lineas = leerArchivo("usuario.txt");
    }

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

    public boolean leerCuentasCrear() {

        // Verificar si el archivo existe
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            System.out.println("El archivo no existe. Creando...");
            try {
                archivo.createNewFile();
                FileWriter writer = new FileWriter(archivo);
                // Puedes escribir algún contenido predeterminado si lo deseas
                //writer.write("usuario2,pin2;" + generateAccountNumber() + ",checking;Jane,Smith,Doe,1985-05-15,XYZ789\n");
                writer.close();
                System.out.println("Archivo creado con éxito.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //String nombreArchivo = "../usuarios.txt";
        boolean existe = false;
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(";");
                String[] infoUsuario = partes[0].split(",");
                //String usuario = infoUsuario[0];
                //String pin = infoUsuario[1];
                String[] infoCuenta = partes[1].split(",");
                //String numeroCuenta = infoCuenta[0];
                String tipoCuenta = infoCuenta[1];
                String[] infoNombre = partes[2].split(",");
                //String nombre = infoNombre[0];
                //String apellido1 = infoNombre[1];
                //String apellido2 = infoNombre[2];
                //String fechaNacimiento = infoNombre[3];
                int codigo = Integer.parseInt(infoNombre[6]);
                existe = comprobarCiYTipoCuenta(codigo, tipoCuenta);
                if (existe == true) {
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return existe;
    }

    public boolean comprobarCiYTipoCuenta(int ci, String tipoCuenta) {
        return this.ci == ci && this.tipoCuenta.equals(tipoCuenta);
    }

    public boolean cuentaExistente() {
        return true;
    }

    public boolean comprobarUsuario(String usuario, int pin) {
        boolean existe = false;
        if (!lineas.isEmpty()) {
            for (String linea : lineas) {
                String[] partes = linea.split(";");
                String[] infoUsuario = partes[0].split(",");
                String usuarioLinea = infoUsuario[0];
                int pinLinea = Integer.parseInt(infoUsuario[1]);
                String[] infoCuenta = partes[1].split(",");
                //String numeroCuenta = infoCuenta[0];
                //String tipoCuenta = infoCuenta[1];
                String[] infoNombre = partes[2].split(",");
                //String nombre = infoNombre[0];
                //String apellido1 = infoNombre[1];
                //String apellido2 = infoNombre[2];
                //int dia = Integer.parseInt(infoNombre[3]);
                //int mes = Integer.parseInt(infoNombre[4]);
                //int anio = Integer.parseInt(infoNombre[5]);
                //int codigo = Integer.parseInt(infoNombre[6]);
                if (usuario.equals(usuarioLinea) && pin == pinLinea) {
                    existe = true;
                    break;
                }
            }
        }
        return existe;
    }

    public boolean crearCuentaExiste(int ci, String tipocuenta) {
        boolean comprobar = false;
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;

            boolean eCi = false;
            boolean eCuenta = true;
            int aux = 0;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(";");
                String[] infoUsuario = partes[0].split(",");
                String usuario = infoUsuario[0];
                String pin = infoUsuario[1];
                String[] infoCuenta = partes[1].split(",");
                //String numeroCuenta = infoCuenta[0];
                String tipoCuenta = infoCuenta[1];
                String[] infoNombre = partes[2].split(",");
                String nombre = infoNombre[0];
                String apellido1 = infoNombre[1];
                String apellido2 = infoNombre[2];
                int dias = Integer.parseInt(infoNombre[3]);
                int meses = Integer.parseInt(infoNombre[4]);
                int anios = Integer.parseInt(infoNombre[5]);
                int codigo = Integer.parseInt(infoNombre[6]);
                if (!eCi) {
                    if (ci == codigo) {
                        eCi = true;
                    }
                }
                if (eCuenta) {
                    if (!tipoCuenta.equals(tipocuenta) && ci == codigo) {
                        aux++;
                        System.out.println(aux);
                    }
                }
                if (eCi && aux <= 2) {
                    this.usuario = usuario;
                    this.pin = Integer.parseInt(pin);
                    this.nombre = nombre;
                    this.Ap = apellido1;
                    this.Am = apellido2;
                    this.ci = ci;
                    this.dia = dias;
                    this.mes = meses;
                    this.anio = anios;
                    crearCuenta();
                    comprobar = true;
                    System.out.println(comprobar);
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return comprobar;
    }

    //Para crear la cuenta
    public void crearCuenta() {
        String filename = "usuario.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            // Escribir datos en el archivo para dos usuarios de ejemplo
            //writer.write("usuario1,pin1;" + generateAccountNumber() + ",savings;John,Doe,Smith,01,01,2001,ABC123\n");
            //writer.write("usuario2,pin2;" + generateAccountNumber() + ",checking;Jane,Smith,Doe,1985-05-15,XYZ789\n");
            // Agrega más líneas según sea necesario
            writer.write(usuario + "," + pin + ";" + generateAccountNumber() + "," + tipoCuenta + "," + "0" + ";" + nombre + "," + Ap + "," + Am + "," + dia + "," + mes + "," + anio + "," + ci + "\n");
            System.out.println("Datos agregados exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public int generateAccountNumber() {
        String ultimo = leerUltimoElemento(rutaArchivo);
        if (ultimo != null) {
            String[] partes = ultimo.split(";");
            String datosCuenta[] = partes[1].split(",");
            int numeroCuenta = Integer.parseInt(datosCuenta[0]);

            return numeroCuenta + 1;
        } else {
            return 1;
        }//Aqui incremento en 1 el ultimo numero de cuenta existente
    }

    public static String leerUltimoElemento(String rutaArchivo) {
        String ultimoElemento = null;
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                ultimoElemento = linea; // Guarda cada línea mientras lees
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return ultimoElemento;
    }

    // Iniciar Sesion
    public boolean iniciarSesion(String usuario, int pin) {
        boolean existe = false;
        if (!lineas.isEmpty()) {
            for (String linea : lineas) {
                String[] partes = linea.split(";");
                String[] infoUsuario = partes[0].split(",");
                String usuarioLinea = infoUsuario[0];
                int pinLinea = Integer.parseInt(infoUsuario[1]);
                String[] infoCuenta = partes[1].split(",");
                //String numeroCuenta = infoCuenta[0];
                //String tipoCuenta = infoCuenta[1];
                String[] infoNombre = partes[2].split(",");
                //String nombre = infoNombre[0];
                //String apellido1 = infoNombre[1];
                //String apellido2 = infoNombre[2];
                //int dia = Integer.parseInt(infoNombre[3]);
                //int mes = Integer.parseInt(infoNombre[4]);
                //int anio = Integer.parseInt(infoNombre[5]);
                //int codigo = Integer.parseInt(infoNombre[6]);
                if (usuario.equals(usuarioLinea) && pin == pinLinea) {
                    existe = true;
                    break;
                }
            }
        }
        return existe;
    }
    
    //Tener nombre
        public String getNombreCuenta(String userR) {
        String nombreC = "NA";
        if (!lineas.isEmpty()) {
            for (String linea : lineas) {
                String[] partes = linea.split(";");
                String[] infoUsuario = partes[0].split(",");
                String usuarioLinea = infoUsuario[0];
                int pinLinea = Integer.parseInt(infoUsuario[1]);
                String[] infoCuenta = partes[1].split(",");
                String numeroCuenta = infoCuenta[0];
                String tipoCuentaLinea = infoCuenta[1];
                String[] infoNombre = partes[2].split(",");
                String nombre = infoNombre[0];
                String apellido1 = infoNombre[1];
                String apellido2 = infoNombre[2];
                //int dia = Integer.parseInt(infoNombre[3]);
                //int mes = Integer.parseInt(infoNombre[4]);
                //int anio = Integer.parseInt(infoNombre[5]);
                //int codigo = Integer.parseInt(infoNombre[6]);
                if (userR.equals(usuarioLinea)) {
                    nombreC = nombre+" "+apellido1+" "+apellido2;
                    break;
                }
            }
        }
        return nombreC;
    }

    //Tener el numero de cuenta
    public String getnCuenta(String userR, String tipoCuentaR) {
        String nCuentaActual = "NA";
        if (!lineas.isEmpty()) {
            for (String linea : lineas) {
                String[] partes = linea.split(";");
                String[] infoUsuario = partes[0].split(",");
                String usuarioLinea = infoUsuario[0];
                int pinLinea = Integer.parseInt(infoUsuario[1]);
                String[] infoCuenta = partes[1].split(",");
                String numeroCuenta = infoCuenta[0];
                String tipoCuentaLinea = infoCuenta[1];
                String[] infoNombre = partes[2].split(",");
                //String nombre = infoNombre[0];
                //String apellido1 = infoNombre[1];
                //String apellido2 = infoNombre[2];
                //int dia = Integer.parseInt(infoNombre[3]);
                //int mes = Integer.parseInt(infoNombre[4]);
                //int anio = Integer.parseInt(infoNombre[5]);
                //int codigo = Integer.parseInt(infoNombre[6]);
                if (userR.equals(usuarioLinea) && tipoCuentaR.equals(tipoCuentaLinea)) {
                    nCuentaActual = numeroCuenta;
                    break;
                }
            }
        }
        return nCuentaActual;
    }

    //Obtener saldo
    public String getSaldo(String nCuenta, String tipoCuenta) {
        String saldo = "";
        if (!lineas.isEmpty()) {
            for (String linea : lineas) {
                String[] partes = linea.split(";");
                String[] infoUsuario = partes[0].split(",");
                String usuarioLinea = infoUsuario[0];
                int pinLinea = Integer.parseInt(infoUsuario[1]);
                String[] infoCuenta = partes[1].split(",");
                String numeroCuenta = infoCuenta[0];
                String tipoCuentaLinea = infoCuenta[1];
                saldo = infoCuenta[2];
                String[] infoNombre = partes[2].split(",");
                //String nombre = infoNombre[0];
                //String apellido1 = infoNombre[1];
                //String apellido2 = infoNombre[2];
                //int dia = Integer.parseInt(infoNombre[3]);
                //int mes = Integer.parseInt(infoNombre[4]);
                //int anio = Integer.parseInt(infoNombre[5]);
                //int codigo = Integer.parseInt(infoNombre[6]);
                if (nCuenta.equals(numeroCuenta) && tipoCuentaLinea.equals(tipoCuenta)) {

                    break;
                }
            }
        }
        return saldo;
    }

    //Retirar
    public void retirar(int monto, String nCuenta) {
        String nuevaLinea = "No entre al if";
        System.out.println(nCuenta);
        if (!lineas.isEmpty()) {
            for (String linea : lineas) {
                String[] partes = linea.split(";");
                String[] infoUsuario = partes[0].split(",");
                String usuarioLinea = infoUsuario[0];
                int pinLinea = Integer.parseInt(infoUsuario[1]);
                String[] infoCuenta = partes[1].split(",");
                String numeroCuenta = infoCuenta[0];
                String tipoCuentaLinea = infoCuenta[1];
                String saldo = infoCuenta[2];
                String[] infoNombre = partes[2].split(",");
                String nombre = infoNombre[0];
                String apellido1 = infoNombre[1];
                String apellido2 = infoNombre[2];
                int dia = Integer.parseInt(infoNombre[3]);
                int mes = Integer.parseInt(infoNombre[4]);
                int anio = Integer.parseInt(infoNombre[5]);
                int codigo = Integer.parseInt(infoNombre[6]);
                System.out.println("Cuenta de linea: " + numeroCuenta);
                if (nCuenta.equals(numeroCuenta)) {
                    // Modificar solo el valor de saldo
                    System.out.println("Llegue al if: " + nCuenta);
                    int nuevoSaldo = Integer.parseInt(saldo) - monto; // Aquí asigna el nuevo valor de saldo
                    String nuevoS = String.valueOf(nuevoSaldo);
                    // Reconstruir la línea con el nuevo saldo
                    nuevaLinea = usuarioLinea + "," + pinLinea + ";" + nCuenta + "," + tipoCuentaLinea + "," + nuevoS + ";" + nombre + "," + apellido1 + "," + apellido2 + "," + dia + "," + mes + "," + anio + "," + codigo;
                    break;
                }
            }
        }
        System.out.println(nuevaLinea);

        try {
            // Lectura del archivo y almacenamiento de las línea

            // Buscar y reemplazar la línea
            for (int i = 0; i < lineas.size(); i++) {
                if (lineas.get(i).contains(nCuenta)) {
                    lineas.set(i, nuevaLinea);
                    break; // Salir del bucle una vez que se haya hecho el reemplazo
                }
            }

            // Escritura de las líneas en el archivo
            BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo));
            for (String line : lineas) {
                bw.write(line + "\n");
            }
            bw.close();
            System.out.println("Nueva linea: " + nuevaLinea);
            System.out.println("Línea sobreescribida exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Depositar
    public void depositar(double monto, String nCuenta) {
        String nuevaLinea = "No entre al if";
        String comprobador = "";
        System.out.println(nCuenta);
        if (!lineas.isEmpty()) {
            for (String linea : lineas) {
                String[] partes = linea.split(";");
                String[] infoUsuario = partes[0].split(",");
                String usuarioLinea = infoUsuario[0];
                int pinLinea = Integer.parseInt(infoUsuario[1]);
                String[] infoCuenta = partes[1].split(",");
                String numeroCuenta = infoCuenta[0];
                String tipoCuentaLinea = infoCuenta[1];
                String saldo = infoCuenta[2];
                String[] infoNombre = partes[2].split(",");
                String nombre = infoNombre[0];
                String apellido1 = infoNombre[1];
                String apellido2 = infoNombre[2];
                int dia = Integer.parseInt(infoNombre[3]);
                int mes = Integer.parseInt(infoNombre[4]);
                int anio = Integer.parseInt(infoNombre[5]);
                int codigo = Integer.parseInt(infoNombre[6]);
                System.out.println("Cuenta de linea: " + numeroCuenta);
                if (nCuenta.equals(numeroCuenta)) {
                    // Modificar solo el valor de saldo
                    System.out.println("Llegue al if: " + nCuenta);
                    double nuevoSaldo = Double.parseDouble(saldo) + monto; // Aquí asigna el nuevo valor de saldo
                    String nuevoS = String.valueOf(nuevoSaldo);
                    // Reconstruir la línea con el nuevo saldo
                    nuevaLinea = usuarioLinea + "," + pinLinea + ";" + nCuenta + "," + tipoCuentaLinea + "," + nuevoS + ";" + nombre + "," + apellido1 + "," + apellido2 + "," + dia + "," + mes + "," + anio + "," + codigo;
                    comprobador = usuarioLinea + "," + pinLinea + ";" + nCuenta;
                    break;
                }
            }
        }
        System.out.println(nuevaLinea);

        try {
            // Lectura del archivo y almacenamiento de las línea

            // Buscar y reemplazar la línea
            for (int i = 0; i < lineas.size(); i++) {
                if (lineas.get(i).contains(comprobador)) {
                    lineas.set(i, nuevaLinea);
                    break; // Salir del bucle una vez que se haya hecho el reemplazo
                }
            }

            // Escritura de las líneas en el archivo
            BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo));
            for (String line : lineas) {
                bw.write(line + "\n");
            }
            bw.close();
            System.out.println("Nueva linea: " + nuevaLinea);
            System.out.println("Línea sobreescribida exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //Recibir tipo de cuenta

    public String tipoCuenta(String nCuentaE) {
        if (!lineas.isEmpty()) {
            for (String linea : lineas) {
                String[] partes = linea.split(";");
                String[] infoUsuario = partes[0].split(",");
                String usuarioLinea = infoUsuario[0];
                int pinLinea = Integer.parseInt(infoUsuario[1]);
                String[] infoCuenta = partes[1].split(",");
                String numeroCuenta = infoCuenta[0];
                String tipoCuentaLinea = infoCuenta[1];
                String saldo = infoCuenta[2];
                String[] infoNombre = partes[2].split(",");
                String nombre = infoNombre[0];
                String apellido1 = infoNombre[1];
                String apellido2 = infoNombre[2];
                int dia = Integer.parseInt(infoNombre[3]);
                int mes = Integer.parseInt(infoNombre[4]);
                int anio = Integer.parseInt(infoNombre[5]);
                int codigo = Integer.parseInt(infoNombre[6]);
                System.out.println("Cuenta de linea: " + numeroCuenta);
                if (nCuentaE.equals(numeroCuenta)) {
                    return tipoCuentaLinea;
                }
            }
        }
        return "Error";
    }
    //Verificar si existe cuenta

    public boolean existeCuenta(String nCuentaE) {
        boolean existe = false;
        if (!lineas.isEmpty()) {
            for (String linea : lineas) {
                String[] partes = linea.split(";");
                String[] infoUsuario = partes[0].split(",");
                String usuarioLinea = infoUsuario[0];
                int pinLinea = Integer.parseInt(infoUsuario[1]);
                String[] infoCuenta = partes[1].split(",");
                String numeroCuenta = infoCuenta[0];
                String tipoCuentaLinea = infoCuenta[1];
                String saldo = infoCuenta[2];
                String[] infoNombre = partes[2].split(",");
                String nombre = infoNombre[0];
                String apellido1 = infoNombre[1];
                String apellido2 = infoNombre[2];
                int dia = Integer.parseInt(infoNombre[3]);
                int mes = Integer.parseInt(infoNombre[4]);
                int anio = Integer.parseInt(infoNombre[5]);
                int codigo = Integer.parseInt(infoNombre[6]);
                System.out.println("Cuenta de linea: " + numeroCuenta);
                if (nCuentaE.equals(numeroCuenta)) {
                    existe = true;
                    break;
                }
            }
        }
        return existe;
    }

    //Transferir a otra cuenta
    public void transferir(int monto, String nCuenta) {
        String nuevaLinea = "";
        String nuevaLineaR = "";
        String comprobar = "";
        String comprobarR = "";
        if (!lineas.isEmpty()) {
            for (String linea : lineas) {
                String[] partes = linea.split(";");
                String[] infoUsuario = partes[0].split(",");
                String usuarioLinea = infoUsuario[0];
                int pinLinea = Integer.parseInt(infoUsuario[1]);
                String[] infoCuenta = partes[1].split(",");
                String numeroCuenta = infoCuenta[0];
                String tipoCuentaLinea = infoCuenta[1];
                String saldo = infoCuenta[2];
                String[] infoNombre = partes[2].split(",");
                String nombre = infoNombre[0];
                String apellido1 = infoNombre[1];
                String apellido2 = infoNombre[2];
                int dia = Integer.parseInt(infoNombre[3]);
                int mes = Integer.parseInt(infoNombre[4]);
                int anio = Integer.parseInt(infoNombre[5]);
                int codigo = Integer.parseInt(infoNombre[6]);
                System.out.println("Cuenta de linea: " + numeroCuenta);
                if (nCuenta.equals(numeroCuenta)) {
                    // Modificar solo el valor de saldo
                    System.out.println("Llegue al if: " + nCuenta);
                    double nuevoSaldo = Double.parseDouble(saldo) - monto; // Aquí asigna el nuevo valor de saldo
                    String nuevoS = String.valueOf(nuevoSaldo);
                    // Reconstruir la línea con el nuevo saldo
                    nuevaLinea = usuarioLinea + "," + pinLinea + ";" + nCuenta + "," + tipoCuentaLinea + "," + nuevoS + ";" + nombre + "," + apellido1 + "," + apellido2 + "," + dia + "," + mes + "," + anio + "," + codigo;
                    comprobar = usuarioLinea + "," + pinLinea + ";" + nCuenta;
                    break;

                    }

                }
            
                    System.out.println("Lineas Normales");
                    for (int i = 0; i < lineas.size(); i++) {
                        if (lineas.get(i).contains(comprobar)) {
                            lineas.set(i, nuevaLinea);
                            break;
                            // Salir del bucle una vez que se haya hecho el reemplazo
                        }
                        System.out.println(lineas.get(i));
                    }
            }
        }
        public void transferirReceptor(double monto, String nCuentaR){
            String nuevaLinea = "";
            String nuevaLineaR = "";
            String comprobar = "";
            String comprobarR = "";
            if (!lineas.isEmpty()) {
                for (String linea : lineas) {
                    String[] partes = linea.split(";");
                    String[] infoUsuario = partes[0].split(",");
                    String usuarioLinea = infoUsuario[0];
                    int pinLinea = Integer.parseInt(infoUsuario[1]);
                    String[] infoCuenta = partes[1].split(",");
                    String numeroCuenta = infoCuenta[0];
                    String tipoCuentaLinea = infoCuenta[1];
                    String saldo = infoCuenta[2];
                    String[] infoNombre = partes[2].split(",");
                    String nombre = infoNombre[0];
                    String apellido1 = infoNombre[1];
                    String apellido2 = infoNombre[2];
                    int dia = Integer.parseInt(infoNombre[3]);
                    int mes = Integer.parseInt(infoNombre[4]);
                    int anio = Integer.parseInt(infoNombre[5]);
                    int codigo = Integer.parseInt(infoNombre[6]);
                    System.out.println("Cuenta de linea: " + numeroCuenta);
                    if (nCuentaR.equals(numeroCuenta)) {
                        // Modificar solo el valor de saldo
                        System.out.println("Llegue al if R: " + nCuentaR);
                        double nuevoSaldo = Double.parseDouble(saldo) + monto; // Aquí asigna el nuevo valor de saldo
                        String nuevoS = String.valueOf(nuevoSaldo);
                        // Reconstruir la línea con el nuevo saldo
                        nuevaLineaR = usuarioLinea + "," + pinLinea + ";" + nCuentaR + "," + tipoCuentaLinea + "," + nuevoS + ";" + nombre + "," + apellido1 + "," + apellido2 + "," + dia + "," + mes + "," + anio + "," + codigo;
                        comprobarR = usuarioLinea + "," + pinLinea + ";" + nCuentaR;
                        break;

                    }

                }
                //System.out.println("Nueva linea: " + nuevaLinea);
                //System.out.println("Nueva lineaR: " + nuevaLineaR);
                try {
                    // Lectura del archivo y almacenamiento de las línea

                    // Buscar y reemplazar la línea
                    
                    System.out.println("Lineas R");
                    for (int i = 0; i < lineas.size(); i++) {
                        if (lineas.get(i).contains(comprobarR)) {
                            lineas.set(i, nuevaLineaR);

                            // Salir del bucle una vez que se haya hecho el reemplazo
                        }
                        System.out.println(lineas.get(i));
                    }

                    // Escritura de las líneas en el archivo
                    BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo));
                    for (String line : lineas) {
                        bw.write(line + "\n");
                    }
                    bw.close();
                    //System.out.println("Nueva linea: " + nuevaLinea);
                    System.out.println("Línea sobreescribida exitosamente.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
        
        }
        }
}
