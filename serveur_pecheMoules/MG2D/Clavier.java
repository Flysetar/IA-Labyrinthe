/*********************************************************************/
/*                                                                   */
/* Copyright 2012-2018 Remi Synave, Anthony Desitter,                */
/*                     Nicolas Dubrunfaut, Maxime Langa,             */
/*                     Guillaume Langa et Ryan Verlande              */
/*                     (qui a ajouté tous les boutons. Ce grand      */
/*                     malade... ^^)                                 */
/*                                                                   */
/* This file is part of MG2D.                                        */
/* This library uses javazoom library piece of code                  */
/* http://www.javazoom.net                                           */
/* and can be found with this library (file jlayer1.0.1.tar.gz)      */
/*                                                                   */
/* MG2D is free software: you can redistribute it and/or modify      */
/* it under the terms of the GNU General Public License as published */
/* by the Free Software Foundation, either version 3 of the License, */
/* or (at your option) any later version.                            */
/*                                                                   */
/* MG2D is distributed in the hope that it will be useful,           */
/* but WITHOUT ANY WARRANTY; without even the implied warranty of    */
/* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the      */
/* GNU General Public License for more details.                      */
/*                                                                   */
/* You should have received a copy of the GNU General Public License */
/* along with MG2D. If not, see <http://www.gnu.org/licenses/>.      */
/*                                                                   */
/*********************************************************************/

package MG2D;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Cette classe implémente les méthodes de KeyListener permettant la gestion du clavier.
 * <br /><br />
 * Elle permet de gérer le clavier dans des applications développées avec la bibliotheque MG2D.<br />
 * Elle fonctionne sur le principe de booléen que l'on change quand on appuie ou relâche les touches.
 * @author Equipe 2D, Remi Synave
 * @version 2.8
 */
public class Clavier implements KeyListener {

    // Attributs //

    private boolean gauche;
    private boolean gaucheTape;
    private boolean droite;
    private boolean droiteTape;
    private boolean haut;
    private boolean hautTape;
    private boolean bas;
    private boolean basTape;

    private boolean espace;
    private boolean espaceTape;
    private boolean entree;
    private boolean entreeTape;
    private boolean echap;
    private boolean echapTape;

    private boolean a;
    private boolean aTape;
    private boolean z;
    private boolean zTape;
    private boolean e;
    private boolean eTape;
    private boolean q;
    private boolean qTape;
    private boolean s;
    private boolean sTape;
    private boolean d;
    private boolean dTape;
    private boolean b;
    private boolean bTape;
    private boolean c;
    private boolean cTape;
    private boolean f;
    private boolean fTape;
    private boolean g;
    private boolean gTape;
    private boolean h;
    private boolean hTape;
    private boolean i;
    private boolean iTape;
    private boolean j;
    private boolean jTape;
    private boolean k;
    private boolean kTape;
    private boolean l;
    private boolean lTape;
    private boolean m;
    private boolean mTape;
    private boolean n;
    private boolean nTape;
    private boolean o;
    private boolean oTape;
    private boolean p;
    private boolean pTape;
    private boolean r;
    private boolean rTape;
    private boolean t;
    private boolean tTape;
    private boolean u;
    private boolean uTape;
    private boolean v;
    private boolean vTape;
    private boolean w;
    private boolean wTape;
    private boolean x;
    private boolean xTape;
    private boolean y;
    private boolean yTape;
    
    private boolean numpad0;
    private boolean numpad0Tape;
    private boolean numpad1;
    private boolean numpad1Tape;
    private boolean numpad2;
    private boolean numpad2Tape;
    private boolean numpad3;
    private boolean numpad3Tape;
    private boolean numpad4;
    private boolean numpad4Tape;
    private boolean numpad5;
    private boolean numpad5Tape;
    private boolean numpad6;
    private boolean numpad6Tape;
    private boolean numpad7;
    private boolean numpad7Tape;
    private boolean numpad8;
    private boolean numpad8Tape;
    private boolean numpad9;
    private boolean numpad9Tape;

    private boolean num0;
    private boolean num0Tape;
    private boolean num1;
    private boolean num1Tape;
    private boolean num2;
    private boolean num2Tape;
    private boolean num3;
    private boolean num3Tape;
    private boolean num4;
    private boolean num4Tape;
    private boolean num5;
    private boolean num5Tape;
    private boolean num6;
    private boolean num6Tape;
    private boolean num7;
    private boolean num7Tape;
    private boolean num8;
    private boolean num8Tape;
    private boolean num9;
    private boolean num9Tape;

    // Constructeur //

    /**
     * Crée un clavier et initialise tous les attributs à faux pour touches relâchés.
     */
    public Clavier () {

	gauche = gaucheTape = droite = droiteTape = false;
	haut = hautTape = bas = basTape = false;

	espace = espaceTape = entree = entreeTape = echap = echapTape = false;

	a = b = c = d = e = f = g = h = i = j = k = l = m = n = o = p = q = r = s = t = u = v = w = x = y = z = false;
	aTape = bTape = cTape = dTape = eTape = fTape = gTape = hTape = iTape = jTape = kTape = lTape = mTape = nTape = oTape = pTape = qTape = rTape = sTape = tTape = uTape = vTape = wTape = xTape = yTape = zTape = false;
	numpad0 = numpad1 = numpad2 = numpad3 = numpad4 = numpad5 = numpad6 = numpad7 = numpad8 = numpad9 = false;
	numpad0Tape = numpad1Tape = numpad2Tape = numpad3Tape = numpad4Tape = numpad5Tape = numpad6Tape = numpad7Tape = numpad8Tape = numpad9Tape = false;

	num0 = num1 = num2 = num3 = num4 = num5 = num6 = num7 = num8 = num9 = false;
	num0Tape = num1Tape = num2Tape = num3Tape = num4Tape = num5Tape = num6Tape = num7Tape = num8Tape = num9Tape = false;
	
    }

