package it.polito.tdp.indonummvc;

import java.security.InvalidParameterException;

public class Model {
	
	private int NMAX = 100;
	private int TMAX = 7; // log2(100) = 6.64 RICERCA DICOTOMICA
	
	private int segreto; // NUMERO DA INDOVINARE
	private int tentativi; // NUMERO DI TENTATIVI FATTI DALL'UTENTE
	
	private boolean inGame;
	
	public Model() {
		this.inGame = false;
	}
	
	/**
	 * AVVIA UNA NUOVA PARTITA, GENERANDO UN NUOVO NUMERO SEGRETO
	 */
	public void newGame() {
		
		// GENERO UN VALORE CASUALE double DENTRO [0,1), LO SI MOLTIPLICA PER 100 PER AVERE VALORI 
        // IN [0,100) POI LO TRASFORMO IN UN INTERO E SOMMO 1 COSI' HO UN NUMERO CASUALE IN [1,100]
		
		this.segreto = (int)(Math.random()*NMAX) + 1; 
    	this.tentativi = 0;
    	this.inGame = true;
		
	}
	
	// PER GENERARE QUESTO COMMENTO IN JAVADOC FACCIO /** + INVIO, DOVE SCRIVO COSA FA LA FUNZIONE COSA
	// SONO I PARAMETRI PASSATI E COS'E' IL VALORE DI RITORNO 
	/**
	 * FAI UN TENTATIVO DI INDOVINARE IL NUMERO SEGRETO
	 * @param t VALORE NUMERICO DEL TENTATIVO
	 * @return 0 SE E' INDOVINATO, +1 SE è TROPPO GRANDE, -1 SE E' TROPPO PICCOLO
	 */
	public int tentativo(int t) {
		
		if (!inGame){
			throw new IllegalStateException("Partita non attiva");
		}
		
		if (t<1 || t>this.TMAX) {
			throw new InvalidParameterException("Tentativo fuori range");
		}
		
		this.tentativi++;
		
		if (t == this.segreto) 
			return 0;
		if (t < this.segreto)
			return -1;
		return +1;
		
	}

	public boolean isInGame() {
		return inGame;
	}
	
	public int getTentativi() {
		return this.tentativi;
	}

	public int getNMAX() {
		return NMAX;
	}

	public int getTMAX() {
		return TMAX;
	}

}
