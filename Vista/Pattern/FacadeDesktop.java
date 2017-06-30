/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Pattern;

import DataAccess.ConexionDB;
import DataAccess.Session;
import DataAccess.UsuarioBibliotecaDB;
import Modelo.UsuarioBiblioteca;
import Vista.Gestion.BuscarUsuario;
import Vista.Gestion.GenerarPrestamo;
import Vista.Gestion.GestionBiblioteca;
import Vista.Gestion.GestionReporte;
import Vista.Gestion.GestionReservas;
import Vista.Gestion.GestionUsuariosIF;
import Vista.Gestion.RegistroBibliotecario;
import Vista.Search.BuscadorLibros;
import Vista.Search.ResultadosBusqueda;
import Vista.Search.Solicitudes;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 *
 * @author alulab14
 */
public class FacadeDesktop {
    
    //Lazy initialization of desktop tools
    private Map<DesktopTool, Object> tools = new HashMap<>();
    
    //Dependency injection
    private final JDesktopPane desktop;
    private final ArrayList<DesktopToolListener> listeners = new ArrayList<>();//AMI
    
    public FacadeDesktop(JDesktopPane desktop) {
        this.desktop = desktop;
    }
    
    public void addDesktopToolListener(DesktopToolListener listener) {
        listeners.add(listener);
    }
    
    public Object getIfAvailable(DesktopTool tool) {
        return tools.getOrDefault(tool, null);
    }
    
    public boolean addTool(DesktopTool type) {
        //Check if initialized
        if(tools.containsKey(type)) return true;        
        
        switch(type) {
            case BUSCADOR_LIBROS:
                crearBuscadorLibros();
                break;
            case GENERAR_PRESTAMO:
                crearGenerarPrestamo();
                break;
            case GESTION_USUARIOS:
                crearGestionUsuarios();
                break;
            case REPORTES:
                crearReportes();
                break;
            case RESERVAS:
                crearReservas();
                break;
            case SOLICITUDES:
                crearSolicitudes();
                break;
            case BUSCAR_USUARIO:
                crearBuscarUsuario();
                break;
            case GESTION_BIBLIOTECA:
                gestionBibliotecas();
                break;
        }
        
        return tools.containsKey(type);
    }
    
    private class ClosingFrameAdapter extends InternalFrameAdapter {
        
        //Dependency injection
        private final DesktopTool type;
        
        public ClosingFrameAdapter(DesktopTool type) {
            this.type = type;
        }
        
        @Override
        public void internalFrameClosing(InternalFrameEvent ev) {
            tools.remove(type);
            for(DesktopToolListener listener: listeners)
                listener.DesktopToolClosing(type);
            
            if(type==DesktopTool.GESTION_BIBLIOTECA) {
                String lock = Session.getSession().getLock("Biblioteca");
                Session.getSession().putLock("Biblioteca", null);
                if(lock!=null) {
                    ConexionDB.releaseLock(getClass(), "Biblioteca", lock);
                }
            }
        }
                
    }
    
    private class ClosingWindowAdapter extends WindowAdapter {
        
        //Dependency injection
        private final DesktopTool type;
        
        public ClosingWindowAdapter(DesktopTool type) {
            this.type = type;
        }
        
        @Override
        public void windowClosing(WindowEvent ev) {
            tools.remove(type);
            for(DesktopToolListener listener: listeners)
                listener.DesktopToolClosing(type);
        }
                
    }
    
    public void forceDesktopTool(DesktopTool type, JInternalFrame tool) {
        if(tools.containsKey(type)) {
            Object c = tools.get(type);
            if(c instanceof JInternalFrame)
                ((JInternalFrame)c).dispose();
            else if(c instanceof JFrame)
                ((JFrame)c).dispose();
            tools.remove(type);
        }
        tools.put(type, tool);
        tool.setVisible(true);
    }
    
