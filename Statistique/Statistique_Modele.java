import javax.swing.*;
import java.awt.*;
import java.sql.*;

// java -cp "/export/documents/mariadb-client.jar:."
public class Statistique_Modele {

    private Connection connexion_ihm;
    private Connection connexion_boutalja;
    private PreparedStatement requete;
    private ResultSet resultat;
    private ResultSet resultat2;

    public Statistique_Modele() {
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            try{
                this.connexion_ihm = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/projetihm", "projetihm", "mhitejorp");
            } catch(SQLException e) {
                System.err.println("Erreur connexion ihm: "+e.getMessage());
            }
            try{
                this.connexion_boutalja = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/boutalja", "boutalja", "boutalja");
            } catch(SQLException e) {
                System.err.println("Erreur connexion boutalja: "+e.getMessage());
            }                 
        } catch(ClassNotFoundException e) {
            System.err.println("Pilote indisponible !");
        }
    }

    //Retourne reservation grace a la reference
    public int tauxOccupation(Date date) throws SQLException{
        int resultat;
        this.connexion_ihm = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/projetihm", "projetihm", "mhitejorp");
        this.connexion_boutalja = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/boutalja", "boutalja", "boutalja");
        this.requete=this.connexion_boutalja.prepareStatement("SELECT count(*) FROM Chambre_Reserve WHERE date=?");
        this.requete.setDate(1,date);
        this.resultat=this.requete.executeQuery();
        this.resultat.next();
        resultat=this.resultat.getInt(1);
        this.resultat.close();
        this.requete.close();
        this.connexion_ihm.close();
        this.connexion_boutalja.close();
        return resultat;
    }

    public void getIdReservation(int tab[], Date date) throws SQLException{
        int res,i=0;
        this.connexion_ihm = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/projetihm", "projetihm", "mhitejorp");
        this.connexion_boutalja = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/boutalja", "boutalja", "boutalja");
        this.requete=this.connexion_ihm.prepareStatement("SELECT id FROM Reservation WHERE debut=?");
        this.requete.setDate(1,date);
        this.resultat=this.requete.executeQuery();
        while(this.resultat.next()) {
        res=this.resultat.getInt(1);
        tab[i]=res;
        i++;
    }
        this.resultat.close();
        this.requete.close();
        this.connexion_ihm.close();
        this.connexion_boutalja.close();
    }

     public int nombreReservation(Date date) throws SQLException{
        int count_reservation;
        this.connexion_ihm = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/projetihm", "projetihm", "mhitejorp");
        this.connexion_boutalja = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/boutalja", "boutalja", "boutalja");
        this.requete=this.connexion_ihm.prepareStatement("SELECT count(*) FROM Reservation WHERE debut=?");
        this.requete.setDate(1,date);
        this.resultat=this.requete.executeQuery();
        this.resultat.next();
        count_reservation=this.resultat.getInt(1);
        this.resultat.close();
        this.requete.close();
        this.connexion_ihm.close();
        this.connexion_boutalja.close();
        return count_reservation;
    }

    public int nonPresence(int tab[], Date date) throws SQLException{
        int count_non_presence=0;
        int tmp=0;
        this.connexion_ihm = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/projetihm", "projetihm", "mhitejorp");
        this.connexion_boutalja = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/boutalja", "boutalja", "boutalja");
        this.requete=this.connexion_boutalja.prepareStatement("SELECT count(*) FROM Chambre_Reserve WHERE date=? AND id_reservation=?");
        for(int i=0; i<tab.length; i++) {
        
        this.requete.setDate(1,date);
         this.requete.setInt(2,tab[i]);
        this.resultat=this.requete.executeQuery();
        this.resultat.next();
        tmp=this.resultat.getInt(1);
        count_non_presence=count_non_presence+tmp;
        }
        count_non_presence=tab.length-count_non_presence;
        this.resultat.close();
        this.requete.close();
        this.connexion_ihm.close(); 
        this.connexion_boutalja.close();
        
        return count_non_presence;
    }
}