    // Accesseurs //

    // Getter //

    /**
     * Permet de savoir si la touche "flèche gauche" est appuyée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "flèche gauche" : vrai pour appuyée, faux sinon.
     * @see #getGaucheEnfoncee() getGaucheEnfoncee()
     */
    @Deprecated
    public boolean getGauche () {

	return gauche;
    }

    /**
     * Permet de savoir si la touche "flèche gauche" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "flèche gauche" : vrai pour enfoncée, faux sinon.
     */
    public boolean getGaucheEnfoncee () {

	return gauche;
    }

    /**
     * Permet de savoir si la touche "flèche gauche" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "flèche gauche tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getGaucheTape () {
	boolean aRetourner=gaucheTape;
	gaucheTape=false;
	return aRetourner;
    }

    /**
     * Permet de savoir si la touche "flèche droite" est appuyée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "flèche droite" : vrai pour appuyée, faux sinon.
     * @see #getDroiteEnfoncee() getDroiteEnfoncee()
     */
    @Deprecated
    public boolean getDroite () {

	return droite;
    }

    /**
     * Permet de savoir si la touche "flèche droite" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "flèche droite" : vrai pour enfoncée, faux sinon.
     */
    public boolean getDroiteEnfoncee () {

	return droite;
    }

    /**
     * Permet de savoir si la touche "flèche droite" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "flèche droite tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getDroiteTape () {
	boolean aRetourner=droiteTape;
	droiteTape=false;
	return aRetourner;
    }

    /**
     * Permet de savoir si la touche "flèche haut" est appuyée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "flèche haut" : vrai pour appuyée, faux sinon.
     * @see #getHautEnfoncee() getHautEnfoncee()
     */
    @Deprecated
    public boolean getHaut () {

	return haut;
    }

    /**
     * Permet de savoir si la touche "flèche haut" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "flèche haut" : vrai pour enfoncée, faux sinon.
     */
    public boolean getHautEnfoncee () {

	return haut;
    }

    /**
     * Permet de savoir si la touche "flèche haut" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "flèche haut tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getHautTape () {
	boolean aRetourner=hautTape;
	hautTape=false;
	return aRetourner;
    }

    /**
     * Permet de savoir si la touche "flèche bas" est appuyée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "flèche bas" : vrai pour appuyée, faux sinon.
     * @see #getBasEnfoncee() getBasEnfoncee()
     */
    @Deprecated
    public boolean getBas () {

	return bas;
    }

    /**
     * Permet de savoir si la touche "flèche bas" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "flèche bas" : vrai pour enfoncée, faux sinon.
     */
    public boolean getBasEnfoncee () {

	return bas;
    }

    /**
     * Permet de savoir si la touche "flèche bas" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "flèche bas tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getBasTape () {
	boolean aRetourner=basTape;
	basTape=false;
	return aRetourner;
    }

    /**
     * Permet de savoir si la touche "espace" est appuyée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "espace" : vrai pour appuyée, faux sinon.
     * @see #getEspaceEnfoncee() getEspaceEnfoncee()
     */
    @Deprecated
    public boolean getEspace () {

	return espace;
    }

    /**
     * Permet de savoir si la touche "espace" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "espace" : vrai pour enfoncée, faux sinon.
     */
    public boolean getEspaceEnfoncee () {

	return espace;
    }

    /**
     * Permet de savoir si la touche "espace" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "espace tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getEspaceTape () {
	boolean aRetourner=espaceTape;
	espaceTape=false;
	return aRetourner;
    }

    /**
     * Permet de savoir si la touche "entree" est appuyée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "entree" : vrai pour appuyée, faux sinon.
     * @see #getEntreeEnfoncee() getEntreeEnfoncee()
     */
    @Deprecated
    public boolean getEntree () {

	return entree;
    }

    /**
     * Permet de savoir si la touche "entree" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "entree" : vrai pour enfoncée, faux sinon.
     */
    public boolean getEntreeEnfoncee () {

	return entree;
    }

    /**
     * Permet de savoir si la touche "entree" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "entree tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getEntreeTape () {
	boolean aRetourner=entreeTape;
	entreeTape=false;
	return aRetourner;
    }

    /**
     * Permet de savoir si la touche "echap" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "echap" : vrai pour enfoncée, faux sinon.
     */
    public boolean getEchapEnfoncee () {

	return echap;
    }

    /**
     * Permet de savoir si la touche "echap" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "echap tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getEchapTape () {
	boolean aRetourner=echapTape;
	echapTape=false;
	return aRetourner;
    }

    /**
     * Permet de savoir si la touche "a" est appuyée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "a" : vrai pour appuyée, faux sinon.
     * @see #getAEnfoncee() getAEnfoncee()
     */
    @Deprecated
    public boolean getA () {

	return a;
    }

