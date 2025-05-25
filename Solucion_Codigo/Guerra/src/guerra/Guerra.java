
package guerra;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class Guerra {

   
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner tcl = new Scanner(System.in);
        char opcion = 'S';
        String nombresEventos [] = {"Batalla", "Tratado de paz", "Reunión Diplomatica"};
        while (opcion == 'S'){
            ArrayList<Pais> paises1 = new ArrayList<>(
                    Arrays.asList(new Pais("Bolivia", false, 200, 15),
                                new Pais("Ecuador", false, 150, 13),
                                new Pais("Uruguay", false, 180, 14)));
            ArrayList<Pais> paises2 = new ArrayList<>(
                    Arrays.asList(new Pais("Rusia", true, 300, 800),
                                new Pais("Eslovaquia", false, 190, 14),
                                new Pais("China", true, 120, 11)));
            ArrayList<Evento> eventos = new ArrayList<>(
                    Arrays.asList(new Evento(nombresEventos[rand.nextInt(nombresEventos.length)],
                                        LocalDate.of(2025, 4, 13),
                                        "Europa", "Problemas de territorio", false, paises1),
                                  new Evento(nombresEventos[rand.nextInt(nombresEventos.length)],
                                        LocalDate.of(2024, 7, 20),
                                        "Europa", "Problemas politicos", true, paises2)));
            Conflicto conflicto = new Conflicto("Reseteo", LocalDate.of(2025, 5, 15),100, eventos);
            conflicto.actualizarEstado();
            System.out.println(conflicto);
            System.out.print("SIMULAR MAS: ");
            opcion = tcl.next().charAt(0);
        }
    }  
}
class Conflicto{
    public String nombre;
    public LocalDate fechaInicio;
    public String estadoActual;
    public int totalPaisesMundo;
    public ArrayList<Evento> eventos;
    public Conflicto(String nombre, LocalDate fechaInicio, int totalPaisesMundo, ArrayList<Evento> eventos) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.totalPaisesMundo = totalPaisesMundo;
        this.eventos = eventos;
    }
    public void actualizarEstado(){
        int numeroPaisesBatalla = 0;
        for(Evento evento : eventos){
            numeroPaisesBatalla += (evento.nombre.equals("Batalla")) ? 1 : 0;
        }
        if (((numeroPaisesBatalla/100)*totalPaisesMundo)  > 50)
            this.estadoActual = "GUERRA MUNDIAL";
        else if (((numeroPaisesBatalla/100)*totalPaisesMundo)  > 30 && 
                ((numeroPaisesBatalla/100)*totalPaisesMundo)  <= 50)
            this.estadoActual = "CONVOCAR ONU URGENTE";
        for(Evento evento : eventos){
            for (Pais pais : evento.paises) {
                if (pais.estadoDesarrollo && evento.usoArmasNucleares){
                    this.estadoActual = "GUERRA MUNDIAL";
                    break;
                }
            }
        }
        for(Evento evento : eventos){
            for (Pais pais : evento.paises) {
                if (((pais.numeroBajas/100)*pais.totalHabitantes)  > 50 ) {
                    this.estadoActual = "CONVOCAR ONU URGENTE";
                    break;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Conflicto{" + "nombre=" + nombre + ", fechaInicio=" + 
                fechaInicio + ", estadoActual=" + estadoActual + ", "
                + "totalPaisesMundo=" + totalPaisesMundo + ", eventos=" + eventos + '}';
    }   
}
class Evento{
    public String nombre; 
    public LocalDate fecha;
    public String ubicacion;
    public String descripcion;
    public boolean usoArmasNucleares; 
    public ArrayList<Pais> paises;
    public Evento(String nombre, LocalDate fecha, String ubicacion, String descripcion, boolean usoArmasNucleares, ArrayList<Pais> paises) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
        this.descripcion = descripcion;
        this.usoArmasNucleares = usoArmasNucleares;
        this.paises = paises;
    }
    public String toString() {
        return "\n   Evento{" + "\n   nombre=" + nombre + ", \n   fecha=" + 
                fecha + ", \n   ubicacion=" + ubicacion + ", \n   "
                + "descripcion=" + descripcion + ", \n   paises=" + paises + ","
                + " \n   usoArmasNucleares=" + usoArmasNucleares + '}';
    }
}
class Pais{
    public String nombre;
    public boolean estadoDesarrollo; 
    public int totalHabitantes;
    public int numeroBajas;
    public Pais(String nombre, boolean estadoDesarrollo, int totalHabitantes, int numeroBajas) {
        this.nombre = nombre;
        this.estadoDesarrollo = estadoDesarrollo;
        this.totalHabitantes = totalHabitantes;
        this.numeroBajas = numeroBajas;
    }
    public String toString() {
        return "\n\t- Pais{" + "\n\tnombre=" + nombre + ", "
                + "\n\testadoDesarrollo=" + estadoDesarrollo + ", "
                + "\n\ttotalHabitantes=" + totalHabitantes + ", "
                + "\n\tnumeroBajas=" + numeroBajas + '}';
    }
    
    
}
