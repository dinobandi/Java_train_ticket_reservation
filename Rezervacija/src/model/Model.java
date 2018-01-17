package model;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
import java.util.Properties;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField.AbstractFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;





public class Model {



private static String id_vlak;
private static String id_ruta;
private static String id_datum;
private static String id_put;
private static String id_poc_put;
private static String id_zav_put;
private static String id_poc_stan;
private static String id_zav_stan;
private static String naz_vl;
private static String vr_pol;
private static String stan_pol;
private static String stan_dol;
private static String vr_dol;
private static String cijena;
private static int preostaloMjesta;
private static Vector<Vector<String>> data = new Vector<Vector<String>>();
private static Vector<String> vlak = new Vector<String>();
private static Connection co1=null;
private static String r;
private static PreparedStatement stm;
private static Vector<String> s;
private static Vector<String> s1;
private static String brsj;
private static String idsj;
private static String naziv;
private static Vector<String> stan;
private static UtilDateModel modelDat = new UtilDateModel();
private static Properties propierties = new Properties();
private static JDatePanelImpl datePanel;
private static JDatePickerImpl datePicker;
private static String[] nazivi ={"Putna Karta","Datum rezervacije","Polazak","Dolazak","Naziv Vlaka","Sjedalo","Cijena" };
   

    public void con1()
    {
    	 
    	try { 
    		Class.forName("org.sqlite.JDBC");
    		co1 = DriverManager.getConnection("jdbc:sqlite:Rezervacija.db");
    		co1.setAutoCommit(true);  
    		} catch ( Exception e ) 
    			{
    				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    				System.exit(0);
    			}
      
    	
    }
    

	
	public static class DateLabelFormatter extends AbstractFormatter 
	{

		
		private static final long serialVersionUID = -6951649709206729211L;
		private String datePattern = "dd-MM-yyyy";
		private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern, Locale.getDefault());

		public Object stringToValue(String text) throws ParseException 
		{

			return dateFormatter.parseObject(text);

		}

