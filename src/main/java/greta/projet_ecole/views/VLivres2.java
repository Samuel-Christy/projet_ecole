package greta.projet_ecole.views;

import java.awt.Button;
import java.awt.EventQueue;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import greta.projet_ecole.controllers.CBibliotheque;
import greta.projet_ecole.models.MLivre;

public class VLivres2 {

	private CBibliotheque controller;
	// private JFrame frame;
	// private JTextField edTitre;
	// private JTextField edNomAuteur;
	// private JTextField edPrenomAuteur;
	// private JLabel lblNomAuteur;
	// private JLabel lblPrenomAuteur;
	// private JLabel lblEditeur;
	// private JTextField edEditeur;
	// private Button btnAccept;
	// private Button btnCancel;

	private boolean editsfromList = false;

	private JFrame frame = new JFrame();

	public void setController(CBibliotheque controller) {
		this.controller = controller;
	}

	private List list = new List();
	private Button buttonNew = new Button("nouveau livre");
	private JPanel panel = new JPanel();
	private JTextField edTitre = new JTextField();
	private JTextField edNomAuteur = new JTextField();
	private JTextField edPrenomAuteur = new JTextField();
	private JLabel lblTitre = new JLabel("Titre");
	private JLabel lblNomAuteur = new JLabel("Nom de l'auteur");
	private JLabel lblPrenomAuteur = new JLabel("Prénom de l'auteur");
	private JLabel lblEditeur = new JLabel("Editeur");
	private JTextField edEditeur = new JTextField();
	private JSpinner spinEdDate = new JSpinner();
	private JLabel lblAnnee = new JLabel("Année de publication");
	private Button btnAccept = new Button("OK");
	private Button btnCancel = new Button("Annuler");

	private ArrayList<MLivre> livres;
	private final JLabel lblError = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, final CBibliotheque controller) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VLivres2 window = new VLivres2();
					window.frame.setVisible(true);
					window.setController(controller);
					window.getList();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VLivres2() {
		initialize();
		// getList();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame.setBounds(100, 100, 538, 630);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				editsfromList = true;
				listOnClic();
			}
		});

		list.setBounds(10, 33, 497, 85);
		frame.getContentPane().add(list);
		buttonNew.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				editsfromList = false;
				prepareNewLivre();
			}
		});

		buttonNew.setBounds(420, 123, 87, 22);
		frame.getContentPane().add(buttonNew);

		panel.setBounds(10, 164, 497, 310);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		edTitre.setBounds(10, 44, 477, 20);
		panel.add(edTitre);
		edTitre.setColumns(10);

		edNomAuteur.setBounds(10, 91, 477, 20);
		panel.add(edNomAuteur);
		edNomAuteur.setColumns(10);

		edPrenomAuteur.setBounds(10, 140, 477, 20);
		panel.add(edPrenomAuteur);
		edPrenomAuteur.setColumns(10);

		lblTitre.setBounds(10, 28, 46, 14);
		panel.add(lblTitre);

		lblNomAuteur.setBounds(10, 75, 126, 14);
		panel.add(lblNomAuteur);

		lblPrenomAuteur.setBounds(10, 122, 126, 14);
		panel.add(lblPrenomAuteur);

		lblEditeur.setBounds(10, 171, 114, 14);
		panel.add(lblEditeur);

		edEditeur.setColumns(10);
		edEditeur.setBounds(10, 187, 477, 20);
		panel.add(edEditeur);

		spinEdDate.setModel(new SpinnerNumberModel(2018, -2000, 2100, 1));
		spinEdDate.setBounds(10, 235, 126, 20);
		panel.add(spinEdDate);

		lblAnnee.setBounds(10, 218, 126, 14);
		panel.add(lblAnnee);
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveLivre();
				prepareNewLivre();
			}
		});

		btnAccept.setBounds(417, 278, 70, 22);
		panel.add(btnAccept);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prepareNewLivre();
			}
		});

		btnCancel.setBounds(335, 278, 70, 22);
		panel.add(btnCancel);
		lblError.setBounds(149, 258, 338, 14);

		panel.add(lblError);
	}

	private void getList() {
		list.removeAll();
		livres = controller.getLivres();
		for (MLivre mLivre : livres) {
			list.add(mLivre.toString());
		}

	}

	private void livreToEdits(MLivre livre) {
		edEditeur.setText(livre.getEditeur());
		edNomAuteur.setText(livre.getNom_auteur());
		edPrenomAuteur.setText(livre.getPrenom_auteur());
		edTitre.setText(livre.getTitre());
		spinEdDate.setValue(livre.getAnnee());
	}

	private void listOnClic() {

		livreToEdits(controller.getLivres().get(list.getSelectedIndex()));
		lblError.setText(""); // reset de l'erreur

	}

	private void prepareNewLivre() {
		edEditeur.setText("");
		edNomAuteur.setText("");
		edPrenomAuteur.setText("");
		edTitre.setText("");
		spinEdDate.setValue(2018);
		lblError.setText(""); // reset de l'erreur

	}

	private boolean fieldsOk() {
		return (edEditeur.getText().equals("") == false && edNomAuteur.getText().equals("") == false
				&& edPrenomAuteur.getText().equals("") == false && edTitre.getText().equals("") == false);
	}

	private void saveLivre() {

		if (fieldsOk()) {
			lblError.setText("");
			MLivre livre = editsfromList ? controller.getLivres().get(list.getSelectedIndex()) : new MLivre();

			// edNomAuteur.getText(), edPrenomAuteur.getText(),
			// edTitre.getText(),
			// , edEditeur.getText()

			livre.setAnnee(Integer.parseInt(spinEdDate.getValue().toString()));
			livre.setEditeur(edEditeur.getText());
			livre.setNom_auteur(edNomAuteur.getText());
			livre.setPrenom_auteur(edPrenomAuteur.getText());
			livre.setTitre(edTitre.getText());

			if (!controller.getLivres().contains(livre)) {
				controller.getLivres().add(livre);
			}

			getList();

		} else {
			lblError.setText("Erreur, un des champs est vide");
		}

	}

}
