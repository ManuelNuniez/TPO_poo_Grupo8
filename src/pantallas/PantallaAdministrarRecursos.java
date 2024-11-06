package pantallas;

import modelo.Evento;
import modelo.Recurso;
import modelo.Teclado;

import java.util.List;
import java.util.Scanner;

public class PantallaAdministrarRecursos {

    private final Teclado teclado;


    public PantallaAdministrarRecursos(Scanner teclado) {
        this.teclado = new Teclado();
    }

    public void iniciar(Evento eventoAEditar) {
        int opcion = -1;
        while (opcion != 0) {
            mostrarRecursos(eventoAEditar);
            System.out.println(); //espacio entre el listado y el menu
            var menu = """
                    1 - Agregar
                    2 - Eliminar
                    3 - Editar
                    0 - Volver al menu anterior
                    """;
            System.out.println(menu);
            opcion = teclado.leerNumero();
            teclado.leerLinea();

            switch (opcion) {
                case 1:
                    agregarRecurso(eventoAEditar);
                    break;
                case 2:
                    eliminarRecurso(eventoAEditar);
                    break;
                case 0:
                    System.out.println("Volviendo al menu principal");
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void eliminarRecurso(Evento eventoAEditar) {
        System.out.println("Ingresa el numero del recurso a eliminar");
        int numero = teclado.leerNumero();
        int tamanioLista = eventoAEditar.cantidadRecursos();
        if (numero <= tamanioLista) {
            eventoAEditar.eliminarRecurso(numero - 1);
        }

    }

    private void agregarRecurso(Evento eventoAEditar) {
        System.out.println("Ingresa recurso a agregar :");
        String recurso = teclado.leerLinea();
        eventoAEditar.agregarRecurso(recurso);
    }


    private void mostrarRecursos(Evento eventoAEditar) {
        if (eventoAEditar.tieneRecursos()) {
            int numeracion = 1;
            System.out.println("Recursos: ");
            List<Recurso> recursos = eventoAEditar.getRecursos();
            for (Recurso in : recursos) {
                System.out.println(numeracion + ".  " + in.getNombre());
                numeracion += 1;
            }
        } else {
            System.out.println("No hay recursos asignados en este evento");
        }
    }
}
