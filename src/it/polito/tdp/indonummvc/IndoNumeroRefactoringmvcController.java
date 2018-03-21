/**
 * Sample Skeleton for 'IndoNumeroRefactoringmvc.fxml' Controller Class
 */

package it.polito.tdp.indonummvc;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class IndoNumeroRefactoringmvcController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnNuova"
    private Button btnNuova; // Value injected by FXMLLoader

    @FXML // fx:id="txtCurr"
    private TextField txtCurr; // Value injected by FXMLLoader

    @FXML // fx:id="txtMax"
    private TextField txtMax; // Value injected by FXMLLoader

    @FXML // fx:id="boxGioco"
    private HBox boxGioco; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativo"
    private TextField txtTentativo; // Value injected by FXMLLoader

    @FXML // fx:id="txtLog"
    private TextArea txtLog; // Value injected by FXMLLoader

    @FXML
    void handleNuova(ActionEvent event) {
    	
    	model.newGame();
    	
    	// QUESTO E' QUELLO CHE BISOGNA FARE QUANDO NON SI USANO I BINDINGS:
    	//btnNuova.setDisable(true); // disattivo il bottone Nuova Partita
    	//boxGioco.setDisable(false); // attivo HBox con Tentativo e bottone Prova
    	//txtCurr.setText(String.format("%d", model.getTentativi())); 
    	//txtMax.setText(String.format("%d", model.getTMAX())); // tentativi massimi permessi
    	
    	txtLog.clear();
    	txtTentativo.clear();
    	
    	txtLog.setText(String.format("Indovina un numero tra %d e %d\n", 1, model.getNMAX()));

    }

    @FXML
    void handleProva(ActionEvent event) {
    	
    	String numS = txtTentativo.getText() ;
    	
    	if (numS.length() == 0) { 
    		txtLog.appendText("Devi inserire un numero\n");
    		return ; 
    	}
    	
    	try { // la funzione parseInt genera un'eccezione che devo gestire
    		
    		int num = Integer.parseInt(numS);
    		// numero era effettivamente un intero
    		
    		if (!model.valoreValido(num)) {
    			txtLog.appendText("Valore fuori dall'intervallo consentito\n");
    			return;
    		}
    		
    		int risultato = model.tentativo(num);
    		
    		// QUESTO E' QUELLO CHE BISOGNA FARE QUANDO NON SI USANO I BINDINGS:
        	//txtCurr.setText(String.format("%d", model.getTentativi())); 

    		if(risultato == 0) {
    			// indovinato
    			txtLog.appendText("Hai vinto!\n");
    		} else if(risultato < 0) {
    			txtLog.appendText("Troppo basso!\n");
    		} else {
    			txtLog.appendText("Troppo alto!\n");
    		}
    		
    		if (!model.isInGame()) {
    			// la partita è finita (vittoria o sconfitta)
    			
    			if (risultato!=0) {
    				txtLog.appendText("Hai perso!\n");
    				txtLog.appendText(String.format("Il numero segreto era: %d!\n",model.getSegreto()));
    			}
    			
    			// QUESTO E' QUELLO CHE BISOGNA FARE QUANDO NON SI USANO I BINDINGS:
    			// chiudo la partita: disabilito area gioco e riabilito bottone Nuova Partita
    			//boxGioco.setDisable(true);
    			//btnNuova.setDisable(false);
    		}
    		
    	// PER AVERE IL SUGGERIMENTO SULLE ECCEZIONI O ALTRO FARE Ctrl + Spazio
    	} catch (NumberFormatException ex)  {
    		txtLog.setText("Il dato inserito non è numerico \n");
    		return ;
    	}

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnNuova != null : "fx:id=\"btnNuova\" was not injected: check your FXML file 'IndoNumeroRefactoringmvc.fxml'.";
        assert txtCurr != null : "fx:id=\"txtCurr\" was not injected: check your FXML file 'IndoNumeroRefactoringmvc.fxml'.";
        assert txtMax != null : "fx:id=\"txtMax\" was not injected: check your FXML file 'IndoNumeroRefactoringmvc.fxml'.";
        assert boxGioco != null : "fx:id=\"boxGioco\" was not injected: check your FXML file 'IndoNumeroRefactoringmvc.fxml'.";
        assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'IndoNumeroRefactoringmvc.fxml'.";
        assert txtLog != null : "fx:id=\"txtLog\" was not injected: check your FXML file 'IndoNumeroRefactoringmvc.fxml'.";

    }

	public void setModel(Model model) {
		
		this.model = model;
		
		// AGGIORNA AUTOMATICAMENTE I TENTATIVI FATTI E QUELLI TOTALI (BINDINGS)
		txtCurr.textProperty().bind(Bindings.convert(model.tentativiProperty()));
		txtMax.textProperty().bind(Bindings.convert(model.NMAXProperty()));
		
		// LE AREE DELLINTERFACCIA SI ABILITANO-DISABILITANO AUTOMATICAMENTE IN FUNZIONE DI inGame (BINDINGS)
		btnNuova.disableProperty().bind(model.inGameProperty());
		boxGioco.disableProperty().bind(Bindings.not(model.inGameProperty()));
		
	}
	
}
