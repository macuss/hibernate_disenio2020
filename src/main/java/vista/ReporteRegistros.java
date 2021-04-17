package vista;

import javax.swing.table.DefaultTableModel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.Fondo;
import modelo.PID;
import modelo.UCT;
import modelo.Registro;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;

public class ReporteRegistros extends JFrame {

	private JPanel contentPane;
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");
	private JTable table;
	

	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReporteRegistros frame = new ReporteRegistros();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	public ReporteRegistros() {
		
		EntityManager man = emf.createEntityManager();
		
		List<UCT> luct = (List<UCT>) man.createQuery("FROM UCT").getResultList();
		List<PID> lpid = (List<PID>) man.createQuery("FROM PID").getResultList();
		luct.size();
		lpid.size();
		
		setTitle("Visualizar registros");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 884, 596);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBuscarPor = new JLabel("Buscar por:");
		lblBuscarPor.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblBuscarPor.setBounds(83, 76, 131, 22);
		contentPane.add(lblBuscarPor);
		
		JLabel lblResultados = new JLabel("Resultados:");
		lblResultados.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblResultados.setBounds(83, 128, 131, 22);
		contentPane.add(lblResultados);
		
		JComboBox incisos_cbx = new JComboBox();
		incisos_cbx.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		incisos_cbx.setBounds(345, 184, 242, 22);
		contentPane.add(incisos_cbx);
		
		JComboBox resultados_cbx = new JComboBox();
		resultados_cbx.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		resultados_cbx.setBounds(345, 128, 242, 22);
		contentPane.add(resultados_cbx);
		
		JComboBox buscar_cbx = new JComboBox();
		buscar_cbx.setModel(new DefaultComboBoxModel(new String[] {"UCT", "PID"}));
		buscar_cbx.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		buscar_cbx.setBounds(345, 76, 242, 22);
		contentPane.add(buscar_cbx);
		
		JLabel lblTipoFinanciamiento = new JLabel("Tipo Financiamiento:");
		lblTipoFinanciamiento.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblTipoFinanciamiento.setBounds(83, 181, 205, 22);
		contentPane.add(lblTipoFinanciamiento);
		
		JLabel lblRegistros = new JLabel("REGISTROS");
		lblRegistros.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		lblRegistros.setBounds(371, 11, 139, 37);
		contentPane.add(lblRegistros);
		JTable tabla = new JTable();
		table = new JTable();
		table.setBounds(83, 377, 681, 76);
		contentPane.add(table);
		
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.addColumn("Fecha");
		modelo.addColumn("Condicion");
		modelo.addColumn("Descripcion");
		modelo.addColumn("Monto");
		
		
		incisos_cbx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for (int i = 0; i< modelo.getRowCount(); i++) {
				modelo.removeRow(i);
				}
				String informacion= "";
				if(buscar_cbx.getSelectedItem() == "UCT") {
					
					for (UCT unaUct : luct) {
						if(unaUct.getNombre() == resultados_cbx.getSelectedItem()) { 
							
							List<Fondo> fondos1 = unaUct.getFondos();
							
							
							for (Fondo fondo : fondos1) {
								
								
								String variable = fondo.getTipo();
								System.out.println(variable);
								if((String) incisos_cbx.getSelectedItem() == variable) {
									String str = String.valueOf(fondo.getMonto());
									
									System.out.println(str);
									List<Registro> registros = fondo.getRegistros();
									for (Registro reg: registros) {
										
																				
										modelo.addRow(new Object [] {reg.toString(),reg.getCondicion(),reg.getDescripcion(),Double.toString(reg.getMonto())});
										
										table.setModel(modelo);
										System.out.println(reg.toString());
										
										
									}
									
									
									
																		
									
								}
									
									
									
								
							}
						}
									
					}
				}else if(buscar_cbx.getSelectedItem() == "PID"){
					
					for (PID unPid : lpid) {
						if(unPid.getNombre() == resultados_cbx.getSelectedItem()) { 
							
							List<Fondo> fondos1 = unPid.getFondos();
							
							
							for (Fondo fondo : fondos1) {
							
								
								String variable = fondo.getTipo();
								System.out.println(variable);
								if((String) incisos_cbx.getSelectedItem() == variable) {
									String str = String.valueOf(fondo.getMonto());
									
									System.out.println(str);
									List<Registro> registros = fondo.getRegistros();
									for (Registro reg: registros) {
										
										modelo.addRow(new Object [] {reg.toString(),reg.getCondicion(),reg.getDescripcion(),Double.toString(reg.getMonto())});
										
										table.setModel(modelo);
										System.out.println(reg.toString());
									}
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
		
		
		JButton cancelar_btn = new JButton("Volver");
		cancelar_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ReporteRegistros.this.dispose();
			}
		});
		cancelar_btn.setForeground(Color.RED);
		cancelar_btn.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		cancelar_btn.setBounds(689, 490, 109, 37);
		contentPane.add(cancelar_btn);
		
		JLabel lblNewLabel = new JLabel("FECHA               CONDICION               DESCRIPCION               MONTO");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblNewLabel.setBounds(83, 335, 651, 31);
		contentPane.add(lblNewLabel);
		
		
				
		}
	}

		
