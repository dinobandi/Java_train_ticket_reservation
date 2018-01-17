package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;

import java.util.UUID;
import java.util.Vector;



import javax.swing.JComboBox;
import javax.swing.JTable;


import model.Model;

import view.Prozor2;
import view.Prozor3;
import view.Prozor1;

public class Controller {

	private Prozor1 prozor1;
	private Prozor2 prozor2;
	private Prozor3 prozor3;
	
	
	
	private Model model;
	
	private static int idOdabranoSjedaloInt;
	private static String odabraniDatum;
	private static String idOdabranogVlaka;
	private static String idOdabraneRute;
	private static String idOdabranogDatuma;
	private static String idOdabranogPuta;
	private static String idPocetneRelacije;
	private static String idZavrsneRelacije;
	private static String idPocetneStanice;
	private static String idZavrsneStanice;
	private static String nazivVlaka;
	private static String vrPol;
	private static String vrDol;
	private static String Cijena;
	private static String uniqueID;
	private static Vector <String> idsjedala;
	private String polaziste;
	private String odrediste;
	
	private ActionListener actionListener;
	private ActionListener actionListener1;
	private MouseListener MouseList;
	
	
	public Controller(Model model, Prozor1 prozor1, Prozor2 prozor2, Prozor3 prozor3){
		this.model=model;
		this.prozor1=prozor1;
		this.prozor2=prozor2;
		this.prozor3=prozor3;
	}
	
	public void startApp()
	{	popuniCB();
		Model.setDatePicker(Model.getDatePicker());
		prozor1.startApp();
		prozor1.getPanel2().add(Model.getDatePicker());
		control();
	}
	
	public void popuniCB()
	{
		try {
			Prozor1.setKolodvori(model.GetStanice().toArray(new String [model.GetStanice().size()]));
			} catch (SQLException e) 
				{
			e.printStackTrace();
				}
		
	}
	
	public void control(){
		actionListener = new ActionListener(){
			public void actionPerformed(ActionEvent event)
			{
				if(event.getSource()==prozor1.getTrazi())
				{	odabraniDatum=Model.getDatePicker().getJFormattedTextField().getText();
					pretraga();
				}
				
			}
			
		};
	
		prozor1.getButton().addActionListener(actionListener);
		
		
}
	
	
	
	private void pretraga(){
		
    	 polaziste = getPolaziste(prozor1); 
         odrediste = getOdrediste(prozor1); 
         
         prikaziProzor2();
        
       
	}

