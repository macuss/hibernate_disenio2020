package modelo;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "pid")
public class PID implements Serializable{
    
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "idPID")
    private int idPID;
    
	@Column(name = "nombre")
    private String nombre;
    
	@Column(name = "tipo")
    private String tipo;
    
	@Column(name = "descripcion")
    private String descripcion;
    
	@OneToMany(mappedBy = "pid", cascade = CascadeType.ALL)
	private List<Fondo> fondos = new ArrayList<>();
    
    
    public PID() { 
    	
    }

    public PID(int idPID, String nombre, String tipo, String descripcion) {
        this.idPID = idPID;
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        
    }

    public int getIdPID() {
        return idPID;
    }

    public void setIdPID(int idPID) {
        this.idPID = idPID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFondos(List<Fondo> fondos) {
		this.fondos = fondos;
		for (Fondo fondo : fondos) {
			fondo.setPid(this);
		}
	}
    
    public List<Fondo> getFondos() {
		return fondos;
	}

	@Override
	public String toString() {
		return "PID [idPID=" + idPID + ", nombre=" + nombre + ", tipo=" + tipo + ", descripcion=" + descripcion + "]";
	}
}
