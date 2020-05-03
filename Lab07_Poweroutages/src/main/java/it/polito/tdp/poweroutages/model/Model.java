package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	List<PowerOutages>rilevamenti;
	private static int MAX_AFFECTED = 0;
	private static int MAX_HOURS;
	private static int MAX_YEARS;
	int cont =0;
	
	List<PowerOutages>bestSoluzione;
	public Model() {
		podao = new PowerOutageDAO();
		
		
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	
	public List<PowerOutages> getPowerOutagesByNercId(Integer id, int oreMax){
		
		List<PowerOutages>accettabili = new ArrayList<PowerOutages>();
		
		for(PowerOutages po: podao.getPowerOutages(id)) {
			if((po.getDurata())<oreMax)
				accettabili.add(po);
		}
		
		return accettabili;
	}
	
	public List<PowerOutages>trovaByNerc(Integer id, int oreMax, int anniMax){
		
		MAX_YEARS = anniMax;
		MAX_HOURS = oreMax;
		
		List<PowerOutages>parziale = new ArrayList<PowerOutages>();
		
		this.rilevamenti =  this.getPowerOutagesByNercId(id, oreMax);
		
		double oreRilevate = 0;
		int livello = 0;
		cerca(parziale, livello, oreRilevate);
		
		//System.out.println("Persone: "+ MAX_AFFECTED +"");
		
		return bestSoluzione;
		
	}

	private void cerca(List<PowerOutages> parziale, int livello, double oreRilevate) {
		
		
		if(livello==this.rilevamenti.size() || oreRilevate==MAX_HOURS) {
			//trovato soluzione parziale
			calcolaParziale(parziale);
			return;
	
		}
		if(livello!=0) {
			
				int contatore= livello;
				for(int i = contatore; i< this.rilevamenti.size();i++) {
					
					oreRilevate+= (this.rilevamenti.get(i).getDurata());
					//verifico che introducendo un rilevamento successivo, non superi le ore massime e gli anni
					if((oreRilevate>MAX_HOURS || (this.rilevamenti.get(i).getDataFine().getYear() - parziale.get(0).getDataInizio().getYear())> MAX_YEARS)) {
						calcolaParziale(parziale);
						return ;
						}
					
					parziale.add(rilevamenti.get(i));
				
					cerca(parziale, i+1,oreRilevate);
				
					//backtracking
					oreRilevate-=(this.rilevamenti.get(i).getDurata()/60);
					parziale.remove(parziale.size()-1);	
					
			}
		}else{//CASO IN CUI IL LIVELLO SIA UGUALE A ZERO
			for(int i = 0; i< this.rilevamenti.size()-1;i++) {
				oreRilevate+= (this.rilevamenti.get(i).getDurata()/60);
			
				parziale.add(rilevamenti.get(i));
				//come livello metto i perche cosi, nell iterazione ricorsiva essendo rilevamenti ordinato riparto
				//da quello successivo a quello messo in parziale.
				
				cerca(parziale, i+1,oreRilevate);
			
				//backtracking
				oreRilevate-=(this.rilevamenti.get(i).getDurata()/60);
				parziale.remove(parziale.size()-1);	
			}	
			
		}
			
		
		
	}

	private void calcolaParziale(List<PowerOutages> parziale) {
		//Stampo tutte le soluzioni parziali
	/*		cont++;
			double durata =0;
			System.out.println("PARZIALE n:"+cont+"\n Anno inizio e fine:"+parziale.get(0).getDataInizio().getYear()+"-"+parziale.get(parziale.size()-1).getDataFine().getYear());
			for(PowerOutages po: parziale) {
				durata +=po.getDurata();
			}
			System.out.println("DURATA: " +durata);
			
	*/
		int affectedPeople=0;
		for(PowerOutages po: parziale) {
			affectedPeople+= po.getAffectedCustomers();
			
		}
	/*	System.out.println("Persone affette: "+affectedPeople+"\n------------------------------------------------------------------");
		
	*/
		if(affectedPeople> MAX_AFFECTED) {
			MAX_AFFECTED = affectedPeople;
			
			bestSoluzione = new ArrayList<>(parziale);
			
		}
		
	}

	public int getAffected(List<PowerOutages>list) {
		
		int affected =0;
		for(PowerOutages po: list) {
			affected+=po.getAffectedCustomers();
		}
		return affected;
	}
	
public double getHours(List<PowerOutages>list) {
		
		double h =0;
		for(PowerOutages po: list) {
			h+=po.getDurata();
		}
		return h;
	}
}
