package model;

import java.time.LocalDate;

/**
 * an object ,type Result with the followings attributes :id, studierende....
 */
public class Result {

    private int Id;
    private String Held;
    private  Konfrontationstyp Konfrontationstyp ;
    private String Antagonist;
    private String Ort;
    private LocalDate Datum;
    private double GlobalerEinfluss;

    public int getId() {
        return Id;
    }

    public String getHeld() {
        return Held;
    }

    public model.Konfrontationstyp getKonfrontationstyp() {
        return Konfrontationstyp;
    }

    public String getAntagonist() {
        return Antagonist;
    }

    public String getOrt() {
        return Ort;
    }

    public LocalDate getDatum() {
        return Datum;
    }

    public double getGlobalerEinfluss() {
        return GlobalerEinfluss;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setHeld(String held) {
        Held = held;
    }

    public void setKonfrontationstyp(model.Konfrontationstyp konfrontationstyp) {
        Konfrontationstyp = konfrontationstyp;
    }

    public void setAntagonist(String antagonist) {
        Antagonist = antagonist;
    }

    public void setOrt(String ort) {
        Ort = ort;
    }

    public void setDatum(LocalDate datum) {
        Datum = datum;
    }

    public void setGlobalerEinfluss(double globalerEinfluss) {
        GlobalerEinfluss = globalerEinfluss;
    }

    @Override
    public String toString() {
        return "Result{" +
                "Id=" + Id +
                ", Held='" + Held + '\'' +
                ", Konfrontationstyp=" + Konfrontationstyp +
                ", Antagonist='" + Antagonist + '\'' +
                ", Ort='" + Ort + '\'' +
                ", Datum=" + Datum +
                ", GlobalerEinfluss=" + GlobalerEinfluss +
                '}';
    }


}



