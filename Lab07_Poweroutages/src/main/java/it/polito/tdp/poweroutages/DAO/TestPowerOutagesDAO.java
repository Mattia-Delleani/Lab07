package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.util.List;

import it.polito.tdp.poweroutages.model.PowerOutages;

public class TestPowerOutagesDAO {

	public static void main(String[] args) {
		
		try {
			Connection connection = ConnectDB.getConnection();
			connection.close();
			System.out.println("Connection Test PASSED");
			
			PowerOutageDAO dao = new PowerOutageDAO() ;
			
			System.out.println(dao.getNercList()) ;

		} catch (Exception e) {
			System.err.println("Test FAILED");
		}
		PowerOutageDAO dao = new PowerOutageDAO() ;
		List<PowerOutages> lista = dao.getPowerOutages(8);
		
		PowerOutages p1 = lista.get(0);
		
		
	
		System.out.println(lista.toString());

	}

}