    /**
     * Permet de savoir si la touche "a" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "a" : vrai pour enfoncée, faux sinon.
     */
    public boolean getAEnfoncee () {

	return a;
    }

    /**
     * Permet de savoir si la touche "a" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "a tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getATape () {
	boolean aRetourner=aTape;
	aTape=false;
	return aRetourner;
    }

    /**
     * Permet de savoir si la touche "b" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "b" : vrai pour enfoncée, faux sinon.
     */
    public boolean getBEnfoncee () {

	return b;
    }

    /**
     * Permet de savoir si la touche "b" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "b tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getBTape () {
	boolean aRetourner=bTape;
	bTape=false;
	return aRetourner;
    }

    /**
     * Permet de savoir si la touche "z" est appuyée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "z" : vrai pour appuyée, faux sinon.
     * @see #getZEnfoncee() getZEnfoncee()
     */
    @Deprecated
    public boolean getZ () {

	return z;
    }

    /**
     * Permet de savoir si la touche "z" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "z" : vrai pour enfoncée, faux sinon.
     */
    public boolean getZEnfoncee () {

	return z;
    }

    /**
     * Permet de savoir si la touche "z" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "z tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getZTape () {
	boolean aRetourner=zTape;
	zTape=false;
	return aRetourner;
    }

    /**
     * Permet de savoir si la touche "e" est appuyée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "e" : vrai pour appuyée, faux sinon.
     * @see #getEEnfoncee() getEEnfoncee()
     */
    @Deprecated
    public boolean getE () {

	return e;
    }

    /**
     * Permet de savoir si la touche "e" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "e" : vrai pour enfoncée, faux sinon.
     */
    public boolean getEEnfoncee () {

	return e;
    }

    /**
     * Permet de savoir si la touche "e" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "e tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getETape () {
	boolean aRetourner=eTape;
	eTape=false;
	return aRetourner;
    }

    /**
     * Permet de savoir si la touche "q" est appuyée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "q" : vrai pour appuyée, faux sinon.
     * @see #getQEnfoncee() getQEnfoncee()
     */
    @Deprecated
    public boolean getQ () {

	return q;
    }

    /**
     * Permet de savoir si la touche "q" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "q" : vrai pour enfoncée, faux sinon.
     */
    public boolean getQEnfoncee () {

	return q;
    }

    /**
     * Permet de savoir si la touche "q" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "q tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getQTape () {
	boolean aRetourner=qTape;
	qTape=false;
	return aRetourner;
    }

    /**
     * Permet de savoir si la touche "s" est appuyée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "s" : vrai pour appuyée, faux sinon.
     * @see #getSEnfoncee() getSEnfoncee()
     */
    @Deprecated
    public boolean getS () {

	return s;
    }

    /**
     * Permet de savoir si la touche "s" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "s" : vrai pour enfoncée, faux sinon.
     */
    public boolean getSEnfoncee () {

	return s;
    }

    /**
     * Permet de savoir si la touche "s" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "s tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getSTape () {
	boolean aRetourner=sTape;
	sTape=false;
	return aRetourner;
    }

    /**
     * Permet de savoir si la touche "d" est appuyée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "d" : vrai pour appuyée, faux sinon.
     * @see #getDEnfoncee() getDEnfoncee()
     */
    @Deprecated
    public boolean getD () {

	return d;
    }

    /**
     * Permet de savoir si la touche "d" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "d" : vrai pour enfoncée, faux sinon.
     */
    public boolean getDEnfoncee () {

	return d;
    }

    /**
     * Permet de savoir si la touche "d" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "d tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getDTape () {
	boolean aRetourner=dTape;
	dTape=false;
	return aRetourner;
    }

    /**
     * Permet de savoir si la touche "f" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "f" : vrai pour enfoncée, faux sinon.
     */
    public boolean getFEnfoncee () {

	return f;
    }

    /**
     * Permet de savoir si la touche "f" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "f tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getFTape () {
	boolean aRetourner=fTape;
	fTape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "g" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "g" : vrai pour enfoncée, faux sinon.
     */
    public boolean getGEnfoncee () {

	return g;
    }


    /**
     * Permet de savoir si la touche "g" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "g tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getGTape () {
	boolean aRetourner=gTape;
	gTape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "h" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "h" : vrai pour enfoncée, faux sinon.
     */
    public boolean getHEnfoncee () {

	return h;
    }

    /**
     * Permet de savoir si la touche "h" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "h tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getHTape () {
	boolean aRetourner=hTape;
	hTape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "i" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "i" : vrai pour enfoncée, faux sinon.
     */
    public boolean getIEnfoncee () {

	return i;
    }


    /**
     * Permet de savoir si la touche "i" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "i tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getITape () {
	boolean aRetourner=iTape;
	iTape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "j" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "j" : vrai pour enfoncée, faux sinon.
     */
    public boolean getJEnfoncee () {

	return j;
    }


    /**
     * Permet de savoir si la touche "j" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "j tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getJTape () {
	boolean aRetourner=jTape;
	jTape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "k" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "k" : vrai pour enfoncée, faux sinon.
     */
    public boolean getKEnfoncee () {
    	
	return k;
    }

