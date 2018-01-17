package main;
import javax.swing.SwingUtilities;
import controller.Controller;
import model.Model;
import view.Prozor2;
import view.Prozor3;
import view.Prozor1;

public class Main {

	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
		    @Override
		    public void run() {

		        Model model = new Model();
		        Prozor1 prozor1 = new Prozor1();
		        Prozor2 prozor2=new Prozor2();
		        Prozor3 prozor3 = new Prozor3();
		       
		     Controller Controller=new Controller(model, prozor1, prozor2,prozor3);
		     Controller.startApp();
		     
		    
		   
		    
		    }
		   
		});
}
}