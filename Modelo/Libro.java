package Modelo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;

public class Libro {
    private int id;
    private ArrayList<Autor> autores;
    private String titulo;
    private int anhoPublicacion;
    private int numPaginas;
    private String ISBN;
    private String idioma;
    private String editorial;
    private int edicion;
    private ArrayList<Tema> temas;
    private ArrayList<EjemplarLibro> lista_ejemplares;
    private String portada, portadaGrande;

    public Libro(String titulo, int anhoPublicacion, int numPaginas,
            String ISBN, String idioma, String editorial, int edicion, String portada, String portadaGrande) {
        //this.autores = autores;
        this.titulo = titulo;
        this.anhoPublicacion = anhoPublicacion;
        this.numPaginas = numPaginas;
        this.ISBN = ISBN;
        this.idioma = idioma;
        this.editorial = editorial;
        this.edicion = edicion;
        //this.temas = temas;
        this.portada = portada;
        this.portadaGrande = portadaGrande==null?portada:portadaGrande;
        
        this.lista_ejemplares = new ArrayList();
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    public void addAutor(Autor autor) {
        autores.add(autor);
    }
    
    public void addTema(Tema tema) {
        temas.add(tema);
    }
    
    public String getAutoresSeparados() {
        String strAutores = "";
        for(Autor autor : autores) {
            strAutores += autor.getNombres() + " " + autor.getApellidos() + "; ";
        }
        return strAutores;
    }
    
    public int getDisponibles() {
        int disponibles = 0;
        for(EjemplarLibro itm: lista_ejemplares) {
            switch(itm.getEstado()) {
            case DISPONIBLE:
                disponibles ++;
                break;
            }
        }
        return disponibles;
    }
    
    public int getReservados() {
        int reservados = 0;
        for(EjemplarLibro itm: lista_ejemplares) {
            switch(itm.getEstado()) {
            case RESERVADO:
            case EN_COLA:
                reservados ++;
                break;
            }
        }
        return reservados;
    }
    
    /**
     * @param itm the itemLibro to add
     */
    
    public void anhadir_itemLibro(EjemplarLibro itm){
        lista_ejemplares.add(itm);
    }
    
    public void setEjemplares(ArrayList<EjemplarLibro> ejemplares) {
        lista_ejemplares = ejemplares;
    }
    
    
    public ArrayList<EjemplarLibro> getEjemplares() {
        return lista_ejemplares;
    }
    
    /**
     * @return the autores
     */
    public ArrayList<Autor> getAutores() {
        return autores;
    }

    /**
     * @param autores the autores to set
     */
    public void setAutores(ArrayList<Autor> autores) {
        this.autores = autores;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnhoPublicacion() {
        return anhoPublicacion;
    }
    
    public void setAnhoPublicacion(int anhoPublicacion) {
        this.anhoPublicacion = anhoPublicacion;
    }

    /**
     * @return the numPaginas
     */
    public int getNumPaginas() {
        return numPaginas;
    }

    /**
     * @param numPaginas the numPaginas to set
     */
    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }

    /**
     * @return the ISBN
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * @param ISBN the ISBN to set
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * @return the idioma
     */
    public String getIdioma() {
        return idioma;
    }

    /**
     * @param idioma the idioma to set
     */
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    /**
     * @return the editorial
     */
    public String getEditorial() {
        return editorial;
    }

    /**
     * @param editorial the editorial to set
     */
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    /**
     * @return the edicion
     */
    public int getEdicion() {
        return edicion;
    }
    
    public String getEdicionString() {
        switch(edicion) {
            case -1:
            case 0:
                return "";
            default:
                return edicion+"º edición";
        }
    }

    /**
     * @param edicion the edicion to set
     */
    public void setEdicion(int edicion) {
        this.edicion = edicion;
    }

    /**
     * @return the temas
     */
    public ArrayList<Tema> getTemas() {
        return temas;
    }

    /**
     * @param temas the temas to set
     */
    public void setTemas(ArrayList<Tema> temas) {
        this.temas = temas;
    }
    
    public ImageIcon getPortada() {
        try {
            ImageIcon icon = new ImageIcon(new URL(portada));
            
            if(icon.getImage().getWidth(null)<=1)
                return new ImageIcon(Libro.class.getResource("../assets/bookIcon.png"));
            
            return icon;
        } catch (MalformedURLException ex) {
            //return new ImageIcon(Libro.class.getResource("../assets/bookIcon.png"));
            return null;
        }
    }
    
    public ImageIcon getPortadaGrandeSW(int newWidth) {
        try {
            BufferedImage image = ImageIO.read(new URL(portadaGrande));
            if(image.getWidth(null)<=1)
                image = ImageIO.read(Libro.class.getResource("../assets/bookIcon.png"));
            
            int newHeight = image.getHeight()*newWidth/image.getWidth();
            
            final BufferedImage resized = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
            final Graphics2D g = resized.createGraphics();
            g.drawImage(image, 0, 0, newWidth, newHeight, null);
            g.dispose();
            
            return new ImageIcon(resized);
        } catch (MalformedURLException ex) {
            //return new ImageIcon(Libro.class.getResource("../assets/bookIcon.png"));
            return null;
        } catch (IOException ex) {
            Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ImageIcon getPortadaGrandeSH(int newHeight) {
        try {
            BufferedImage image = ImageIO.read(new URL(portadaGrande));
            if(image.getWidth(null)<=1)
                image = ImageIO.read(Libro.class.getResource("../assets/bookIcon.png"));
            //System.out.println(image.getWidth(null));
            
            int newWidth = image.getWidth()*newHeight/image.getHeight();
            
            final BufferedImage resized = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
            final Graphics2D g = resized.createGraphics();
            g.drawImage(image, 0, 0, newWidth, newHeight, null);
            g.dispose();
            
            return new ImageIcon(resized);
        } catch (MalformedURLException ex) {
            //return new ImageIcon(Libro.class.getResource("../assets/bookIcon.png"));
            return null;
        } catch (IOException ex) {
            Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void setPortada(String url) {
        this.portada = url;
    }
   
    @Override
    public boolean equals(Object o) {
        if(o==this) return true;
        if(!(o instanceof Libro)) return false;
        Libro l = (Libro)o;
        return l.id==id && l.ISBN.equals(ISBN) && l.titulo.equals(titulo);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.id;
        hash = 71 * hash + Objects.hashCode(this.titulo);
        hash = 71 * hash + Objects.hashCode(this.ISBN);
        return hash;
    }

}