    /**
     * Permet de savoir si la touche "k" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "k tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getKTape () {
	boolean aRetourner=kTape;
	kTape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "l" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "l" : vrai pour enfoncée, faux sinon.
     */
    public boolean getLEnfoncee () {

	return l;
    }

    /**
     * Permet de savoir si la touche "l" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "l tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getLTape () {
	boolean aRetourner=lTape;
	lTape=false;
	return aRetourner;
    }

    /**
     * Permet de savoir si la touche "m" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "m tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getMTape () {
	boolean aRetourner=mTape;
	mTape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "m" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "m" : vrai pour enfoncée, faux sinon.
     */
    public boolean getMEnfoncee () {

	return m;
    }


    /**
     * Permet de savoir si la touche "n" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "n" : vrai pour enfoncée, faux sinon.
     */
    public boolean getNEnfoncee () {

	return n;
    }

    /**
     * Permet de savoir si la touche "n" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "n tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getNTape () {
	boolean aRetourner=nTape;
	nTape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "o" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "o" : vrai pour enfoncée, faux sinon.
     */
    public boolean getOEnfoncee () {

	return o;
    }

    /**
     * Permet de savoir si la touche "o" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "o tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getOTape () {
	boolean aRetourner=oTape;
	oTape=false;
	return aRetourner;
    }

    /**
     * Permet de savoir si la touche "p" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "p tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getPTape () {
	boolean aRetourner=pTape;
	pTape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "p" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "p" : vrai pour enfoncée, faux sinon.
     */
    public boolean getPEnfoncee () {

	return p;
    }

    /**
     * Permet de savoir si la touche "r" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "r tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getRTape () {
	boolean aRetourner=rTape;
	rTape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "r" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "r" : vrai pour enfoncée, faux sinon.
     */
    public boolean getREnfoncee () {

	return r;
    }

    /**
     * Permet de savoir si la touche "t" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "t tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getTTape () {
	boolean aRetourner=tTape;
	tTape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "t" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "t" : vrai pour enfoncée, faux sinon.
     */
    public boolean getTEnfoncee () {

	return t;
    }

    /**
     * Permet de savoir si la touche "u" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "u tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getUTape () {
	boolean aRetourner=uTape;
	uTape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "u" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "u" : vrai pour enfoncée, faux sinon.
     */
    public boolean getUEnfoncee () {

	return u;
    }

    /**
     * Permet de savoir si la touche "v" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "v tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getVTape () {
	boolean aRetourner=vTape;
	vTape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "v" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "v" : vrai pour enfoncée, faux sinon.
     */
    public boolean getVEnfoncee () {

	return v;
    }

    /**
     * Permet de savoir si la touche "w" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "w tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getWTape () {
	boolean aRetourner=wTape;
	wTape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "w" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "w" : vrai pour enfoncée, faux sinon.
     */
    public boolean getWEnfoncee () {

	return w;
    }

    /**
     * Permet de savoir si la touche "x" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "x tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getXTape () {
	boolean aRetourner=xTape;
	xTape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "x" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "x" : vrai pour enfoncée, faux sinon.
     */
    public boolean getXEnfoncee () {

	return x;
    }

    /**
     * Permet de savoir si la touche "y" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "y tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getYTape () {
	boolean aRetourner=yTape;
	yTape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "y" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "y" : vrai pour enfoncée, faux sinon.
     */
    public boolean getYEnfoncee () {

	return y;
    }

    /**
     * Permet de savoir si la touche "c" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "c tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getCTape () {
	boolean aRetourner=cTape;
	cTape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "c" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "c" : vrai pour enfoncée, faux sinon.
     */
    public boolean getCEnfoncee () {

	return c;
    }


    /**
     * Permet de savoir si la touche "numpad0" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "numpad0 tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getNumpad0Tape () {
	boolean aRetourner=numpad0Tape;
	numpad0Tape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "numpad0" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "numpad0" : vrai pour enfoncée, faux sinon.
     */
    public boolean getNumpad0Enfoncee () {
    	
	return numpad0;
    }

    /**
     * Permet de savoir si la touche "numpad1" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "numpad1 tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getNumpad1Tape () {
	boolean aRetourner=numpad1Tape;
	numpad1Tape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "numpad1" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "numpad1" : vrai pour enfoncée, faux sinon.
     */
    public boolean getNumpad1Enfoncee () {
    	
	return numpad1;
    }

    /**
     * Permet de savoir si la touche "numpad2" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "numpad2 tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getNumpad2Tape () {
	boolean aRetourner=numpad2Tape;
	numpad2Tape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "numpad2" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "numpad2" : vrai pour enfoncée, faux sinon.
     */
    public boolean getNumpad2Enfoncee () {
    	
	return numpad2;
    }

    /**
     * Permet de savoir si la touche "numpad3" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "numpad3 tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getNumpad3Tape () {
	boolean aRetourner=numpad3Tape;
	numpad3Tape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "numpad3" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "numpad3" : vrai pour enfoncée, faux sinon.
     */
    public boolean getNumpad3Enfoncee () {
    	
	return numpad3;
    }

    /**
     * Permet de savoir si la touche "numpad4" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "numpad4 tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getNumpad4Tape () {
	boolean aRetourner=numpad4Tape;
	numpad4Tape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "numpad4" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "numpad4" : vrai pour enfoncée, faux sinon.
     */
    public boolean getNumpad4Enfoncee () {
    	
	return numpad4;
    }

