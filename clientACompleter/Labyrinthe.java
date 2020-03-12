import java.util.ArrayList;
import java.util.Random;

class Labyrinthe{
    private int tailleX, tailleY;
    private Case tableau[];
    private ArrayList<Joueur> listeJoueur;

    public Labyrinthe(){
	tailleX=3;
	tailleY=3;
	tableau = new Case[tailleX*tailleY];
	for(int i=0;i<tailleX*tailleY;i++)
	    tableau[i]=new Case();
	tableau[4].setType("SABLE");
	listeJoueur=new ArrayList<Joueur>();
    }

    public Labyrinthe(String infos){
	String[] tailleInfos=infos.split("/");
	if(tailleInfos.length!=3){
	    System.out.println("Impossible de construire un labyrinthe à partir de ces informations :");
	    System.out.println(infos);
	    System.exit(0);
	}
	String[] taille=tailleInfos[0].split("x");
	if(taille.length!=2){
	    System.out.println("Impossible de construire un labyrinthe à partir de ces informations car la taille n'est pas reconnue :");
	    System.out.println(infos);
	    System.exit(0);
	}
	try{
	    tailleX=Integer.parseInt(taille[0]);
	    tailleY=Integer.parseInt(taille[1]);
	}catch(java.lang.NumberFormatException e){
	    System.out.println("Impossible de construire un labyrinthe à partir de ces informations car la taille n'est pas reconnue :");
	    System.out.println(infos);
	    System.exit(0);
	}
	
	
	String[] infosLaby = tailleInfos[1].split("-");
	if(infosLaby.length!=tailleX*tailleY){
	    System.out.println("les infos du labyrinthe envoyé et la taille ne correspondent pas");
	    System.out.println("tailleX : "+tailleX+" - tailleY : "+tailleY);
	    System.out.println("infos : \n"+infos);
	    System.exit(0);
	}
	
	tableau = new Case[tailleX*tailleY];
	
	for(int i=0;i<tailleX*tailleY;i++){
	    if(infosLaby[i].equals("S"))
		tableau[i] = new Case("SABLE",0);		
	    else{
		if(infosLaby[i].equals("D"))
		    tableau[i] = new Case("DUNE",0);
		else{
		    if(infosLaby[i].equals("F"))
			tableau[i] = new Case("FRITE",0);
		    else{
			if(infosLaby[i].equals("B"))
			    tableau[i] = new Case("BIERE",0);
			else{
			    try{
				tableau[i] = new Case("MOULE",Integer.parseInt(infosLaby[i]));
			    }catch(java.lang.NumberFormatException e){
				System.out.println("Je ne comprends pas cette case : "+infosLaby[i]);
				System.exit(0);
			    }
			}	
		    }   
		}	
	    }    
	}

	listeJoueur=new ArrayList<Joueur>();

	String[] infosJoueur = tailleInfos[2].split("-");
	int nbJoueur=Integer.parseInt(infosJoueur[0]);
	for(int i=0;i<nbJoueur;i++){
	    String[] infosPos = infosJoueur[i+1].split(",");
	    listeJoueur.add(new Joueur(Integer.parseInt(infosPos[0]),Integer.parseInt(infosPos[1]),"joueur"+(i+1)));
	}
	    
	    
    }

    public Labyrinthe(Labyrinthe la){
	tailleX=la.tailleX;
	tailleY=la.tailleY;
	listeJoueur=new ArrayList<Joueur>();
	for(int i=0;i<la.listeJoueur.size();i++)
	    listeJoueur.add(new Joueur(la.listeJoueur.get(i)));
	tableau=new Case[la.tableau.length];
	for(int i=0;i<la.tableau.length;i++)
	    tableau[i]=new Case(la.tableau[i]);
    }

    public int getTailleX(){
	return tailleX;
    }

    public int getTailleY(){
	return tailleY;
    }

    public Case getXY(int x, int y){
	// TODO - Vérifier que X et y soit bien dans le plateau
	return tableau[y*tailleX+x];
    }

    public int getNbJoueur(){
	return listeJoueur.size();
    }

    public Joueur getJoueur(int n){
	return listeJoueur.get(n);
    }

