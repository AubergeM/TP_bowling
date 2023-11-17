package bowling;

import java.util.ArrayList;

/**
 * Cette classe a pour but d'enregistrer le nombre de quilles abattues lors des
 * lancers successifs d'<b>un seul et même</b> joueur, et de calculer le score
 * final de ce joueur
 */
public class PartieMonoJoueur {
	public static final int nbTours = 10;
	public static final int nbQuilles = 10;
	private int numTour = 1;
	private ArrayList<Tour> laPartie = new ArrayList<>(); 
	/**
	 * Constructeur
	 */
	public PartieMonoJoueur() {
		for (int i = 1; i<=nbTours; i++){
			laPartie.add(new Tour(i));
		}	
	}

	

	/**
	 * Cette méthode doit être appelée à chaque lancer de boule
	 *
	 * @param nombreDeQuillesAbattues le nombre de quilles abattues lors de ce lancer
	 * @throws IllegalStateException si la partie est terminée
	 * @return vrai si le joueur doit lancer à nouveau pour continuer son tour, faux sinon	
	 */
	public boolean enregistreLancer(int nombreDeQuillesAbattues) {
		boolean b = true;
		if (estTerminee()) throw new IllegalStateException("la partie est terminée !");

		laPartie.get(numTour-1).lancer(nombreDeQuillesAbattues);
		if (!laPartie.get(numTour-1).estFini()) return true;
		if (numTour < nbTours) numTour++;
		
		return false;
	}

	/**
	 * Cette méthode donne le score du joueur.
	 * Si la partie n'est pas terminée, on considère que les lancers restants
	 * abattent 0 quille.
	 * @return Le score du joueur
	 */
	public int score() {
		int scoreTotal = 0;

		for (int i = 0; i < nbTours - 1; i++) {
			Tour tour = laPartie.get(i);
			scoreTotal += tour.getNbQuillesAbattues1() + tour.getNbQuillesAbattues2();

			if (tour.estUnSpare()) {
				scoreTotal += laPartie.get(i + 1).getNbQuillesAbattues1();
			} else if (tour.estUnStrike()) {
				if (i + 1 == nbTours - 1 || !laPartie.get(i + 1).estUnStrike()) {
					scoreTotal += laPartie.get(i + 1).getNbQuillesAbattues1() + laPartie.get(i + 1).getNbQuillesAbattues2();
				} else {
					scoreTotal += laPartie.get(i + 1).getNbQuillesAbattues1() + laPartie.get(i + 2).getNbQuillesAbattues1();
				}
			}
		}

		Tour dernierTour = laPartie.get(nbTours - 1);
		scoreTotal += dernierTour.getNbQuillesAbattues1() + dernierTour.getNbQuillesAbattues2() + dernierTour.getNbQuillesAbattues3();

		System.out.println(dernierTour.getNbQuillesAbattues1() + dernierTour.getNbQuillesAbattues2() + dernierTour.getNbQuillesAbattues3());
		System.out.println("Lancer 1 : " + dernierTour.getNbQuillesAbattues1());
		System.out.println("Lancer 2 : " + dernierTour.getNbQuillesAbattues2());

		return scoreTotal;
	}

		/**
		 * @return vrai si la partie est terminée pour ce joueur, faux sinon
		 */
	public boolean estTerminee() {
		boolean b=false;
		if(laPartie.get(nbTours-1).estFini()) {
			b = true;
		}
		return b;

		
	}

	/**
	 * @return Le numéro du tour courant [1..10], ou 0 si le jeu est fini
	 */
	public int numeroTourCourant() {
		if (estTerminee()) {
			numTour = 0;
		}
		return numTour;
	}

	/**
	 * @return Le numéro du prochain lancer pour tour courant [1..3], ou 0 si le jeu
	 *         est fini
	 */
	public int numeroProchainLancer() {
		int nblancer = 0;
		if (estTerminee()) {
			nblancer = 0;
		} else if (numTour == nbTours) {
			nblancer = laPartie.get(nbTours - 1).getNumCoup() + 1;
		} else 
			nblancer = laPartie.get(numTour).getNumCoup();
	
		return nblancer;
	}

}


