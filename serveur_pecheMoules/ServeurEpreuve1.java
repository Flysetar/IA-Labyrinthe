import java.net.*;
import java.io.*;
import MG2D.*;
import MG2D.geometrie.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.awt.Font;

class ServeurEpreuve1{

    public static int tailleCase=20;
    public static int tailleX=25;
    public static int tailleY=17;
    public static boolean screen=false;
    public static int tour=0;
    public static Labyrinthe la=null;
    public static Fenetre f = null;
    public static ArrayList<Texture> textureJoueur = null;
    public static ArrayList<Texture> textureMoule = null;
    public static ArrayList<Texture> texturePoint = null;
    public static ArrayList<Texture> textureBiere = null;
    public static ArrayList<Texture> textureFrite = null;
    public static String cheminEnregistrerImage = "./";
    public static ArrayList<Texte> nomsJoueur = null;
    public static ArrayList<Texte> scores = null;
    public static String msgRecu="";
    public static char dernierCoup[];
    

    public static void usage(){
	System.out.println("java ServeurEpreuve1 <option> <valeur> <option> <valeur> ...");
	System.out.println("     avec option =");
	System.out.println("-tailleX <int> : modifie la taille en X du plateau");
	System.out.println("-tailleY <int> : modifie la taille en Y du plateau");
	System.out.println("-numLaby <int> : spécifie le numéro du labyrinthe pour la taille définie");
	System.out.println("-numPlacementBonus <int> : spécifie le numéro de placement des bonus pour le layrinthe");
	System.out.println("-nbJoueur <int> : modifie le nombre de joueur (doit être compris entre 1 et 4");
	System.out.println("-tauxDeDune <int> : modifie le taux de dunes (doit être compris entre 0 et 50)");
	System.out.println("-nbFrite <int> : modifie le nombre de frites dans le niveau");
	System.out.println("-nbBiere <int> : modifie le nombre de bières dans le niveau");
	System.out.println("-nbMoule <int> : modifie le nombre de moule dans le niveau");
	System.out.println("-tailleCase <int> : modifie la taille des cases pour l'affichage");
	System.out.println("-port <int> : modifie le port pour le serveur");
	System.out.println("-delay <int> : modifie le temps d'attente minimum entre deux tours");
	System.out.println("-timeout <int> : modifie le temps d'attente maximum entre deux tours");
	System.out.println("-nbTourMax <int> : fixer un nombre de tours maximal ****par joueur****");
	System.out.println("-screen <String> : permet de prendre des screenshots à chaque mouvement de d'enregistrer les images dans le répertoire passé en paramètre");
	System.exit(0);
    }

    public static String getDate(){
	String jour=new SimpleDateFormat("dd", Locale.FRANCE).format(new Date());
	String mois=new SimpleDateFormat("MM", Locale.FRANCE).format(new Date());
	String annee=new SimpleDateFormat("yyyy", Locale.FRANCE).format(new Date());
	String heure=new SimpleDateFormat("HH", Locale.FRANCE).format(new Date());
	String minute=new SimpleDateFormat("mm", Locale.FRANCE).format(new Date());
	String seconde=new SimpleDateFormat("ss", Locale.FRANCE).format(new Date());
	return jour+mois+annee+"-"+heure+minute+seconde;
    }

    public static String getSimpleDate(){
	String jour=new SimpleDateFormat("dd", Locale.FRANCE).format(new Date());
	String mois=new SimpleDateFormat("MM", Locale.FRANCE).format(new Date());
	String annee=new SimpleDateFormat("yyyy", Locale.FRANCE).format(new Date());
	String heure=new SimpleDateFormat("HH", Locale.FRANCE).format(new Date());
	String minute=new SimpleDateFormat("mm", Locale.FRANCE).format(new Date());
	return jour+mois+annee+"-"+heure+minute;
    }

    private static String getNomFichier(String date, int n){
	if(n<10)
	    return date+"_0000"+n;
	if(n<100)
	    return date+"_000"+n;
	if(n<1000)
	    return date+"_00"+n;
	if(n<10000)
	    return date+"_0"+n;
	return date+"_"+n;
    }