    /**
     * Permet de savoir si la touche "numpad5" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "numpad5 tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getNumpad5Tape () {
	boolean aRetourner=numpad5Tape;
	numpad5Tape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "numpad5" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "numpad5" : vrai pour enfoncée, faux sinon.
     */
    public boolean getNumpad5Enfoncee () {
    	
	return numpad5;
    }

    /**
     * Permet de savoir si la touche "numpad6" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "numpad6 tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getNumpad6Tape () {
	boolean aRetourner=numpad6Tape;
	numpad6Tape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "numpad6" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "numpad6" : vrai pour enfoncée, faux sinon.
     */
    public boolean getNumpad6Enfoncee () {
    	
	return numpad6;
    }

    /**
     * Permet de savoir si la touche "numpad7" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "numpad7 tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getNumpad7Tape () {
	boolean aRetourner=numpad7Tape;
	numpad7Tape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "numpad7" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "numpad7" : vrai pour enfoncée, faux sinon.
     */
    public boolean getNumpad7Enfoncee () {
    	
	return numpad7;
    }

    /**
     * Permet de savoir si la touche "numpad8" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "numpad8 tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getNumpad8Tape () {
	boolean aRetourner=numpad8Tape;
	numpad8Tape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "numpad8" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "numpad8" : vrai pour enfoncée, faux sinon.
     */
    public boolean getNumpad8Enfoncee () {
    	
	return numpad8;
    }

    /**
     * Permet de savoir si la touche "numpad9" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "numpad9 tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getNumpad9Tape () {
	boolean aRetourner=numpad9Tape;
	numpad9Tape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "numpad9" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "numpad9" : vrai pour enfoncée, faux sinon.
     */
    public boolean getNumpad9Enfoncee () {
    	
	return numpad9;
    }


    /**
     * Permet de savoir si la touche "numpad0" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "numpad0 tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getNum0Tape () {
	boolean aRetourner=num0Tape;
	num0Tape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "num0" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "num0" : vrai pour enfoncée, faux sinon.
     */
    public boolean getNum0Enfoncee () {
    	
	return num0;
    }

    /**
     * Permet de savoir si la touche "num1" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "num1 tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getNum1Tape () {
	boolean aRetourner=num1Tape;
	num1Tape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "num1" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "num1" : vrai pour enfoncée, faux sinon.
     */
    public boolean getNum1Enfoncee () {
    	
	return num1;
    }

    /**
     * Permet de savoir si la touche "num2" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "num2 tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getNum2Tape () {
	boolean aRetourner=num2Tape;
	num2Tape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "num2" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "num2" : vrai pour enfoncée, faux sinon.
     */
    public boolean getNum2Enfoncee () {
    	
	return num2;
    }

    /**
     * Permet de savoir si la touche "num3" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "num3 tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getNum3Tape () {
	boolean aRetourner=num3Tape;
	num3Tape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "num3" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "num3" : vrai pour enfoncée, faux sinon.
     */
    public boolean getNum3Enfoncee () {
    	
	return num3;
    }

    /**
     * Permet de savoir si la touche "num4" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "num4 tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getNum4Tape () {
	boolean aRetourner=num4Tape;
	num4Tape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "num4" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "num4" : vrai pour enfoncée, faux sinon.
     */
    public boolean getNum4Enfoncee () {
    	
	return num4;
    }

    /**
     * Permet de savoir si la touche "num5" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "num5 tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getNum5Tape () {
	boolean aRetourner=num5Tape;
	num5Tape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "num5" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "num5" : vrai pour enfoncée, faux sinon.
     */
    public boolean getNum5Enfoncee () {
    	
	return num5;
    }

    /**
     * Permet de savoir si la touche "num6" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "num6 tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getNum6Tape () {
	boolean aRetourner=num6Tape;
	num6Tape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "num6" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "num6" : vrai pour enfoncée, faux sinon.
     */
    public boolean getNum6Enfoncee () {
    	
	return num6;
    }

    /**
     * Permet de savoir si la touche "num7" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "num7 tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getNum7Tape () {
	boolean aRetourner=num7Tape;
	num7Tape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "num7" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "num7" : vrai pour enfoncée, faux sinon.
     */
    public boolean getNum7Enfoncee () {
    	
	return num7;
    }

    /**
     * Permet de savoir si la touche "num8" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "num8 tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getNum8Tape () {
	boolean aRetourner=num8Tape;
	num8Tape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "num8" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "num8" : vrai pour enfoncée, faux sinon.
     */
    public boolean getNum8Enfoncee () {
    	
	return num8;
    }

    /**
     * Permet de savoir si la touche "num9" a été appuyée puis relâchée.
     * @return retourne la valeur du booléen correspondant à la touche "num9 tapée" : vrai pour tapée, faux sinon.
     */
    public boolean getNum9Tape () {
	boolean aRetourner=num9Tape;
	num9Tape=false;
	return aRetourner;
    }


    /**
     * Permet de savoir si la touche "num9" est enfoncée ou non.
     * @return retourne la valeur du booléen correspondant à la touche "num9" : vrai pour enfoncée, faux sinon.
     */
    public boolean getNum9Enfoncee () {
    	
	return num9;
    }


    
    

    // Setter //

