
package help;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Paths;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Random;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import modelo.Alineacion;
import modelo.Carta;
import modelo.Configuracion;
import modelo.Reporte;

public class HelperJuego 
{
    public static String rutaGeneral = Paths.get("").toAbsolutePath().toString();
    public static String rutaArchivo = rutaGeneral+"/src/main/java/archivo";   
    
    //Metodo que te permite mostrar una alerta en cualquier momento
     public static void showMessage(Alert show, String titulo, String encabezado, String mensaje){
        show.setHeaderText(encabezado);
        show.setTitle(titulo);
        show.setContentText(mensaje);
        show.showAndWait();
    }
    
      //Permite convertir la fecha de tipo String a Date para ser guardada en el archivo
    public static Date StringToDateTime(String fecha)
    {        
        try {  
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fecha);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return new Date();
    }
    
    //Permite convertir la fecha de tipo Date a String para ser guardada en el archivo
    public static String dateTimeToString(Date fecha)
    {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(fecha);
    }
    
    public static ArrayList<Reporte> consultarReportesFecha(Date fechaInicio, Date fechaFin)
    {
        ArrayList<Reporte> reportes = new ArrayList<>();
        ArrayList<Reporte> reportesTemp = Reporte.desserializarReporte("Reporte.ser");
        for(Reporte reporte: reportesTemp)
        {
            if(reporte.getFechaReporte().after(fechaInicio) && reporte.getFechaReporte().before(fechaFin))
                reportes.add(reporte);
        }
        return reportes;
    }
    
    public static Alineacion generarAlineacionGanadora()
    {
        Alineacion alineacionGanadora;
        ArrayList<Alineacion> listaAlineaciones = new ArrayList<>();
        listaAlineaciones.add(Alineacion.FILA);
        listaAlineaciones.add(Alineacion.COLUMNA);
        listaAlineaciones.add(Alineacion.ESQUINAS);
        listaAlineaciones.add(Alineacion.EN_CUALQUIER_ESQUINA);  
        
        //Generar alineacion al azar
        int indiceAlineacion = new Random().nextInt(3);
        alineacionGanadora = listaAlineaciones.get(indiceAlineacion);        
        return alineacionGanadora;        
    }
    
    public static String getAlineacionGanadora(Alineacion alineacion)
    {
        switch(alineacion)
        {
            case FILA:
                return "cualquierfila.png";                
                
            case COLUMNA:
                return "cualquiercolumna.png";
             
            case ESQUINAS:
                return "figurasenextremos.png";
                
            case EN_CUALQUIER_ESQUINA:
                return "figurasjuntasenesquina";
        }
        return "";
    }   
    
    public static Carta[][] generarTableroJugador()
    {
        Carta[][] tablero = new Carta[4][4];
        ArrayList<Carta> listaCartas = getListaCartas();                
        ArrayList<Integer> listaIndices = new ArrayList<>();
        int indice = 0, indiceCarta;
        while(indice <= 15)
        {
            indiceCarta = new Random().nextInt(53);
            if(!listaIndices.contains(indiceCarta))
            {
                listaIndices.add(indiceCarta);
                indice++;
            }
        }
        
        tablero[0][0] = listaCartas.get(listaIndices.get(0));
        tablero[0][1] = listaCartas.get(listaIndices.get(1));
        tablero[0][2] = listaCartas.get(listaIndices.get(2));
        tablero[0][3] = listaCartas.get(listaIndices.get(3));
        
        tablero[1][0] = listaCartas.get(listaIndices.get(4));
        tablero[1][1] = listaCartas.get(listaIndices.get(5));
        tablero[1][2] = listaCartas.get(listaIndices.get(6));
        tablero[1][3] = listaCartas.get(listaIndices.get(7));
        
        tablero[2][0] = listaCartas.get(listaIndices.get(8));
        tablero[2][1] = listaCartas.get(listaIndices.get(9));
        tablero[2][2] = listaCartas.get(listaIndices.get(10));
        tablero[2][3] = listaCartas.get(listaIndices.get(11));
        
        tablero[3][0] = listaCartas.get(listaIndices.get(12));
        tablero[3][1] = listaCartas.get(listaIndices.get(13));
        tablero[3][2] = listaCartas.get(listaIndices.get(14));
        tablero[3][3] = listaCartas.get(listaIndices.get(15));                                                       
        return tablero;        
    }
    
