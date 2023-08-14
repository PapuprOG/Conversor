package conversor_moneda;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;

public class ConversorMoneda {

	private JFrame frame;
	private JTextField txt;
	private JButton btn;
	private JComboBox<Moneda> cmb;
	private JLabel lbl;
	

	public enum Moneda {
		pesosCol_dolar, pesosCol_euro, pesosCol_libra, dolar_pesosCol, euro_pesosCol, libra_pesosCol
	}
	
	/**
	 * se ininicializan las variables de los valores de las monedas y ademas el valor del input
	 */

	public double dolar = 3961;
	public double euro = 4343;
	public double libra = 5028;

	public double valorInput = 0.00;
	private JLabel lblNewLabel;

	/**
	 * Creacion de la aplicación
	 */
	public ConversorMoneda() {
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

		cmb = new JComboBox<Moneda>();
		cmb.setFont(new Font("SansSerif", Font.PLAIN, 18));
		cmb.setModel(new DefaultComboBoxModel<>(Moneda.values()));
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

		lblNewLabel = new JLabel("Conversor de Moneda");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblNewLabel.setBounds(243, 42, 331, 39);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 0, 0));
		panel.setBounds(0, 0, 791, 116);
		frame.getContentPane().add(panel);
	}
	
	/**
	 * se añade el metodo para la conversion de las distintas monedas
	 */

	public void Convertir() {
		if (Validar(txt.getText())) {
			Moneda moneda = (Moneda) cmb.getSelectedItem();

			switch (moneda) {

			case pesosCol_dolar:
				PesosAMoneda(dolar);
				break;
			case pesosCol_euro:
				PesosAMoneda(euro);
				break;
			case pesosCol_libra:
				PesosAMoneda(libra);
				break;
			case dolar_pesosCol:
				MonedaAPesos(dolar);
				break;
			case euro_pesosCol:
				MonedaAPesos(euro);
				break;
			case libra_pesosCol:
				MonedaAPesos(libra);
				break;
			default:
				throw new IllegalArgumentException("unexpected value: " + moneda);
			}
		}
	}
	
	/**
	 * Se añaden los metodos de las formulas para la coversion de las distintas monedas
	 * @param moneda
	 */

	public void PesosAMoneda(double moneda) {
		double res = valorInput / moneda;
		lbl.setText(Redondear(res));
	}

	public void MonedaAPesos(double moneda) {
		double res = valorInput * moneda;
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
	 * metodo el cual nos va a permitir mostrar nuestro conversor de moneda,
	 * cuando apretemos la opcion en la interfaz principal
	 */

	public static void MostrarConversorMoneda() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConversorMoneda window = new ConversorMoneda();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
