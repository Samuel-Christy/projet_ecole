package greta.projet_ecole.views;

import java.awt.Choice;
import java.awt.EventQueue;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class VEmprunts {

	private JFrame frame;

	// Création des deux listes
	List listLivresempruntes = new List();
	List listLivresEmpruntables = new List();

	// création des composants
	JLabel lblLivresEmpruntables = new JLabel("Livres empruntables :");
	JLabel lblLivresEmprunts = new JLabel("Livres empruntés :");
	JLabel lblEmprunteurs = new JLabel("Emprunteurs :");
	Choice choiceEmprunteurs = new Choice();
	JLabel lblDateDemprunts = new JLabel("Date d'emprunts :");
	JLabel lblDateDeRetour = new JLabel("Date de retour prévue :");
	JButton btnAjouter = new JButton("Ajouter");
	JButton btnModifier = new JButton("Modifier");
	JTextField textFieldDateEmprunts = new JTextField();
	JTextField textFielddateDeRetour = new JTextField();

	// format de la date
	String format = "dd/MM/yyyy";
	SimpleDateFormat sdf = new SimpleDateFormat(format);

	int index = 0;

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

		listLivresempruntes.setBounds(430, 41, 371, 153);
		frame.getContentPane().add(listLivresempruntes);

		lblLivresEmpruntables.setBounds(32, 16, 144, 14);
		frame.getContentPane().add(lblLivresEmpruntables);

		lblLivresEmprunts.setBounds(430, 16, 124, 14);
		frame.getContentPane().add(lblLivresEmprunts);

		lblEmprunteurs.setBounds(22, 220, 81, 14);
		frame.getContentPane().add(lblEmprunteurs);

		choiceEmprunteurs.setBounds(22, 242, 343, 100);
		frame.getContentPane().add(choiceEmprunteurs);

		lblDateDemprunts.setBounds(22, 309, 114, 14);
		frame.getContentPane().add(lblDateDemprunts);
		textFieldDateEmprunts.setToolTipText("dd/mm/yy");

		textFieldDateEmprunts.setBounds(22, 334, 114, 20);
		frame.getContentPane().add(textFieldDateEmprunts);
		textFieldDateEmprunts.setColumns(10);

		lblDateDeRetour.setBounds(210, 309, 155, 14);
		frame.getContentPane().add(lblDateDeRetour);
		textFielddateDeRetour.setToolTipText("dd/mm/yyyy");

		textFielddateDeRetour.setBounds(210, 334, 114, 20);
		frame.getContentPane().add(textFielddateDeRetour);
		textFielddateDeRetour.setColumns(10);

		btnModifier.setBounds(608, 333, 89, 23);
		frame.getContentPane().add(btnModifier);

		btnAjouter.setBounds(608, 333, 89, 23);
		frame.getContentPane().add(btnAjouter);

		listLivresEmpruntables.setBounds(32, 41, 371, 153);

		// desactivation des boutons au lancement
		btnModifier.setVisible(false);
		btnAjouter.setVisible(false);

		frame.getContentPane().add(listLivresEmpruntables);

		// ajouter elements au livres empruntables

		listLivresEmpruntables.add("Livres empruntable 1");
		listLivresEmpruntables.add("Livres empruntable 2");
		listLivresEmpruntables.add("Livres empruntable 3");
		listLivresEmpruntables.add("Livres empruntable 4");
		listLivresEmpruntables.add("Livres empruntable 5");
		listLivresEmpruntables.add("Livres empruntable 6");

		// Ajouter elements aux livres emprunters
		listLivresempruntes.add("Livres emprunters1");
		listLivresempruntes.add("Livres emprunters2");
		listLivresempruntes.add("Livres emprunters3");
		listLivresempruntes.add("Livres emprunters4");

		// Ajout de la liste des emprunteurs
		choiceEmprunteurs.add("emprunteurs1");
		choiceEmprunteurs.add("emprunteurs2");
		choiceEmprunteurs.add("emprunteurs3");
		choiceEmprunteurs.add("emprunteurs4");

		// Lorsque que l'on clique sur un element de la liste de livre empruntables
		listLivresEmpruntables.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// desactivation la selection sur la liste de livres empruntes
				listLivresempruntes.select(-1);

				if (listLivresempruntes.getSelectedIndex() == -1) {

					// activation de la modification du choix de l'emprunteurs
					choiceEmprunteurs.setEnabled(true);
					// activation de la modification due la date de début d'emprunt
					textFieldDateEmprunts.setEnabled(true);

					// on désactive le bouton modifiers
					btnModifier.setVisible(false);
					btnAjouter.setVisible(true);

				}
			}
		});

		// Lorsque que l'on clique sur un element de la liste de livre empruntes
		listLivresempruntes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				// desactivation la selection sur la liste de livres empruntables

				listLivresEmpruntables.select(-1);

				if (listLivresEmpruntables.getSelectedIndex() == -1) {

					// recuperation de l'emprunteur lier au livre
					choiceEmprunteurs.add("emprunteurs1");
					// desactivation du choix de l'emprunteur
					choiceEmprunteurs.setEnabled(false);
					// recuperation de la date d'emprunts
					textFieldDateEmprunts.setText("15/03/2018");

					// desactivation de la date d'emprunts
					textFieldDateEmprunts.setEnabled(false);
					// desactivation du bouton ajouter
					btnAjouter.setVisible(false);
					btnModifier.setVisible(true);
				}

			}
		});

		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Veérification du format de la date

				sdf.setLenient(false);

				try {
					Date date = sdf.parse(textFielddateDeRetour.getText());
					if (!sdf.format(date).equals(textFielddateDeRetour.getText())) {
						throw new ParseException(textFielddateDeRetour.getText() + " is not a valid format for " + format, 0);
					}
				} catch (ParseException ex) {
					ex.printStackTrace();
				} finally {
					System.out.println("Date Modifier");
				}

			}
		});

		// Evenement lors du clique sur le bouton modifier pour changer la date de rendu ou en

		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// si les deux champs de la date sont vides
				if (textFieldDateEmprunts.getText().isEmpty() || textFielddateDeRetour.getText().isEmpty()) {
					System.out.println("Un des champs est vide");
				} else {
					try {
						Date dateRetour = sdf.parse(textFielddateDeRetour.getText());
						System.out.println(dateRetour);
						Date dateEmprunts = sdf.parse(textFieldDateEmprunts.getText());

						if (!sdf.format(dateRetour).equals(textFielddateDeRetour.getText())) {
							throw new ParseException(textFielddateDeRetour.getText() + " is not a valid format for " + format, 0);
						}
						else{
//							if(dateRetour>dateEmprunts)
//							{
//
//							}
						}
					} catch (ParseException ex) {
						ex.printStackTrace();
					} finally {
						System.out.println("Livre bien emprunter");

					}

				}

				if(date>date2)

			}
		});

	}
}