    /**
     * Permet de simuler l'appui ou non sur la touche "flèche gauche".
     * @param gauche le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setGauche ( boolean gauche ) {

	this.gauche = gauche;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "flèche droite".
     * @param droite le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setDroite ( boolean droite ) {

	this.droite = droite;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "flèche haut".
     * @param haut le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setHaut( boolean haut ) {

	this.haut = haut;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "flèche bas".
     * @param bas le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setBas ( boolean bas ) {

	this.bas = bas;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "espace".
     * @param espace le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setEspace ( boolean espace ) {

	this.espace = espace;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "entree".
     * @param entree le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setEntree ( boolean entree ) {

	this.entree = entree;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "echap".
     * @param echap le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setEchap ( boolean echap ) {

	this.echap = echap;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "a".
     * @param a le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setA ( boolean a ) {

	this.a = a;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "z".
     * @param z le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setZ ( boolean z ) {

	this.z = z;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "e".
     * @param e le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setE ( boolean e ) {

	this.e = e;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "q".
     * @param q le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setQ ( boolean q ) {

	this.q = q;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "s".
     * @param s le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setS ( boolean s ) {

	this.s = s;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "d".
     * @param d le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setD ( boolean d ) {

	this.d = d;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "b".
     * @param b le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setB(boolean b) {
	this.b = b;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "c".
     * @param c le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setC(boolean c) {
	this.c = c;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "f".
     * @param f le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setF(boolean f) {
	this.f = f;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "g".
     * @param g le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setG(boolean g) {
	this.g = g;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "h".
     * @param h le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setH(boolean h) {
	this.h = h;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "i".
     * @param i le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setI(boolean i) {
	this.i = i;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "j".
     * @param j le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setJ(boolean j) {
	this.j = j;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "k".
     * @param k le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setK(boolean k) {
	this.k = k;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "l".
     * @param l le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setL(boolean l) {
	this.l = l;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "m".
     * @param m le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setM(boolean m) {
	this.m = m;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "n".
     * @param n le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setN(boolean n) {
	this.n = n;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "o".
     * @param o le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setO(boolean o) {
	this.o = o;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "p".
     * @param p le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setP(boolean p) {
	this.p = p;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "r".
     * @param r le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setR(boolean r) {
	this.r = r;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "t".
     * @param t le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setT(boolean t) {
	this.t = t;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "u".
     * @param u le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setU(boolean u) {
	this.u = u;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "v".
     * @param v le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setV(boolean v) {
	this.v = v;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "w".
     * @param w le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setW(boolean w) {
	this.w = w;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "x".
     * @param x le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setX(boolean x) {
	this.x = x;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "y".
     * @param y le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setY(boolean y) {
	this.y = y;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "numpad0".
     * @param numpad0 le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setNumpad0(boolean numpad0) {
	this.numpad0 = numpad0;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "numpad1".
     * @param numpad1 le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setNumpad1(boolean numpad1) {
	this.numpad1 = numpad1;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "numpad2".
     * @param numpad2 le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setNumpad2(boolean numpad2) {
	this.numpad2 = numpad2;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "numpad3".
     * @param numpad3 le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setNumpad3(boolean numpad3) {
	this.numpad3 = numpad3;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "numpad4".
     * @param numpad4 le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setNumpad4(boolean numpad4) {
	this.numpad4 = numpad4;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "numpad5".
     * @param numpad5 le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setNumpad5(boolean numpad5) {
	this.numpad5 = numpad5;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "numpad6".
     * @param numpad6 le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setNumpad6(boolean numpad6) {
	this.numpad6 = numpad6;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "numpad7".
     * @param numpad7 le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setNumpad7(boolean numpad7) {
	this.numpad7 = numpad7;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "numpad8".
     * @param numpad8 le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setNumpad8(boolean numpad8) {
	this.numpad8 = numpad8;
    }

    /**
     * Permet de simuler l'appui ou non sur la touche "numpad9".
     * @param numpad9 le parametre doit prendre la valeur "true" si on veut simuler l'appui sur la touche, "false" dans le cas contraire.
     */
    public void setNumpad9(boolean numpad9) {
	this.numpad9 = numpad9;
    }
    
    

    // Méthodes //

    /**
     * Méthode permettant la reinitialisation du clavier. Reinitialisation de tous les événements.
     */
    public void reinitialisation(){

	gauche = gaucheTape = droite = droiteTape = false;
	haut = hautTape = bas = basTape = false;

	espace = espaceTape = entree = entreeTape = echap = echapTape = false;

	a = b = c = d = e = f = g = h = i = j = k = l = m = n = o = p = q = r = s = t = u = v = w = x = y = z = false;
	aTape = bTape = cTape = dTape = eTape = fTape = gTape = hTape = iTape = jTape = kTape = lTape = mTape = nTape = oTape = pTape = qTape = rTape = sTape = tTape = uTape = vTape = wTape = xTape = yTape = zTape = false;
	numpad0 = numpad1 = numpad2 = numpad3 = numpad4 = numpad5 = numpad6 = numpad7 = numpad8 = numpad9 = false;
	numpad0Tape = numpad1Tape = numpad2Tape = numpad3Tape = numpad4Tape = numpad5Tape = numpad6Tape = numpad7Tape = numpad8Tape = numpad9Tape = false;
	num0 = num1 = num2 = num3 = num4 = num5 = num6 = num7 = num8 = num9 = false;
	num0Tape = num1Tape = num2Tape = num3Tape = num4Tape = num5Tape = num6Tape = num7Tape = num8Tape = num9Tape = false;
    }

