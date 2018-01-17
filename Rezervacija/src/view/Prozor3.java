package view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;


public class Prozor3 {
	
	private JFrame f;
	private JPanel p1;
	private JPanel p2;
	private JButton rezerviraj;
	private JComboBox<Integer> cb;
	private JLabel unos1;
	

	public void start(){
	
		setF(new JFrame("Rezervacija"));
		getF().setLayout(new BorderLayout());
		getF().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		p1=new JPanel();
		p2=new JPanel(); 
		
		getF().setVisible(true);

		getF().add(p1, BorderLayout.NORTH);
		getF().add(p2, BorderLayout.SOUTH);


	
		unos1= new JLabel("Rezervirajte sjedalo: ");
		p1.add(unos1);
		p1.add(getCb());
		
		
		p1.setBackground(Color.LIGHT_GRAY);
		p1.setVisible(true);
		
		setRezerviraj(new JButton("REZERVIRAJ!"));
		p2.add(getRezerviraj());
		
		
		p2.setBackground(Color.LIGHT_GRAY);
		p2.setVisible(true);
		getF().pack();
		getF().setVisible(true);
	}


	public JComboBox<Integer> getCb() {
		return cb;
	}


	public void setCb(JComboBox<Integer> cb) {
		this.cb = cb;
	}


	public JButton getRezerviraj() {
		return rezerviraj;
	}


	public void setRezerviraj(JButton rezerviraj) {
		this.rezerviraj = rezerviraj;
	}


	public JFrame getF() {
		return f;
	}


	public void setF(JFrame f) {
		this.f = f;
	}
	

}
