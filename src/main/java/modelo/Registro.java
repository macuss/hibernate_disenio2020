package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "registro")
public class Registro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "idregistro", updatable = true)
    private int idregistro;
	
	@Column(name = "fecha")
    private LocalDate fecha;
	
	@Column(name = "descripcion")
    private String descripcion;
	
	@Column(name = "condicion")
    private String condicion; /* ingreso  o egreso de dinero */
	
	@Column(name = "monto")
    private Double monto;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fondo_idfondo")
    private Fondo fondo;   
    
    public Registro(){ }

    public Registro( /*int idregistro, */LocalDate fecha, String descripcion, String condicion, Double monto, Fondo fondo) {
       // this.idregistro = idregistro;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.condicion = condicion;
        this.monto = monto;
        this.fondo = fondo;
    }

    public int getIdregistro() {
        return idregistro;
    }

    public void setIdregistro(int idregistro) {
        this.idregistro = idregistro;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Fondo getFondo() {
		return fondo;
	}
    
    public void setFondo(Fondo fondo) {
		this.fondo = fondo;
		
	}
    
    @Override
    public String toString() {
        return "{" + ", fecha=" + fecha +'}';
    }
}