    /**
     * Implémentation de la méthode KeyReleased - méthode appelée automatiquement lors d'un événement clavier.
     * <br /><br />
     * Elle permet de mettre à jour les valeurs des attributs en fonction des interactions au clavier.<br />
     * Ici, si une touche est relâchée, l'attribut gérant cette touche est mis à jour.<br />
     * Pour la gestion sur l'appui des touches, voir la méthode keyPressed ( KeyEvent k ).
     * @param key un événement clavier.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyEvent.html" target="_blank">KeyEvent</a>
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyListener.html" target="_blank">KeyListener</a>
     * @see #keyPressed keyPressed ( KeyEvent k )
     */
    @Override
    public void keyReleased ( KeyEvent key ) {

	switch (key.getKeyCode()) {
		
	case KeyEvent.VK_ESCAPE:
	    echap = false;
	    echapTape = true;
	    break;

	case KeyEvent.VK_NUMPAD0:
	    numpad0 = false;
	    numpad0Tape = true;
	    break;

	case KeyEvent.VK_NUMPAD1:
	    numpad1 = false;
	    numpad1Tape = true;
	    break;

	case KeyEvent.VK_NUMPAD2:
	    numpad2 = false;
	    numpad2Tape = true;
	    break;

	case KeyEvent.VK_NUMPAD3:
	    numpad3 = false;
	    numpad3Tape = true;
	    break;

	case KeyEvent.VK_NUMPAD4:
	    numpad4 = false;
	    numpad4Tape = true;
	    break;

	case KeyEvent.VK_NUMPAD5:
	    numpad5 = false;
	    numpad5Tape = true;
	    break;

	case KeyEvent.VK_NUMPAD6:
	    numpad6 = false;
	    numpad6Tape = true;
	    break;

	case KeyEvent.VK_NUMPAD7:
	    numpad7 = false;
	    numpad7Tape = true;
	    break;

	case KeyEvent.VK_NUMPAD8:
	    numpad8 = false;
	    numpad8Tape = true;
	    break;

	case KeyEvent.VK_NUMPAD9:
	    numpad9 = false;
	    numpad9Tape = true;
	    break;

	case KeyEvent.VK_0:
	    num0 = false;
	    num0Tape = true;
	    break;

	case KeyEvent.VK_1:
	    num1 = false;
	    num1Tape = true;
	    break;

	case KeyEvent.VK_2:
	    num2 = false;
	    num2Tape = true;
	    break;

	case KeyEvent.VK_3:
	    num3 = false;
	    num3Tape = true;
	    break;

	case KeyEvent.VK_4:
	    num4 = false;
	    num4Tape = true;
	    break;

	case KeyEvent.VK_5:
	    num5 = false;
	    num5Tape = true;
	    break;

	case KeyEvent.VK_6:
	    num6 = false;
	    num6Tape = true;
	    break;

	case KeyEvent.VK_7:
	    num7 = false;
	    num7Tape = true;
	    break;

	case KeyEvent.VK_8:
	    num8 = false;
	    num8Tape = true;
	    break;

	case KeyEvent.VK_9:
	    num9 = false;
	    num9Tape = true;
	    break;

	case KeyEvent.VK_LEFT:
	    gauche = false;
	    gaucheTape = true;
	    break;

	case KeyEvent.VK_RIGHT:
	    droite = false;
	    droiteTape = true;
	    break;

	case KeyEvent.VK_UP:
	    haut = false;
	    hautTape = true;
	    break;

	case KeyEvent.VK_DOWN:
	    bas = false;
	    basTape = true;
	    break;

	case KeyEvent.VK_SPACE:
	    espace = false;
	    espaceTape = true;
	    break;

	case KeyEvent.VK_ENTER:
	    entree = false;
	    entreeTape = true;
	    break;

	case KeyEvent.VK_A:
	    a = false;
	    aTape = true;
	    break;

	case KeyEvent.VK_B:
	    b = false;
	    bTape = true;
	    break;

	case KeyEvent.VK_C:
	    c = false;
	    cTape = true;
	    break;

	case KeyEvent.VK_D:
	    d = false;
	    dTape = true;
	    break;

	case KeyEvent.VK_E:
	    e = false;
	    eTape = true;
	    break;

	case KeyEvent.VK_F:
	    f = false;
	    fTape = true;
	    break;

	case KeyEvent.VK_G:
	    g = false;
	    gTape = true;
	    break;

	case KeyEvent.VK_H:
	    h = false;
	    hTape = true;
	    break;

	case KeyEvent.VK_I:
	    i = false;
	    iTape = true;
	    break;

	case KeyEvent.VK_J:
	    j = false;
	    jTape = true;
	    break;

	case KeyEvent.VK_K:
	    k = false;
	    kTape = true;
	    break;

	case KeyEvent.VK_L:
	    l = false;
	    lTape = true;
	    break;

	case KeyEvent.VK_M:
	    m = false;
	    mTape = true;
	    break;

	case KeyEvent.VK_N:
	    n = false;
	    nTape = true;
	    break;

	case KeyEvent.VK_O:
	    o = false;
	    oTape = true;
	    break;

	case KeyEvent.VK_P:
	    p = false;
	    pTape = true;
	    break;

	case KeyEvent.VK_Q:
	    q = false;
	    qTape = true;
	    break;

	case KeyEvent.VK_R:
	    r = false;
	    rTape = true;
	    break;

	case KeyEvent.VK_S:
	    s = false;
	    sTape = true;
	    break;

	case KeyEvent.VK_T:
	    t = false;
	    tTape = true;
	    break;

	case KeyEvent.VK_U:
	    u = false;
	    uTape = true;
	    break;

	case KeyEvent.VK_V:
	    v = false;
	    vTape = true;
	    break;

	case KeyEvent.VK_W:
	    w = false;
	    wTape = true;
	    break;

	case KeyEvent.VK_X:
	    x = false;
	    xTape = true;
	    break;

	case KeyEvent.VK_Y:
	    y = false;
	    yTape = true;
	    break;

	case KeyEvent.VK_Z:
	    z = false;
	    zTape = true;
	    break;
	}
    }

