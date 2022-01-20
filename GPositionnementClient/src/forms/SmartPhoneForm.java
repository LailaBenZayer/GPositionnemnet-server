package forms;

import java.awt.EventQueue;
import java.util.Date;
import java.util.Hashtable;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.IDaoRemote;
import entities.SmartPhone;
import entities.Utilisateur;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Font;
import javax.swing.border.MatteBorder;

public class SmartPhoneForm extends JInternalFrame {
	private JTextField textFieldimei;
	private JComboBox comboBoxUsers;
	DefaultTableModel model;
	private int id=-1, idUser;
	private String imei;
	private Utilisateur user;
	
	public static dao.IDaoRemote<SmartPhone> lookUpSmartPhoneRemote() throws NamingException {
		Hashtable<Object, Object> jndiProperties = new Hashtable<Object, Object>();

		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		final Context context = new InitialContext(jndiProperties);
		return (IDaoRemote<SmartPhone>) context.lookup("ejb:/GPosionnementServer/SmartPhoneService!dao.IDaoRemote");

	}
	
	public static dao.IDaoRemote<Utilisateur> lookUpUtilisateurRemote() throws NamingException {
		Hashtable<Object, Object> jndiProperties = new Hashtable<Object, Object>();

		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		final Context context = new InitialContext(jndiProperties);
		return (IDaoRemote<Utilisateur>) context.lookup("ejb:/GPosionnementServer/UtilisateurService!dao.IDaoRemote");

	}

	IDaoRemote<Utilisateur> u;

	IDaoRemote<SmartPhone> s;
	private JTable tableSmartPhones;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SmartPhoneForm frame = new SmartPhoneForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
    private void loadCombobox() {
        for (Utilisateur u : u.findAll()) {
            comboBoxUsers.addItem(u);
        }
    }

    public void loadContenu() {
		model.setRowCount(0);
		//model.addRow(new Object[] { "ID", "IMEI", "Utilisateur"});

		if (s.findAll() != null) {
			for (SmartPhone phone : s.findAll()) {
				model.addRow(new Object[] { 
						  phone.getId(),
						  phone.getImei()
						, u.findById(phone.getUtilisateur().getId()) });
			}
		}

	}
    
    public void Clear(){
		textFieldimei.setText("");
		//comboBoxUsers.setSelectedIndex(-1);
	}
	/**
	 * Create the frame.
	 */
	public SmartPhoneForm() {
		setRootPaneCheckingEnabled(false);
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		getContentPane().setBackground(Color.WHITE);
		
		try {
			s = lookUpSmartPhoneRemote();
			u = lookUpUtilisateurRemote();
		} catch (NamingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		setBounds(100, 100, 804, 593);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Infos SmartPhone :");
		lblNewLabel.setForeground(new Color(0, 128, 128));
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 44, 131, 25);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("IMEI :");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(59, 80, 37, 19);
		getContentPane().add(lblNewLabel_1);
		
		textFieldimei = new JTextField();
		textFieldimei.setBounds(95, 79, 162, 22);
		getContentPane().add(textFieldimei);
		textFieldimei.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Utilisateur :");
		lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(342, 80, 74, 19);
		getContentPane().add(lblNewLabel_2);
		
		comboBoxUsers = new JComboBox();
		comboBoxUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		            Utilisateur us = (Utilisateur) comboBoxUsers.getSelectedItem();
		            System.out.println(us.getId());
		            idUser = us.getId();
		            user = us;
		        } catch (Exception ex) {
		            System.out.println(ex);
		        }
			}
		});
		comboBoxUsers.setBounds(426, 77, 131, 22);
		getContentPane().add(comboBoxUsers);
		
		JLabel lblNewLabel_3 = new JLabel("Liste Telephones :");
		lblNewLabel_3.setForeground(new Color(0, 128, 128));
		lblNewLabel_3.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_3.setBounds(24, 213, 142, 22);
		getContentPane().add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.setBackground(new Color(70, 130, 180));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imei = textFieldimei.getText();
				
		        if (s.Create(new SmartPhone(imei,user))) {
		            loadContenu();
		            JOptionPane.showMessageDialog(null, "SmartPhone Bien Ajouter");
		            Clear();
		        } else {
		            JOptionPane.showMessageDialog(null, "Erreur d'ajout");
		        }
			}
		});
		btnNewButton.setBounds(82, 143, 100, 27);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Modifier");
		btnNewButton_1.setBackground(new Color(70, 130, 180));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Calibri", Font.BOLD, 12));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (id != -1) {
					String imeii = textFieldimei.getText();
					
					SmartPhone smart=new SmartPhone(id,imeii,user);
					smart.setImei(imeii);
					smart.setUtilisateur(user);
					s.update(smart);
					loadContenu();
					JOptionPane.showMessageDialog(null, "SmartPhone bien modifier");
					Clear();
				} else {
					JOptionPane.showMessageDialog(null, "Veuillez selectionner une ligne");
				}
			}
		});
		btnNewButton_1.setBounds(283, 143, 100, 27);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Supprimer");
		btnNewButton_2.setBackground(new Color(70, 130, 180));
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setFont(new Font("Calibri", Font.BOLD, 12));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (id != -1) {
					s.delete(s.findById(id));
					loadContenu();
					JOptionPane.showMessageDialog(null, "Delete Succesfully");
					Clear();
				} else {
					JOptionPane.showMessageDialog(null, "Veuillez selectionner une ligne");
				}
			}
		});
		btnNewButton_2.setBounds(495, 143, 114, 27);
		getContentPane().add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		scrollPane.setBounds(48, 237, 711, 199);
		getContentPane().add(scrollPane);
		
		tableSmartPhones = new JTable();
		tableSmartPhones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id = Integer.parseInt(model.getValueAt(tableSmartPhones.getSelectedRow(), 0).toString());

		        textFieldimei.setText(model.getValueAt(tableSmartPhones.getSelectedRow(), 1).toString());
		        String item = comboBoxUsers.getEditor().getItem().toString();
		        comboBoxUsers.getEditor().setItem(model.getValueAt(tableSmartPhones.getSelectedRow(), 2).toString());
		        
		        System.out.println("1***"+item);
			}
		});
		tableSmartPhones.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				
			}
		});
		tableSmartPhones.setBackground(new Color(245, 245, 245));
		model = new DefaultTableModel();
		Object[] column = { "ID", "IMEI", "Utilisateur"};
		Object[] row = new Object[0];
		model.setColumnIdentifiers(column);
		tableSmartPhones.setModel(model);
		loadContenu();
		scrollPane.setViewportView(tableSmartPhones);
		loadCombobox();
		loadContenu();

	}
}
