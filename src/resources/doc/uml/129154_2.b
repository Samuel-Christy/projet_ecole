class VEmprunts
!!!139138.java!!!	main(inout args : String [[]]) : void
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
!!!139266.java!!!	VEmprunts()
		initialize();
!!!139394.java!!!	initialize() : void
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

