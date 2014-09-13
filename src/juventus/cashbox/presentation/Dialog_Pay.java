package juventus.cashbox.presentation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

public class Dialog_Pay extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_Kunde;
	private String strTotal = "";
	private boolean boolOk = false;


	/**
	 * Create the dialog.
	 */
	public Dialog_Pay(String param_Total) {
		strTotal = param_Total;
		setBounds(100, 100, 234, 168);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblBetrag = new JLabel("Kosten");
		JLabel lblKunde = new JLabel("Einzahlungsbetrag");
		
		textField_Kunde = new JTextField();
		textField_Kunde.setColumns(10);
		
		JLabel label_Total = new JLabel("");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblBetrag)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(label_Total)
							.addGap(25))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblKunde)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField_Kunde, GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
							.addContainerGap())))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBetrag)
						.addComponent(label_Total))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblKunde)
						.addComponent(textField_Kunde, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
											
						if(TestDigits() && Double.valueOf(textField_Kunde.getText()) >= Double.valueOf(strTotal))
						{
							BigDecimal bigdecEinzahlung;
							bigdecEinzahlung = BigDecimal.valueOf(Double.valueOf(textField_Kunde.getText()));
							
							BigDecimal bigdecRueckgeld;
							bigdecRueckgeld = bigdecEinzahlung.subtract(BigDecimal.valueOf(Double.valueOf(strTotal)));
							JOptionPane.showMessageDialog(null, "Total: " + strTotal + "\n" + "Bezahlt: " + bigdecEinzahlung.setScale(2).toString() + "\n" + "Rueckgeld: " + bigdecRueckgeld.setScale(2).toString());
							JOptionPane.showMessageDialog(null, "Vielen Dank und einen guten Tag.");
							SetState(true);
							dispose();
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Bitte geben Sie einen Korrekten Betrag ein");
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						SetState(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		label_Total.setText(param_Total);		
	}
	public boolean TestDigits()
	{
	    try {
	    	double x = Double.parseDouble(textField_Kunde.getText());
	        //JOptionPane.showMessageDialog(null, "Ist Zahl");
	        return true;
	    } catch (NumberFormatException nfe) {
	    	return false;
	    }
	}
	
	public boolean GetState()
	{
		return boolOk;
	}
	
	public void SetState(boolean param_State)
	{
		this.boolOk = param_State;
	}
	
}
