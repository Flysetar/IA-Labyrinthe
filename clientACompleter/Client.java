import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.ArrayList;

public class Client {

	public static void main(String[] args) {
		if (args.length != 3) {

			System.out.println("Il faut 3 arguments : l'adresse ip du serveur, le port et le nom d'équipe.");
			System.exit(0);
		}
		
		Random rand = new Random();
		
		String msgPrec = new String();
		String msgBonus = new String();
		boolean depart = false;
		int compteur = 0;

		try {
			Socket s = new Socket(args[0], Integer.parseInt(args[1]));
			boolean fin = false;

			// ecriture
			OutputStream os = s.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			// lecture
			InputStream is = s.getInputStream();
			BufferedReader bf = new BufferedReader(new InputStreamReader(is));

			pw.println(args[2]);
			pw.flush();

			String numJoueur = bf.readLine();

			System.out.println("Numero de joueur : " + numJoueur);

			while (!fin) {
				String msg = bf.readLine();

				System.out.println("Message recu : " + msg);
				System.out.println();
				fin = msg.equals("FIN");

				if (!fin) {

					/*-----------------------------------------------------------------------*/

					// Creation du labyrinthe en fonction des informations recues
					
					Labyrinthe laby = new Labyrinthe(msg);

					// Informations sur le joueur
					System.out.println("Je me trouve en : (" + laby.getJoueur(Integer.parseInt(numJoueur)).getPosX()
							+ "," + laby.getJoueur(Integer.parseInt(numJoueur)).getPosY() + ")");
					ArrayList<Integer> infosMoule = new ArrayList<Integer>();
					// Parcours du plateau pour trouver toutes les moules et
					// leur valeur
					for (int j = 0; j < laby.getTailleY(); j++)
						for (int i = 0; i < laby.getTailleX(); i++)
							if (laby.getXY(i, j).getType() == Case.MOULE) {
								infosMoule.add(i);
								infosMoule.add(j);
								infosMoule.add(laby.getXY(i, j).getPointRapporte());
							}

					// Affichage des informations sur les moules du plateau
					for (int i = 0; i < infosMoule.size() / 3; i++)
						System.out.println("Moule en (" + infosMoule.get(i * 3) + "," + infosMoule.get(i * 3 + 1)
								+ ") pour " + infosMoule.get(i * 3 + 2) + " points");

					// Je prépare le message suivant à envoyer au serveur :

					
					/***** Main Droite *****/

					if (depart == false) {
						depart = true;

						msg = "S";
						msgPrec = msg;

					}

					if (depart == true) {
						
						//Thread.sleep(50);
						
						if (msgPrec.equals("O") || msgPrec.equals("F-O")) {
							if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX(),
									laby.getJoueur(Integer.parseInt(numJoueur)).getPosY() - 1).getType() != Case.DUNE) {
								msg = "N";
								msgPrec = msg;

							} else if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX() - 1,
									laby.getJoueur(Integer.parseInt(numJoueur)).getPosY()).getType() != Case.DUNE && compteur == 0) {
								msg = "O";
								msgPrec = msg;

							} else if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX(),
									laby.getJoueur(Integer.parseInt(numJoueur)).getPosY() + 1).getType() != Case.DUNE) {
								msg = "S";
								msgPrec = msg;

							}else {
								msg = "E";
								msgPrec = msg;
							}
							
