package it.fi.meucci;

public class Biglietto 
{
    double ID;
    String nBiglietto;
    
    
    public Biglietto(double ID, String nBiglietto) {
        this.ID = ID;
        this.nBiglietto = nBiglietto;
    }


    public Biglietto() {
    }


    public double getID() {
        return ID;
    }


    public void setID(double iD) {
        ID = iD;
    }


    public String getnBiglietto() {
        return nBiglietto;
    }


    public void setnBiglietto(String nBiglietto) {
        this.nBiglietto = nBiglietto;
    }
    
    
}
