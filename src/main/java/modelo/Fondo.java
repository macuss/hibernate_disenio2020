package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "fondo")
public class Fondo implements Serializable {
	
		private static final long serialVersionUID = 1L;
		
		@Id
		@Column(name = "idfondo")
		private int idFondo;
		
		@Column(name = "tipo")
	    private String tipo;
	    
		@Column(name = "descripcion")
	    private String descripcion;
	    
		@Column(name = "monto")
	    private double monto;
	    
		@ManyToOne(fetch = FetchType.EAGER, optional = true)
		@JoinColumn(name = "UCT_idUCT")
	    private UCT uct;    
		
		@ManyToOne(fetch = FetchType.EAGER, optional = true)
		@JoinColumn(name = "PID_idPID")
	    private PID pid;  
	    
		@OneToMany(mappedBy = "fondo", cascade = CascadeType.ALL)
		private List<Registro> registros = new ArrayList<>();
		
		
		
	    public Fondo() {
	    	
	    }
    
	    
	  

	    public Fondo(int idFondo, String tipo, String descripcion, double monto, UCT uct, PID pid) {
			this.idFondo = idFondo;
			this.tipo = tipo;
			this.descripcion = descripcion;
			this.monto = monto;
			this.uct = uct;
			this.pid = pid;
		}




		public int getIdFondo() {
	        return idFondo;
	    }

	    public void setIdFondo(int idFondo) {
	        this.idFondo = idFondo;
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

	    public double getMonto() {
	        return monto;
	    }

	    public void setMonto(double monto) {
	        this.monto = monto;
	    }
	    
	    public void setUct(UCT uct) {
			this.uct = uct;
		}
	    
	    public UCT getUct() {
			return uct;
		}
	    
	    public void setPid(PID pid) {
			this.pid = pid;
		}
	    
	    public PID getPid() {
			return pid;
		}
	    
	    public void setRegistros(List<Registro> registros) {
			this.registros = registros;
			for (Registro registro : registros) {
				registro.setFondo(this);
			}
		}
	    
	    public List<Registro> getRegistros() {
			return registros;
		}


		@Override
		public String toString() {
			return "Fondo [idFondo=" + idFondo + ", tipo=" + tipo + ", descripcion=" + descripcion + ", monto=" + monto
					+ ", uct=" + uct + ", pid=" + pid + "]";
		}


		
	    
	    
}
