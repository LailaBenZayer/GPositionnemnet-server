package forms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JDesktopPane;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import forms.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ImageIcon;

public class MenuForm extends JFrame {

	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuForm frame = new MenuForm();
					frame.setVisible(true);
					//frame.setMaximizedBounds(null);
					//rame.setBounds(EXIT_ON_CLOSE, ABORT, 1280, 667);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	private void ShowForm(){
		
	}
	
	public MenuForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1033, 564);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 135, 525);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Menu :");
		lblNewLabel.setForeground(new Color(0, 0, 128));
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 95, 78, 24);
		panel.add(lblNewLabel);
		
		JDesktopPane deskPane = new JDesktopPane();
		deskPane.setBackground(Color.LIGHT_GRAY);
		deskPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.PINK));
		deskPane.setBounds(133, 0, 864, 525);
		contentPane.add(deskPane);
		
		JLabel lblUsers = new JLabel("Utilisateurs ");
		lblUsers.setFont(new Font("Calibri", Font.BOLD, 16));
		lblUsers.setBackground(new Color(255, 245, 238));
		lblUsers.setForeground(new Color(47, 79, 79));
		lblUsers.setBounds(20, 130, 102, 28);
		panel.add(lblUsers);
		
		JLabel lblNewLabel_1 = new JLabel("SmartPhone");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 16));
		lblNewLabel_1.setBackground(new Color(255, 245, 238));
		lblNewLabel_1.setForeground(new Color(47, 79, 79));
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SmartPhoneForm f=new SmartPhoneForm();
				deskPane.removeAll();
				deskPane.add(f);
				int a = deskPane.getWidth(), b = deskPane.getHeight();
				f.setSize(a, b);
				f.setLocation((deskPane.getWidth() - deskPane.getWidth()) / 2, (deskPane.getHeight() - deskPane.getHeight()) / 3);
		        f.setVisible(true);
			}
		}); 
		lblNewLabel_1.setBounds(20, 186, 105, 28);
		panel.add(lblNewLabel_1);
		
		Component verticalGlue = Box.createVerticalGlue();
		verticalGlue.setBounds(124, 11, 1, 1);
		panel.add(verticalGlue);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(MenuForm.class.getResource("/icon/2.jpg")));
		lblNewLabel_2.setBounds(10, 11, 102, 72);
		panel.add(lblNewLabel_2);
		lblUsers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UtilisateursForm f=new UtilisateursForm();
				deskPane.removeAll();
				deskPane.add(f);
				int a = deskPane.getWidth(), b = deskPane.getHeight();
				f.setSize(a, b);
				f.setLocation((deskPane.getWidth() - deskPane.getWidth()) / 2, (deskPane.getHeight() - deskPane.getHeight()) / 3);
		        f.setVisible(true);
			}
		});
		
		
		
	}
}