    public static ArrayList<Carta> getListaCartas()
    {
        String rutaNaipes = rutaArchivo+"/"+"cartasloteria.csv";
        ArrayList<Carta> naipes = new ArrayList<>();
        ArrayList<String> listaNaipes =  leerLineasArchivo(rutaNaipes);
        for(String naipeString : listaNaipes)
        {
            String[] naipePart    = naipeString.split(",");
            String numero         = naipePart[0];
            String nombreNaipe    = naipePart[1];
            
            String urlImagen = numero+".png";
            String urlImagenSeleccionado = numero+"seleccionado.png";
            String urlImagenSeleccionadoPC = "match.png";
            String urlImagenOcultaPC = "back.png";
            
            Image imagenNaipe = getImagenNaipe(urlImagen);
            Image imagenNaipeSeleccionado = getImagenNaipe(urlImagenSeleccionado);
            Image imagenNaipeSeleccionadoPC = getImagenNaipe(urlImagenSeleccionadoPC);
            Image imagenNaipeOcultoPC = getImagenNaipe(urlImagenOcultaPC);
            naipes.add(new Carta(
                                    Integer.parseInt(numero),
                                    nombreNaipe,
                                    urlImagen,
                                    imagenNaipe,
                                    imagenNaipeSeleccionado,
                                    imagenNaipeSeleccionadoPC,
                                    imagenNaipeOcultoPC));            
        }                
        return naipes;
    }
    
    public static Image getImagenNaipe(String imagenURL)
    {
        //Cargar la imagen del naipe
        File file;
        file = new File("src/main/resources/imagenesNaipe/"+imagenURL);
        Image  image = new Image(file.toURI().toString());        
        return image;
    } 
    
    public static ArrayList generarMasoNaipes()
    {
        ArrayList<Carta> listaNaipes = getListaCartas();      
        Collections.shuffle(listaNaipes);
        return listaNaipes;
    }
    
    public static boolean validarNaipeSeleccionado(int naipeSeleccionado, int naipeActual)
    {
        boolean estadoNaipeSeleccionado = false;
        if(naipeSeleccionado == naipeActual)
            estadoNaipeSeleccionado = true;
        return estadoNaipeSeleccionado;
    }
    
    public static boolean verificarJuegoGanado(Carta[][] tablero, Alineacion alineacion)
    {        
        switch(alineacion)
        {
            case FILA:
                return validarGanadorEnFila(tablero);
                
                case COLUMNA:
                    return validarGanadorEnColumna(tablero);
                
                case ESQUINAS:
                    return validarGanadorEnEsquinas(tablero);
                
                case EN_CUALQUIER_ESQUINA:
                    return validarCuatroFigurasEnEsquina(tablero);                                
        }
        return false;
    }
    
    public static boolean validarGanadorEnFila(Carta[][] tablero)
    {
        boolean primeraFila = tablero[0][0].getCartaSeleccionada() && 
                              tablero[0][1].getCartaSeleccionada() && 
                              tablero[0][2].getCartaSeleccionada() && 
                              tablero[0][3].getCartaSeleccionada(); 
        
        boolean segundaFila = tablero[1][0].getCartaSeleccionada() && 
                              tablero[1][1].getCartaSeleccionada() && 
                              tablero[1][2].getCartaSeleccionada() && 
                              tablero[1][3].getCartaSeleccionada();

        boolean terceraFila = tablero[2][0].getCartaSeleccionada() && 
                              tablero[2][1].getCartaSeleccionada() && 
                              tablero[2][2].getCartaSeleccionada() && 
                              tablero[2][3].getCartaSeleccionada();        
                
        boolean CuartaFila = tablero[3][0].getCartaSeleccionada() && 
                             tablero[3][1].getCartaSeleccionada() && 
                             tablero[3][2].getCartaSeleccionada() && 
                             tablero[3][3].getCartaSeleccionada();        
        
        return primeraFila || segundaFila || terceraFila || CuartaFila;
    }
    
