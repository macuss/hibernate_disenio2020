package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Fondo;
import modelo.PID;
import modelo.Registro;
import modelo.UCT;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.DefaultComboBoxModel;

public class ConsumirFondos extends JFrame {

	private JPanel contentPane;
	private JTextField descripcion_tf;
	private JTextField fecha_tf;
	private JTextField monto_tf;
	private JTextField disponible_tf;

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsumirFondos frame = new ConsumirFondos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public ConsumirFondos() {
		
		EntityManager man = emf.createEntityManager();
		
		List<UCT> luct = (List<UCT>) man.createQuery("FROM UCT").getResultList();
		List<PID> lpid = (List<PID>) man.createQuery("FROM PID").getResultList();
		luct.size();
		lpid.size();
		
		man.getTransaction().begin();
		setResizable(false);
		setTitle("Consumir Fondos");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 886, 628);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Realizar Gasto - Consumo");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		lblNewLabel.setBounds(298, 11, 370, 37);
		contentPane.add(lblNewLabel);
		
		JButton aceptar_btn = new JButton("Aceptar");
		
		aceptar_btn.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		aceptar_btn.setBounds(563, 541, 105, 37);
		contentPane.add(aceptar_btn);
		
		JButton cancelar_btn = new JButton("Cancelar");
		cancelar_btn.setForeground(new Color(255, 0, 0));
		cancelar_btn.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		cancelar_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ConsumirFondos.this.dispose();
				
			}
		});
		cancelar_btn.setBounds(731, 541, 97, 37);
		contentPane.add(cancelar_btn);
		
		JLabel lblBuscarPor = new JLabel("Buscar por:");
		lblBuscarPor.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblBuscarPor.setBounds(28, 77, 131, 22);
		contentPane.add(lblBuscarPor);
		
		JLabel lblResultados = new JLabel("Resultados:");
		lblResultados.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblResultados.setBounds(28, 129, 131, 22);
		contentPane.add(lblResultados);
		
		JComboBox incisos_cbx = new JComboBox();
		
		
		
		
		
		
		incisos_cbx.setModel(new DefaultComboBoxModel(new String[] {"inciso2", "inciso3", "inciso4.3"}));
		incisos_cbx.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		incisos_cbx.setBounds(290, 185, 242, 22);
		contentPane.add(incisos_cbx);
		
		JComboBox resultados_cbx = new JComboBox();
		resultados_cbx.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		resultados_cbx.setBounds(290, 129, 437, 22);
		contentPane.add(resultados_cbx);
		
		JComboBox buscar_cbx = new JComboBox();
		buscar_cbx.setModel(new DefaultComboBoxModel(new String[] {"UCT", "PID"}));
		buscar_cbx.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		buscar_cbx.setBounds(290, 77, 242, 22);
		contentPane.add(buscar_cbx);
		
		//-------ACTION LISTENERS------------------------------------------
		// CARGA DE COMBOBOX CON OBJETOS UCT Y PID TRAIDOS DE LA BASE DE DATOS
		
		incisos_cbx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disponible_tf.setText("0.0");
				
				if(buscar_cbx.getSelectedItem() == "UCT") {
					
					for (UCT unaUct : luct) {
						if(unaUct.getNombre() == resultados_cbx.getSelectedItem()) { 
							
							List<Fondo> fondos1 = unaUct.getFondos();
							
							
							for (Fondo fondo : fondos1) {
								
								
								String variable = fondo.getTipo();
								System.out.println(variable);
								if((String) incisos_cbx.getSelectedItem() == variable) {
									String str = String.valueOf(fondo.getMonto());
									disponible_tf.setText(str);
								}
							}
						}
									
					}
				}else if(buscar_cbx.getSelectedItem() == "PID"){
					
					for (PID unPid : lpid) {
						if(unPid.getNombre() == resultados_cbx.getSelectedItem()) { 
							//man.merge(unPid);
							List<Fondo> fondos1 = unPid.getFondos();
							//fondos1.size();
							
							for (Fondo fondo : fondos1) {
								//man.merge(fondo);
								
								String variable = fondo.getTipo();
								System.out.println(variable);
								if((String) incisos_cbx.getSelectedItem() == variable) {
									String str = String.valueOf(fondo.getMonto());
									disponible_tf.setText(str);
								}
							}
						}
									
					}
				
				
				}
			}
			});
		
				
		
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
		
		
		// ACTION LISTENER DEL BOTON --ACEPTAR-- 
		
		aceptar_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int exito = 1;
						
				
				
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
									
									int a_descontar = Integer.parseInt(monto_tf.getText());
									if (fondo.getMonto() >= a_descontar){
										fondo.setMonto(fondo.getMonto() - a_descontar );
										System.out.println("El fondo ahora dispone de: " + fondo.getMonto());
										
										crearRegistro(fecha_tf.getText(), descripcion_tf.getText(), "egreso", monto_tf.getText(), fondo);
										
									}else {
										JOptionPane.showMessageDialog(aceptar_btn, "No hay fondos suficientes!!");
										exito = 0;
									}
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
									int a_descontar = Integer.parseInt(monto_tf.getText());
									if (fondo.getMonto() >= a_descontar) {
										fondo.setMonto(fondo.getMonto() - a_descontar );
										System.out.println("El fondo ahora dispone de: " + fondo.getMonto());
										
										crearRegistro(fecha_tf.getText(), descripcion_tf.getText(), "egreso", monto_tf.getText(), fondo);
										
									}else {
										JOptionPane.showMessageDialog(aceptar_btn, "No hay fondos suficientes!!");
										exito = 0;
									}
								}
					
								System.out.println("* " + fondo.toString());
							}
							
						}
					}	
				}	
				
				
				if (exito == 1) {
					JOptionPane.showMessageDialog(aceptar_btn, "Monto Consumido con éxito!! ");
					
					man.getTransaction().commit();
					man.close();
					
					ConsumirFondos.this.dispose();
					
				}else 
					{JOptionPane.showMessageDialog(aceptar_btn, "La operación fue cancelada!! ");}
				
				
				
				
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
		lblTipoFinanciamiento.setBounds(28, 182, 205, 22);
		contentPane.add(lblTipoFinanciamiento);
		
	
		
		JLabel lblDetallesdescripcin = new JLabel("Detalles/Descripci\u00F3n:");
		lblDetallesdescripcin.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblDetallesdescripcin.setBounds(28, 256, 205, 22);
		contentPane.add(lblDetallesdescripcin);
		
		descripcion_tf = new JTextField();
		descripcion_tf.setHorizontalAlignment(SwingConstants.TRAILING);
		descripcion_tf.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		descripcion_tf.setColumns(10);
		descripcion_tf.setBounds(288, 260, 242, 64);
		contentPane.add(descripcion_tf);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblFecha.setBounds(28, 359, 131, 22);
		contentPane.add(lblFecha);
		
		fecha_tf = new JTextField();
		fecha_tf.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		fecha_tf.setColumns(10);
		fecha_tf.setBounds(290, 359, 160, 24);
		contentPane.add(fecha_tf);
		
		JLabel lblMonto_1 = new JLabel("Monto ($):");
		lblMonto_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblMonto_1.setBounds(28, 417, 205, 22);
		contentPane.add(lblMonto_1);
		
		monto_tf = new JTextField();
		monto_tf.setHorizontalAlignment(SwingConstants.CENTER);
		monto_tf.setText("0");
		monto_tf.setForeground(new Color(255, 0, 0));
		monto_tf.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		monto_tf.setColumns(10);
		monto_tf.setBounds(291, 413, 160, 37);
		contentPane.add(monto_tf);
		
		JLabel lblDisponible = new JLabel("Disponible ($): ");
		lblDisponible.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblDisponible.setBounds(507, 417, 145, 22);
		contentPane.add(lblDisponible);
		
		disponible_tf = new JTextField();
		disponible_tf.setBackground(new Color(105, 105, 105));
		disponible_tf.setEditable(false);
		disponible_tf.setHorizontalAlignment(SwingConstants.CENTER);
		disponible_tf.setText("0");
		disponible_tf.setForeground(Color.GREEN);
		disponible_tf.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		disponible_tf.setColumns(10);
		disponible_tf.setBounds(662, 413, 160, 37);
		contentPane.add(disponible_tf);
	}

}