    private static String getNomFichierImageBloc(int x, int y){
	//si le bloc n'est pas une dune, on retourne le bloc sable
	if(la.getXY(x,y).getType()!=Case.DUNE)
	    return "bloc13.png";
	if(x==0){
	    if(y==0)
		//coin haut gauche
		return "bloc01.png";
	    if(y==tailleY)
		//coin bas gauche
		return "bloc03.png";
	    //partie gauche
	    if(la.getXY(x+1,y).getType()==Case.DUNE)
		//partie gauche mais avec un embranchement à droite
		return "bloc10.png";
	    //partie gauche classique
	    return "bloc06.png";
	}
	if(x==tailleX-1){
	    if(y==0)
		//coin haut droite
		return "bloc02.png";
	    if(y==tailleY-1)
		//coin bas droite
		return "bloc04.png";
	    //partie droite
	    if(la.getXY(x-1,y).getType()==Case.DUNE)
		//partie droite mais avec un embranchement à gauche
		return "bloc11.png";
	    //partie droite classique
	    return "bloc07.png";
	}
	if(y==0){
	    //partie haute
	    if(la.getXY(x,y+1).getType()==Case.DUNE)
		//partie haute mais avec un embranchement en bas
		return "bloc09.png";
	    //partie haute classique
	    return "bloc05.png";
	}
	if(y==tailleY-1){
	    //partie basse
	    if(la.getXY(x,y-1).getType()==Case.DUNE)
		//partie basse mais avec un embranchement en haut
		return "bloc12.png";
	    //partie basse classique
	    return "bloc08.png";
	}

	// cas d'une dune isolée sans voisin
	// ooo
	// oxo
	// ooo
	if(la.getXY(x,y-1).getType()!=Case.DUNE && la.getXY(x-1,y).getType()!=Case.DUNE && la.getXY(x+1,y).getType()!=Case.DUNE && la.getXY(x,y+1).getType()!=Case.DUNE)
	    return "bloc14.png";
	// ooo
	// oxo
	// oxo
	if(la.getXY(x,y-1).getType()!=Case.DUNE && la.getXY(x-1,y).getType()!=Case.DUNE && la.getXY(x+1,y).getType()!=Case.DUNE && la.getXY(x,y+1).getType()==Case.DUNE)
	    return "bloc15.png";
	// ooo
	// oxx
	// ooo
	if(la.getXY(x,y-1).getType()!=Case.DUNE && la.getXY(x-1,y).getType()!=Case.DUNE && la.getXY(x+1,y).getType()==Case.DUNE && la.getXY(x,y+1).getType()!=Case.DUNE)
	    return "bloc16.png";
	// ooo
	// oxx
	// oxo
	if(la.getXY(x,y-1).getType()!=Case.DUNE && la.getXY(x-1,y).getType()!=Case.DUNE && la.getXY(x+1,y).getType()==Case.DUNE && la.getXY(x,y+1).getType()==Case.DUNE)
	    return "bloc17.png";
	// ooo
	// xxo
	// ooo
	if(la.getXY(x,y-1).getType()!=Case.DUNE && la.getXY(x-1,y).getType()==Case.DUNE && la.getXY(x+1,y).getType()!=Case.DUNE && la.getXY(x,y+1).getType()!=Case.DUNE)
	    return "bloc18.png";
	// ooo
	// xxo
	// oxo
	if(la.getXY(x,y-1).getType()!=Case.DUNE && la.getXY(x-1,y).getType()==Case.DUNE && la.getXY(x+1,y).getType()!=Case.DUNE && la.getXY(x,y+1).getType()==Case.DUNE)
	    return "bloc19.png";
	// ooo
	// xxx
	// ooo
	if(la.getXY(x,y-1).getType()!=Case.DUNE && la.getXY(x-1,y).getType()==Case.DUNE && la.getXY(x+1,y).getType()==Case.DUNE && la.getXY(x,y+1).getType()!=Case.DUNE)
	    return "bloc20.png";
	// ooo
	// xxx
	// oxo
	if(la.getXY(x,y-1).getType()!=Case.DUNE && la.getXY(x-1,y).getType()==Case.DUNE && la.getXY(x+1,y).getType()==Case.DUNE && la.getXY(x,y+1).getType()==Case.DUNE)
	    return "bloc21.png";
	// oxo
	// oxo
	// ooo
	if(la.getXY(x,y-1).getType()==Case.DUNE && la.getXY(x-1,y).getType()!=Case.DUNE && la.getXY(x+1,y).getType()!=Case.DUNE && la.getXY(x,y+1).getType()!=Case.DUNE)
	    return "bloc22.png";
	// oxo
	// oxo
	// oxo
	if(la.getXY(x,y-1).getType()==Case.DUNE && la.getXY(x-1,y).getType()!=Case.DUNE && la.getXY(x+1,y).getType()!=Case.DUNE && la.getXY(x,y+1).getType()==Case.DUNE)
	    return "bloc23.png";
	// oxo
	// oxx
	// ooo
	if(la.getXY(x,y-1).getType()==Case.DUNE && la.getXY(x-1,y).getType()!=Case.DUNE && la.getXY(x+1,y).getType()==Case.DUNE && la.getXY(x,y+1).getType()!=Case.DUNE)
	    return "bloc24.png";
	// oxo
	// oxx
	// oxo
	if(la.getXY(x,y-1).getType()==Case.DUNE && la.getXY(x-1,y).getType()!=Case.DUNE && la.getXY(x+1,y).getType()==Case.DUNE && la.getXY(x,y+1).getType()==Case.DUNE)
	    return "bloc25.png";
	// oxo
	// xxo
	// ooo
	if(la.getXY(x,y-1).getType()==Case.DUNE && la.getXY(x-1,y).getType()==Case.DUNE && la.getXY(x+1,y).getType()!=Case.DUNE && la.getXY(x,y+1).getType()!=Case.DUNE)
	    return "bloc26.png";
	// oxo
	// xxo
	// oxo
	if(la.getXY(x,y-1).getType()==Case.DUNE && la.getXY(x-1,y).getType()==Case.DUNE && la.getXY(x+1,y).getType()!=Case.DUNE && la.getXY(x,y+1).getType()==Case.DUNE)
	    return "bloc27.png";
	// oxo
	// xxx
	// ooo
	if(la.getXY(x,y-1).getType()==Case.DUNE && la.getXY(x-1,y).getType()==Case.DUNE && la.getXY(x+1,y).getType()==Case.DUNE && la.getXY(x,y+1).getType()!=Case.DUNE)
	    return "bloc28.png";
	// oxo
	// xxx
	// oxo
	//if(la.getXY(x,y-1).getType()==Case.DUNE && la.getXY(x-1,y).getType()==Case.DUNE && la.getXY(x+1,y).getType()==Case.DUNE && la.getXY(x,y+1).getType()==Case.DUNE)
	    return "bloc29.png";
    }

