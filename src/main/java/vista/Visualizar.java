package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import antlr.CommonToken;
import modelo.Fondo;
import modelo.PID;
import modelo.UCT;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;
import java.awt.SystemColor;

public class Visualizar extends JFrame {

	private JPanel contentPane;
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Visualizar frame = new Visualizar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	
	public Visualizar() {
		
		EntityManager man = emf.createEntityManager();
		
		List<UCT> luct = (List<UCT>) man.createQuery("FROM UCT").getResultList();
		List<PID> lpid = (List<PID>) man.createQuery("FROM PID").getResultList();
		luct.size();
		lpid.size();
		
		
		setResizable(false);
		setTitle("Ver el estado de los tipos de financiamientos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 791, 561);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton volver_btn = new JButton("Volver");
		volver_btn.setBounds(525, 462, 150, 40);
		volver_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Visualizar.this.dispose();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(volver_btn);
		
		JLabel lblNewLabel = new JLabel("Buscar por..");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblNewLabel.setBounds(90, 76, 131, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblOrgen = new JLabel("Resultados");
		lblOrgen.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblOrgen.setBounds(90, 128, 131, 22);
		contentPane.add(lblOrgen);
		
		JComboBox buscar_cbx = new JComboBox();
		buscar_cbx.setModel(new DefaultComboBoxModel(new String[] {"UCT", "PID"}));
		buscar_cbx.setBounds(291, 76, 242, 22);
		contentPane.add(buscar_cbx);
		
		JComboBox resultados_cbx = new JComboBox();
		resultados_cbx.setBounds(291, 128, 242, 22);
		contentPane.add(resultados_cbx);
		
		JLabel lblNewLabel_1 = new JLabel("INCISO 2");
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(90, 212, 162, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("INCISO 3");
		lblNewLabel_1_1.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(307, 212, 179, 20);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("INCISO 4.3");
		lblNewLabel_1_1_1.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(496, 212, 179, 20);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Visualizar Fondos");
		lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(199, 11, 199, 22);
		contentPane.add(lblNewLabel_2);
		
		JTextPane textPane = new JTextPane();
		textPane.setBackground(new Color(192, 192, 192));
		textPane.setForeground(Color.BLUE);
		textPane.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		textPane.setBounds(90, 243, 609, 40);
		contentPane.add(textPane);
		
		resultados_cbx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String informacion= "";
				if(buscar_cbx.getSelectedItem() == "UCT") {
					
					for (UCT unaUct : luct) {
						if(unaUct.getNombre() == resultados_cbx.getSelectedItem()) { 
							
							List<Fondo> fondos1 = unaUct.getFondos();
							fondos1.size();							
							for (Fondo fondo : fondos1) {
								
								
								String str1 = String.valueOf(fondo.getMonto());
								String un = "  $ " + str1 + "          ";
								
								System.out.println(un + String.valueOf(fondo.getMonto()));
								
							
								informacion = informacion + un;
							}
						}
					}
				} else if (buscar_cbx.getSelectedItem() == "PID") {
					
					for (PID unPid : lpid) {
						if(unPid.getNombre() == resultados_cbx.getSelectedItem()) { 
							
							List<Fondo> fondos1 = unPid.getFondos();
							fondos1.size();							
							for (Fondo fondo : fondos1) {
								
								String str1 = String.valueOf(fondo.getMonto());
								String un = "  $ " + str1 + "         ";
								
								System.out.println(un + String.valueOf(fondo.getMonto()));
								
							
								informacion = informacion + un;
								
							}
						}
					}
				}
			textPane.setText(informacion);
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
		
		
		
	}
}