    /*public void ajouterJoueur(int posJoueurX, int posJoueurY){
	ajouterJoueur(posJoueurX,posJoueurY, "J"+listeJoueur.size());
    }

    private void ajouterJoueur(int posJoueurX, int posJoueurY, String nomJoueur){
	if(posJoueurX<1 || posJoueurY<1)
	    throw new java.lang.RuntimeException("Ajout d'un joueur sur une case en dehors du plateau");
	if(posJoueurX>tailleX-2 || posJoueurY>tailleY-2)
	    throw new java.lang.RuntimeException("Ajout d'un joueur sur une case en dehors du plateau");
	if(getXY(posJoueurX,posJoueurY).getType()!=Case.SABLE)
	    throw new java.lang.RuntimeException("Ajout d'un joueur sur une case qui n'est pas du sable");
	int indice = listeJoueur.size();
	listeJoueur.add(new Joueur(posJoueurX,posJoueurY,nomJoueur));
	//System.out.println("Joueur "+nomJoueur+" a bien été ajouté : il porte le numéro "+indice);
    }

    public void faireBougerJoueur(int nbJoueur, char direction){
	if(nbJoueur<0 || nbJoueur>=listeJoueur.size())
	    throw new java.lang.RuntimeException("Vous essayez de faire bouger un joueur qui n'existe pas");
	int deltaX=0;
	int deltaY=0;
	switch(direction){
	case 'N' :
	    deltaY=-1;
	    break;
	case 'S' :
	    deltaY=1;
	    break;
	case 'E' :
	    deltaX=1;
	    break;
	case 'O' :
	    deltaX=-1;
	    break;
	case 'C' : // Le joueur ne bouge pas 'C' pour Centre
	    break;
	default :
	    throw new java.lang.RuntimeException("Vous essayez de faire bouger un joueur dans une direction inconnue");
	}
	if(getXY(listeJoueur.get(nbJoueur).getPosX()+deltaX,listeJoueur.get(nbJoueur).getPosY()+deltaY).getType()==Case.DUNE)
	    System.out.println("Impossible de vous déplacer sur une case qui est une dune");
	else
	    listeJoueur.get(nbJoueur).translate(deltaX,deltaY);

	ramasseEtMAJ(nbJoueur);
    }

    public boolean marchable(int x, int y){
	return !(getXY(x,y).getType()==Case.DUNE);
    }

    public void placerBonus(int typeCase){
	// méthode qui utilise randPlacementBonus instancié dans les constructeurs
	
	ArrayList<Integer> liste = new ArrayList<Integer>();
	for(int i=3;i<tailleX-3;i++)
	    for(int j=3;j<tailleY-3;j++)
		if(getXY(i,j).getType()==Case.SABLE)
		    liste.add(j*tailleX+i);
	
	if(liste.size()<1){
	    System.out.println("Il n'y a pas assez d'espace pour placer tous les bonus demandés");
	    System.exit(0);
	}
	
	int indiceAlea=randPlacementBonus.nextInt(liste.size());
	tableau[liste.get(indiceAlea)].setType(typeCase);
	if(typeCase==Case.MOULE)
	    tableau[liste.get(indiceAlea)].setPointRapporte((randPlacementBonus.nextInt(9)+1)*10);
	
    }

    public void simplifierLabyrinthe(double pourcentDune){
	// Méthode qui utilise randCreationLaby instancié dans les constructeurs
	int nbCasesTotal=(tailleX-2)*(tailleY-2);
	int nbDune=(int)(nbCasesTotal*pourcentDune/100.0);
	ArrayList<Integer> liste = new ArrayList<Integer>();
	for(int i=1;i<tailleX-1;i++)
	    for(int j=1;j<tailleY-1;j++)
		if(getXY(i,j).getType()==Case.DUNE)
		    liste.add(j*tailleX+i);
	while(liste.size()>nbDune){
	    int indiceAlea=randCreationLaby.nextInt(liste.size());
	    tableau[liste.get(indiceAlea)].setType(Case.SABLE);
	    liste.remove(indiceAlea);
	}	
	}*/

    public int getIndex(int x, int y){
	return y*tailleX+x;
    }

    /*private int retrouverJoueur(String nom){
	for(int i=0;i<listeJoueur.size();i++)
	    if(nom.equals(listeJoueur.get(i).getNom()))
		return i;
	System.out.println("Le joueur "+nom+" n'existe pas");
	return -1;
	}*/
    