    private void gestionBibliotecas() {
        Session.getSession().putLock("Biblioteca", ConexionDB.getLock(getClass(), "Biblioteca"));
        if(Session.getSession().getLock("Biblioteca")==null) {
            JOptionPane.showMessageDialog(null, "Alguien mas esta editando las bibliotecas",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        DesktopTool type = DesktopTool.GESTION_BIBLIOTECA;
        
        GestionBiblioteca gestionBiblioteca = new GestionBiblioteca();
        
        gestionBiblioteca.addInternalFrameListener(new ClosingFrameAdapter(type));
        desktop.add(gestionBiblioteca);
        tools.put(type, gestionBiblioteca);
        
        gestionBiblioteca.setVisible(true);
    }
    
    private void crearReportes() {
        DesktopTool type = DesktopTool.REPORTES;
        
        GestionReporte gr = new GestionReporte();
        
        gr.addInternalFrameListener(new ClosingFrameAdapter(type));
        desktop.add(gr);
        tools.put(type, gr);
        
        gr.setVisible(true);
    }
    
    private void crearSolicitudes() {
        DesktopTool type = DesktopTool.SOLICITUDES;
        
        Solicitudes sol = new Solicitudes();
        
        sol.addInternalFrameListener(new ClosingFrameAdapter(type));
        desktop.add(sol);
        tools.put(type, sol);
        
        sol.setVisible(true);
    }
    
    private void crearReservas() {
        DesktopTool type = DesktopTool.GESTION_USUARIOS;
        
        String strCodigo = JOptionPane.showInputDialog(desktop, "Ingrese el codigo del usuario:",
                "Datos", JOptionPane.PLAIN_MESSAGE);
        
        int codigo;
        try {
            codigo = Integer.parseInt(strCodigo);
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(desktop, "Codigo invalido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        UsuarioBiblioteca usuario = UsuarioBibliotecaDB.buscarUsuario(codigo);
        if(usuario==null) {
            JOptionPane.showMessageDialog(desktop, "Usuario no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        GestionReservas gestionReservas = new GestionReservas(usuario, this);
        gestionReservas.addInternalFrameListener(new ClosingFrameAdapter(type));
        desktop.add(gestionReservas);
        tools.put(type, gestionReservas);
        
        gestionReservas.setVisible(true);
    }
    
    private void crearGestionUsuarios() {
        DesktopTool type = DesktopTool.GESTION_USUARIOS;
        
        GestionUsuariosIF gestionUsuarios = new GestionUsuariosIF(this);
        desktop.add(gestionUsuarios);
        tools.put(type, gestionUsuarios);
        
        gestionUsuarios.setVisible(true);
    }
    
    private void crearBuscarUsuario() {
        DesktopTool type = DesktopTool.BUSCAR_USUARIO;
        
        BuscarUsuario buscarUsuario = new BuscarUsuario();
        buscarUsuario.addInternalFrameListener(new ClosingFrameAdapter(type));
        tools.put(type, buscarUsuario);
        desktop.add(buscarUsuario);
        
        buscarUsuario.setVisible(true);
    }
    
    private void crearBuscadorLibros() {
        DesktopTool type = DesktopTool.BUSCADOR_LIBROS;
        
        ResultadosBusqueda buscadorLibros = new ResultadosBusqueda();
        buscadorLibros.addWindowListener(new ClosingWindowAdapter(type));
        //desktop.add(buscadorLibros);
        tools.put(type, buscadorLibros);
        
        buscadorLibros.setVisible(true);
        /* 
        BuscadorLibros buscadorLibros = new BuscadorLibros();
        buscadorLibros.addInternalFrameListener(new ClosingFrameAdapter(type));
        desktop.add(buscadorLibros);
        tools.put(type, buscadorLibros);
        
        buscadorLibros.setVisible(true);
        */
    }
    
    private void crearGenerarPrestamo() {
        DesktopTool type = DesktopTool.GENERAR_PRESTAMO;
        
        String strCodigo = JOptionPane.showInputDialog(desktop, "Ingrese codigo del usuario", "Crear prestamo", JOptionPane.PLAIN_MESSAGE);
        if(strCodigo==null) {
            return;
        }
        try {
            int codigo = Integer.parseInt(strCodigo);
            UsuarioBiblioteca usuario = UsuarioBibliotecaDB.buscarUsuario(codigo);
            if(usuario==null) {
                JOptionPane.showMessageDialog(desktop, "No se encontro usuario", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            GenerarPrestamo generarPrestamo = new GenerarPrestamo(usuario);
            generarPrestamo.addInternalFrameListener(new ClosingFrameAdapter(type));
            tools.put(type, generarPrestamo);
            desktop.add(generarPrestamo);
            generarPrestamo.setVisible(true);
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(desktop, "No se encontro usuario", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
    
}
