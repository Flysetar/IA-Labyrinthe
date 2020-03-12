class Case{
    public static final int SABLE = 0;
    public static final int DUNE = 1;
    public static final int FRITE = 2;
    public static final int BIERE = 3;
    public static final int MOULE = 4;

    // ATTENTION ! L'ordre doit correspondre à ce qui est déclarer au dessus !
    private static String[] listeType = {"SABLE", "DUNE", "FRITE", "BIERE", "MOULE"};

    public int type=Case.DUNE;
    public int pointRapporte=0;

    public Case(){
	type = Case.DUNE;
	pointRapporte=0;
    }
    
    public Case(int ttype, int ppointRapporte){
	setType(ttype);
	setPointRapporte(ppointRapporte);
    }
    
    public Case(String ttype, int ppointRapporte){
	setType(ttype);
	setPointRapporte(ppointRapporte);
    }

    public Case(Case c){
	type=c.type;
	pointRapporte=c.pointRapporte;
    }

    public int getType(){
	return type;
    }

    public int getPointRapporte(){
	return pointRapporte;
    }

    public void setType(int ttype){
	if(ttype<0 || ttype>listeType.length)
	    throw new java.lang.RuntimeException("Classe Case\n--> Type de case inconnu");
	type=ttype;
    }

    public void setType(String ttype){
	type=-1;
	for(int i=0;i<listeType.length;i++)
	    if(ttype.equals(listeType[i])){
		type=i;
	    }
	if(type==-1)
	    throw new java.lang.RuntimeException("Classe Case\n--> Type de case inconnu");
    }

    public void setPointRapporte(int ppointRapporte){
	if(ppointRapporte<0)
	    throw new java.lang.RuntimeException("Classe Case\n--> Nombre de point négatif");
	if(type!=Case.MOULE && ppointRapporte>0)
	    throw new java.lang.RuntimeException("Classe Case\n--> Une case qui n'est pas de type moule ne peut pas rapporter de points");
	pointRapporte=ppointRapporte;
    }

    public String toString(){
	if(type==Case.DUNE)
	    return "D";
	if(type==Case.SABLE)
	    return "S";
	if(type==Case.BIERE)
	    return "B";
	if(type==Case.MOULE)
	    return ""+pointRapporte;
	return "F";
    }
}
