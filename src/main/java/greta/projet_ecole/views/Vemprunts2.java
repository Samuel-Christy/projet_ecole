package greta.projet_ecole.views;

import java.awt.Button;
import java.awt.EventQueue;
import java.awt.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import greta.projet_ecole.controllers.CBibliotheque;
import greta.projet_ecole.models.MLivre;
import greta.projet_ecole.models.MUsager;

public class Vemprunts2 {

	private CBibliotheque controller;

	private JFrame frame;
	private Button btnOK = new Button("New button");
	private Button btnReturn = new Button("New button");
	private JSpinner spindateReturn = new JSpinner();
	private List listNotAvailable = new List();
	private List listAvailable = new List();

	private JComboBox<String> cbUser = new JComboBox();
	private JSpinner spinDateOut = new JSpinner();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, final CBibliotheque controller) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Vemprunts2 window = new Vemprunts2();
					window.frame.setVisible(true);
					window.setController(controller);

					window.listUsers();
					window.listBooks();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setController(CBibliotheque controller) {
		this.controller = controller;
	}

	/**
	 * Create the application.
	 */
	public Vemprunts2() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 272);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		listAvailable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				swapMode(listAvailable);
			}
		});

		listAvailable.setBounds(10, 10, 198, 115);
		frame.getContentPane().add(listAvailable);
		listNotAvailable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				swapMode(listNotAvailable);
			}
		});

		listNotAvailable.setBounds(226, 10, 198, 115);
		frame.getContentPane().add(listNotAvailable);

		cbUser.setBounds(10, 143, 198, 20);
		frame.getContentPane().add(cbUser);

		spinDateOut.setModel(new SpinnerDateModel(new Date(1523484000000L), null, null, Calendar.DAY_OF_YEAR));
		spinDateOut.setBounds(10, 174, 70, 20);
		frame.getContentPane().add(spinDateOut);

		spindateReturn.setModel(new SpinnerDateModel(new Date(1523484000000L), null, null, Calendar.DAY_OF_YEAR));
		spindateReturn.setBounds(138, 174, 70, 20);
		frame.getContentPane().add(spindateReturn);

		btnReturn.setBounds(226, 141, 198, 22);
		frame.getContentPane().add(btnReturn);

		btnOK.setBounds(10, 205, 198, 22);
		frame.getContentPane().add(btnOK);
	}

	private void swapMode(List sender) {
		if (sender.equals(listAvailable)) {
			// we chose an available entry
			newEmprunt();
		} else {
			editEmprunt();
		}
	}

	private void newEmprunt() {
		listNotAvailable.select(-1);

	}

	private void editEmprunt() {
		listAvailable.select(-1);
	}

	private void listUsers() {
		cbUser.removeAllItems();
		ArrayList<MUsager> usagers = controller.getUsagers();
		cbUser.addItem("");
		for (MUsager mUsager : usagers) {
			cbUser.addItem(mUsager.toString());

		}

	}

	private void listBooks() {
		listAvailableBooks();
		listUnavailableBooks();
	}

	private void listAvailableBooks() {

		ArrayList<MLivre> livres = controller.getLivres();
		listAvailable.removeAll();
		for (MLivre mLivre : livres) {
			if (mLivre.getEmprunteur() == null) {
				listAvailable.add(livres.toString());
			}
		}
	}

	private void listUnavailableBooks() {
		ArrayList<MLivre> livres = controller.getLivres();
		listNotAvailable.removeAll();
		for (MLivre mLivre : livres) {
			if (mLivre.getEmprunteur() != null) {
				listNotAvailable.add(livres.toString());
			}
		}
	}

}
