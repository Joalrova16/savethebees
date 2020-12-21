package VIEW;

import CONTROLLER.ControllerArchivos;
import CONTROLLER.ControllerNaturaleza;

import javax.imageio.IIOException;
import java.io.IOException;

public class Main2 {

    public static void main(String[] args) throws IOException {
        int CantidadFlores=500;
        int CantidadAbejas=10;
        ControllerNaturaleza naturaleza=ControllerNaturaleza.getInstance();
        naturaleza.Simulacion(CantidadFlores,CantidadAbejas);
        ControllerArchivos archivos=ControllerArchivos.getInstance();
        archivos.escribirArchivoInformacionGeneraciones(naturaleza.getGeneracionesCampoFlores(),naturaleza.getGeneracionesAbejas());
    }
}
