package oto;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Transparency;

import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.Dialog.ModalExclusionType;

public class AddCar extends JFrame {
	private JTextField textField;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCar frame = new AddCar();
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
	public AddCar() {
		setUndecorated(true);
		setResizable(false);
		setTitle("ARAÇ KAYDI\r\n");
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 893, 515);
		contentPane = new JPanel();
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		contentPane.setBackground(UIManager.getColor("ToolTip.background"));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JFrame jf=this;
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("ToolTip.background"));
		panel.setBorder(new TitledBorder(null, "Ara\u00E7 Tipini Se\u00E7in", TitledBorder.LEADING, TitledBorder.TOP, null, null));
 
		panel.setToolTipText("");
		panel.setBounds(39, 118, 781, 133);
		contentPane.add(panel);
		
		ButtonGroup group = new ButtonGroup();
		panel.setLayout(null);
		
		JRadioButton official = new JRadioButton("Resmi Araç");
		official.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		official.setBackground(UIManager.getColor("ToolTip.background"));
		official.setBounds(57, 30, 165, 54);
		panel.add(official);
		
		JRadioButton regular = new JRadioButton("Normal Araç");
		regular.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		regular.setBackground(UIManager.getColor("ToolTip.background"));
		regular.setBounds(270, 30, 181, 54);
		panel.add(regular);
		
		JRadioButton subs = new JRadioButton("Üye Araç");
		subs.setFont(new Font("Tw Cen MT", Font.PLAIN, 20));
		subs.setBackground(UIManager.getColor("ToolTip.background"));
		subs.setBounds(524, 30, 153, 54);
		panel.add(subs);
		
	     group.add(official);
	        group.add(regular);
	        group.add(subs);
	        
	        textField = new JTextField();
	        textField.setBounds(357, 39, 229, 39);
	        contentPane.add(textField);
	        textField.setColumns(10);
	        
	        JLabel lblAraPlakas = new JLabel("Araç Plakası");
	        lblAraPlakas.setFont(new Font("Eras Medium ITC", Font.ITALIC, 19));
	        lblAraPlakas.setBounds(189, 39, 141, 39);
	        contentPane.add(lblAraPlakas);
	        
	        JButton btnNewButton = new JButton("Onayla");
	        btnNewButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		
	        		
	        	}
	        });
	        btnNewButton.setFont(new Font("Source Code Pro Semibold", Font.ITALIC, 18));
	        btnNewButton.setBounds(306, 321, 229, 46);
	        contentPane.add(btnNewButton);
	        
	        JButton btnIptal = new JButton("İptal");
	        btnIptal.addActionListener(new ActionListener() {
	        	
	        	public void actionPerformed(ActionEvent arg0) {
	        		jf.setVisible(false);
	        	}
	        });
	        btnIptal.setFont(new Font("Source Code Pro Semibold", Font.ITALIC, 18));
	        btnIptal.setBounds(306, 397, 229, 46);
	        contentPane.add(btnIptal);
	}
}