							if ( msgBonus.equals("F")){
								
								if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX(),
										laby.getJoueur(Integer.parseInt(numJoueur)).getPosY() - 2).getType() != Case.DUNE) {
										
										msg="F-N";
										msgPrec=msg;
										msgBonus="V";
								}
							
							else if	(laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX() - 2,
									laby.getJoueur(Integer.parseInt(numJoueur)).getPosY()).getType() != Case.DUNE) {
									
									msg="F-O";
									msgPrec=msg;
									msgBonus="V";
							}
							
							else if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX(),
								
									laby.getJoueur(Integer.parseInt(numJoueur)).getPosY() + 2).getType() != Case.DUNE) {
									
									msg="F-S";
									msgPrec=msg;
									msgBonus="V";
							}
							
							else if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX() + 2,
									laby.getJoueur(Integer.parseInt(numJoueur)).getPosY()).getType() != Case.DUNE) {
										
									msg="F-E";
									msgPrec=msg;
									msgBonus="V";
							}
								System.out.println("C'est toi : " + msgBonus);
							}
						} else if (msgPrec.equals("S") || msgPrec.equals("F-S")) {
							if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX() - 1,
									laby.getJoueur(Integer.parseInt(numJoueur)).getPosY()).getType() != Case.DUNE) {
								msg = "O";
								msgPrec = msg;

							} else if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX(),
									laby.getJoueur(Integer.parseInt(numJoueur)).getPosY() + 1).getType() != Case.DUNE) {
								msg = "S";
								msgPrec = msg;

							} else if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX() + 1,
									laby.getJoueur(Integer.parseInt(numJoueur)).getPosY()).getType() != Case.DUNE) {
								msg = "E";
								msgPrec = msg;
							} else {
								msg = "N";
								msgPrec = msg;
							}
							
							if ( msgBonus.equals("F")){
								
								if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX() - 2,
									laby.getJoueur(Integer.parseInt(numJoueur)).getPosY()).getType() != Case.DUNE) {
									
									msg="F-O";
									msgPrec=msg;
									msgBonus="V";
								}
							
							else if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX(),
								
									laby.getJoueur(Integer.parseInt(numJoueur)).getPosY() + 2).getType() != Case.DUNE) {
									
									msg="F-S";
									msgPrec=msg;
									msgBonus="V";
								}
							
							else if  (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX() + 2,
									laby.getJoueur(Integer.parseInt(numJoueur)).getPosY()).getType() != Case.DUNE) {
										
									msg="F-E";
									msgPrec=msg;
									msgBonus="V";
								}
							
							else if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX(),
									laby.getJoueur(Integer.parseInt(numJoueur)).getPosY() - 2).getType() != Case.DUNE) {
										
									msg="F-N";
									msgPrec=msg;
									msgBonus="V";
								}
							}
						
						} else if (msgPrec.equals("E") || msgPrec.equals("F-E")) {
							if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX(),
									laby.getJoueur(Integer.parseInt(numJoueur)).getPosY() + 1).getType() != Case.DUNE) {
								msg = "S";
								msgPrec = msg;

							} else if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX() + 1,
									laby.getJoueur(Integer.parseInt(numJoueur)).getPosY()).getType() != Case.DUNE) {
								msg = "E";
								msgPrec = msg;
								

							} else if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX(),
									laby.getJoueur(Integer.parseInt(numJoueur)).getPosY() - 1).getType() != Case.DUNE) {
								msg = "N";
								msgPrec = msg;

							} else {
								msg = "O";
								msgPrec = msg;
							}
							
							if ( msgBonus.equals("F")){
								
								if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX(),
								
									laby.getJoueur(Integer.parseInt(numJoueur)).getPosY() + 2).getType() != Case.DUNE) {
									
									msg="F-S";
									msgPrec=msg;
									msgBonus="V";
								}
							
							else if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX() + 2,
									laby.getJoueur(Integer.parseInt(numJoueur)).getPosY()).getType() != Case.DUNE) {
										
									msg="F-E";
									msgPrec=msg;
									msgBonus="V";
								}
							
							else if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX(),
									laby.getJoueur(Integer.parseInt(numJoueur)).getPosY() - 2).getType() != Case.DUNE) {
										
									msg="F-N";
									msgPrec=msg;
									msgBonus="V";
								}
							
							else if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX() - 2,
									laby.getJoueur(Integer.parseInt(numJoueur)).getPosY()).getType() != Case.DUNE) {
									
									msg="F-O";
									msgPrec=msg;
									msgBonus="V";
								}
							}
						} else if (msgPrec.equals("N") || msgPrec.equals("F-N")) {
							if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX() + 1,
									laby.getJoueur(Integer.parseInt(numJoueur)).getPosY()).getType() != Case.DUNE) {
								msg = "E";
								msgPrec = msg;

							} else if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX(),
									laby.getJoueur(Integer.parseInt(numJoueur)).getPosY() - 1).getType() != Case.DUNE) {
								msg = "N";
								msgPrec = msg;

							} else if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX() - 1,
									laby.getJoueur(Integer.parseInt(numJoueur)).getPosY()).getType() != Case.DUNE) {
								msg = "O";
								msgPrec = msg;

							} else {
								msg = "S";
								msgPrec = msg;
							}
							
							if ( msgBonus.equals("F")){
								
								if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX() + 2,
									laby.getJoueur(Integer.parseInt(numJoueur)).getPosY()).getType() != Case.DUNE) {
										
									msg="F-E";
									msgPrec=msg;
									msgBonus="V";
								}
							
							else if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX(),
									laby.getJoueur(Integer.parseInt(numJoueur)).getPosY() - 2).getType() != Case.DUNE) {
										
									msg="F-N";
									msgPrec=msg;
									msgBonus="V";
								}
							
							else if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX() - 2,
									laby.getJoueur(Integer.parseInt(numJoueur)).getPosY()).getType() != Case.DUNE) {
									
									msg="F-O";
									msgPrec=msg;
									msgBonus="V";
								}
							
							else if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX(),
								
									laby.getJoueur(Integer.parseInt(numJoueur)).getPosY() + 2).getType() != Case.DUNE) {
									
									msg="F-S";
									msgPrec=msg;
									msgBonus="V";
								}
							}
						}
					}
					
					System.out.println(msgPrec);
					
					
					
					// Biere en priorite

					if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX() - 1,
							laby.getJoueur(Integer.parseInt(numJoueur)).getPosY()).getType() == Case.BIERE) {
						msg = "O";
						msgPrec=msg;
					}
					if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX() + 1,
							laby.getJoueur(Integer.parseInt(numJoueur)).getPosY()).getType() == Case.BIERE) {
						msg = "E";
						msgPrec=msg;
					}
					if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX(),
							laby.getJoueur(Integer.parseInt(numJoueur)).getPosY() - 1).getType() == Case.BIERE) {
						msg = "N";
						msgPrec=msg;
					}
					if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX(),
							laby.getJoueur(Integer.parseInt(numJoueur)).getPosY() + 1).getType() == Case.BIERE) {
						msg = "S";
						msgPrec=msg;
					}
				

					// Frite en priorite

					if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX() - 1,
							laby.getJoueur(Integer.parseInt(numJoueur)).getPosY()).getType() == Case.FRITE) {
						msg = "O";
						msgPrec=msg;
						msgBonus="F";
						System.out.println("C'est toi : " + msgBonus);
					}
					else if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX() + 1,
							laby.getJoueur(Integer.parseInt(numJoueur)).getPosY()).getType() == Case.FRITE) {
						msg = "E";
						msgPrec=msg;
						msgBonus="F";
						System.out.println("C'est toi : " + msgBonus);
					}
					else if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX(),
							laby.getJoueur(Integer.parseInt(numJoueur)).getPosY() - 1).getType() == Case.FRITE) {
						msg = "N";
						msgPrec=msg;
						msgBonus="F";
						System.out.println("C'est toi : " + msgBonus);
					}
					else if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX(),
							laby.getJoueur(Integer.parseInt(numJoueur)).getPosY() + 1).getType() == Case.FRITE) {
						msg = "S";
						msgPrec=msg;
						msgBonus="F";
						System.out.println("C'est toi : " + msgBonus);
					}

					// Moule en priorite

					if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX() - 1,
							laby.getJoueur(Integer.parseInt(numJoueur)).getPosY()).getType() == Case.MOULE) {
						msg = "O";
						msgPrec=msg;
					}
					if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX() + 1,
							laby.getJoueur(Integer.parseInt(numJoueur)).getPosY()).getType() == Case.MOULE) {
						msg = "E";
						msgPrec=msg;
					}
					if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX(),
							laby.getJoueur(Integer.parseInt(numJoueur)).getPosY() - 1).getType() == Case.MOULE) {
						msg = "N";
						msgPrec=msg;
					}
					if (laby.getXY(laby.getJoueur(Integer.parseInt(numJoueur)).getPosX(),
							laby.getJoueur(Integer.parseInt(numJoueur)).getPosY() + 1).getType() == Case.MOULE) {
						msg = "S";
						msgPrec=msg;
					}
					
				

					/*-----------------------------------------------------------------------*/

					// Envoi du message au serveur.
					pw.println(msg);
					pw.flush();
				}

			}
			s.close();

		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}

}
