class Joueur{
    private int posX=0,posY=0;
    private String nom;
    private int nbPoints=0;
    private int nbFrite=0;
    private int nbBiere=0;

    public Joueur(){
	posX=0;
	posY=0;
	nom="Inconnu";
	nbPoints=0;
	nbFrite=0;
	nbBiere=0;
    }

    public Joueur(int pposX, int pposY, String nnom){
	posX=pposX;
	posY=pposY;
	nom=new String(nnom);
	nbPoints=0;
	nbFrite=0;
	nbBiere=0;
    }

    public Joueur(Joueur j){
	posX=j.posX;
	posY=j.posY;
	nom=new String(j.nom);
	nbPoints=j.nbPoints;
	nbFrite=j.nbFrite;
	nbBiere=j.nbBiere;
    }

    public void translate(int deltaX, int deltaY){
	posX+=deltaX;
	posY+=deltaY;
    }

    public int getPosX(){
	return posX;
    }

    public int getPosY(){
	return posY;
    }

    public void setPosX(int pposX){
	posX=pposX;
    }

    public void setPosY(int pposY){
	posY=pposY;
    }

    public String getNom(){
	return nom;
    }

    public int getNbPoints(){
	return nbPoints;
    }

    public void ajouterBonus(char c){
	switch(c){
	case 'B' :
	    nbBiere++;
	    break;
	case 'F':
	    nbFrite++;
	    break;
	default:
	    throw new java.lang.RuntimeException("Bonus "+c+" inconnu");
	}
    }

    public void ajouterBiere(){
	nbBiere++;
    }

    public void ajouterFrite(){
	nbFrite++;
    }

    public void enleverBiere(){
	nbBiere--;
    }

    public void enleverFrite(){
	nbFrite--;
    }

    public void ajouterPoints(int pt){
	nbPoints+=pt;
    }

    public int getNbFrite(){
	return nbFrite;
    }

    public int getNbBiere(){
	return nbBiere;
    }

    public String toString(){
	String retour = "Joueur "+nom+" en ("+posX+","+posY+") a "+nbPoints;
	if(nbPoints==0)
	    retour=retour.concat(" point et possède "+nbBiere);
	else
	    retour=retour.concat(" points et possède "+nbBiere);
	if(nbBiere<2)
	    retour=retour.concat(" bière et "+nbFrite);
	else
	    retour=retour.concat(" bières et "+nbFrite);
	if(nbFrite<2)
	    retour=retour.concat(" frite");
	else
	    retour=retour.concat(" frites");
	
	return retour;
    }
}
