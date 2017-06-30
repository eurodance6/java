/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import java.util.Properties;

/**
 *
 * @author franc
 */
public class DBInfo {
    
    public static final String TB_SOLICITUD = "Solicitud";
    public static final String TB_LIBRO = "Libro";
    public static final String TB_EJEMPLAR_LIBRO = "EjemplarLibro";
    public static final String TB_PRESTAMO = "Prestamo";
    
    public static final String ATTR_BIBLIOTECA = "idBiblioteca";
    public static final String ATTR_PISO = "idPiso";
    
    private static Properties accessProps = null;
    
    private static void initProperties() {
        if(accessProps!=null) return;
        accessProps = new Properties();
        accessProps.put(TB_SOLICITUD, new String[]{ATTR_BIBLIOTECA});
        accessProps.put(TB_EJEMPLAR_LIBRO, new String[]{ATTR_BIBLIOTECA, ATTR_PISO});
    }
    
    public static boolean tableHasAccess(String table, String attr) {
        initProperties();
        String[] access = (String[])accessProps.get(table);
        if(access==null) return false;
        for(String acc:access)
            if(acc.equals(attr))
                return true;
        return false;
    }
    
}
