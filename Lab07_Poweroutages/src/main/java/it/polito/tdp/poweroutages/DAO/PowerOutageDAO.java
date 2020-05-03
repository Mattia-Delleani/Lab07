package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutages;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	public List<PowerOutages> getPowerOutages(Integer id){
		
		String sql = "SELECT id, nerc_id, customers_affected, date_event_began, date_event_finished, TIMESTAMPDIFF(HOUR, date_event_began, date_event_finished) AS diff\r\n" + 
				"FROM poweroutages " + 
				"WHERE nerc_id = ? " + 
				"ORDER BY date_event_began ASC";
		List<PowerOutages>lista = new ArrayList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				
				lista.add(new PowerOutages(rs.getInt("id"), rs.getInt("nerc_id"), rs.getInt("customers_affected"), rs.getTimestamp("date_event_began").toLocalDateTime(), rs.getTimestamp("date_event_finished").toLocalDateTime()));
			}
			conn.close();
			
			return lista;
			
		}catch(SQLException sque) {
			
			throw new RuntimeException(sque);
		}
	}
	

}
