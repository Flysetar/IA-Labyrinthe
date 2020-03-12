/*********************************************************************/
/*                                                                   */
/* Copyright 2012-2017 Rémi Synave, Anthony Desitter,                */
/*                     Nicolas Dubrunfaut, Maxime Langa,             */
/*                     Guillaume Langa                               */
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

import java.awt.GraphicsDevice;

import java.awt.GraphicsEnvironment;

/**
 * Cette classe crée une fenêtre comportant une zone d'affichage aux dimensions précisées.
 * <br /><br />
 * L'origine, le point (0,0) se trouve en bas à gauche de la zone d'affichage.<br />
 * L'utilisateur peut définir un titre, une largeur et une hauteur à cette fenêtre.<br />
 * Par la suite, il pourra, au travers de cette classe, gérer un ensemble de formes affichés.
 * @author Equipe 2D, Rémi Synave
 * @version 2.8
 */
public class FenetrePleinEcran extends Fenetre {


    // Constructeur //

    /**
     * Construit une Fenetre ayant une zone d'affichage de la même résolution que l'écran.
     * @param ttitre Titre de la Fenetre.
     */
    
    public FenetrePleinEcran(String ttitre) {
	super(ttitre,GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDisplayMode().getWidth(),GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].getDisplayMode().getHeight());
	
	// définie une fenêtre "nue", sans barre de titre ni boutons
	this.dispose();
	this.setUndecorated(true);
		
	// force la fenêtre en plein écran
	GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0].setFullScreenWindow(this);
	this.setVisible (true);
    }
    

}