    public static boolean validarGanadorEnColumna(Carta[][] tablero)
    {
        boolean primeraColumna = tablero[0][0].getCartaSeleccionada() && 
                                 tablero[1][0].getCartaSeleccionada() && 
                                 tablero[2][0].getCartaSeleccionada() && 
                                 tablero[3][0].getCartaSeleccionada(); 
        
        boolean segundaColumna = tablero[0][1].getCartaSeleccionada() && 
                                 tablero[1][1].getCartaSeleccionada() && 
                                 tablero[2][1].getCartaSeleccionada() && 
                                 tablero[3][1].getCartaSeleccionada();

        boolean terceraColumna = tablero[0][2].getCartaSeleccionada() && 
                                 tablero[1][2].getCartaSeleccionada() && 
                                 tablero[2][2].getCartaSeleccionada() && 
                                 tablero[2][3].getCartaSeleccionada();        
                
        boolean CuartaColumna = tablero[0][3].getCartaSeleccionada() && 
                                tablero[1][3].getCartaSeleccionada() && 
                                tablero[2][3].getCartaSeleccionada() && 
                                tablero[3][3].getCartaSeleccionada();        
        
        return primeraColumna || segundaColumna || terceraColumna || CuartaColumna;
    }
    
    public static boolean validarGanadorEnEsquinas(Carta[][] tablero)
    {
        boolean primeraEsquina = tablero[0][0].getCartaSeleccionada();         
        boolean segundaEsquina = tablero[0][3].getCartaSeleccionada();
        boolean terceraEsquina = tablero[3][0].getCartaSeleccionada();
        boolean cuartaEsquina  = tablero[3][3].getCartaSeleccionada();
        
        return primeraEsquina && segundaEsquina && terceraEsquina && cuartaEsquina;
    }
    
    public static boolean validarCuatroFigurasEnEsquina(Carta[][] tablero)
    {
        boolean primeraGrupo = tablero[0][0].getCartaSeleccionada() && 
                               tablero[0][1].getCartaSeleccionada() && 
                               tablero[1][0].getCartaSeleccionada() && 
                               tablero[1][1].getCartaSeleccionada(); 
        
        boolean segundoGrupo = tablero[0][2].getCartaSeleccionada() && 
                               tablero[0][3].getCartaSeleccionada() && 
                               tablero[1][2].getCartaSeleccionada() && 
                               tablero[1][3].getCartaSeleccionada();

        boolean tercerGrupo  = tablero[2][0].getCartaSeleccionada() && 
                               tablero[2][1].getCartaSeleccionada() && 
                               tablero[3][0].getCartaSeleccionada() && 
                               tablero[3][1].getCartaSeleccionada();        
                
        boolean CuartoGrupo  = tablero[2][2].getCartaSeleccionada() && 
                               tablero[2][3].getCartaSeleccionada() && 
                               tablero[3][2].getCartaSeleccionada() && 
                               tablero[3][3].getCartaSeleccionada();        
        
        return primeraGrupo || segundoGrupo || tercerGrupo || CuartoGrupo;
    }         

    //Permite leer las lineas que provienen del archivo 
    public static ArrayList<String> leerLineasArchivo(String name)
    {    
        ArrayList<String> lineasArchivo = new ArrayList<>();        
        BufferedReader reader;
        try
        {
            reader = new BufferedReader(new FileReader(name));
            String line = reader.readLine();
            while (line != null) 
            {    
                lineasArchivo.add(line);
                // read next line
                line = reader.readLine();                
            }
            reader.close();
        } catch (IOException e) {
                e.printStackTrace();
        }     
        return lineasArchivo;                 
    }
    
    public static Configuracion cargarConfiguracion() {
        try (var in = new ObjectInputStream(new FileInputStream("Configuracion.ser"))) {
            return (Configuracion)in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new Configuracion();
        }
    }
}
