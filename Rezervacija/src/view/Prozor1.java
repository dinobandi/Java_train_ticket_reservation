package view;

import javax.swing.*;
import java.awt.*;


public class Prozor1{
	private JFrame frame;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JButton trazi;
	
	private static String [] kolodvori = null;
	private JComboBox<String> cb1;
	private JComboBox<String> cb2;
	
	public void startApp() {	
		
		setFrame(new JFrame("Odabir relacije"));
		
		getFrame().setLayout(new BorderLayout());
		getFrame().setPreferredSize(new Dimension(520, 180));
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel1=new JPanel();
		panel2=new JPanel();
		panel3=new JPanel();
		
		
		getFrame().setVisible(true);
		getFrame().add(panel1, BorderLayout.NORTH);
		
		JLabel label1=new JLabel("Polazišni kolodvor:");	
		panel1.add(label1);
		
		setCb1(new JComboBox<String>(getKolodvori()));
		panel1.add(getCb1());
		
		JLabel label2=new JLabel("Odredišni kolodvor:");	
		panel1.add(label2);
		
		setCb2(new JComboBox<String>(getKolodvori()));
		panel1.add(getCb2());
		
		panel1.setBackground(Color.LIGHT_GRAY);
		panel1.setVisible(true);		
		getFrame().add(panel2, BorderLayout.CENTER);
		
		JLabel label3 = new JLabel("Odaberi datum:");
		
	    panel2.add(label3);

	
	    panel2.setBackground(Color.LIGHT_GRAY);
	    panel2.setVisible(true);
		
		getFrame().add(panel3, BorderLayout.SOUTH);
		setTrazi(new JButton("TRAŽI"));
		panel3.add(getTrazi());
		
		panel3.setBackground(Color.LIGHT_GRAY);
		panel3.setVisible(true);
		    
		getFrame().pack();
		getFrame().setVisible(true);	
		}

		public JButton getButton(){
			return getTrazi();
		}
		
		public static String [] getKolodvori() {
			return kolodvori;
		}

		public static void setKolodvori(String [] kolodvori) {
			Prozor1.kolodvori = kolodvori;
		}

		public JComboBox<String> getCb1() {
			return cb1;
		}

		public void setCb1(JComboBox<String> cb1) {
			this.cb1 = cb1;
		}

		public JComboBox<String> getCb2() {
			return cb2;
		}

		public void setCb2(JComboBox<String> cb2) {
			this.cb2 = cb2;
		}

		public JFrame getFrame() {
			return frame;
		}

		public void setFrame(JFrame frame) {
			this.frame = frame;
		}


		public JButton getTrazi() {
			return trazi;
		}

		public void setTrazi(JButton trazi) {
			this.trazi = trazi;
		}

		public JPanel getPanel2() {
			return panel2;
		}

		public void setPanel2(JPanel panel2) {
			this.panel2 = panel2;
		}
}