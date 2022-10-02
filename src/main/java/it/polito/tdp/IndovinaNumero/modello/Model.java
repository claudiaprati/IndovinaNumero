package it.polito.tdp.IndovinaNumero.modello;

import org.w3c.dom.ranges.RangeException;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {
    private int segreto;
    private final int TMAX = 8;
    private final int NMAX = 100;
    private int tentativiFatti;
    private boolean inGioco=false;
    Set<Integer> numerigiocati;
    public Model() {
        this.segreto = 0;
        this.tentativiFatti = 0;
        numerigiocati=null;
// se invece creo qui l'hashset devo farne il clear nel metodo NUOVAPARTITA
    }
    public void NuovaPartita(){
        //gestione di una nuova partita
        this.segreto = (int)((Math.random() * NMAX) +1);
        this.tentativiFatti = 0;
        this.inGioco=true;
        this.numerigiocati= new HashSet<Integer>();
    }
private boolean tentativoValido(int tentativo){
    if (tentativo < 1 || tentativo > NMAX || this.numerigiocati.contains(tentativo))
        return false;
    else
        return true;
}
    /**
     * verifica se il tentativo fatto dall'utente è corretto o grande o piccolo o non valido
     * input numerico cerco perchè tipo dati controllato dal controller
     * @param tentativo
     * @return =0 se numero indovinato, 1 se tentativo > del segreto, -1 se tentativo < segreto, se tentativi esauriti
     * sollevate 2 eccezioni:
     * IllegalStateException quando ho finito ilnumero di tentativi possibili
     * InvalidParamException numero fornito non è nell'intervallo prescelto
     */
    public int tentativo(int tentativo) throws InvalidParameterException {

        if (!inGioco)
            throw  new IllegalStateException("Partita terminata!! HAI PERSO!! Il segreto era "+this.segreto+"-");

        //controllo 2 -> intervallo numerico corretto
        if(!tentativoValido(tentativo)) {
            throw new InvalidParameterException("Devi inserire un numero tra 1 e "+ this.getNMAX()+ "oppure il numero è già stato giocato!!!");
        }

        this.tentativiFatti ++;
        this.numerigiocati.add(tentativo);
        if(this.tentativiFatti == TMAX) {
            //esaurito i tentativi -> HAI PERSO
           this.inGioco=false;
           return 2;
        }
        if(tentativo == this.segreto) {
            //HAI VINTO
            this.inGioco=false;
            return 0;
        } else if(tentativo < this.segreto) {
                    return -1;
        } else {
                    return 1;
        }

    }

    public int getSegreto() {
        return segreto;
    }

    public void setSegreto(int segreto) {
        this.segreto = segreto;
    }

    public int getTMAX() {
        return TMAX;
    }

    public int getNMAX() {
        return NMAX;
    }

    public int getTentativiFatti() {
        return tentativiFatti;
    }

    public void setTentativiFatti(int tentativiFatti) {
        this.tentativiFatti = tentativiFatti;
    }
}
