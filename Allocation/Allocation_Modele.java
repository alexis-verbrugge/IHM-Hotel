import javax.swing.*;
import java.awt.*;
import java.sql.*;

// java -cp "/export/documents/mariadb-client.jar:."
public class Allocation_Modele {

    private Connection connexion_ihm;
    private Connection connexion_boutalja;
    private PreparedStatement requete;
    private PreparedStatement requete2;
    private ResultSet resultat;
    private ResultSet resultat2;

    public Allocation_Modele() {
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
                System.err.println("Erreur connexion boutalja: "+e.getMessage());get
            }                 
        } catch(ClassNotFoundException e) {
            System.err.println("Pilote indisponible !");
        }
    }

    //Retourne reservation grace a la reference
    public Reservation getReservation(String ref) throws SQLException{
        int id_client;
        this.connexion_ihm = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/projetihm", "projetihm", "mhitejorp");
        this.connexion_boutalja = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/boutalja", "boutalja", "boutalja");
        this.requete=this.connexion_ihm.prepareStatement("SELECT categorie,debut,reference,id,nuits,client FROM Reservation WHERE reference=?");
        this.requete.setString(1,ref);
        this.resultat=this.requete.executeQuery();
        this.resultat.next();
        id_client=this.resultat.getInt(6);
        this.requete2=this.connexion_ihm.prepareStatement("SELECT prenom,nom FROM Client WHERE id=?");
        this.requete2.setInt(1,id_client);
        this.resultat2=this.requete2.executeQuery();
        this.resultat2.next();
        System.out.println(this.resultat2.getString(1)+ " " + this.resultat2.getString(2));
        Reservation rez = new Reservation(this.resultat.getInt(1),this.resultat.getDate(2),
        this.resultat.getString(3), this.resultat.getInt(4), this.resultat.getInt(5), this.resultat2.getString(1), this.resultat2.getString(2));
        System.out.println("categorie: "+rez.getCategorie()+" date: "+rez.getDate()+" id: "+rez.getId());
        this.resultat.close();
        this.requete.close();
        this.connexion_ihm.close();
        this.connexion_boutalja.close();
        return rez;
    }
    //Retourne reservation grace au nom et prenom
    public Reservation getReservation(String nomzer, String prenomzer) throws SQLException {
        this.connexion_ihm = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/projetihm", "projetihm", "mhitejorp");
        this.connexion_boutalja = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/boutalja", "boutalja", "boutalja");
        this.requete=this.connexion_ihm.prepareStatement("SELECT categorie,debut,reference,Reservation.id,nuits FROM Reservation JOIN Client ON Client.id=Reservation.client WHERE nom=? AND prenom=?");
        this.requete.setString(1,nomzer);
        this.requete.setString(2,prenomzer);
        this.resultat=this.requete.executeQuery();
        this.resultat.next();
        Reservation rez = new Reservation(this.resultat.getInt(1),this.resultat.getDate(2),
        this.resultat.getString(3),this.resultat.getInt(4),this.resultat.getInt(5), prenomzer, nomzer);
        System.out.println("categorie: "+rez.getCategorie()+" date: "+rez.getDate()+" id: "+rez.getId());
        this.resultat.close();
        this.requete.close();
        this.connexion_ihm.close();
        this.connexion_boutalja.close();
        return rez;
    }
    //Verifie si une reservation existe grace au nom et prenom et s'il y en a plusieurs
    public int countNomPrenom(String nomzer, String prenomzer) throws SQLException{
        int tmp=0;
                this.connexion_ihm = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/projetihm", "projetihm", "mhitejorp");
        this.connexion_boutalja = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/boutalja", "boutalja", "boutalja");
        this.requete=this.connexion_ihm.prepareStatement("SELECT count(*) FROM Reservation JOIN Client ON Client.id=Reservation.client WHERE nom=? AND prenom=?");
        this.requete.setString(1,nomzer);
        this.requete.setString(2,prenomzer);
        this.resultat=this.requete.executeQuery();
        this.resultat.next();
        tmp=this.resultat.getInt(1);
        this.resultat.close();
        this.requete.close();
        this.connexion_ihm.close();
        this.connexion_boutalja.close();
        return tmp;
    }


    //Verifie si une reservation existe grace a la ref.
    public int countReference(String ref) throws SQLException{
        int tmp=0;
        this.connexion_ihm = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/projetihm", "projetihm", "mhitejorp");
        this.connexion_boutalja = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/boutalja", "boutalja", "boutalja");
        this.requete=this.connexion_ihm.prepareStatement("SELECT count(*) FROM Reservation WHERE reference=?");
        this.requete.setString(1,ref);
        this.resultat=this.requete.executeQuery();
        this.resultat.next();
        tmp=this.resultat.getInt(1);
        this.resultat.close();
        this.requete.close();
        this.connexion_ihm.close();
        this.connexion_boutalja.close();
        System.out.println("nb de reference: "+ tmp);
        return tmp;
    }
    public int propositionChambre(Reservation r) throws SQLException{
        int tmp=0;
        int res=1;
        int count=0;
        int duree=r.getDuree();
        Date date=r.getDate();
        boolean dispo=true;
        boolean condition=false;
        this.connexion_ihm = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/projetihm", "projetihm", "mhitejorp");
        this.connexion_boutalja = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/boutalja", "boutalja", "boutalja");
        this.requete=this.connexion_boutalja.prepareStatement("SELECT id FROM Chambre WHERE categorie=?");
        this.requete.setInt(1,r.getCategorie());
        System.out.println("categorie =" + r.getCategorie());
        this.resultat=this.requete.executeQuery();
        while(this.resultat.next() && condition==false){ 
            tmp=this.resultat.getInt(1);
            dispo=true;
            date=r.getDate();
            for (int i=0; i<duree; i++) {
            this.requete=this.connexion_boutalja.prepareStatement("SELECT count(*) FROM Chambre_Reserve WHERE id_chambre=? AND date=?");
            this.requete.setInt(1,tmp);
            this.requete.setDate(2,date);
            this.resultat2=this.requete.executeQuery();
            this.resultat2.next();
                if (this.resultat2.getInt(1)==1) {
                    dispo=false;
                }    
            date =new Date(date.getTime() + 24*60*60*1000);
            }
            if (dispo==true) {
                condition=true;
                res=tmp;
            }
        }
        
        this.resultat.close();
        this.requete.close();
        this.connexion_ihm.close();
        this.connexion_boutalja.close();
        return res;
    }

    public void validerReservation(Reservation r) throws SQLException{
          this.connexion_ihm = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/projetihm", "projetihm", "mhitejorp");
        this.connexion_boutalja = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/boutalja", "boutalja", "boutalja");
         this.requete=this.connexion_boutalja.prepareStatement("INSERT INTO Chambre_Reserve VALUES(?,?,?)");
        this.requete.setInt(1,r.getChambre());
        this.requete.setInt(2,r.getId());
        this.requete.setDate(3,r.getDate());
        this.resultat=this.requete.executeQuery();
        this.resultat.close();
        this.requete.close();
        this.connexion_ihm.close();
        this.connexion_boutalja.close();

    }

    public int countReservation(Reservation r) throws SQLException{
         this.connexion_ihm = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/projetihm", "projetihm", "mhitejorp");
        this.connexion_boutalja = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/boutalja", "boutalja", "boutalja");
        this.requete=this.connexion_boutalja.prepareStatement("SELECT count(*) FROM Chambre_Reserve WHERE id_reservation=?");
        this.requete.setInt(1,r.getId());
        this.resultat=this.requete.executeQuery();
        this.resultat.next();
        int res=this.resultat.getInt(1);
        this.resultat.close();
        this.requete.close();
        this.connexion_ihm.close();
        this.connexion_boutalja.close();
        return res;
    }

    public int countDateChambre(int chambre, Date date) throws SQLException {
        this.connexion_ihm = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/projetihm", "projetihm", "mhitejorp");
        this.connexion_boutalja = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/boutalja", "boutalja", "boutalja");
        this.requete=this.connexion_boutalja.prepareStatement("SELECT count(*) FROM Chambre_Reserve WHERE date=? AND id_chambre=?");
        this.requete.setDate(1,date);
        this.requete.setInt(2,chambre);
        this.resultat=this.requete.executeQuery();
        this.resultat.next();
        int res=this.resultat.getInt(1);
        this.resultat.close();
        this.requete.close();
        this.connexion_ihm.close();
        this.connexion_boutalja.close();
        return res;
    }

     public void selectChambres(Reservation r, int[] tab, int taille) throws SQLException{
        int tmp=0;
        int res=1;
        int count=0;
        int i=0;
        int duree=r.getDuree();
        Date date=r.getDate();
        boolean condition=false;
                this.connexion_ihm = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/projetihm", "projetihm", "mhitejorp");
        this.connexion_boutalja = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/boutalja", "boutalja", "boutalja");
        this.requete=this.connexion_boutalja.prepareStatement("SELECT id FROM Chambre WHERE categorie=?");
        this.requete.setInt(1,r.getCategorie());
        this.resultat=this.requete.executeQuery();
        while(this.resultat.next()){ 
            tmp=this.resultat.getInt(1);
            date = r.getDate();
            for (int j=0; j<duree;j++) {
            this.requete=this.connexion_boutalja.prepareStatement("SELECT count(*) FROM Chambre_Reserve WHERE id_chambre=? AND date=?");
            this.requete.setInt(1,tmp);
            this.requete.setDate(2,date);
            this.resultat2=this.requete.executeQuery();
            this.resultat2.next();

                if (this.resultat2.getInt(1)==1) {
                    break;
                }
                if (this.resultat2.getInt(1)==0 && j==duree-1) {
                tab[i]=this.resultat.getInt(1);
                i++;
            } 
            date =new Date(date.getTime() + 24*60*60*1000);    
        }
            
            }
        this.resultat.close();
        this.requete.close();
        this.connexion_ihm.close();
        this.connexion_boutalja.close();
    }


public void getReservation(String nomzer, String prenomzer, String[] reference) throws SQLException {
        int i=0; 
        this.connexion_ihm = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/projetihm", "projetihm", "mhitejorp");
        this.connexion_boutalja = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/boutalja", "boutalja", "boutalja");
        this.requete=this.connexion_ihm.prepareStatement("SELECT reference FROM Reservation JOIN Client ON Client.id=Reservation.client WHERE nom=? AND prenom=?");
        this.requete.setString(1,nomzer);
        this.requete.setString(2,prenomzer);
        this.resultat=this.requete.executeQuery();
        while(this.resultat.next()) {
        reference[i]=this.resultat.getString(1);
        i++;
        }
        this.resultat.close();
        this.requete.close();
        this.connexion_ihm.close();
        this.connexion_boutalja.close();
    }
}