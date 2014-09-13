package juventus.cashbox.presentation;
import java.awt.BorderLayout;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Vector;

import  sun.audio.*;    //import the sun.audio package

import  java.io.*;

import juventus.cashbox.business.CashBox;
import juventus.cashbox.persistence.Order;


public class Dialog_CashBox extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private Dialog_Products dialogCashBox;
	private Dialog_Pay dialogPay;
	private Order order;
	private DefaultTableModel model;
	private CashBox cashBox;
	private ArrayList<String> list_Temp;
	private JTextField textField;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Dialog_CashBox dialog = new Dialog_CashBox();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Create the dialog.
	 */
	public Dialog_CashBox() {
		order = new Order();
		cashBox = new CashBox();
		list_Temp = new ArrayList<String>();
		
		setTitle("Kasse: " + cashBox.GetCashBoxMoney().setScale(2).toString());
		setBounds(100, 100, 378, 342);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblProdukte = new JLabel("Produkte");


		table = new JTable();
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Preis"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		model = (DefaultTableModel) table.getModel();
		
		
		JButton button_Add = new JButton("+");
		button_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialogCashBox = new Dialog_Products(order);
				dialogCashBox.setModal(true);
				dialogCashBox.setVisible(true);
				LoadOrderdProducts();
			}
		});
		
		JButton button_Remove = new JButton("-");
		button_Remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//JOptionPane.showMessageDialog(null, table.getSelectedRowCount());
				if(table.getSelectedRowCount() == 1)
				{
					order.RemoveProduct(model.getValueAt(table.getSelectedRow(), 1).toString());
					LoadOrderdProducts();
				}
			}
		});
		
		JButton button_Pay = new JButton("Bezahlen");
		button_Pay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialogPay = new Dialog_Pay(order.GetTotal());
				dialogPay.setModal(true);
				dialogPay.setVisible(true);
				
				if(dialogPay.GetState())
				{
					cashBox.AddCashBoxMoney(Double.valueOf(order.GetTotal()));
					setTitle("Kasse: " + cashBox.GetCashBoxMoney().setScale(2).toString());
					order.CleanOrder();
					LoadOrderdProducts();
				}
			}
		});
		
		JLabel lblNewLabel = new JLabel("Total:");
		
		JLabel label_Total = new JLabel("");
		
		textField = new JTextField();
		textField.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblProdukte)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(table, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_Total)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
								.addComponent(button_Pay))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(button_Remove, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(button_Add, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addContainerGap(24, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(21)
					.addComponent(lblProdukte)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(button_Add)
							.addGap(4)
							.addComponent(button_Remove))
						.addComponent(table, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_Total)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel)))
					.addGap(18)
					.addComponent(button_Pay)
					.addGap(32))
		);
		
		contentPanel.setLayout(gl_contentPanel);
	}
	
	public void LoadOrderdProducts()
	{
		//Clean
		list_Temp.clear();
		model.getDataVector().removeAllElements();
		this.textField.setText("");
				
		for(int i=0; order.GetOrder().size()>i ; i++)
		{
			if(!list_Temp.contains(order.GetOrderedProduct(i).GetName()))
			{
				list_Temp.add(order.GetOrderedProduct(i).GetName());
				//JOptionPane.showMessageDialog(null, order.GetOrderedProduct(i).GetName());

				
				Vector<Comparable> row = new Vector();
				row.add(order.GetProductCount(order.GetOrderedProduct(i).GetName()));
				row.add(order.GetOrderedProduct(i).GetName());
				row.add(String.valueOf(order.GetOrderedProduct(i).GetPrice().setScale(2).toString()));
			    
				
				model.addRow(row);
			}
		}
		
		this.textField.setText(order.GetTotal());
		this.table.repaint();
		   
	}
	
}
