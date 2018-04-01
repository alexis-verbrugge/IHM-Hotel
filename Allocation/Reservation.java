import javax.swing.*;
import java.awt.*;
import java.sql.*;


public class Reservation {

	private int cat;
	private Date date;
	private String ref;
	private int id;
	private int chambre;
	private int duree;
	private String prenom;
	private String nom;

	public Reservation(int catzer, Date datezer, String refzer, int idzer, int nuitzer, String prenomzer, String nomzer) {
		this.cat=catzer;
		this.date=datezer;
		this.ref=refzer;
		this.id=idzer;
		this.duree=nuitzer;
		this.nom=nomzer;
		this.prenom=prenomzer;

	}

	// public Reservation(int catzer, Date datezer, String refzer, int idzer, int nuitzer) {
	// 	this.cat=catzer;
	// 	this.date=datezer;
	// 	this.ref=refzer;
	// 	this.id=idzer;
	// 	this.duree=nuitzer;
	// }

	public Reservation() {
		this.cat=0;
		this.ref="";
	}
	public int getCategorie(){
		return this.cat;
	}
	public Date getDate(){
		return this.date;
	}
	public String getReference(){
		return this.ref;
	}
	public int getId() {
		return this.id;
	}
	public int getChambre() {
		return chambre;
	}
	public void setChambre(int c) {
		this.chambre=c;
	}
	public int getDuree() {
		return this.duree;
	}
	public void setDate(Date d) {
		this.date=d;
	}
	public String getNom() {
		System.out.println(this.nom + " " + this.prenom);
		return this.nom;
	}
	public String getPrenom() {
		System.out.println(this.nom + " " + this.prenom);
		return this.prenom;
	}
}