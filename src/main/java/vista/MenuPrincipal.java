package vista;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Color;
import javax.swing.border.TitledBorder;


import javax.swing.border.CompoundBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

 


public class MenuPrincipal extends JFrame {

	private JPanel contentPane;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
				
			
			
		});
		
		
	}

	
	
	public MenuPrincipal() {
		setResizable(false);
		setTitle("Menu Principal");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 871, 492);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton ingresarFondo_btn = new JButton("Ingresar Financiamiento");
		ingresarFondo_btn.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		ingresarFondo_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregarFondos agregar = new AgregarFondos();
				
				agregar.setVisible(true);
				
				
			}
		});
		ingresarFondo_btn.setBackground(Color.LIGHT_GRAY);
		ingresarFondo_btn.setBounds(100, 90, 253, 85);
		contentPane.add(ingresarFondo_btn);
		
		JButton sacarFondo_btn = new JButton("Consumir / Realizar Gasto");
		sacarFondo_btn.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		sacarFondo_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ConsumirFondos consumir = new ConsumirFondos();
				consumir.setVisible(true);
				
			}
		});
		sacarFondo_btn.setBackground(Color.LIGHT_GRAY);
		sacarFondo_btn.setBounds(477, 90, 253, 85);
		contentPane.add(sacarFondo_btn);
		
		JLabel titulo_lbl = new JLabel("GESTION DE FONDOS");
		titulo_lbl.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		titulo_lbl.setBounds(349, 11, 205, 35);
		contentPane.add(titulo_lbl);
		
		JButton visualizar_btn = new JButton("Visualizar Fondos");
		visualizar_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Visualizar visualizar = new Visualizar();
				visualizar.setVisible(true);
			}
		});
		visualizar_btn.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		visualizar_btn.setBackground(Color.LIGHT_GRAY);
		visualizar_btn.setBounds(100, 241, 253, 85);
		contentPane.add(visualizar_btn);
		
		JButton visualizar_btn_1 = new JButton("Ver Registros");
		visualizar_btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ReporteRegistros reg = new ReporteRegistros();
				reg.setVisible(true);
				
			}
		});
		visualizar_btn_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		visualizar_btn_1.setBackground(Color.LIGHT_GRAY);
		visualizar_btn_1.setBounds(477, 241, 253, 85);
		contentPane.add(visualizar_btn_1);
		
		JButton salir_btn = new JButton("Salir");
		salir_btn.setForeground(new Color(255, 0, 0));
		salir_btn.setFont(new Font("Trebuchet MS", Font.PLAIN, 25));
		salir_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				JOptionPane.showMessageDialog(salir_btn, "Gracias por usar el sistema!");
				
				MenuPrincipal.this.dispose();
			}
		});
		salir_btn.setBounds(738, 393, 89, 59);
		contentPane.add(salir_btn);
	}
}
