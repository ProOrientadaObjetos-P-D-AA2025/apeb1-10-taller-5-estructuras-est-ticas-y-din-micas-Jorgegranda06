
package appfiscalia;

import java.util.ArrayList;
import java.util.Scanner;


public class Appfiscalia {

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char opcion = 'S';
        
        String[] nombresPersonas = {"Juan Guevara", "Ana Torres", "Matias Ochoa", "Lionel Gutierrez", "Andres Sanchez"};
        String[] ocupaciones = {"Funcionario pblico", "Contador", "Abogado", "Empresario", "Juez"};
        String[] roles = {"Acusado", "Testigo", "Victima", "Abogado defensor"};

        while (opcion == 'S' || opcion == 's') {
            System.out.println("\n=== NUEVO CASO DE CORRUPCION ===");

            
            String[] fechaInicio = {
                String.valueOf(2023 + (int) (Math.random() * 3)), 
                String.valueOf(1 + (int) (Math.random() * 12)), 
                String.valueOf(1 + (int) (Math.random() * 28)) 
            };
            CasoCorrupcion caso = new CasoCorrupcion(
                    "Caso " + (char) (65 + (int) (Math.random() * 26)) + "-" + (1000 + (int) (Math.random() * 9000)),
                    fechaInicio, "Presunto delito de " + new String[]{"sobornos", "malversacion", "trafico de influencias"}[(int) (Math.random() * 3)]);

            
            int numPersonas = 1 + (int) (Math.random() * 4);
            ArrayList<PersonaImplicada> personas = new ArrayList<>();

            for (int i = 0; i < numPersonas; i++) {
                personas.add(new PersonaImplicada(
                        nombresPersonas[(int) (Math.random() * nombresPersonas.length)],
                        25 + (int) (Math.random() * 40), 
                        ocupaciones[(int) (Math.random() * ocupaciones.length)],
                        roles[(int) (Math.random() * roles.length)],
                        (int) (Math.random() * 2), 
                        Math.random() * 5, 
                        10000 + (Math.random() * 90000) 
                ));
            }
            caso.personas = personas;
            
            caso.actualizarEstado();
            caso.mostrarInfo();
            System.out.print("\n¿Desea crear otro caso? (S/N): ");
            opcion = sc.next().charAt(0);
        }
        System.out.println("\n=== PROGRAMA FINALIZADO ===");
    }
}

class CasoCorrupcion {

    String nombre;
    String[] fechaInicio; 
    String estado;
    String detalles;
    ArrayList<PersonaImplicada> personas;

    public CasoCorrupcion(String nombre, String[] fechaInicio, String detalles) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.detalles = detalles;
        this.estado = "Iniciado";
        this.personas = new ArrayList<>();
    }

    public void actualizarEstado() {
        
        int diasTranscurridos = (int) (Math.random() * 30); 

        if (diasTranscurridos > 14) {
            estado = "URGENTE (mas de 2 semanas)";
        } else if (diasTranscurridos > 7) {
            estado = "Alerta (mas de 1 semana)";
        }
    }

    public double calcularFianza(PersonaImplicada persona) {
        if (persona.nivelImplicacion.equals("Acusado") && persona.colabora == 1 && persona.sentencia < 1) {
            return persona.danioEconomico * 0.5;
        }
        return 0;
    }

    public void mostrarInfo() {
        System.out.println("\n--- DATOS DEL CASO ---");
        System.out.println("Nombre: " + nombre);
        System.out.println("Fecha inicio: " + fechaInicio[2] + "/" + fechaInicio[1] + "/" + fechaInicio[0]);
        System.out.println("Estado: " + estado);
        System.out.println("Detalles: " + detalles);

        System.out.println("\n--- PERSONAS IMPLICADAS ---");
        for (PersonaImplicada persona : personas) {
            System.out.println("Nombre: " + persona.nombre);
            System.out.println("  Edad: " + persona.edad + " anios");
            System.out.println("  Ocupacion: " + persona.ocupacion);
            System.out.println("  Rol: " + persona.nivelImplicacion);
            System.out.println("  Colabora: " + (persona.colabora == 1 ? "Si" : "No"));

            if (persona.nivelImplicacion.equals("Acusado")) {
                System.out.println("  Sentencia: " + String.format("%.1f", persona.sentencia) + " años");
                System.out.println("  Danio economico: $" + String.format("%,.2f", persona.danioEconomico));
                double fianza = calcularFianza(persona);
                if (fianza > 0) {
                    System.out.println("  [FIANZA CALCULADA]: $" + String.format("%,.2f", fianza));
                }
            }
            System.out.println("----------------------");
        }
    }

    @Override
    public String toString() {
        return "CasoCorrupcion{"
                + "\nNombre:" + nombre
                + "\nFecha inicio: " + fechaInicio
                + "\nEstado: " + estado
                + "\nDetalles: " + detalles
                + "\nPersonas: " + personas
                + "\n}\n";
    }

}

class PersonaImplicada {

    String nombre;
    int edad;
    String ocupacion;
    String nivelImplicacion;
    int colabora; 
    double sentencia; 
    double danioEconomico;

    public PersonaImplicada(String nombre, int edad, String ocupacion,
            String nivelImplicacion, int colabora,
            double sentencia, double danioEconomico) {
        this.nombre = nombre;
        this.edad = edad;
        this.ocupacion = ocupacion;
        this.nivelImplicacion = nivelImplicacion;
        this.colabora = colabora;
        this.sentencia = sentencia;
        this.danioEconomico = danioEconomico;
    }

    @Override
    public String toString() {
        return "PersonaImplicada{"
                + "\nNombre: " + nombre
                + "\nEdad: " + edad
                + "\nOcupacion: " + ocupacion
                + "\nNivel implicacion: " + nivelImplicacion
                + "\nColabora: " + colabora
                + "\nSentencia: " + sentencia
                + "\nDanio economico: " + danioEconomico
                + "\n}\n";
    }
    
}
