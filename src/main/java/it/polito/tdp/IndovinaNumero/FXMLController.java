/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.IndovinaNumero;

import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ResourceBundle;

import it.polito.tdp.IndovinaNumero.modello.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {
	
    private Model modello;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnNuovaPartita"
    private Button btnNuovaPartita; // Value injected by FXMLLoader
    
    @FXML // fx:id="hboxTentativi"
    private HBox hboxTentativi; // Value injected by FXMLLoader


    @FXML // fx:id="btnProva"
    private Button btnProva; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativi"
    private TextField txtTentativi; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativo"
    private TextField txtTentativo; // Value injected by FXMLLoader

    @FXML
    void doNuovaPartita(ActionEvent event) {
		//cambiamento dei dati del modello => lavoro del model
		this.modello.NuovaPartita();
    	//gestione interfaccia quindi lavoro del controller
    	txtTentativi.setText(""+ this.modello.getTMAX());
    	hboxTentativi.setDisable(false);
    	txtRisultato.clear();    	
    }

    @FXML
    void doTentativo(ActionEvent event) {

        int risultato=0;
    	//controllo 1 -> input numerico
    	try {
    		//tentativo = Integer.parseInt(ts);
			risultato= modello.tentativo(Integer.parseInt(txtTentativo.getText()));
    	}
        catch(NumberFormatException e){
            txtRisultato.setText("Formato del numero NON valido");
            return;
        }
        catch (InvalidParameterException e) {
    		txtRisultato.setText(e.getMessage());
    		return;
    	}
        catch(IllegalStateException e){
            txtRisultato.setText(e.getMessage());
            hboxTentativi.setDisable(true);
            return;
        }
        if (risultato==0) {
            txtRisultato.setText("HAI INDOVINATO CON " + this.modello.getTentativiFatti() + " TENTATIVI");
            hboxTentativi.setDisable(true);
        }
        else if (risultato==-1) {
            txtRisultato.setText("Tentativo Troppo Basso!");
        }
        else
            txtRisultato.setText("Tentativo Troppo Alto!");

    	txtTentativi.setText(Integer.toString(this.modello.getTMAX()-this.modello.getTentativiFatti()));
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnNuovaPartita != null : "fx:id=\"btnNuovaPartita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'Scene.fxml'.";

    }
 	public  void setModel(Model m){
		this.modello=m;
	}
}
