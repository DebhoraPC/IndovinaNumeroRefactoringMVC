/**
 * Sample Skeleton for 'IndoNumeroRefactoringmvc.fxml' Controller Class
 */

package it.polito.tdp.indonummvc;

import java.net.URL;
import java.util.ResourceBundle;
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
    	
    	btnNuova.setDisable(true); // disattivo il bottono Nuova Partita
    	boxGioco.setDisable(false); // attivo HBox con Tentativo e bottone Prova
    	
    	txtCurr.setText(String.format("%d", this.tentativi)); 
    	txtMax.setText(String.format("%d", this.TMAX)); 
    	
    	txtLog.clear();
    	txtTentativo.clear();
    	txtLog.setText(String.format("Indovina un numero tra %d e %d\n", 1, NMAX));

    }

    @FXML
    void handleProva(ActionEvent event) {
    	
    	String numS = txtTentativo.getText() ;
    	
    	if (numS.length() == 0) { 
    		txtLog.appendText("Devi inserire un numero \n");
    		return ; 
    	}
    	
    	try { // la funzione parseInt genera un'eccezione che devo gestire
    		
    		int num = Integer.parseInt(numS) ;
    		// numero era effettivamente un intero
    		if (num < 0 || num > NMAX) {
    			txtLog.appendText("Valore fuori dall'intervallo consentito\n");
    			return;
    		}
    		
    		if (num == this.segreto) {
    			
    			// indovinato
    			txtLog.appendText("Hai vinto!\n");
    			
    			// chiudere la partire: disabilitare area gioco e riabilitare bottone Nuova Partita
    			boxGioco.setDisable(true);
    			btnNuova.setDisable(false);
    			//this.inGame = false;
    			
    		} else {
    			
    			// tentativo errato
    			this.tentativi++;
    			txtCurr.setText(String.format("%d", this.tentativi));
    			if (this.tentativi==this.TMAX) {
    				// perso
    				txtLog.appendText(String.format("Hai perso! Il numero era: %d\n", this.segreto));
    				
    				// chiudere la partire: disabilitare area gioco e riabilitare bottone Nuova Partita
        			boxGioco.setDisable(true);
        			btnNuova.setDisable(false);
        			//this.inGame = false;
        			
    			} else {
    				// ancora in gioco
    				if (num < segreto) {
    					// troppo basso
    					txtLog.appendText("Troppo basso\n");
    				} else {
    					// troppo alto
    					txtLog.appendText("Troppo alto\n");
    				}
    			}
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
	}
}
