package conversor_temperatura;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class ConversorTemperatura {

	private JFrame frame;
	private JTextField txt;
	private JButton btn;
	private JComboBox<Temperatura> cmb;
	private JLabel lbl;

	public enum Temperatura {
		celsius_fahrenheit, celsius_kelvin, fahrenheit_celsius, fahrenheit_kelvin, kelvin_celsius, kelvin_fahrenheit
	}
	
	/**
	 * se inicializan las variables que corresponden a celsius, kelvin y farenheit
	 * respectivamente, ademas del valor del input
	 */

	public double c = 0;
	public double k = 273.15;
	public double f = 32;
	

	public double valorInput = 0.00;
	private JLabel lblNewLabel;

	/**
	 * Creacion de la aplicacion
	 */
	public ConversorTemperatura() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(new Color(192, 192, 192));
		frame.getContentPane().setFont(new Font("SansSerif", Font.PLAIN, 11));
		frame.setBounds(100, 100, 814, 467);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		txt = new JTextField();
		txt.setFont(new Font("SansSerif", Font.PLAIN, 18));
		txt.setBounds(132, 195, 226, 41);
		frame.getContentPane().add(txt);
		txt.setColumns(10);

		cmb = new JComboBox<Temperatura>();
		cmb.setFont(new Font("SansSerif", Font.PLAIN, 18));
		cmb.setModel(new DefaultComboBoxModel<>(Temperatura.values()));
		cmb.setBounds(132, 267, 226, 38);
		frame.getContentPane().add(cmb);

		// evento boton
		btn = new JButton("Convertir");
		btn.setFont(new Font("SansSerif", Font.PLAIN, 18));
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Convertir();
			}
		});
		btn.setBounds(424, 266, 213, 41);
		frame.getContentPane().add(btn);

		lbl = new JLabel("00.00");
		lbl.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lbl.setBounds(424, 201, 298, 28);
		frame.getContentPane().add(lbl);

		lblNewLabel = new JLabel("Conversor de Temperatura");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblNewLabel.setBounds(217, 42, 404, 39);
		frame.getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 255));
		panel.setBounds(0, 0, 791, 116);
		frame.getContentPane().add(panel);
	}
	
	/**
	 * se añade el metodo Convertir para la conversion de los distintos tipos
	 * de temperatura
	 */

	public void Convertir() {
		if (Validar(txt.getText())) {
			Temperatura temp = (Temperatura) cmb.getSelectedItem();

			switch (temp) {

			case celsius_fahrenheit:
				CelsiusAfahrenheit(f);
				break;
			case celsius_kelvin:
				CelsiusAKelvin(k);
				break;
			case fahrenheit_celsius:
				FahrenheitACelsius(c);
				break;
			case fahrenheit_kelvin:
				FahrenheitAKelvin(k);
				break;
			case kelvin_celsius:
				KelvinACelsius(c);
				break;
			case kelvin_fahrenheit:
				KelvinAFahrenheit(f);
				break;

			default:
				throw new IllegalArgumentException("unexpected value: " + temp);
			}
		}
	}
	
	/**
	 * Se añaden los metodos de las formulas para la coversion de los distintos
	 * tipos de temperatura
	 * @param moneda
	 */

	public void CelsiusAfahrenheit(double temp) {
		double res = (valorInput * 9 / 5) + f;
		lbl.setText(Redondear(res));
	}

	public void CelsiusAKelvin(double temp) {
		double res = valorInput + k;
		lbl.setText(Redondear(res));
	}

	public void FahrenheitACelsius(double temp) {
		double res = (valorInput - f) * 5/9;
		lbl.setText(Redondear(res));
	}

	public void FahrenheitAKelvin(double temp) {
		double res = (valorInput - f) * 5/9 + k;
		lbl.setText(Redondear(res));
	}

	public void KelvinACelsius(double temp) {
		double res = valorInput - k;
		lbl.setText(Redondear(res));
	}

	public void KelvinAFahrenheit(double temp) {
		double res = (valorInput - k) * 9/5 + f ;
		lbl.setText(Redondear(res));
	}
	
	/**
	 * añadimos un metodo que nos retorne el valor de una manera simplificada
	 * @param valor
	 * @return
	 */

	public String Redondear(double valor) {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(valor);
	}
	
	/**
	 * añadimos metodo para que solo se permitan numeros en el valor del input
	 * de lo contrarion nos retorna NumberFormatException
	 * @param texto
	 * @return
	 */

	public boolean Validar(String texto) {
		try {
			double x = Double.parseDouble(texto);
			if (x > 0)
				;
			valorInput = x;
			return true;
		} catch (NumberFormatException e) {
			lbl.setText("Solamente numeros !!");
			return false;
		}
	}
	
	/**
	 * metodo el cual nos va a permitir mostrar nuestro conversor de Temperatura,
	 * cuando apretemos la opcion en la interfaz principal
	 */

	public static void MostrarConversorTemperatura() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConversorTemperatura window = new ConversorTemperatura();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
