package help;

import java.util.Comparator;
import modelo.Reporte;

public class OrdenadorFecha implements Comparator<Reporte>{
    //Permite ordenar de mas reciente a mas antigua los reportes de los juegos de los estudiantes
     @Override
     public int compare(Reporte o1, Reporte o2) {
         return o2.getFechaReporte().compareTo(o1.getFechaReporte());
     }  
}