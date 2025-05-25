
package sistemaventas;

import java.util.ArrayList;


public class SistemaVentas {

    
    public static void main(String[] args) {
        
        ArrayList<Producto> listaProducto = new ArrayList<>();

        
        listaProducto.add(new Producto("Aceite", 0.99, 3));
        listaProducto.add(new Producto("Galletas", 1.20, 2));
        listaProducto.add(new Producto("Balon de futbol", 2.50, 1));

        
        for (Producto producto1 : listaProducto) {
            System.out.print(producto1.toString());
        }

        
        CarritoDeCompras carrito = new CarritoDeCompras(listaProducto); 
        carrito.montoCliente = 15.00; 

        System.out.println("\n=== Detalle de Compra ===");
        carrito.calcularTotal();
        carrito.realizarPago();
        System.out.println();
        carrito.mostrarDetalleCompra();

        System.out.println("\n");
    }
}


class Producto {

    public String nombre;
    public double precio;
    public int cantidad;

    
    public Producto() {
    }

    public Producto(String nombre, double precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre
                + "\nPrecio: " + precio
                + "\nCantidad: " + cantidad
                + "\n";
    }

}


class CarritoDeCompras {

    public ArrayList<Producto> listaProductos;
    public double montoCliente;
    public double totalPagar;

    
    public CarritoDeCompras() {
        this.listaProductos = new ArrayList<>();
    }

    public CarritoDeCompras(ArrayList<Producto> productos) {
        this.listaProductos = productos;
    }

    //Métodos
    public double calcularTotal() {
        totalPagar = 0;
        for (Producto producto : listaProductos) {
            totalPagar += producto.precio * producto.cantidad;
        }
        return totalPagar;
    }

    public void realizarPago() {
        if (montoCliente > totalPagar) {
            System.out.print("Pago exitoso.");
            System.out.print("\nSobrante: " + (montoCliente - totalPagar));
        } else if (montoCliente < totalPagar) {
            System.out.print("Pago insuficiente.");
            System.out.print("\nFaltante: " + (totalPagar - montoCliente));
        } else {
            System.out.print("Pago exacto realizado.");
            System.out.print("\nSobrante: 0.0");
        }
    }

    public void mostrarDetalleCompra() {
        System.out.println("\n\n=== Productos Comprados ===");
        for (Producto producto : listaProductos) {
            System.out.print(producto.toString());
        }

        System.out.println("Total a pagar: " + totalPagar);
        System.out.println("Total de efectivo recibido: " + montoCliente);
    }
}
