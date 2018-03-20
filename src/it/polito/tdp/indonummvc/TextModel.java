package it.polito.tdp.indonummvc;

public class TextModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		model.newGame();
		
		int min = 1;
		int max = model.getNMAX();
		
		while(model.isInGame()) {
			int t = (min+max)/2; // media
			System.out.format("Tra %d e %d provo %d\n", min, max, t);
			int ris = model.tentativo(t); // prova sempre lo stesso numero così
			
			if (ris>0)
				max = t-1;
			else
				min = t+1;
			System.out.println(ris);
		}
	}

}
