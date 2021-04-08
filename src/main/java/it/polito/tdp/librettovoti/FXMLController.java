package it.polito.tdp.librettovoti;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco= false;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnNuovaPartita"
    private Button btnNuovaPartita; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativi"
    private TextField txtTentativi; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativoUtente"
    private TextField txtTentativoUtente; // Value injected by FXMLLoader

    @FXML // fx:id="btnProva"
    private Button btnProva; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML
    void doNuovaPartita(ActionEvent event) {
    	// gestione inizio nuova partita
    	this.segreto= (int) (Math.random() * NMAX) + 1;
    	this.tentativiFatti = 0 ;
    	this.inGioco = true;
    	
    	// gestione dell' interfaccia
    	this.txtTentativi.setText(Integer.toString(TMAX));
    }

    @FXML
    void doTentativo(ActionEvent event) {
    // lettura input utente e controllo dell input
    	String ts = txtTentativoUtente.getText();
    	int tentativo;
    	try {
    		tentativo = Integer.parseInt(ts);
    	}
    	catch(NumberFormatException e) {
    		txtRisultato.setText("Devi inserire un numero!");
    		return; // esco non permetto all'utente di continuare
    	}
    	txtTentativoUtente.setText("");
    	tentativiFatti ++;
    	this.txtTentativi.setText(Integer.toString(TMAX-tentativiFatti));
    	
    	if(tentativo == segreto) {
    		txtRisultato.setText("HAI VINTO CON " + tentativiFatti + "TENTATIVI!");
    		inGioco = false;
    		return; // il metodo finisce qui
    	}
    	
    	if(tentativiFatti == TMAX) {
    		// ho perso!
    		txtRisultato.appendText("HAI PERSO ! IL NUMERO ERA :"+ segreto);	
    		inGioco = false;
    		return;
    	}
    	
    	// non ho finito -> devo informare il giocatore circa la bontà del suo tentativo
    	if(tentativo < this.segreto) {
    		txtRisultato.appendText("TENTATIVO TROPPO BASSO");
    	} else {
    		txtRisultato.appendText("TENTATIVO TROPPO ALTO");
    	}
    	
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnNuovaPartita != null : "fx:id=\"btnNuovaPartita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativoUtente != null : "fx:id=\"txtTentativoUtente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}
