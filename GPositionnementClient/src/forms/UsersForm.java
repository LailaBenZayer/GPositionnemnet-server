package forms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.LookUpUtilisateur;
import dao.UtilisateurRemote;
import entities.Utilisateur;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.Hashtable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

public class UsersForm extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JTextField textFieldTelephone;
	private JTextField textFieldEmail;
	private JTextField textFieldDate;
	//private DefaultTableModel model;

	public static dao.UtilisateurRemote lookUpUtilisateurRemote() throws NamingException {
		Hashtable<Object, Object> jndiProperties = new Hashtable<Object, Object>();

		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		final Context context = new InitialContext(jndiProperties);

		return (UtilisateurRemote) context.lookup("ejb:/GPosionnementServer/UtilisateurService!dao.UtilisateurRemote");

	}

	UtilisateurRemote u;
	private String nom, prenom, email, telephone, date;

	private JTable listUsers;
	
	
	/*
	public void loadContenu(){
		 model.setRowCount(0);
	    for (Utilisateur user : u.listUsers()) {
	    	model.addRow(new Object[]{
	                    user.getId(),
	                    user.getNom(),
	                    user.getPrenom(),
	                    user.getTelephone(),
	                    user.getEmail(),
	                    user.getDateNaissance()
	        });
	     }
		
	}*/
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsersForm frame = new UsersForm();
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
	public UsersForm() {

		try {
			u = lookUpUtilisateurRemote();
		} catch (NamingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		//model = (DefaultTableModel) listUsers.getModel();
		//loadContenu();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 592, 592);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 556, 221);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Ajouter Utilisateur ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 0, 139));
		lblNewLabel.setBounds(187, 0, 138, 25);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nom :");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 55, 61, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Prenom :");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 98, 61, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("T\u00E9lephone :");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(10, 143, 61, 14);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Email :");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(293, 55, 46, 14);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Date Naissance :");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(293, 98, 81, 14);
		panel.add(lblNewLabel_5);

		textFieldNom = new JTextField();
		textFieldNom.setBounds(73, 52, 121, 20);
		panel.add(textFieldNom);
		textFieldNom.setColumns(10);

		textFieldPrenom = new JTextField();
		textFieldPrenom.setBounds(73, 95, 121, 20);
		panel.add(textFieldPrenom);
		textFieldPrenom.setColumns(10);

		textFieldTelephone = new JTextField();
		textFieldTelephone.setBounds(73, 140, 121, 20);
		panel.add(textFieldTelephone);
		textFieldTelephone.setColumns(10);

		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(384, 52, 119, 20);
		panel.add(textFieldEmail);
		textFieldEmail.setColumns(10);

		textFieldDate = new JTextField();
		textFieldDate.setBounds(384, 95, 119, 20);
		panel.add(textFieldDate);
		textFieldDate.setColumns(10);

		JButton btnAjouter = new JButton("Ajouter ");
		btnAjouter.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnAjouter.setBackground(new Color(255, 192, 203));
		btnAjouter.setForeground(new Color(112, 128, 144));
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nom = textFieldNom.getText();
				prenom = textFieldPrenom.getText();
				telephone = textFieldTelephone.getText();
				email = textFieldEmail.getText();
				date = textFieldDate.getText();

					u.addUser(new Utilisateur(nom, prenom, telephone, email, new Date()));
					JOptionPane.showMessageDialog(null, "Utilisateur Bien Ajouter");
				

			}

		});
		btnAjouter.setBounds(223, 187, 89, 23);
		panel.add(btnAjouter);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(384, 126, 119, 20);
		panel.add(dateChooser);

		JLabel lblNewLabel_6 = new JLabel("Liste Utilisateur :");
		lblNewLabel_6.setBounds(10, 245, 109, 20);
		contentPane.add(lblNewLabel_6);

		listUsers = new JTable();
		listUsers.setBounds(21, 413, 531, -136);
		contentPane.add(listUsers);
	}
}
