package it.polito.tdp.poweroutages.model;

import java.sql.Timestamp;
import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		List<PowerOutages>lista = model.getPowerOutagesByNercId(1,20);
		//System.out.println(lista.get(0).toString());
		//double durata = lista.get(0).getDurata();
		
		System.out.println(lista.size());
		
		
		/*
		for(int i=0; i<lista.size()-1; i++) {
			int diff =0;
			
			diff=(lista.get(lista.size()-1).getDataFine().getYear() - lista.get(i).getDataInizio().getYear());
			System.out.println("DIff anni: "+diff);
			
			
		}
		*/
		
		List<PowerOutages>list = model.trovaByNerc(1, 20, 1);
		for(PowerOutages po: list) {
			System.out.println(po.toString());
			
			
		}
		
		

	}

}