    /*private boolean directionValide(char c){
	return ((c=='N') || (c=='S') || (c=='E') || (c=='O'));
    }

    private void ramasseEtMAJ(int indiceJoueur){
	switch(getXY(listeJoueur.get(indiceJoueur).getPosX(),listeJoueur.get(indiceJoueur).getPosY()).getType()){
	case Case.BIERE :
	    listeJoueur.get(indiceJoueur).ajouterBiere();
	    break;
	case Case.MOULE :
	    listeJoueur.get(indiceJoueur).ajouterPoints(getXY(listeJoueur.get(indiceJoueur).getPosX(),listeJoueur.get(indiceJoueur).getPosY()).getPointRapporte());
	    getXY(listeJoueur.get(indiceJoueur).getPosX(),listeJoueur.get(indiceJoueur).getPosY()).setPointRapporte(0);
	    break;
	case Case.FRITE :
	    listeJoueur.get(indiceJoueur).ajouterFrite();
	    break;
	default :
	    break;
	}
	getXY(listeJoueur.get(indiceJoueur).getPosX(),listeJoueur.get(indiceJoueur).getPosY()).setType(Case.SABLE);
    }

    public String executerCommande(String cmd){
	String[] eltCmd=cmd.split("-");
	if(eltCmd.length==1)
	    System.out.println("Commande recue : "+cmd+" - ne peut être exécutée");
	int indiceJoueur=retrouverJoueur(eltCmd[0]);
	if(indiceJoueur!=-1){
	    boolean actionTrouve=false;
	    if(eltCmd[1].equals("B")){
		actionTrouve=true;
		// Verifier qu'il a bien une biere
		if(listeJoueur.get(indiceJoueur).getNbBiere()>0){
		    // Possibilité de faire 3 mouvements
		    char direction1='C',direction2='C',direction3='C';
		    if(eltCmd.length>2)
			direction1=eltCmd[2].charAt(0);
		    if(eltCmd.length>3)
			direction2=eltCmd[3].charAt(0);
		    if(eltCmd.length>4)
			direction3=eltCmd[4].charAt(0);
		    if(!directionValide(direction1))
			direction1='C';
		    if(!directionValide(direction2))
			direction2='C';
		    if(!directionValide(direction3))
			direction3='C';
		    faireBougerJoueur(indiceJoueur,direction1);
		    faireBougerJoueur(indiceJoueur,direction2);
		    faireBougerJoueur(indiceJoueur,direction3);
		    listeJoueur.get(indiceJoueur).enleverBiere();
		    
		}
		else{
		    System.out.println("Le joueur "+listeJoueur.get(indiceJoueur)+" n'a pas de bière...");
		}
	    }
	    if(eltCmd[1].equals("F")){
		actionTrouve=true;
		// Vérifier s'il a bien une frite
		if(listeJoueur.get(indiceJoueur).getNbFrite()>0){
		    // Possibilité de passer au dessus d'un mur
		    if(eltCmd.length>2){
			char direction=direction=eltCmd[2].charAt(0);
			if(directionValide(direction)){
			    int joueurPosX=listeJoueur.get(indiceJoueur).getPosX(),joueurPosY=listeJoueur.get(indiceJoueur).getPosY();
			    int deltaX=0,deltaY=0;
			    if(direction=='N')
				deltaY=-2;
			    if(direction=='S')
				deltaY=2;
			    if(direction=='O')
				deltaX=-2;
			    if(direction=='E')
				deltaX=2;
			    joueurPosX+=deltaX;
			    joueurPosY+=deltaY;
			    if(joueurPosX<0 || joueurPosX>=tailleX || joueurPosY<0 || joueurPosY>=tailleY)
				System.out.println("Apres le saut, le joueur serait hors plateau");
			    else{
				if(getXY(joueurPosX,joueurPosY).getType()!=Case.DUNE){
				    listeJoueur.get(indiceJoueur).setPosX(joueurPosX);
				    listeJoueur.get(indiceJoueur).setPosY(joueurPosY);
				
				    ramasseEtMAJ(indiceJoueur);
				    listeJoueur.get(indiceJoueur).enleverFrite();
				}
				else{
				    System.out.println("Impossible de se déplacer sur une dune. Vous ne pouvez sauter qu'au dessus d'une simple dune. On va tout de même tenter un déplacement simple.");
				    faireBougerJoueur(indiceJoueur,direction);				
				}
			    
			    }
			}
		    }else{
			System.out.println("Le joueur "+listeJoueur.get(indiceJoueur).getNom()+" n'a pas de frite");
		    }
		}
	    }
	    if(eltCmd[1].equals("N")){
		actionTrouve=true;
		faireBougerJoueur(indiceJoueur,'N');
	    }
	    if(eltCmd[1].equals("S")){
		actionTrouve=true;
		faireBougerJoueur(indiceJoueur,'S');
	    }
	    if(eltCmd[1].equals("E")){
		actionTrouve=true;
		faireBougerJoueur(indiceJoueur,'E');
	    }
	    if(eltCmd[1].equals("O")){
		actionTrouve=true;
		faireBougerJoueur(indiceJoueur,'O');
	    }
	    if(eltCmd[1].equals("C")){
		actionTrouve=true;
		System.out.println("Le joueur passe son tour");
	    }
	    if(actionTrouve==false)
		System.out.println("Je ne comprends pas la commande :"+cmd);
	}

	return "";
    
	}*/

    public String toString(){
	String retour=tailleX+"x"+tailleY+"/";
	retour=retour.concat(tableau[0].toString());
	for(int i=1;i<tableau.length;i++)
	    retour=retour.concat("-"+tableau[i]);
	retour=retour.concat("/"+listeJoueur.size()+"-"+listeJoueur.get(0).getPosX()+","+listeJoueur.get(0).getPosY());
	
	for(int i=1;i<listeJoueur.size();i++)
	    retour=retour.concat("-"+listeJoueur.get(i).getPosX()+","+listeJoueur.get(i).getPosY());
	
	return retour;
    }

    public String toStringLaby(){
	String retour="";
	for(int j=0;j<tailleY;j++){
	    for(int i=0;i<tailleX;i++)
		if(getXY(i,j).getType()!=Case.MOULE)
		    retour=retour.concat(" - "+getXY(i,j).getType());
		else
		    retour=retour.concat(" - "+getXY(i,j).getPointRapporte());
	    retour=retour.concat("\n");
	}
	
	return retour;
    }

    /*public static void main(String[] args){
	Labyrinthe l = new Labyrinthe(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
	l.ajouterJoueur(1,1);
	System.out.println(l);
	}*/
		      
}
