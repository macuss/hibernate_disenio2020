package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "uct")
public class UCT implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idUCT")
	private int idUCT;

	@Column(name = "nombre")
    private String nombre;
	
	@Column(name = "descripcion")
    private String descripcion;

	@Column(name = "tipo")
    private String tipo;

	@OneToMany(mappedBy = "uct", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Fondo> fondos = new ArrayList<>();
	
  
	public UCT() {  }

    public UCT(int idUCT, String nombre, String descripcion, String tipo) {
        this.idUCT = idUCT;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        
    }

    
     public int getIdUCT() {
        return idUCT;
    }

    public void setIdUCT(int idUCT) {
        this.idUCT = idUCT;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public List<Fondo> getFondos() {
		return fondos;
	}
    
    public void setFondos(List<Fondo> fondos) {
		this.fondos = fondos;
	
	}
   	
  	@Override
	public String toString() {
		return "UCT [idUCT=" + idUCT + ", nombre=" + nombre + ", descripcion=" + descripcion + ", tipo=" + tipo
				+ "]";
	}
}