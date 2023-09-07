package application;

enum Form{Vegan,Vegetarisch,Fleichreduziert, Mischkost,Fleischbetonte};
enum Verhalten{hauptsächtsächlich,Teileweise,Keine};
enum Regional{Regionaler_Einkauf,Saisonaler_Einkauf};
enum Kuehlkost{gelegentlich,ein_bis_DreiMal_pro_woche,täglich} /////
enum sport{Keiner,WenigSport,VielSport}; 

public class Ernaehrung {
    Form Ernährungsform;
    Verhalten Bio_Produkte;
    Regional Regionale_Produkte;
    Kuehlkost TiefKuehlkost;
    sport sport;


    public Ernaehrung() {
        Ernährungsform = Form.Vegan;
        Bio_Produkte = Verhalten.hauptsächtsächlich;
        Regionale_Produkte = Regional.Regionaler_Einkauf;
        TiefKuehlkost = Kuehlkost.gelegentlich;
        sport = sport.Keiner;
    }

    public void setErnährungsform(Form Ernährungsform) {
        this.Ernährungsform = Ernährungsform;
    }

    public void setBio_Produkte(Verhalten Bio_Produkte) {
        this.Bio_Produkte = Bio_Produkte;
    }

    public void setRegionale_Produkte(Regional Regionale_Produkte) {
        this.Regionale_Produkte = Regionale_Produkte;
    }

    public void setTiefKuehlkost(Kuehlkost TiefKuehlkost) {
        this.TiefKuehlkost = TiefKuehlkost;
    }

    public void setSport(sport sport) {
        this.sport = sport;
    }

    public Form getErnährungsform() {
        return Ernährungsform;
    }

    public Verhalten getBio_Produkte() {
        return Bio_Produkte;
    }

    public Regional getRegionale_Produkte() {
        return Regionale_Produkte;
    }

    public Kuehlkost getTiefKuehlkost() {
        return TiefKuehlkost;
    }

    public sport getSport() {
        return sport;
    }


    public double gewichtFormel() {
        int KcalUser = 0;
        int KcalDurschnitt = 0;
        double Tonnage = 0;


        return Tonnage;
    }
}