    /**
     * Implémentation de la méthode KeyTyped - méthode appelee automatiquement lors d'un événement clavier.
     * <br /><br />
     * Cette méthode doit etre implémentee mais est inutile dans ce moteur.
     */
    @Override
    public void keyTyped ( KeyEvent k ) {
    }

    /**
     * Implémentation de la méthode KeyPressed - méthode appelee automatiquement lors d'un événement clavier.
     * <br /><br />
     * Elle permet de mettre a jour les valeurs des attributs en fonction des interactions au clavier.<br />
     * Ici, si une touche est pressee, l'attribut gerant cette touche est mis a jour.<br />
     * Pour la gestion du relachement des touches, voir la méthode keyReleased ( KeyEvent k ).
     * @param key un événement clavier.
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyEvent.html" target="_blank">KeyEvent</a>
     * @see <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/event/KeyListener.html" target="_blank">KeyListener</a>
     * @see #keyReleased keyReleased ( KeyEvent k )
     */
    @Override
    public void keyPressed(KeyEvent key) {

	switch (key.getKeyCode()) {
		
	case KeyEvent.VK_ESCAPE:
	    echap = true;
	    break;

	case KeyEvent.VK_NUMPAD0:
	    numpad0 = true;
	    break;

	case KeyEvent.VK_NUMPAD1:
	    numpad1 = true;
	    break;

	case KeyEvent.VK_NUMPAD2:
	    numpad2 = true;
	    break;

	case KeyEvent.VK_NUMPAD3:
	    numpad3 = true;
	    break;

	case KeyEvent.VK_NUMPAD4:
	    numpad4 = true;
	    break;

	case KeyEvent.VK_NUMPAD5:
	    numpad5 = true;
	    break;

	case KeyEvent.VK_NUMPAD6:
	    numpad6 = true;
	    break;

	case KeyEvent.VK_NUMPAD7:
	    numpad7 = true;
	    break;

	case KeyEvent.VK_NUMPAD8:
	    numpad8 = true;
	    break;

	case KeyEvent.VK_NUMPAD9:
	    numpad9 = true;
	    break;

	case KeyEvent.VK_LEFT:
	    gauche = true;
	    break;

	case KeyEvent.VK_RIGHT:
	    droite = true;
	    break;

	case KeyEvent.VK_UP:
	    haut = true;
	    break;

	case KeyEvent.VK_DOWN:
	    bas = true;
	    break;

	case KeyEvent.VK_SPACE:
	    espace = true;
	    break;

	case KeyEvent.VK_ENTER:
	    entree = true;
	    break;

	case KeyEvent.VK_A:
	    a = true;
	    break;

	case KeyEvent.VK_B:
	    b = true;
	    break;

	case KeyEvent.VK_C:
	    c = true;
	    break;

	case KeyEvent.VK_D:
	    d = true;
	    break;

	case KeyEvent.VK_E:
	    e = true;
	    break;

	case KeyEvent.VK_F:
	    f = true;
	    break;

	case KeyEvent.VK_G:
	    g = true;
	    break;

	case KeyEvent.VK_H:
	    h = true;
	    break;

	case KeyEvent.VK_I:
	    i = true;
	    break;

	case KeyEvent.VK_J:
	    j = true;
	    break;

	case KeyEvent.VK_K:
	    k = true;
	    break;

	case KeyEvent.VK_L:
	    l = true;
	    break;

	case KeyEvent.VK_M:
	    m = true;
	    break;

	case KeyEvent.VK_N:
	    n = true;
	    break;

	case KeyEvent.VK_O:
	    o = true;
	    break;

	case KeyEvent.VK_P:
	    p = true;
	    break;

	case KeyEvent.VK_Q:
	    q = true;
	    break;

	case KeyEvent.VK_R:
	    r = true;
	    break;

	case KeyEvent.VK_S:
	    s = true;
	    break;

	case KeyEvent.VK_T:
	    t = true;
	    break;

	case KeyEvent.VK_U:
	    u = true;
	    break;

	case KeyEvent.VK_V:
	    v = true;
	    break;

	case KeyEvent.VK_W:
	    w = true;
	    break;

	case KeyEvent.VK_X:
	    x = true;
	    break;

	case KeyEvent.VK_Y:
	    y = true;
	    break;

	case KeyEvent.VK_Z:
	    z = true;
	    break;
	}
    }
}
