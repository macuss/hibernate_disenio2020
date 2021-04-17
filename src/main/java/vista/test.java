package vista;


import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Query;

import java.util.List;

import modelo.PID;
import modelo.UCT;


public class test {

	private static EntityManager manager;
	private static EntityManagerFactory emf;
	
	public static void main(String[] args) {
		/* Crea gestor de persistencia */
		emf = Persistence.createEntityManagerFactory("Persistencia");
		manager = emf.createEntityManager();
		
		/*consulta JPQL */
		List<UCT> luct = (List<UCT>) manager.createQuery("FROM UCT").getResultList();
		List<PID> lpid = (List<PID>) manager.createQuery("FROM PID").getResultList();
		
		System.out.println("En esta tabla hay " + luct.size() + " UCTs." );
		System.out.println("En esta tabla hay " + lpid.size() + " PIDs." );
		
		
		/* --------------- Para actualizar en la base de datos ---------------*/
		
		manager.getTransaction().begin();
		
		for (UCT unidad : luct) {
			System.out.println(unidad.toString());
			manager.merge(unidad);
			unidad.setNombre(unidad.getNombre()+"H");
		}
		
		
		manager.getTransaction().commit();
		System.out.println("COMITEADO: ");
		System.out.println(" ");
		for (UCT unidad : luct) {
			
			System.out.println(unidad.toString());
			
		}
		/* --------------- Para actualizar en la base de datos ---------------*/
		
	}
	
}
