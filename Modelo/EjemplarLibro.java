package Modelo;

public class EjemplarLibro{
    
    private int idEjemplar;
    private final Libro libro;
    private int numEjemplar;
    private EstadoEjemplar estado;
    private Piso piso;
    private Biblioteca biblioteca;

    public EjemplarLibro(Libro libro, int numEjemplar, int idEstadoEjemplar, int idPiso, int idBiblioteca) {
        this.libro = libro;
        this.numEjemplar = numEjemplar;
        this.estado = EstadoEjemplar.getEstadoEjemplar(idEstadoEjemplar);
        this.piso = Piso.getPiso(idPiso);
        this.biblioteca = Biblioteca.getBiblioteca(idBiblioteca);
        
        assert this.estado!=null;
        assert this.piso!=null;
        assert this.biblioteca!=null;
    }
    
    public void setId(int id) {
        setIdEjemplar(id);
    }
    
    public int getId() {
        return getIdEjemplar();
    }
    
    /**
     * @return the numEjemplar
     */
    public int getNumEjemplar() {
        return numEjemplar;
    }

    /**
     * @param numEjemplar the numEjemplar to set
     */
    public void setNumEjemplar(int numEjemplar) {
        this.numEjemplar = numEjemplar;
    }

    public EstadoEjemplar getEstado() {
        return estado;
    }

    public void setPiso(int idPiso) {
        this.piso = Piso.getPiso(idPiso);
    }
    
    public void setPiso(Piso piso) {
        this.piso = piso;
    }
    
    public void setEstado(EstadoEjemplar estado) {
        this.estado = estado;
    }

    public Piso getPiso() {
        return piso;
    }
    
    public void setBiblioteca(int idBiblioteca) {
        this.biblioteca = Biblioteca.getBiblioteca(idBiblioteca);
    }
    
    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }
    
    public Biblioteca getBiblioteca() {
        return biblioteca;
    }
    
    public Libro getLibro() {
        return libro;
    }
    
    public String getTitulo() {
        return libro.getTitulo();
    }

    /**
     * @return the idEjemplar
     */
    public int getIdEjemplar() {
        return idEjemplar;
    }

    /**
     * @param idEjemplar the idEjemplar to set
     */
    public void setIdEjemplar(int idEjemplar) {
        this.idEjemplar = idEjemplar;
    }
}