	public void prikaziProzor2()
	{		
		 	Model.getData().clear();
	        prikaziTablicu();
	        prozor2.start();
	        prozor2.getTf1().setText(polaziste);
	        prozor2.getTf2().setText(odrediste);
	        prozor2.getTf3().setText(odabraniDatum);
	        Mousecontrol();
	        try {
				model.getRaspored(polaziste,odrediste,odabraniDatum);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
		
	}
	
	public void Mousecontrol()
	{
		
		MouseList=new MouseAdapter() 
		{
			  public void mouseClicked(MouseEvent e) 
			  
			  {
			     
			     prozor2.setTable((JTable) e.getSource());
			     
			      int row = prozor2.getTable().getSelectedRow();
			      Object idOznacenogVlaka= prozor2.getTable().getModel().getValueAt(row, 0);
			      idOdabranogVlaka=(String) idOznacenogVlaka;
			      Object idOznaceneRute= prozor2.getTable().getModel().getValueAt(row, 1);
			      idOdabraneRute=(String) idOznaceneRute;
			      Object idOznacenogDatuma= prozor2.getTable().getModel().getValueAt(row, 2);
			      idOdabranogDatuma=(String) idOznacenogDatuma;
			      Object idOznacenogPuta= prozor2.getTable().getModel().getValueAt(row, 3);
			      idOdabranogPuta=(String) idOznacenogPuta;
			      Object idOznacenogPocetnogPuta= prozor2.getTable().getModel().getValueAt(row, 4);
			      idPocetneRelacije=(String) idOznacenogPocetnogPuta;
			      Object idOznacenogZavrsnogPuta= prozor2.getTable().getModel().getValueAt(row, 5);
			      idZavrsneRelacije=(String) idOznacenogZavrsnogPuta;
			      Object idPocetneStaniceObjekt= prozor2.getTable().getModel().getValueAt(row, 6);
			      idPocetneStanice=(String) idPocetneStaniceObjekt;
			      Object idZavrsneStaniceObjekt= prozor2.getTable().getModel().getValueAt(row, 7);
			      idZavrsneStanice=(String) idZavrsneStaniceObjekt;
			      Object naz_vl= prozor2.getTable().getModel().getValueAt(row, 8);
			      nazivVlaka=(String) naz_vl;
			      Object vri_pol= prozor2.getTable().getModel().getValueAt(row, 9);
			      vrPol=(String) vri_pol;
			      Object vri_dol= prozor2.getTable().getModel().getValueAt(row, 12);
			      vrDol=(String) vri_dol;
			      Object CijenaObjekt= prozor2.getTable().getModel().getValueAt(row, 13);
			      Cijena=(String) CijenaObjekt;
			     
			      
			      prikaziProzor3();
			     
			      
			  }	  
		};
	
		prozor2.getTable().addMouseListener(MouseList);
		
	}
	

	
	public void prikaziTablicu()
	{
		
		
		prozor2.setTable(new JTable(Model.getData(), model.getColumns())); 
		prozor2.getTable().removeColumn(prozor2.getTable().getColumnModel().getColumn(0));
		prozor2.getTable().removeColumn(prozor2.getTable().getColumnModel().getColumn(0));
		prozor2.getTable().removeColumn(prozor2.getTable().getColumnModel().getColumn(0));
		prozor2.getTable().removeColumn(prozor2.getTable().getColumnModel().getColumn(0));
		prozor2.getTable().removeColumn(prozor2.getTable().getColumnModel().getColumn(0));
		prozor2.getTable().removeColumn(prozor2.getTable().getColumnModel().getColumn(0));
		prozor2.getTable().removeColumn(prozor2.getTable().getColumnModel().getColumn(0));
		prozor2.getTable().removeColumn(prozor2.getTable().getColumnModel().getColumn(0));
		
		
	}
	

	
	public void nadiSlobodnaSjedala()
	{
		
		String [] sjedala = null;
		 idsjedala= new Vector <String>();
		try {
			sjedala= model.GetSlobodna(idOdabranogVlaka,idOdabranogDatuma,idOdabraneRute,polaziste,odrediste).toArray(new String [model.GetSlobodna(idOdabranogVlaka ,idOdabranogDatuma,idOdabraneRute,polaziste,odrediste).size()]);
			idsjedala=Model.getS1();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		
		prozor3.setCb(new JComboBox(sjedala));
	}
	
	
	public void prikaziProzor3()
	{
		
		 nadiSlobodnaSjedala();
		 
		 if(Model.getPreostaloMjesta()==0)
		 {
			 Model.nemaMjesta();}
		 else
		 {
	      prozor3.start();
	      controlProzor3();
		 }
		
	}
	
	public void controlProzor3()
	{

		actionListener1 = new ActionListener(){
		public void actionPerformed(ActionEvent event){

			if(event.getSource() == prozor3.getRezerviraj())
			{	
		 
				String odabranoSjedalo= (String) prozor3.getCb().getSelectedItem();
				int red=(int)prozor3.getCb().getSelectedIndex();
				String odabraniIdsjedala= idsjedala.get(red);
				idOdabranoSjedaloInt=Integer.parseInt(odabraniIdsjedala);
				uniqueID=UUID.randomUUID().toString();
				String [] vrijednosti={uniqueID,Model.getSadasnjiDatum(),"Datum polaska :"+odabraniDatum+" iz " +polaziste+" u "+vrPol+" sati",odrediste+" u " +vrDol+" sati",nazivVlaka,odabranoSjedalo,Cijena};
	     
	     
				try {
					rezerviraj(idOdabranogDatuma,idOdabraneRute,idPocetneStanice,idZavrsneStanice,Cijena,odabranoSjedalo);
					} catch (SQLException e) 
					{
				// TODO Auto-generated catch block
						e.printStackTrace();
					}
			try {
				zauzmi(idOdabranoSjedaloInt,idOdabranogDatuma,idOdabraneRute,idOdabranogPuta,idPocetneRelacije, idZavrsneRelacije,uniqueID);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try 
			{ 
				
				Model.saveImg(vrijednosti,uniqueID);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	     prozor3.getF().dispose();
	     
	     Model.infoBox();
		
	}
	
	}
			};
		prozor3.getRezerviraj().addActionListener(actionListener1);
	}
	
	
	public void rezerviraj(String idOdabranogDatuma, String idOdabraneRute, String idPocetneStanice, String idZavrsneStanice, String cijena,String odabranoSjedalo) throws  SQLException
	{
		
		
		 int idOdabranogDatumaInt= Integer.parseInt(idOdabranogDatuma);
		 int idOdabraneRuteInt = Integer.parseInt(idOdabraneRute);

		 int idPocetneStaniceInt = Integer.parseInt(idPocetneStanice);
		 int idZavrsneStaniceInt = Integer.parseInt(idZavrsneStanice);
		 double CijenaDouble=Double.parseDouble(cijena);
		 int odabranoSjedaloInt=Integer.parseInt(odabranoSjedalo);
		 
	  
		 model.rezerviraj(uniqueID, Model.getSadasnjiDatum(),idOdabranogDatumaInt,idOdabraneRuteInt,idPocetneStaniceInt,idZavrsneStaniceInt,CijenaDouble,odabranoSjedaloInt);
	}
	
	private void zauzmi(int idOdabranoSjedaloInt, String idOdabranogDatuma, String idOdabraneRute,String idOdabranogPuta, String idPocetneRelacije, String idZavrsneRelacije, String uniqueID) throws SQLException {
		
		int idOdabranogDatumaInt= Integer.parseInt(idOdabranogDatuma);
		 int idOdabraneRuteInt = Integer.parseInt(idOdabraneRute);
		 int idOdabranogPutaInt= Integer.parseInt(idOdabranogPuta);
		 int idPocetneRelacijeInt = Integer.parseInt(idPocetneRelacije);
		 int idZavrsneRelacijeInt = Integer.parseInt(idZavrsneRelacije);
		 
		 model.zauzmi(idOdabranoSjedaloInt, idOdabranogDatumaInt, idOdabraneRuteInt, idPocetneRelacijeInt, idZavrsneRelacijeInt, uniqueID);
		
	}
	
	public String getPolaziste(Prozor1 prozor1) 
	{		
		String polaziste = (String) prozor1.getCb1().getSelectedItem();	
		return polaziste;
	}

	
	public String getOdrediste(Prozor1 prozor1) 
	{
		String odrediste = String.valueOf(prozor1.getCb2().getSelectedItem());
		return odrediste;
	}
	
	

	
}//zatvorena klasa
