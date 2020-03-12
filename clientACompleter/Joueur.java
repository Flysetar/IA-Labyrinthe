class Joueur{
    private int posX=0,posY=0;
    private String nom;

    public Joueur(){
	posX=0;
	posY=0;
	nom="Inconnu";
    }

    public Joueur(int pposX, int pposY, String nnom){
	posX=pposX;
	posY=pposY;
	nom=new String(nnom);
    }

    public Joueur(Joueur j){
	posX=j.posX;
	posY=j.posY;
	nom=new String(j.nom);
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

    public String toString(){
	String retour = "Joueur "+nom+" en ("+posX+","+posY+")";
	
	return retour;
    }
}
