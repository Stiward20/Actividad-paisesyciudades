package Almacenamiento;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class Procesos {
    String pais, ciudad;

    ArrayList<String> listCiudades;
    HashMap<String, ArrayList<String>> mapais = new HashMap<String, ArrayList<String>> ();

    public Procesos() {

        String veces="";
        do {
            iniciar();
            
            veces = JOptionPane.showInputDialog("Para continuar 'C' o 'n' para cancelar");
            if (veces.equalsIgnoreCase("n")) {
                JOptionPane.showInternalMessageDialog(null, "ingresaste " + veces, "PROCESO TERMINADO", JOptionPane.CANCEL_OPTION);
            }
        } while (veces.equalsIgnoreCase("C"));
    }
    
    private void iniciar( ) {
        System.out.println("<<<<<INICIANDO PROCESO>>>>>\n");

        listCiudades = new ArrayList<>();

        String menu = "ALMACENAMIENTO DE PAISES Y CIUDADES.\n";
               menu += "1. Almacenar paises y ciudades.\n";
               menu += "2. Buscar una ciudad.\n";
               menu += "3. Buscar ciudades por pais.\n";
               menu += "4. Imprimir datos almacenados.\n";
               menu += "Escoga la opcion que desee.\n";

        
        int numMenu=0;
            do {
                numMenu = Integer.parseInt(JOptionPane.showInputDialog(menu));
                if (numMenu < 1 || numMenu > 4) {
                    JOptionPane.showMessageDialog(null, numMenu + "Opcion no valida", "ERROR DE OPCION", JOptionPane.ERROR_MESSAGE);
                }
            } while (numMenu < 1 || numMenu > 4);
        
        switch (numMenu) {
            case 1:
                ingresarDatos();
                break;
            case 2:
                buscarCiudad();
                break;
            case 3:
                buscarPais();
                break;
            case 4:
                imprimir();
                break;
            default:
                System.out.println("opcion no valida");
                break;
        }
    }

    private void ingresarDatos() {
        System.out.println("<<<<<INGRESANDO DATOS>>>>>\n");
        
        pais = JOptionPane.showInputDialog("Ingrese el nombre del pais a almacenar");
        String cantidadCiudades="";
        do {
            ciudad = JOptionPane.showInputDialog("Ingrese el nombre la ciudad a almacenar");
            listCiudades.add(ciudad);
            
            cantidadCiudades = JOptionPane.showInputDialog("Si desea continuar almacenando ciudades del pais " + pais + ", ingrese 'S' 0 'N' para terminar");
            
            if (cantidadCiudades.equalsIgnoreCase("n")) {
                JOptionPane.showMessageDialog(null, "Ingresaste 'N' se detuvo el almacenamiento de ciudades", "ALMACENAMIENTO DETENIDO", JOptionPane.CLOSED_OPTION);
            }
        } while (cantidadCiudades.equalsIgnoreCase("S"));

        mapais.put(pais, listCiudades);

        imprimir();
    }

     private void buscarCiudad() {
        String buscaCiudad = JOptionPane.showInputDialog("Ingrese el nombre de la ciudad a buscar");
        boolean encontre = false;
        for (String pais : mapais.keySet()) {
            if (mapais.get(pais).contains(buscaCiudad)) {
                System.out.println(buscaCiudad + " pertenece al pais " + pais);
                encontre=true;
            }
            if (!encontre) {
                System.out.println(buscaCiudad + "la ciudad no se encuentra registrada en un pais");
            }
        }
    }

    private void buscarPais() {
        int validar;
        String buscaPais = JOptionPane.showInputDialog("Ingrese el nombre del pais a buscar ");
        if (mapais.containsKey(buscaPais)) {
            System.out.println("Ciudades del pais " + buscaPais + ": ");
            for (int i = 0; i < mapais.get(buscaPais).size(); i++) {
                System.out.println(mapais.get(buscaPais).get(i));
            }
        } else {
            JOptionPane.showMessageDialog(null, "El pais no se encuentra registrado", "NO ES RECONOCIDO EL PAIS", JOptionPane.NO_OPTION);
            validar = JOptionPane.showConfirmDialog(null, "¿Desea registrar el pais?");
            if (validar == 0) {
                ingresarDatos();
            } else {
                ingresarDatos();
            }
        }
        
    }

    private void imprimir() {
        System.out.println("<<<<<IMPRIMIENDO DATOS>>>>>\n");

        System.out.println("Datos almacenados \n" + mapais + "\n");
    }
}
