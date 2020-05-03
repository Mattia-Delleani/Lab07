/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="comboBox"
    private ComboBox<Nerc> comboBox; // Value injected by FXMLLoader

    @FXML // fx:id="txtYear"
    private TextField txtYear; // Value injected by FXMLLoader

    @FXML // fx:id="txtHours"
    private TextField txtHours; // Value injected by FXMLLoader

    @FXML // fx:id="btnSearch"
    private Button btnSearch; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

	private Model model;

	
   @FXML
	void doChoice(ActionEvent event) {
	   if(comboBox.getValue()!=null) {
   		txtHours.setDisable(false);
   		txtYear.setDisable(false);
   		btnSearch.setDisable(false);
   		
   	}
	    }
    @FXML
    void doSearch(ActionEvent event) {
    	txtResult.clear();
    	if((txtHours.getText().matches("\\d+")) && (txtYear.getText().matches("\\d+"))){
    		List<PowerOutages> soluzione = new ArrayList<>(this.model.trovaByNerc(comboBox.getValue().getId(), Integer.parseInt(txtHours.getText()), Integer.parseInt(txtYear.getText())));
    		txtResult.appendText("Total affected: "+ this.model.getAffected(soluzione));
    		txtResult.appendText("\nTotal hours: "+ this.model.getHours(soluzione)+"");
    		for(PowerOutages po: soluzione) {
    			
    			txtResult.appendText(String.format("\n%5d %5d %.1f %-10d", po.getDataFine().getYear(), po.getDataInizio().getYear(), po.getDurata(), po.getAffectedCustomers()));
    			
    		}
    		
    	}else {
    		txtResult.setText("Input errato!");
    	}
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtYear != null : "fx:id=\"txtYear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtHours != null : "fx:id=\"txtHours\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSearch != null : "fx:id=\"btnSearch\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }


	public void setModel(Model model) {
		
		this.model = model;
		
		comboBox.getItems().addAll(this.model.getNercList());
		
	}
}
