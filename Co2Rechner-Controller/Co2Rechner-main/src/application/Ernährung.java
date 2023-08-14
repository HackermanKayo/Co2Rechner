package application;
import java.util.Scanner;
enum Form{Vegan,Vegetarisch,Fleichreduziert, Mischkost,Fleischbetonte};
enum Verhalten{hauptsächtsächlich,Teileweise,Keine};
enum Regional{Regionaler_Einkauf,Saisonaler_Einkauf};
enum Kühlkost{gelegentlich,ein_bis_DreiMal_pro_woche,täglich} /////
enum sport{Keiner,WenigSport,VielSport}; 

public class Ernährung 
{
    Form Ernährungsform;
    Verhalten Bio_Produkte;
    Regional Regionale_Produkte;
    Kühlkost TiefKühlkost;
    sport sport;
    
    
    
public Ernährung()
{
    Ernährungsform = Form.Vegan;
    Bio_Produkte = Verhalten.hauptsächtsächlich;
    Regionale_Produkte = Regional.Regionaler_Einkauf;
    TiefKühlkost = Kühlkost.gelegentlich;
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
    public void setTiefKühlkost(Kühlkost TiefKühlkost) {
        this.TiefKühlkost = TiefKühlkost;
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
    public Kühlkost getTiefKühlkost() {
        return TiefKühlkost;
    }
    public sport getSport() {
        return sport;
    }
    
   
   public void gewichtFormel () {
    Scanner input = new Scanner(System.in);
    int nutzerIn = input.nextInt();
    int durschschnitt = input.nextInt();  

   }
}