    public static void affichageLabyrinthe(){
	f.effacer();

	for(int i=0;i<tailleX;i++)
	    for(int j=0;j<tailleY;j++)
		f.ajouter(new Texture("./images/"+getNomFichierImageBloc(i,j),new Point(i*tailleCase,(tailleY-j-1)*tailleCase),tailleCase,tailleCase));
	
	int tailleXBordMer = 6*tailleCase;
	int tailleYBordMer = (int)((6*tailleCase*1.0)*512.0/384.0);
	int nbRepetition=((tailleY*tailleCase)/tailleYBordMer)+1;

	for(int i=0;i<nbRepetition;i++)
	    f.ajouter(new Texture("./images/bordMer2.png",new Point(tailleX*tailleCase,(i*tailleYBordMer)),tailleXBordMer,tailleYBordMer));

	for(int i=0;i<textureJoueur.size();i++)
	    f.ajouter(textureJoueur.get(i));
	for(int i=0;i<textureMoule.size();i++)
	    f.ajouter(textureMoule.get(i));
	for(int i=0;i<texturePoint.size();i++)
	    f.ajouter(texturePoint.get(i));
	for(int i=0;i<textureFrite.size();i++)
	    f.ajouter(textureFrite.get(i));
	for(int i=0;i<textureBiere.size();i++)
	    f.ajouter(textureBiere.get(i));

	for(int i=0;i<scores.size();i++){
	    f.ajouter(scores.get(i));
	    f.ajouter(nomsJoueur.get(i));
	}
    }

    public static void miseAJourLabyrinthe(){

	// Mise à jour de la position des joueurs
	
	for(int i=0;i<la.getNbJoueur();i++){
	    /*System.out.println("taille : "+tailleX+","+tailleY+","+tailleCase);
	    System.out.println("pos joueur : "+la.getJoueur(i).getPosX()+","+la.getJoueur(i).getPosY());
	    System.out.println("pos texture : "+textureJoueur.get(i).getA().getX()+","+textureJoueur.get(i).getA().getY());
	    System.out.println("pas de deplacement : "+(la.getJoueur(i).getPosX()*tailleCase-textureJoueur.get(i).getA().getX()));

	    System.out.println("pas de deplacement : "+(la.getJoueur(i).getPosY()*tailleCase-textureJoueur.get(i).getA().getX()));*/

	    /*PATCH de derniere minute.... C'est moche*/
	    f.supprimer(textureJoueur.get(i));
	    if(dernierCoup[i]!='C')
		textureJoueur.set(i,new Texture("./images/joueur"+(i+1)+"_small_"+dernierCoup[i]+".png",new Point(la.getJoueur(i).getPosX()*tailleCase,(tailleY-la.getJoueur(i).getPosY()-1)*tailleCase),tailleCase,tailleCase));
	    else
		textureJoueur.set(i,new Texture("./images/joueur"+(i+1)+"_small_N.png",new Point(la.getJoueur(i).getPosX()*tailleCase,(tailleY-la.getJoueur(i).getPosY()-1)*tailleCase),tailleCase,tailleCase));
	    f.ajouter(textureJoueur.get(i));
	    
	    //textureJoueur.get(i).setA(new Point(la.getJoueur(i).getPosX()*tailleCase,(tailleY-la.getJoueur(i).getPosY()-1)*tailleCase));
	    //textureJoueur.get(i).translater(la.getJoueur(i).getPosX()*tailleCase-textureJoueur.get(i).getA().getX(),);
	    if(dernierCoup[i]!='C'){
		textureJoueur.get(i).setImg("./images/joueur"+(i+1)+"_small_"+dernierCoup[i]+".png");
		textureJoueur.get(i).setLargeur(tailleCase);
		textureJoueur.get(i).setHauteur(tailleCase);
	    }
	    for(int j=textureMoule.size()-1;j>=0;j--){
		// TODO - ca c'est moche, mais ca marche !
		// Tenter une nouvelle version de Labyrinthe avec des arraylist de bonus
		if(textureJoueur.get(i).getA().equals(textureMoule.get(j).getA())){
		    f.supprimer(textureMoule.get(j));
		    textureMoule.remove(j);
		    f.supprimer(texturePoint.get(j));
		    texturePoint.remove(j);
		}
	    }
	    for(int j=textureFrite.size()-1;j>=0;j--){
		if(textureJoueur.get(i).getA().equals(textureFrite.get(j).getA())){
		    f.supprimer(textureFrite.get(j));
		    textureFrite.remove(j);
		}
	    }
	    for(int j=textureBiere.size()-1;j>=0;j--){
		if(textureJoueur.get(i).getA().equals(textureBiere.get(j).getA())){
		    f.supprimer(textureBiere.get(j));
		    textureBiere.remove(j);
		}
	    }
	}

	for(int i=0;i<scores.size();i++){
	    scores.get(i).setTexte(""+la.getJoueur(i).getNbPoints());
	    nomsJoueur.get(i).setA(new Point((int)((tailleX+0.2)*tailleCase+nomsJoueur.get(i).getLargeur()/2),nomsJoueur.get(i).getA().getY()));
	}
	
	// Rafraichissement de la fenetre
	f.rafraichir();
	
    }
    