		public String valueToString(Object value) throws ParseException {

			if (value != null) 
			{

				Calendar cal = (Calendar) value;
				return dateFormatter.format(cal.getTime());

			}

			return "";

		}
	}
	
	public void getRaspored(String polaziste, String odrediste, String odabraniDatum) throws SQLException {
		
		con1();
		
			
	    Statement stmt = co1.createStatement();
	    ResultSet rs=null;
	    rs = stmt.executeQuery("SELECT v.Id_vlak, r.Id_ruta,d.Id_datum,p.Id_put,v.Naziv_vlaka,A.Vrijeme_polaska, s.Stanica as Pocetna, "
	    		+ "s1.Stanica as Zavrsna, s.Id_stanice as Idpocetna, s1.Id_stanice as Idzavrsna, "
	    		+ "B.Vrijeme_dolaska, A.Cijena+B.Cijena as Cijena, A.Id_stanica_na_putu as poc_put, B.Id_stanica_na_putu as zav_put  "
	    		+ "FROM STANICA_NA_PUTU A, STANICA_NA_PUTU B,STANICE s ,STANICE s1, RUTA r, PUT p, "
	    		+ "DATUM d, VLAK v WHERE A.Id_stan_put=B.Id_stan_put AND A.Id_pocetna=s.Id_stanice AND B.Id_zavrsna=s1.Id_stanice AND s.Stanica='"+polaziste+"' "
	    				+ "AND s1.Stanica='"+odrediste+"' AND r.Id_ruta_put=p.Id_put AND r.Id_ruta_datum=d.Id_datum AND r.Id_ruta_vlak=v.Id_vlak "
	    						+ "AND d.Datum='"+ odabraniDatum +"' AND p.Id_put=A.Id_stan_put AND p.Id_put=B.Id_stan_put");
	    while ( rs.next() ) {
	      	 
	    	id_vlak=rs.getString("Id_vlak");
	    	id_ruta = rs.getString("Id_ruta");
	    	id_datum=rs.getString("Id_datum");
	    	id_put=rs.getString("Id_put");
	    	id_poc_put=rs.getString("poc_put");
	    	id_zav_put=rs.getString("zav_put");
	    	id_poc_stan=rs.getString("Idpocetna");
	    	id_zav_stan=rs.getString("Idzavrsna");
	    	naz_vl=rs.getString("Naziv_vlaka");
	      	vr_pol=rs.getString("Vrijeme_polaska");
	      	stan_pol=rs.getString("Pocetna");
	      	stan_dol=rs.getString("Zavrsna");
	       	vr_dol=rs.getString("Vrijeme_dolaska");
	        cijena=rs.getString("Cijena");
	        
	      
	       	vlak.addElement(id_vlak);
	        vlak.addElement(id_ruta);
	      	vlak.addElement(id_datum);
	       	vlak.addElement(id_put);
	       	vlak.addElement(id_poc_put);
	       	vlak.addElement(id_zav_put);
	       	vlak.addElement(id_poc_stan);
	       	vlak.addElement(id_zav_stan);
	       	vlak.addElement(naz_vl);
	       	vlak.addElement(vr_pol);
	       	vlak.addElement(stan_pol);
	       	vlak.addElement(stan_dol);
	       	vlak.addElement(vr_dol);
	       	vlak.addElement(cijena);
	        	
	       	getData().add(new Vector<>(vlak));
	       	vlak.clear();
	    	
	    }
	  
	    co1.close();
	    stmt.close();
	    rs.close();
	   
	}
	
	public static Vector<Vector<String>> getData() {
		return data;
	}
		
	
		
		

	public void rezerviraj( String uniqueID, String sadasnjiDatum, int idOdabranogDatuma, int idOdabraneRute, 
			int idPocetneStanice,int idZavrsneStanice,double Cijena,int odabranoSjedalo) throws SQLException {
		try{
			
			con1();	
			
			r = "INSERT INTO REZERVACIJA ( Id_rezervacije, Datum_rezervacije, Datum_puta, Id_rez_ruta,"
					+ " Id_pocetna, Id_zavrsna, Cijena, Id_sjedalo )" + 
	        		"VALUES( ?, ?, ?, ?, ?, ?, ?, ?)";
	    	
			stm = co1.prepareStatement(r);
			stm.setString(1,uniqueID);
	        stm.setString(2, sadasnjiDatum);
	        stm.setInt(3,idOdabranogDatuma);
	        stm.setInt(4,idOdabraneRute);
	        stm.setInt(5,idPocetneStanice);
	        stm.setInt(6,idZavrsneStanice);
	        stm.setDouble(7, Cijena);
	        stm.setInt(8,odabranoSjedalo);
	        stm.executeUpdate();
	      
	        
			}catch(SQLException e){
				e.printStackTrace();
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			}

		co1.close();
		stm.close();
	}
	public void zauzmi(int idOdabranoSjedaloInt, int idOdabranogDatumaInt, int idOdabraneRuteInt, int idPocetneRelacijeInt, int idZavrsneRelacijeInt, String uniqueID) throws SQLException 
	{
		try{
			con1();	
			for (int i =idPocetneRelacijeInt ; i<=idZavrsneRelacijeInt; i++)
			{
				r="INSERT INTO ZAUZETO_NA_PUTU( Id_zauzeto_sjedalo, Id_zauzeto_datum, "
						+ "Id_zauzeto_ruta,Id_zauzeto_stanput, Id_zauzeto_rez) VALUES ( ?, ?, ?, ?, ?)";
				stm = co1.prepareStatement(r);	
				stm.setInt(1,idOdabranoSjedaloInt);
				stm.setInt(2,idOdabranogDatumaInt);
				stm.setInt(3,idOdabraneRuteInt);
				stm.setInt(4,i);
				stm.setString(5,uniqueID);
				stm.executeUpdate();
		
			}
		 
		}catch(SQLException e)
		{
			e.printStackTrace();
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	
		co1.close();
		stm.close();
	}
	
	
	public Vector<String> GetSlobodna(String idOdabranogVlaka, String idOdabranogDatuma, String idOdabraneRute, String polaziste, String odrediste) throws SQLException 
	{
		
		con1();
		s= new Vector<String>();
		s1= new Vector<String>();
		
	    Statement stmt = co1.createStatement();
	    ResultSet rs=null;
	    String sql = "SELECT s.Naziv_sjedala, s.Id_sjedalo FROM VLAK v, SJEDALO s WHERE v.Id_vlak='"+idOdabranogVlaka+"' AND s.Id_sjedalo_vlak=v.Id_Vlak AND s.Id_sjedalo NOT IN "
	    		+ "(SELECT z.Id_zauzeto_sjedalo FROM STANICA_NA_PUTU snp, STANICA_NA_PUTU snp1, ZAUZETO_NA_PUTU z, STANICE st, STANICE st1 WHERE "
	    		+ "snp.Id_stan_put=snp1.Id_stan_put AND st.Id_stanice=snp.Id_pocetna AND st1.Id_stanice=snp1.Id_zavrsna AND st.Stanica='"+polaziste+"' AND "
	    		+ "st1.Stanica='"+odrediste+"' AND z.Id_zauzeto_datum='"+idOdabranogDatuma+"' AND z.Id_zauzeto_ruta='"+idOdabraneRute+"' AND z.Id_zauzeto_stanput=snp.Id_stanica_na_putu )";
	    
	    rs = stmt.executeQuery(sql);
	    
        while ( rs.next() ) 
        	{	        	
        		brsj=rs.getString("Naziv_sjedala");
        		setIdsj(rs.getString("Id_sjedalo"));
        		s1.addElement(getIdsj());
        		s.addElement(brsj);
        	}
        String sql1 = "SELECT COUNT(s.Id_sjedalo) as ostalo FROM VLAK v, SJEDALO s WHERE v.Id_vlak='"+idOdabranogVlaka+"' AND s.Id_sjedalo_vlak=v.Id_Vlak AND s.Id_sjedalo NOT IN "
	    		+ "(SELECT z.Id_zauzeto_sjedalo FROM STANICA_NA_PUTU snp, STANICA_NA_PUTU snp1, ZAUZETO_NA_PUTU z, STANICE st, STANICE st1 WHERE "
	    		+ "snp.Id_stan_put=snp1.Id_stan_put AND st.Id_stanice=snp.Id_pocetna AND st1.Id_stanice=snp1.Id_zavrsna AND st.Stanica='"+polaziste+"' AND "
	    		+ "st1.Stanica='"+odrediste+"' AND z.Id_zauzeto_datum='"+idOdabranogDatuma+"' AND z.Id_zauzeto_ruta='"+idOdabraneRute+"' AND z.Id_zauzeto_stanput=snp.Id_stanica_na_putu )";
        
        rs=stmt.executeQuery(sql1);
        setPreostaloMjesta(rs.getInt("ostalo"));
        
        co1.close();
	    stmt.close();
	    rs.close();
	    return s;
	}





	public static JDatePickerImpl getDatePicker() 
	{
		
		return datePicker;
	}



	public static void setDatePicker(JDatePickerImpl datePicker) 
	{
		propierties.put("text.today", "Danas");
		propierties.put("text.month", "Mjesec");
		propierties.put("text.year", "Godina");
		datePanel = new JDatePanelImpl(modelDat, propierties);
		Model.datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		
		
	}	
	
	public Vector<String> GetStanice() throws SQLException
	{
		con1();
		stan= new Vector<String>();
		Statement stmt = co1.createStatement();
		ResultSet rs=null;
		rs = stmt.executeQuery("SELECT Stanica FROM STANICE ;");
		while ( rs.next() ) {
			naziv=rs.getString("Stanica");
			stan.addElement(naziv);
      	
							}
        
		co1.close();
		stmt.close();
		rs.close();
		return stan;
	}
	
	public static String getSadasnjiDatum()
	{
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	     LocalDate localDate = LocalDate.now();
	     return dtf.format(localDate);
	}
	
	public static void saveImg(String [] vri,String uniqueID) throws IOException
	{
		int width = 500;
	    int height = 500;
	    
	    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    
	    Graphics2D graphic2D = bufferedImage.createGraphics();
	   draw(graphic2D,nazivi,vri);
	    graphic2D.dispose();
	    RenderedImage rendImage = bufferedImage;

	    File file = new File("rezervacija"+"_"+uniqueID+".png");
	    ImageIO.write(rendImage, "png", file);
		
	}
	
	public static void draw(Graphics2D graphic2D,String [] nazivi, String [] vrijednosti)
	{ 
		graphic2D.setPaint(Color.white);

	    graphic2D.fillRect(0, 0, 500, 500);
	      
	      
		
	      graphic2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
	       
	      for (int i =1 ; i<=7;i++)
	      {
	    	  Font font = new Font("Times New Roman", Font.BOLD, 26);
	    	  graphic2D.setFont(font);
	    	  graphic2D.setPaint(Color.black);
	    	  graphic2D.drawString(nazivi[i-1], 10, i*55);
	    	  
	    	  Font font1 = new Font("Times New Roman", Font.PLAIN, 20);
	    	  graphic2D.setFont(font1);
	    	  graphic2D.drawString(vrijednosti[i-1], 10, i*55+25);
	      }
	}
	
	 public static void infoBox()
	    {	String infoMessage="Uspješno rezervirano";
		 	String titleBar="Rezervirano!";
	        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
	    }

	 public static void nemaMjesta()
	    {	String infoMessage="Na žalost, ovaj vlak je popunjen";
		 	String titleBar="Zauzeto!";
	        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
	    }
	 public Vector<String> getColumns()
	 {
		 Vector<String> columnNames = new Vector<String>();
			columnNames.addElement("Id_vlak");
			columnNames.addElement("Id_ruta");
			columnNames.addElement("Id_datum");
			columnNames.addElement("Id_put");
			columnNames.addElement("Id_poc_put");
			columnNames.addElement("Id_zav_put");
			columnNames.addElement("Id_poc_stan");
			columnNames.addElement("Id_zav_stan");
			columnNames.addElement("Naziv Vlaka");
			columnNames.addElement("Vrijeme Polaska");
			columnNames.addElement("Poèetna Stanica");
			columnNames.addElement("Završna Stanica");
			columnNames.addElement("Vrijeme Dolaska");
			columnNames.addElement("Cijena");
			return columnNames;	
	 }
	
	 public static String getIdsj() {
		return idsj;
	}

	public static void setIdsj(String idsj) {
		Model.idsj = idsj;
	}

	public static Vector<String> getS1() {
		return s1;
	}

	public static void setS1(Vector<String> s1) {
		Model.s1 = s1;
	}

	public static int getPreostaloMjesta() {
		return preostaloMjesta;
	}

	public static void setPreostaloMjesta(int preostaloMjesta) {
		Model.preostaloMjesta = preostaloMjesta;
	}

	
}