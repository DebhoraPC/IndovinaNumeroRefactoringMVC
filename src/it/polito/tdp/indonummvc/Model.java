package it.polito.tdp.indonummvc;

import java.security.InvalidParameterException;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;


/**
 * Modello Applicazione 
 * @author Utente
 */
public class Model {
	
	//private int NMAX = 100; 
	private IntegerProperty NMAX = new SimpleIntegerProperty(100); // intero osservabile (BINDINGS)
	
	//private int TMAX = 7; // log2(100) = 6.64 Ricerca Dicotomica
	private IntegerProperty TMAX = new SimpleIntegerProperty(7); // intero osservabile (BINDINGS)
	
	private int segreto; // NUMERO DA INDOVINARE
	
	//private int tentativi; // NUMERO DI TENTATIVI FATTI DALL'UTENTE
	private IntegerProperty tentativi = new SimpleIntegerProperty(); // intero osservabile (BINDINGS)
	
	//private boolean inGame;
	private BooleanProperty inGame = new SimpleBooleanProperty(false); // intero osservabile (BINDINGS)
	
	public Model() {
		//this.inGame = false; 
		this.inGame.set(false);
	}
	
	/**
	 * Avvia una partita, generando un nuovo numero segreto
	 */
	public void newGame() {
		
		// GENERO UN VALORE CASUALE double DENTRO [0,1), LO SI MOLTIPLICA PER 100 PER AVERE VALORI 
        // IN [0,100) POI LO TRASFORMO IN UN INTERO E SOMMO 1 COSI' HO UN NUMERO CASUALE IN [1,100]
		
		// QUESTO E' QUELLO CHE BISOGNA FARE QUANDO NON SI USANO I BINDINGS:
		//this.segreto = (int)(Math.random()*NMAX) + 1; 
		//tentativi = 0;
		//this.inGame = true;
		
		this.segreto = (int)(Math.random()*NMAX.get()) + 1;
    	this.tentativi.set(0); 
    	this.inGame.set(true);
    	
	}
	
	// PER GENERARE QUESTO COMMENTO IN JAVADOC FACCIO /** + INVIO, DOVE SCRIVO COSA FA LA FUNZIONE COSA
	// SONO I PARAMETRI PASSATI E COS'E' IL VALORE DI RITORNO 
	/**
	 * Fai un tentativo di indovinare il numero segreto
	 * @param t valore numerico del tentativo
	 * @return 0 se è indovinato, +1 se è troppo grande, -1 se è troppo piccolo
	 */
	public int tentativo(int t) {
		
		if (!inGame.get()){
			throw new IllegalStateException("Partita non attiva");
		}
		
		if (!valoreValido(t)) { // PROGRAMMAZIONE PROTETTIVA
			throw new InvalidParameterException("Tentativo fuori range");
		}
		
		//this.tentativi++; 
		this.tentativi.set(this.tentativi.get()+1); 
		
		if (this.tentativi.get() == this.TMAX.get()) {
			this.inGame.set(false);
		}
		
		if (t == this.segreto) {
			this.inGame.set(false); 
			return 0;
		}
		if (t < this.segreto)
			return -1;
		return +1;
		
	}
	
	/**
	 * Controlla se il tentativo fornito rispetto le regole formali
	 * del gioco, cioè è nel range [1, NMAX]
	 * @param tentativo
	 * @return {@code true} se tentativo è valido
	 */
	public boolean valoreValido(int tentativo) {
		return tentativo>=1 && tentativo<=this.NMAX.get();
	}

	//public boolean isInGame() {
		//return inGame;
	//}
	
	//public int getTentativi() { // non serve più perché ora tentativi è osservabile 
		//return this.tentativi;
	//}

	//public int getNMAX() {
		//return NMAX;
	//}

	//public int getTMAX() {
		//return TMAX;
	//}
	
	public int getSegreto() {
		return this.segreto;
	}
	
	public final IntegerProperty tentativiProperty() { // restituisce la property come oggetto
		return this.tentativi;
	}
	
	public final int getTentativi() { 
		return this.tentativiProperty().get();
	}
	
	public final IntegerProperty NMAXProperty() {
		return this.NMAX;
	}
	
	public final int getNMAX() {
		return this.NMAXProperty().get();
	}
		

	public final IntegerProperty TMAXProperty() {
		return this.TMAX;
	}
	
	public final int getTMAX() {
		return this.TMAXProperty().get();
	}
	

	public final BooleanProperty inGameProperty() {
		return this.inGame;
	}
	

	public final boolean isInGame() {
		return this.inGameProperty().get();
	}
	

	public final void setInGame(final boolean inGame) {
		this.inGameProperty().set(inGame);
	}
		
}
