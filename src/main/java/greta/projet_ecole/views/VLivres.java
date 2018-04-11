package greta.projet_ecole.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;

public class VLivres {

	private JFrame frame;

	String donnees[] = { "titre|annee|editeur|prenomauteur|nomauteur", "titre|annee|editeur|prenomauteur|nomauteur", "titre|annee|editeur|prenomauteur|nomauteur",
			"titre|annee|editeur|prenomauteur|nomauteur", "titre|annee|editeur|prenomauteur|nomauteur", "titre|annee|editeur|prenomauteur|nomauteur", "titre|annee|editeur|prenomauteur|nomauteur",
			"titre|annee|editeur|prenomauteur|nomauteur", "titre|annee|editeur|prenomauteur|nomauteur", "titre|annee|editeur|prenomauteur|nomauteur" };
	JList list = new JList(donnees);

	JPanel panel = new JPanel();
	JButton addbutton = new JButton("+");
	JButton delbutton = new JButton("-");
	JLabel lblTitre = new JLabel("Titre :");
	JLabel lblAnne = new JLabel("Année :");
	JLabel lblEditeur = new JLabel("Editeur :");
	JLabel lblPrnomAuteur = new JLabel("Prénom auteur :");
	JLabel lblNomAuteur = new JLabel("Nom auteur :");
	private JTextField textTitle;
	private JTextField textFieldAnnee;
	private JTextField textFieldEditeur;
	private JTextField textFieldPAuteur;
	private JTextField textFieldNAuteur;
	private final JButton btnAjouter = new JButton("Ajouter");
	private final JButton btnNettoyer = new JButton("Annuler");
	// private final JScrollBar scrollBar_1 = new JScrollBar();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VLivres window = new VLivres();
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
	public VLivres() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 684, 312);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		// cache le panel
		panel.setVisible(false);
		list.setBorder(new LineBorder(new Color(0, 0, 0)));

		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(23, 21, 610, 74);

		frame.getContentPane().add(list);
		// ScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		// frame.getContentPane().add(ScrollPane);

		addbutton.setBounds(543, 103, 41, 23);
		frame.getContentPane().add(addbutton);
		addbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
			}
		});

		delbutton.setBounds(594, 103, 44, 23);
		frame.getContentPane().add(delbutton);

		panel.setBackground(Color.WHITE);
		panel.setBounds(28, 137, 610, 116);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		lblTitre.setBounds(10, 11, 46, 14);
		panel.add(lblTitre);

		lblAnne.setBounds(10, 42, 46, 14);
		panel.add(lblAnne);

		lblEditeur.setBounds(10, 78, 46, 14);
		panel.add(lblEditeur);

		lblPrnomAuteur.setBounds(179, 11, 98, 14);
		panel.add(lblPrnomAuteur);

		lblNomAuteur.setBounds(179, 42, 78, 14);
		panel.add(lblNomAuteur);

		textTitle = new JTextField();
		textTitle.setBounds(62, 8, 86, 20);
		panel.add(textTitle);
		textTitle.setColumns(10);

		textFieldAnnee = new JTextField();
		textFieldAnnee.setBounds(62, 39, 86, 20);
		panel.add(textFieldAnnee);
		textFieldAnnee.setColumns(10);

		textFieldEditeur = new JTextField();
		textFieldEditeur.setBounds(62, 75, 86, 20);
		panel.add(textFieldEditeur);
		textFieldEditeur.setColumns(10);

		textFieldPAuteur = new JTextField();
		textFieldPAuteur.setBounds(274, 8, 86, 20);
		panel.add(textFieldPAuteur);
		textFieldPAuteur.setColumns(10);

		textFieldNAuteur = new JTextField();
		textFieldNAuteur.setBounds(274, 39, 86, 20);
		panel.add(textFieldNAuteur);
		textFieldNAuteur.setColumns(10);
		btnAjouter.setBounds(403, 82, 89, 23);

		panel.add(btnAjouter);
		btnNettoyer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldAnnee.setText("");
				textTitle.setText("");
				textFieldEditeur.setText("");
				textFieldPAuteur.setText("");
				textFieldNAuteur.setText("");
			}
		});
		btnNettoyer.setBounds(511, 82, 89, 23);

		panel.add(btnNettoyer);

	}
}
