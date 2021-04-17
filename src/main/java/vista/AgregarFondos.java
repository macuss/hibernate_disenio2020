package vista;

import vista.MenuPrincipal;
import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Fondo;
import modelo.PID;
import modelo.UCT;
import modelo.Registro;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.DoubleAccumulator;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;

public class AgregarFondos extends JFrame {

	private JPanel contentPane;
	private JTextField descripcion_tf;
	private JTextField fecha_tf;
	private JTextField monto_tf;

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");
	
	public AgregarFondos() {
		
		EntityManager man = emf.createEntityManager();
		
		List<UCT> luct = (List<UCT>) man.createQuery("FROM UCT").getResultList();
		List<PID> lpid = (List<PID>) man.createQuery("FROM PID").getResultList();
		luct.size();
		lpid.size();
		setTitle("Ingreso de Fondos");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 781, 611);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton aceptar_btn = new JButton("Aceptar");
	
		aceptar_btn.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
	
		
		aceptar_btn.setBounds(108, 524, 109, 37);
		contentPane.add(aceptar_btn);
		
		JButton cancelar_btn = new JButton("Cancelar");
		cancelar_btn.setForeground(new Color(255, 0, 0));
		cancelar_btn.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		cancelar_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AgregarFondos.this.dispose();
				
				
			}
		});
		
		cancelar_btn.setBounds(333, 524, 109, 37);
		contentPane.add(cancelar_btn);
		
		JLabel lblNewLabel = new JLabel("AGREGAR FONDOS");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		lblNewLabel.setBounds(196, 11, 220, 37);
		contentPane.add(lblNewLabel);
		
		JLabel lblBuscarPor = new JLabel("Buscar por:");
		lblBuscarPor.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblBuscarPor.setBounds(23, 71, 131, 22);
		contentPane.add(lblBuscarPor);
		
		JLabel lblResultados = new JLabel("Resultados:");
		lblResultados.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblResultados.setBounds(23, 123, 131, 22);
		contentPane.add(lblResultados);
		
		JComboBox buscar_cbx = new JComboBox();
		buscar_cbx.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		buscar_cbx.setModel(new DefaultComboBoxModel(new String[] {"PID", "UCT"}));
		buscar_cbx.setBounds(285, 71, 242, 22);
		contentPane.add(buscar_cbx);
		
		JComboBox incisos_cbx = new JComboBox();
		incisos_cbx.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		incisos_cbx.setModel(new DefaultComboBoxModel(new String[] {"inciso2", "inciso3", "inciso4.3"}));
		incisos_cbx.setBounds(285, 179, 242, 22);
		contentPane.add(incisos_cbx);
		
		JComboBox resultados_cbx = new JComboBox();
		resultados_cbx.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		resultados_cbx.setBounds(285, 123, 426, 22);
		contentPane.add(resultados_cbx);
		
		
		//-------ACTION LISTENERS------------------------------------------
		// CARGA DE COMBOBOX CON OBJETOS UCT Y PID TRAIDOS DE LA BASE DE DATOS
		
		
		resultados_cbx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				incisos_cbx.removeAllItems();
				if(buscar_cbx.getSelectedItem() == "UCT") {
					
					for (UCT unaUct : luct) {
						if(unaUct.getNombre() == resultados_cbx.getSelectedItem()) { 
							man.merge(unaUct);
							List<Fondo> fondos1 = unaUct.getFondos();
							fondos1.size();							
							for (Fondo fondo : fondos1) {
								man.merge(fondo);
								String un = fondo.getTipo();
								incisos_cbx.addItem(un);
							}
						}
					}
				} else if (buscar_cbx.getSelectedItem() == "PID") {
					for (PID unPid : lpid) {
						if(unPid.getNombre() == resultados_cbx.getSelectedItem()) { 
							man.merge(unPid);
							List<Fondo> fondos1 = unPid.getFondos();
							fondos1.size();							
							for (Fondo fondo : fondos1) {
								man.merge(fondo);
								String un = fondo.getTipo();
								incisos_cbx.addItem(un);
							}
						}
					}
				}
				
			}
		});
		
		
		
	
		/*CBX BUSCAR*/
		buscar_cbx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(buscar_cbx.getSelectedItem() == "UCT") {
					
					resultados_cbx.removeAllItems();
					for (UCT unidad : luct) {
						String un = unidad.getNombre();
						resultados_cbx.addItem(un);
						
					}
					
				} else if (buscar_cbx.getSelectedItem() == "PID"){

					resultados_cbx.removeAllItems();
					for (PID unidad : lpid) {	
						String un = unidad.getNombre();
						resultados_cbx.addItem(un);
					}
				}
				

			}
		});
		
		aceptar_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				man.getTransaction().begin();
				
				if(buscar_cbx.getSelectedItem() == "UCT") {
					
					for (UCT unaUct : luct) {
						if(unaUct.getNombre() == resultados_cbx.getSelectedItem()) { 
							man.merge(unaUct);
							List<Fondo> fondos1 = unaUct.getFondos();
							fondos1.size();
							System.out.println("Listado de fondos de " + resultados_cbx.getSelectedItem());
							
							for (Fondo fondo : fondos1) {
								man.merge(fondo);
								
								String variable = fondo.getTipo();
								System.out.println(variable);
								if((String) incisos_cbx.getSelectedItem() == variable) {
									System.out.println(fondo.getTipo());
									fondo.setMonto(fondo.getMonto()+ Integer.parseInt(monto_tf.getText()) );
									System.out.println("El fondo ahora tiene: " + fondo.getMonto());
									
									crearRegistro(fecha_tf.getText(), descripcion_tf.getText(), "ingreso", monto_tf.getText(), fondo);
									
								}
					
								System.out.println("* " + fondo.toString());
							}
																					
						}					
				
						}
				
				} else if(buscar_cbx.getSelectedItem() == "PID") {
					for (PID unPid : lpid) {
						if(unPid.getNombre() == resultados_cbx.getSelectedItem()) {
							man.merge(unPid);
							
							List<Fondo> fondos1 = unPid.getFondos();
							fondos1.size();
							System.out.println("Listado de fondos de " + resultados_cbx.getSelectedItem());
							
							for (Fondo fondo : fondos1) {
								man.merge(fondo);
								
								String variable = fondo.getTipo();
								System.out.println(variable);
								if((String) incisos_cbx.getSelectedItem() == variable) {
									System.out.println(fondo.getTipo());
									fondo.setMonto(fondo.getMonto()+ Integer.parseInt(monto_tf.getText()) );
									System.out.println("El fondo ahora tiene: " + fondo.getMonto());
									
									
									
									crearRegistro(fecha_tf.getText(), descripcion_tf.getText(), "ingreso", monto_tf.getText(), fondo);
								}
					
								System.out.println("* " + fondo.toString());
							}
							
						}
					}	
				}	
				
				
				man.getTransaction().commit();
				man.close();
				JOptionPane.showMessageDialog(aceptar_btn, "Monto Agregado con éxito!! ");
				AgregarFondos.this.dispose();
			}

			
			private void crearRegistro(String date, String descripcion, String condicion , String nuevoMonto, Fondo fondo ) {
				
				double monto = Double.parseDouble(nuevoMonto);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
				LocalDate localDate = LocalDate.parse(date, formatter);
				
				// mediante el metodo "persist" se crea un registro en la BD con los datos pertenecientes a la transaccion
				man.persist(new Registro(localDate, descripcion, condicion  , monto , fondo));
			}
		});
		
		JLabel lblTipoFinanciamiento = new JLabel("Tipo Financiamiento:");
		lblTipoFinanciamiento.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblTipoFinanciamiento.setBounds(23, 176, 205, 22);
		contentPane.add(lblTipoFinanciamiento);
		
		
		
		JLabel lblDetallesdescripcin = new JLabel("Detalles/Descripci\u00F3n:");
		lblDetallesdescripcin.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblDetallesdescripcin.setBounds(23, 250, 205, 22);
		contentPane.add(lblDetallesdescripcin);
		
		descripcion_tf = new JTextField();
		descripcion_tf.setHorizontalAlignment(SwingConstants.TRAILING);
		descripcion_tf.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		descripcion_tf.setBounds(283, 254, 242, 64);
		contentPane.add(descripcion_tf);
		descripcion_tf.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblFecha.setBounds(23, 353, 131, 22);
		contentPane.add(lblFecha);
		
		fecha_tf = new JTextField();
		fecha_tf.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		fecha_tf.setBounds(285, 353, 160, 24);
		contentPane.add(fecha_tf);
		fecha_tf.setColumns(10);
		
		JLabel lblMonto = new JLabel("Monto:    $");
		lblMonto.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblMonto.setBounds(23, 411, 205, 22);
		contentPane.add(lblMonto);
		
		monto_tf = new JTextField();
		monto_tf.setForeground(Color.BLUE);
		monto_tf.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		monto_tf.setBounds(286, 407, 160, 37);
		contentPane.add(monto_tf);
		monto_tf.setColumns(10);
		
		// --------------------- trae datos persistentes en BD -----------------------------
		
		
		
		
	}
	
public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarFondos frame = new AgregarFondos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
	}
		
				
}
