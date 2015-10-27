package winedrinker.util;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JButton;

import winedrinker.WineDrinker;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
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
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 152);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Use preset?");
		contentPane.add(chckbxNewCheckBox, BorderLayout.NORTH);
		
		JLabel lblMakeSureYou = new JLabel("Make sure you use preset 1!");
		contentPane.add(lblMakeSureYou, BorderLayout.WEST);
		
		JButton btnOkay = new JButton("Okay");
		btnOkay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	if(chckbxNewCheckBox.isSelected()) {
					WineDrinker.usePresets = true;
				}
		    	WineDrinker.pressedOk = true;
		    	dispose();
			}
		});
		contentPane.add(btnOkay, BorderLayout.SOUTH);
	}

}
