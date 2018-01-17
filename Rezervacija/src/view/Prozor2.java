package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;






public class Prozor2 {

	
	private JFrame f;
	public JPanel p1;
	private JPanel p2;
	private JTextField tf1;
	private JTextField tf2;
	private JTextField tf3;
	private JTable table;
	
	
	
	
	public void start(){
		
		
		f = new JFrame("Odabir polaska");
			
		f.setLayout(new BorderLayout());
		
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		p1=new JPanel(); 
		p2=new JPanel();
			
		f.setVisible(true);

		f.add(p1, BorderLayout.CENTER);
		
		setTf1(new JTextField());
		getTf1().setEditable(false);
		
		
		setTf2(new JTextField());
		getTf2().setEditable(false);
		
		
		setTf3(new JTextField());
		getTf3().setEditable(false);
		
		
		
		p1.add(getTf1());
		p1.add(getTf2());
		p1.add(getTf3());
		
		p1.setBackground(Color.LIGHT_GRAY);
		p1.setVisible(true);
		
		f.add(p2, BorderLayout.SOUTH);
	
		
		
		JScrollPane scrollPane = new JScrollPane(getTable());
		
		getTable().setFillsViewportHeight(true);
		getTable().setPreferredScrollableViewportSize(new Dimension(650,500));
		p2.add(scrollPane);
		p2.setBackground(Color.LIGHT_GRAY);
		
	    p2.setVisible(true);
		
	    f.pack();
		f.setVisible(true);   
	}
	
	public JTable getTable(){
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JTextField getTf1() {
		return tf1;
	}

	public void setTf1(JTextField tf1) {
		this.tf1 = tf1;
	}

	public JTextField getTf2() {
		return tf2;
	}

	public void setTf2(JTextField tf2) {
		this.tf2 = tf2;
	}

	public JTextField getTf3() {
		return tf3;
	}

	public void setTf3(JTextField tf3) {
		this.tf3 = tf3;
	}
}

