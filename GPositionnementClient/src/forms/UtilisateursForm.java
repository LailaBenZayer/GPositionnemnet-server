package forms;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.util.Date;
import java.util.Hashtable;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.IDaoRemote;
import entities.Utilisateur;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.MatteBorder;

public class UtilisateursForm extends JInternalFrame {
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JTextField textFieldTelephone;
	private JTextField textFieldEmail;
	private JTable tableUsers;
	JDateChooser jdateNaiss;
	DefaultTableModel model;
	private int id=-1;

	public static dao.IDaoRemote<Utilisateur> lookUpUtilisateurRemote() throws NamingException {
		Hashtable<Object, Object> jndiProperties = new Hashtable<Object, Object>();

		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		final Context context = new InitialContext(jndiProperties);
		return (IDaoRemote<Utilisateur>) context.lookup("ejb:/GPosionnementServer/UtilisateurService!dao.IDaoRemote");

	}

	IDaoRemote<Utilisateur> u;
	private String nom, prenom, email, telephone;
	Date date;

	public void loadContenu() {
		model.setRowCount(0);
		model.addRow(new Object[] { "ID", "Nom", "Prenom", "Telephone", "Email", "Date naissance" });

		if (u.findAll() != null) {
			for (Utilisateur user : u.findAll()) {
				model.addRow(new Object[] { user.getId(), user.getNom(), user.getPrenom(), user.getTelephone(),
						user.getEmail(), user.getDateNaissance() });
			}
		}

	}
	
	public void Clear(){
		textFieldEmail.setText("");
		textFieldNom.setText("");
		textFieldPrenom.setText("");
		textFieldTelephone.setText("");
		jdateNaiss.setDate(null);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UtilisateursForm frame = new UtilisateursForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UtilisateursForm() {
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		getContentPane().setBackground(Color.WHITE);

		try {
			u = lookUpUtilisateurRemote();
		} catch (NamingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		setBounds(100, 100, 796, 548);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 780, 196);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Nom :");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(58, 59, 61, 14);
		panel.add(lblNewLabel_1);

		textFieldNom = new JTextField();
		textFieldNom.setColumns(10);
		textFieldNom.setBounds(121, 56, 121, 20);
		panel.add(textFieldNom);

		textFieldPrenom = new JTextField();
		textFieldPrenom.setColumns(10);
		textFieldPrenom.setBounds(121, 99, 121, 20);
		panel.add(textFieldPrenom);

		JLabel lblNewLabel_2 = new JLabel("Prenom :");
		lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(58, 102, 61, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("T\u00E9lephone :");
		lblNewLabel_3.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(272, 62, 74, 14);
		panel.add(lblNewLabel_3);

		textFieldTelephone = new JTextField();
		textFieldTelephone.setColumns(10);
		textFieldTelephone.setBounds(343, 56, 121, 20);
		panel.add(textFieldTelephone);

		JLabel lblNewLabel_4 = new JLabel("Email :");
		lblNewLabel_4.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(272, 102, 46, 14);
		panel.add(lblNewLabel_4);

		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(346, 99, 119, 20);
		panel.add(textFieldEmail);

		JLabel lblNewLabel_5 = new JLabel("Date Naissance :");
		lblNewLabel_5.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(492, 78, 102, 20);
		panel.add(lblNewLabel_5);

		jdateNaiss = new JDateChooser();
		jdateNaiss.setBounds(596, 78, 119, 20);
		panel.add(jdateNaiss);

		JButton btnAjouter = new JButton("Ajouter ");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nom = textFieldNom.getText();
				prenom = textFieldPrenom.getText();
				telephone = textFieldTelephone.getText();
				email = textFieldEmail.getText();
				date = jdateNaiss.getDate();

				if (u.Create(new Utilisateur(nom, prenom, telephone, email, date))) {
					JOptionPane.showMessageDialog(null, "Utilisateur Bien Ajouter");
					loadContenu();
					Clear();
				}
				else
					JOptionPane.showMessageDialog(null, "Erreur d'ajout");
			}
		});
		btnAjouter.setForeground(Color.WHITE);
		btnAjouter.setFont(new Font("Calibri", Font.BOLD, 13));
		btnAjouter.setBackground(new Color(70, 130, 180));
		btnAjouter.setBounds(74, 162, 89, 27);
		panel.add(btnAjouter);

		JButton btnNewButton = new JButton("Modifier");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(70, 130, 180));
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (id != -1) {
					String nom = textFieldNom.getText();
					String prenom  = textFieldPrenom.getText();
					String telephone = textFieldTelephone.getText();
					String email = textFieldEmail.getText();
					Date date = jdateNaiss.getDate();
					
					Utilisateur user=new Utilisateur(id,nom,prenom,telephone,email,date);
					user.setNom(nom);
					user.setPrenom(prenom);
					user.setTelephone(telephone);
					user.setEmail(email);
					user.setDateNaissance(date);
					u.update(user);
					loadContenu();
					JOptionPane.showMessageDialog(null, "User bien modifier");
					Clear();
				} else {
					JOptionPane.showMessageDialog(null, "Veuillez selectionner une ligne");
				}
			}
		});
		btnNewButton.setBounds(274, 162, 103, 27);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Supprimer");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(new Color(70, 130, 180));
		btnNewButton_1.setFont(new Font("Calibri", Font.BOLD, 13));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (id != -1) {
					u.delete(u.findById(id));
					loadContenu();
					JOptionPane.showMessageDialog(null, "Delete Succesfully");
					Clear();
				} else {
					JOptionPane.showMessageDialog(null, "Veuillez selectionner une ligne");
				}

			}
		});
		btnNewButton_1.setBounds(504, 162, 103, 27);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Informations Utilisateur :");
		lblNewLabel.setForeground(new Color(0, 128, 128));
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel.setBounds(32, 11, 170, 23);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(245, 245, 245));
		panel_1.setBounds(0, 195, 780, 323);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 42, 733, 238);
		panel_1.add(scrollPane);

		tableUsers = new JTable();
		tableUsers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 id = Integer.parseInt(model.getValueAt(tableUsers.getSelectedRow(), 0).toString());

			        textFieldNom.setText(model.getValueAt(tableUsers.getSelectedRow(), 1).toString());
			        textFieldPrenom.setText(model.getValueAt(tableUsers.getSelectedRow(), 2).toString());
			        textFieldTelephone.setText(model.getValueAt(tableUsers.getSelectedRow(), 3).toString());
			        textFieldEmail.setText(model.getValueAt(tableUsers.getSelectedRow(), 4).toString());
			        //System.out.println("id***"+id+"ppp"+model.getValueAt(tableUsers.getSelectedRow(), 5));
			        jdateNaiss.setDate(new Date(model.getValueAt(tableUsers.getSelectedRow(), 5).toString().replace("-", "/")));
			        //System.out.println("id***"+id);
			        //JDesktopPane desktopPane = getDesktopPane();
			        
			}
		});
		tableUsers.setBackground(new Color(255, 250, 250));
		model = new DefaultTableModel();
		Object[] column = { "ID", "Nom", "Prenom", "Telephone", "Email", "Date naissance" };
		Object[] row = new Object[0];
		model.setColumnIdentifiers(column);
		tableUsers.setModel(model);
		loadContenu();
		scrollPane.setColumnHeaderView(tableUsers);
		
		JLabel lblNewLabel_6 = new JLabel("Liste utilisateurs :");
		lblNewLabel_6.setForeground(new Color(0, 128, 128));
		lblNewLabel_6.setFont(new Font("Calibri", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(20, 11, 122, 34);
		panel_1.add(lblNewLabel_6);

	}
}
