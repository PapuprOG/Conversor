package interfaz_principal;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import conversor_moneda.*;
import conversor_temperatura.*;

/**
 * esta es la clase main la cual esta pensada para inicializar el proyecto entero, es una interface
 * para que el usuario elija que tipo de conversor necesita
 */

public class Principal {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Creacion de la aplicacion.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(192, 192, 192));
		frame.setBounds(100, 100, 807, 463);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("CONVERSOR ALURA");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("SansSerif", Font.ITALIC, 30));
		lblNewLabel.setBounds(171, 27, 435, 127);
		frame.getContentPane().add(lblNewLabel);

		JButton btnNewButton = new JButton("Moneda");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConversorMoneda.MostrarConversorMoneda();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.ITALIC, 18));
		btnNewButton.setBounds(127, 189, 210, 95);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Temperatura");
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConversorTemperatura.MostrarConversorTemperatura();
			}
		});

		btnNewButton_1.setFont(new Font("SansSerif", Font.ITALIC, 18));
		btnNewButton_1.setBounds(430, 189, 210, 95);
		frame.getContentPane().add(btnNewButton_1);
	}
}
