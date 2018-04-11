package greta.projet_ecole.views;

import java.awt.Choice;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

public class VEmprunts {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	JList list = new JList();
	JList list_1 = new JList();
	JLabel lblLivresEmpruntables = new JLabel("Livres empruntables :");
	JLabel lblLivresEmprunts = new JLabel("Livres empruntés :");
	JLabel lblEmprunteurs = new JLabel("Emprunteurs :");
	Choice choice = new Choice();
	JLabel lblDateDemprunts = new JLabel("Date d'emprunts :");
	JLabel lblDateDeRetour = new JLabel("Date de retour prévue :");
	JButton btnAjouter = new JButton("Ajouter");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VEmprunts window = new VEmprunts();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VEmprunts() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 852, 406);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		list.setBounds(32, 41, 371, 153);
		frame.getContentPane().add(list);

		list_1.setBounds(430, 41, 371, 153);
		frame.getContentPane().add(list_1);

		lblLivresEmpruntables.setBounds(22, 16, 114, 14);
		frame.getContentPane().add(lblLivresEmpruntables);

		lblLivresEmprunts.setBounds(450, 16, 101, 14);
		frame.getContentPane().add(lblLivresEmprunts);

		lblEmprunteurs.setBounds(22, 220, 81, 14);
		frame.getContentPane().add(lblEmprunteurs);

		choice.setBounds(22, 242, 343, 100);
		frame.getContentPane().add(choice);

		lblDateDemprunts.setBounds(22, 309, 114, 14);
		frame.getContentPane().add(lblDateDemprunts);

		textField = new JTextField();
		textField.setBounds(22, 334, 114, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		lblDateDeRetour.setBounds(210, 309, 124, 14);
		frame.getContentPane().add(lblDateDeRetour);

		textField_1 = new JTextField();
		textField_1.setBounds(210, 334, 114, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		btnAjouter.setBounds(608, 333, 89, 23);
		frame.getContentPane().add(btnAjouter);

	}
}