    public static void main(String args[]){

	int nbJoueurs=1;
	int tauxDune=50;
	int nbFrite=8;
	int nbMoule=10;
	int nbBiere=5;
	int port=1337;
	long nbLaby=-1;
	long numPlacementBonus=-1;
	long delay=100;
	int timeout=3000;
	int nbTourMax=-1;

	Scanner sc = new Scanner(System.in);

	long tpsDepart=new Date().getTime();
	long tpsCourant;

	ArrayList<Character> derniersCoups = new ArrayList<Character>();

	// TRAITEMENT DES OPTIONS
	// OPTIONS POSSIBLES :
	// -tailleX <int> : modifie la taille en X du plateau
	// -tailleY <int> : modifie la taille en Y du plateau
	// -nbJoueur <int> : modifie le nombre de joueur (doit être compris entre 1 et 4
	// -tauxDeDune <int> : modifie le taux de dunes (doit être compris entre 0 et 50)
	// -nbFrite <int> : modifie le nombre de frites dans le niveau
	// -nbBiere <int> : modifie le nombre de bières dans le niveau
	// -nbMoule <int> : modifie le nombre de moule dans le niveau
	// -tailleCase <int> : modifie la taille des cases pour l'affichage
	// -port <int> : modifie le port pour le serveur
	// -delay <int> : modifie le temps d'attente minimum entre deux tours
	// -timeout <int> : modifie le temps d'attente maximum entre deux tours
	// -nbTourMax <int> : fixe un nombre de tours maximal ****par joueur****
	// -screen : permet de prendre des screenshots à chaque mouvement

	for(int i=0;i<args.length;i++){
	    switch(args[i]){
	    case "-tailleX" :
		i++;
		tailleX=Integer.parseInt(args[i]);
		break;
	    case "-tailleY" :
		i++;
		tailleY=Integer.parseInt(args[i]);
		break;
	    case "-nbJoueur" :
		i++;
		nbJoueurs=Integer.parseInt(args[i]);
		if(nbJoueurs<1 || nbJoueurs>4){
		    System.out.println("Le nombre de joueur doit être compris entre 1 et 4");
		    System.exit(0);
		}
		break;
	    case "-tauxDeDune" :
		i++;
		tauxDune=Integer.parseInt(args[i]);
		if(tauxDune<0 || tauxDune>50){
		    System.out.println("Le taux de dune doit être compris entre 0 et 50");
		    System.exit(0);
		}
		break;
	    case "-nbFrite" :
		i++;
		nbFrite=Integer.parseInt(args[i]);
		break;
	    case "-nbBiere" :
		i++;
		nbBiere=Integer.parseInt(args[i]);
		break;
	    case "-nbMoule" :
		i++;
		nbMoule=Integer.parseInt(args[i]);
		break;
	    case "-tailleCase" :
		i++;
		tailleCase=Integer.parseInt(args[i]);
		break;
	    case "-port" :
		i++;
		port=Integer.parseInt(args[i]);
		break;
	    case "-numLaby" :
		i++;
		nbLaby=Long.parseLong(args[i]);
		break;
	    case "-numPlacementBonus" :
		i++;
		numPlacementBonus=Long.parseLong(args[i]);
		break;
	    case "-delay" :
		i++;
		delay=Long.parseLong(args[i]);
		break;
	    case "-timeout" :
		i++;
		timeout=Integer.parseInt(args[i]);
		break;
	    case "-nbTourMax" :
		i++;
		nbTourMax=Integer.parseInt(args[i]);
		break;
	    case "-screen" :
		screen=true;
		i++;
		cheminEnregistrerImage=args[i];
		break;
	    default :
		System.out.println("Je ne comprends pas l'option "+args[i]);
		usage();
	    }
	}

	//Remplissage des derniers coups par de vrais coups
	for(int i=0;i<nbJoueurs;i++)
	    derniersCoups.add(new Character('E'));

	//création du labyrinthe
	if(nbLaby==-1)
	    nbLaby=(long)(Math.random()*65535);
	if(numPlacementBonus==-1)
	    numPlacementBonus=(long)(Math.random()*65535);

	la = new Labyrinthe(tailleX,tailleY,nbLaby,numPlacementBonus);
	
	la.simplifierLabyrinthe(tauxDune);
	la.ajouterJoueur(1,1);
	if(nbJoueurs>1)
	    la.ajouterJoueur(tailleX-2,tailleY-2);
	if(nbJoueurs>2)
	    la.ajouterJoueur(tailleX-2,1);
	if(nbJoueurs>3)
	    la.ajouterJoueur(1,tailleY-2);

	for(int i=0;i<nbMoule;i++)
	    la.placerBonus(Case.MOULE);

	for(int i=0;i<nbBiere;i++)
	    la.placerBonus(Case.BIERE);

	for(int i=0;i<nbFrite;i++)
	    la.placerBonus(Case.FRITE);

	textureJoueur = new ArrayList<Texture>();
	//for(int i=0;i<la.getNbJoueur();i++)
	//    textureJoueur.add(new Texture("./images/joueur"+(i+1)+"_small_E.png",new Point(la.getJoueur(i).getPosX()*tailleCase,(tailleY-la.getJoueur(i).getPosY()-1)*tailleCase),tailleCase,tailleCase));
	textureJoueur.add(new Texture("./images/joueur1_small_E.png",new Point(la.getJoueur(0).getPosX()*tailleCase,(tailleY-la.getJoueur(0).getPosY()-1)*tailleCase),tailleCase,tailleCase));
	if(nbJoueurs>1)
	    textureJoueur.add(new Texture("./images/joueur2_small_O.png",new Point(la.getJoueur(1).getPosX()*tailleCase,(tailleY-la.getJoueur(1).getPosY()-1)*tailleCase),tailleCase,tailleCase));
	if(nbJoueurs>2)
	    textureJoueur.add(new Texture("./images/joueur3_small_S.png",new Point(la.getJoueur(2).getPosX()*tailleCase,(tailleY-la.getJoueur(2).getPosY()-1)*tailleCase),tailleCase,tailleCase));
	if(nbJoueurs>3)
	    textureJoueur.add(new Texture("./images/joueur4_small_N.png",new Point(la.getJoueur(3).getPosX()*tailleCase,(tailleY-la.getJoueur(3).getPosY()-1)*tailleCase),tailleCase,tailleCase));

	dernierCoup = new char[4];
	dernierCoup[0]='E';
	dernierCoup[1]='O';
	dernierCoup[2]='S';
	dernierCoup[3]='N';


	textureMoule = new ArrayList<Texture>();
	texturePoint = new ArrayList<Texture>();
	textureBiere = new ArrayList<Texture>();
	textureFrite = new ArrayList<Texture>();
	scores = new ArrayList<Texte>();
	nomsJoueur = new ArrayList<Texte>();
	
	for(int i=0;i<tailleX;i++)
	    for(int j=0;j<tailleY;j++){
		if(la.getXY(i,j).getType()==Case.MOULE){
		    textureMoule.add(new Texture("./images/moule_small.png",new Point(i*tailleCase,(tailleY-j-1)*tailleCase),tailleCase,tailleCase));
		    texturePoint.add(new Texture("./images/"+la.getXY(i,j).getPointRapporte()+".png",new Point(i*tailleCase,(tailleY-j-1)*tailleCase),tailleCase,tailleCase));		    
		}
		if(la.getXY(i,j).getType()==Case.FRITE)
		    textureFrite.add(new Texture("./images/frite_small.png",new Point(i*tailleCase,(tailleY-j-1)*tailleCase),tailleCase,tailleCase));
		if(la.getXY(i,j).getType()==Case.BIERE)
		    textureBiere.add(new Texture("./images/biere_small.png",new Point(i*tailleCase,(tailleY-j-1)*tailleCase),tailleCase,tailleCase));
	    }

	int hauteur=tailleY*tailleCase;
	int multiplicateur=hauteur/5;
	int tailleFont=hauteur/14;
	if(tailleFont>25)
	    tailleFont=25;
	int addition=tailleFont;

	
	// Création de la fenêtre d'affichage
	f = new Fenetre("A la pêche aux moules",(tailleX+6)*tailleCase,tailleY*tailleCase);

	// Affichage d'un signal d'attente

	Rectangle chargementExterieur = new Rectangle(Couleur.NOIR,new Point(tailleCase,((tailleY*tailleCase)-tailleCase)/2),new Point((tailleX*tailleCase)-tailleCase,((tailleY*tailleCase)+tailleCase)/2),false);
	Rectangle chargementInterieur = new Rectangle(Couleur.ROUGE,new Point(tailleCase,((tailleY*tailleCase)-tailleCase)/2),0,tailleCase,true);
	f.ajouter(chargementInterieur);
	f.ajouter(chargementExterieur);
	f.rafraichir();
	
	// Création de la socket
	int nbConnecte=0;
	String connecte[] = new String[nbJoueurs];
	Socket services[] = new Socket[nbJoueurs];
	String nomFichier="logServeurLaby-"+getDate();
	FileWriter fw=null;
	BufferedWriter output=null;
	ServerSocket s=null;
	String dateLancement=getSimpleDate();
	
	try{
	    fw = new FileWriter(nomFichier, true);
	    output = new BufferedWriter(fw);
	}
	catch(IOException e){
	    System.out.println("Impossible de créer le fichier "+nomFichier);
	    System.exit(0);
	}

	// Ajout de toutes les infos de la configuration de la partie dans les logs
	try{
	    output.write("Création d'un labyrinthe de "+tailleX+"x"+tailleY+" dont les cases font "+tailleCase+" pixels.\n");
	    output.flush();
	    output.write(la+"\n");
	    output.flush();
	    if(nbLaby==-1)
		output.write("Le labyrinthe a été généré aléatoirement.\n");
	    else
		output.write("Le labyrinthe porte le numéro "+nbLaby+".\n");
	    output.flush();
	    if(numPlacementBonus==-1)
		output.write("Le placement des bonus a été généré aléatoirement\n");
	    else
		output.write("Le placement des bonus porte le numéro "+numPlacementBonus+";\n");
	    output.flush();
	    output.write("Il y a "+nbJoueurs+" joueur(s)\n");
	    output.flush();
	    output.write("Le labyrinthe contient un taux de dune de "+tauxDune+"%, "+nbFrite+" frite(s), "+nbMoule+" moule(s) et "+nbBiere+" biére(s)\n");
	    output.flush();
	    output.write("Le port du serveur est le "+port+". Le timeout pour chaque joueur est de "+timeout+" ms. le délai d'attente minimum entre deux tours est de "+delay+" ms.\n");
	    output.flush();
	    output.write("Les screenshots à chaque tour ne sont pas activés\n");
	    output.flush();
	}catch(IOException e){
	    System.out.println("Problème lors de l'écriture dans le fichier "+nomFichier);
	    System.exit(0);
	}
	System.out.println("Le fichier de log est : "+nomFichier);
	System.out.println("Le labyrinthe porte le numéro "+nbLaby);
	System.out.println("Le placement des bonus porte le numéro "+numPlacementBonus);
	System.out.println("Le labyrinthe de "+tailleX+"x"+tailleY+" contient un taux de dune de "+tauxDune+"%, "+nbFrite+" frite(s), "+nbMoule+" moule(s) et "+nbBiere+" biére(s)");

	try{
	    s = new ServerSocket(port);

	    System.out.println("Serveur en attente de connexion");
	    output.write("Serveur en attente de connexion\n");
	    output.flush();
	}
	catch(IOException e){
	    System.out.println("Impossible de créer la socket sur le port "+port);
	    System.exit(0);
	}

	// Attente des joueurs - attendre le bon nombre de joueur
	while(nbConnecte!=nbJoueurs){
	    try{
		services[nbConnecte] = s.accept();
		services[nbConnecte].setSoTimeout(timeout);
	    }
	    catch(IOException e){
		System.out.println("Problème lors de la connexion d'une équipe "+port);
		System.exit(0);
	    }
	    InetSocketAddress adresse = (InetSocketAddress)services[nbConnecte].getRemoteSocketAddress();
	    
	    String ip= adresse.toString();
	    connecte[nbConnecte]=ip.split(":")[0];
	    
	    String hostname = adresse.getHostName();
	    
	    System.out.println("************************************************************\n*    -->    "+new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss", Locale.FRANCE).format(new Date())+"                          *\n*       Connexion "+nbConnecte+" - adresse : "+hostname+", "+ip+"*\n************************************************************");
	    try{
		output.write("************************************************************\n*    -->    "+new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss", Locale.FRANCE).format(new Date())+"                          *\n*       Connexion "+nbConnecte+" - adresse : "+hostname+", "+ip+"\n************************************************************\n");
		output.flush();
	    }catch(IOException e){
		System.out.println("Problème lors de l'écriture dans le fichier "+nomFichier);
		System.exit(0);
	    }
	    
	    nbConnecte++;
	    chargementInterieur.setLargeur((int)((tailleX*tailleCase-2*tailleCase)*((nbConnecte*1.0)/(nbJoueurs*1.0))));
	    f.rafraichir();
	}
	
	// Une fois tout le monde connecté, on envoie les infos du laby à tout le monde
	InputStream is[] = new InputStream[nbJoueurs];
	OutputStream os[] = new OutputStream[nbJoueurs];
	BufferedReader bf[] = new BufferedReader[nbJoueurs];
	PrintWriter pw[] = new PrintWriter[nbJoueurs];
	for(int i=0;i<nbJoueurs;i++){
	    is[i]=null;
	    try{
		is[i] = services[i].getInputStream();
	    }catch(IOException e){
		System.out.println("Impossible de récupérer un canal de communication en lecture pour le joueur "+i);
		System.exit(0);
	    }
	    try{
		os[i]  = services[i].getOutputStream();
	    }catch(IOException e){
		System.out.println("Impossible de récupérer un canal de communication en écriture pour le joueur "+i);
		System.exit(0);
	    }
	    bf[i] = new BufferedReader(new InputStreamReader(is[i]));
	    pw[i] = new PrintWriter(os[i]);
	}

	//On récupère les noms des participants
	for(int i=0;i<nbJoueurs;i++){
	    Couleur c=new Couleur(19,172,172);
	    if(i==1)
		c=new Couleur(160,23,23);
	    if(i==2)
		c=new Couleur(172,188,0);
	    if(i==3)
		c=new Couleur(56,56,56);
	    try{
		msgRecu=bf[i].readLine();
		nomsJoueur.add(new Texte(c,msgRecu,new Font("Calibri",Font.TYPE1_FONT,tailleFont),new Point((tailleX+2)*tailleCase,tailleY*tailleCase-(i+1)*multiplicateur+addition)));
		nomsJoueur.get(i).setA(new Point((int)((tailleX+0.2)*tailleCase),tailleY*tailleCase-(i+1)*multiplicateur+addition));
		scores.add(new Texte(c,"0",new Font("Calibri",Font.TYPE1_FONT,tailleFont),new Point((tailleX+2)*tailleCase,tailleY*tailleCase-(i+1)*multiplicateur)));
	    }catch(IOException e){
		System.out.println("Impossible de lire le nom du joueur "+i);
		nomsJoueur.add(new Texte(c,"Les blaireaux",new Font("Calibri",Font.TYPE1_FONT,tailleFont),new Point((tailleX+2)*tailleCase,tailleY*tailleCase-(i+1)*multiplicateur+addition)));
		nomsJoueur.get(i).setA(new Point((int)((tailleX+0.2)*tailleCase),tailleY*tailleCase-(i+1)*multiplicateur+addition));
		scores.add(new Texte(c,"0",new Font("Calibri",Font.TYPE1_FONT,tailleFont),new Point((tailleX+2)*tailleCase,tailleY*tailleCase-(i+1)*multiplicateur)));
	    }
	}

	// On envoie le numéro de joueur à chaque participant
	for(int i=0;i<nbJoueurs;i++){
	    pw[i].println(""+i);
	    pw[i].flush();
	    System.out.println(getDate()+" - envoi du numéro de joueur au joueur "+i);
	    try{
		output.write(getDate()+" -  envoi du numéro de joueur au joueur "+i+"\n");
		output.flush();
	    }catch(IOException e){
		System.out.println("Problème lors de l'écriture dans le fichier "+nomFichier);
		System.exit(0);
	    }
	}
	
	int numJoueur=-1;
	tour=-1;
	boolean toutLeMondeConnecte=true;

	affichageLabyrinthe();
	
	// Dès que tout le monde est connecté, on lance la boucle.
	// TODO - vérifier si le nombre de tour maximal est atteint
	while(nbMoule>0 && toutLeMondeConnecte && (nbTourMax==-1 || ((tour+1)<nbTourMax*nbJoueurs))){
	    tour++;
	    String infoLaby = la.toString();
	    numJoueur=(numJoueur+1)%nbJoueurs;

	    // on renvoie toutes les infos du laby à chaque tour

	    miseAJourLabyrinthe();

	    tpsCourant=new Date().getTime();
	    //System.out.println("temps d'attente : "+(delay-((tpsCourant-tpsDepart)/1000)));
	    try{Thread.sleep(delay-((tpsCourant-tpsDepart)/1000));}catch(java.lang.Exception e){}
	    tpsDepart=tpsCourant;
	    
	    pw[numJoueur].println(infoLaby);
	    pw[numJoueur].flush();
	    System.out.println(getDate()+" - envoi des infos du labyrinthe au joueur "+numJoueur);
	    try{
		output.write(getDate()+" -  envoi des infos du labyrinthe au joueur "+numJoueur+"\n");
		output.flush();
	    }catch(IOException e){
		System.out.println("Problème lors de l'écriture dans le fichier "+nomFichier);
		System.exit(0);
	    }

	    // On attend la réponse
	    msgRecu="";
	    try{
		msgRecu=bf[numJoueur].readLine();
	    }catch(IOException e){
		// Cas
		System.out.println("Impossible de lire les données sur la socket pour le joueur "+numJoueur);
		System.out.println("Le mouvement joué sera C pour le joueur "+numJoueur);
		msgRecu="C";
	    }
	    if(msgRecu==null){
		System.out.println("Le joueur "+numJoueur+" s'est surement deconnecté car le message est illisible");
		System.out.println("Le mouvement joué sera C pour le joueur "+numJoueur);
		msgRecu="C";
	    }
	    System.out.println(getDate()+" - réception du message \""+msgRecu+"\" de la part du joueur "+numJoueur);
	    try{
		output.write(getDate()+" - réception du message \""+msgRecu+"\" de la part du joueur "+numJoueur+"\n");
		output.flush();
	    }catch(IOException e){
		System.out.println("Problème lors de l'écriture dans le fichier "+nomFichier);
		System.exit(0);
	    }

	    derniersCoups.remove(0);
	    derniersCoups.add(new Character(msgRecu.charAt(0)));

	    // Execution de la commande suivante
	    String msgRetourExecution=la.executerCommande("J"+numJoueur+"-"+msgRecu);
	    if(msgRecu!=null)
		dernierCoup[numJoueur]=msgRecu.charAt(msgRecu.length()-1);
	    // ajout du retour dans les logs
	    try{
		output.write("Retour de l'exécution de la commande : "+msgRetourExecution+"\n");
		output.flush();
	    }catch(IOException e){
		System.out.println("Problème lors de l'écriture dans le fichier "+nomFichier);
		System.exit(0);
	    }

	    nbMoule=0;
	    for(int i=0;i<tailleX;i++)
		for(int j=0;j<tailleY;j++)
		    if(la.getXY(i,j).getType()==Case.MOULE)
			nbMoule++;

	    // On vérifie que tout le monde est encore connecté
	    // Inutile finalement car le cas est géré par les timeout.
	    /*for(int i=0;i<nbJoueurs;i++)
	      toutLeMondeConnecte=(toutLeMondeConnecte && services[i].isConnected());*/

	    // Vérifier que les derniers coups joués ne sont pas tous des "C"
	    // Dans ce cas, cela voudrait dire que tout le monde est déconnecté, que tout le monde a passé ou un mixte des deux.
	    // Dans ce cas, on arrête la simulation.

	    // Vérifie qu'au moins un joueur a joué
	    toutLeMondeConnecte=false;
	    for(int i=0;i<derniersCoups.size();i++)
		if(derniersCoups.get(i)!='C')
		    toutLeMondeConnecte=true;

	    if(toutLeMondeConnecte==false){
		try{
		    output.write("Tous les participants ont passé ou sont déconnectés\n");
		    output.flush();
		}catch(IOException e){
		    System.out.println("Problème lors de l'écriture dans le fichier "+nomFichier);
		    System.exit(0);
		}
	    }

	    if(screen)
		f.snapShot(cheminEnregistrerImage+"/"+getNomFichier(dateLancement,tour)+".jpg");
	}
	tour++;
	miseAJourLabyrinthe();
	tpsCourant=new Date().getTime();
	try{Thread.sleep(delay-((tpsCourant-tpsDepart)/1000));}catch(java.lang.Exception e){}
	
	if(screen)
	    f.snapShot(cheminEnregistrerImage+"/"+getNomFichier(dateLancement,tour)+".jpg");

	if(toutLeMondeConnecte ){
	    if(nbTourMax!=-1 && tour>=nbTourMax*nbJoueurs)
		System.out.println("Nombre de tour maximal par joueur dépassé.");
	    else
		System.out.println("Toutes les moules ont été ramassées en "+tour+" tours.");
	}
	else
	    System.out.println("Fin de partie au bout de "+tour+"+ tours. Quelques moules se sont échappées.");
	
	try{
	    if(toutLeMondeConnecte){
		if(nbTourMax!=-1 && tour>=nbTourMax*nbJoueurs)
		    output.write("Nombre de tour maximal par joueur dépassé.\n");
		else
		    output.write("Toutes les moules ont été ramassées en "+tour+" tours.\n");
	    }
	    else
		output.write("Fin de partie en "+tour+" tours. Quelques moules se sont échappées.\n");
	    output.flush();
	}catch(IOException e){
	    System.out.println("Problème lors de l'écriture dans le fichier "+nomFichier);
	    System.exit(0);
	}
	    
	for(int i=0;i<nbJoueurs;i++){
	    pw[i].println("FIN");
	    pw[i].flush();
	    System.out.println(getDate()+" - envoi de la fin de partie au joueur "+i);
	    try{
		output.write(getDate()+" -  envoi de la fin de partie au joueur "+i+"\n");
		output.flush();
	    }catch(IOException e){
		System.out.println("Problème lors de l'écriture dans le fichier "+nomFichier);
		System.exit(0);
	    }
	}

	try{
	    System.out.println("---------------------------");
	    output.write("---------------------------\n");
	    output.flush();
	    for(int i=0;i<la.getNbJoueur();i++){
		System.out.println(la.getJoueur(i));
		output.write(la.getJoueur(i)+"\n");
		output.flush();
	    }
	    System.out.println("---------------------------");
	    output.write("---------------------------\n");
	    output.flush();
	}catch(IOException e){
		System.out.println("Problème lors de l'écriture dans le fichier "+nomFichier);
		System.exit(0);
	    }

	ArrayList<Integer> vainqueurs=new ArrayList<Integer>();
	vainqueurs.add(0);
	
	for(int i=1;i<la.getNbJoueur();i++){
	    if(la.getJoueur(i).getNbPoints()==la.getJoueur(vainqueurs.get(0)).getNbPoints())
		vainqueurs.add(i);
	    else
		if(la.getJoueur(i).getNbPoints()>la.getJoueur(vainqueurs.get(0)).getNbPoints()){
		    vainqueurs.clear();
		    vainqueurs.add(i);
		}
	}
	
	// ajout du vainqueur dans les logs
	if(vainqueurs.size()==1){
	    System.out.println("VAINQUEUR : "+la.getJoueur(vainqueurs.get(0)).getNom()+" avec "+la.getJoueur(vainqueurs.get(0)).getNbPoints()+" points");
	    try{
		output.write(getDate()+" -  VAINQUEUR : "+la.getJoueur(vainqueurs.get(0)).getNom()+" avec "+la.getJoueur(vainqueurs.get(0)).getNbPoints()+" points\n");
		output.flush();
	    }catch(IOException e){
		System.out.println("Problème lors de l'écriture dans le fichier "+nomFichier);
		System.exit(0);
	    }
	}
	else{
	    System.out.println("NOUS AVONS DES EXAEQUO :");
	    for(int i=0;i<vainqueurs.size();i++)
		System.out.println(la.getJoueur(vainqueurs.get(i)).getNom()+" avec "+la.getJoueur(vainqueurs.get(i)).getNbPoints()+" points");	
	}

	if(screen)
	    f.snapShot(cheminEnregistrerImage+"/"+getNomFichier(dateLancement,tour)+".jpg");


	try{
	    output.close();
	}catch(IOException e){
	    System.out.println("Problème lors de la fermeture du fichier "+nomFichier);
	    System.exit(0);
	}
	
	//try{Thread.sleep(10000);}catch(java.lang.Exception e){}
	
	System.exit(0);
    }
}
