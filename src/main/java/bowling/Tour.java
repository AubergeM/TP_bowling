package bowling;

public class Tour {
	private int numTour; 
	private int nbQuillesAbattues1=0;
	private int nbQuillesAbattues2=0;
	private int nbQuillesAbattues3=0;
	private int numCoup = 1;
	boolean estFini = false;

	public Tour(int numTour){
		this.numTour = numTour;
	}

	public boolean estUnStrike(){
		return nbQuillesAbattues1 == PartieMonoJoueur.nbQuilles;
	}

	public boolean estUnSpare(){
		return numCoup == 2 && nbQuillesAbattues1+nbQuillesAbattues2 == PartieMonoJoueur.nbQuilles && numTour != PartieMonoJoueur.nbTours;
	}

	public boolean estFini(){
		return estFini;
	}

	public int getNbQuillesAbattues1() {
		return nbQuillesAbattues1;
	}

	public int getNbQuillesAbattues2() {
		return nbQuillesAbattues2;
	}

	public int getNbQuillesAbattues3() {
		return nbQuillesAbattues3;
	}

	public int getNumCoup() {
		return numCoup;
	}

	public void lancer(int valeur) {
		if (numCoup == 1) {
			nbQuillesAbattues1 += valeur;
			if (nbQuillesAbattues1 == PartieMonoJoueur.nbQuilles && numTour != PartieMonoJoueur.nbTours) {
				estFini = true;
			} else if (nbQuillesAbattues1 == PartieMonoJoueur.nbQuilles) {
				numCoup += 1;
			} else {
				numCoup++;
			}
		} else if (numCoup == 2) {
			nbQuillesAbattues2 += valeur;
			estFini = true;
			if (numTour == PartieMonoJoueur.nbTours && !(nbQuillesAbattues1 + nbQuillesAbattues2 < PartieMonoJoueur.nbQuilles)) {
				numCoup++;
				estFini = false;
			}
		} else if (numCoup == 3) {
			nbQuillesAbattues3 += valeur;
			estFini = true;
		}
	}
}
