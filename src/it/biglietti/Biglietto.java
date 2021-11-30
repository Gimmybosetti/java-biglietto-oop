package it.biglietti;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Biglietto {

    //////// LAVORO SVOLTO NELLA ROOM 1 ////////////////////

    /// COSTANTI///

    static final BigDecimal COSTO_KM = new BigDecimal("0.21");
    static final BigDecimal SCONTO_OVER = new BigDecimal("0.6");
    static final BigDecimal SCONTO_UNDER = new BigDecimal("0.8");
    static final int NORMAL = 30;
    static final int FLESSIBILE = 90;

    /// ATTRIBUTI///

    private LocalDate date;
    private boolean flessibile;
    private int km;
    private int age;

    /// COSTRUTTORE///

    public Biglietto(int km, int age, boolean flessibile) throws Exception {

    	this.date = LocalDate.now();
    	this.flessibile = flessibile;
    	this.km = km;
        this.age = age;
        if (!isValidKm() || !isValidAge()) { 
        	throw new Exception("Inserisci un valore valido");
        }
    }
    /// METODI///

    // ValidKm

    private boolean isValidKm() {

        if (km <= 0 || km > 100000) {
            return false;
        } else {
            return true;
        }
    }
    
 // ValidEta

    private boolean isValidAge() {

        if (age <= 0 || age > 150) {
            return false;
        } else {
            return true;
        }

    }

    private BigDecimal calcolaSconto() {
    	
        if (age < 18) {
        	return SCONTO_UNDER.multiply(COSTO_KM);
        } else if (age >= 65) {
            return SCONTO_OVER.multiply(COSTO_KM);
        } else {
            return COSTO_KM;
        }
    }

    public BigDecimal calcolaPrezzo() {
    	if (flessibile) {
    		return calcolaSconto().multiply(BigDecimal.valueOf(km).multiply(BigDecimal.valueOf(1.10)));
    	}else {
    		return calcolaSconto().multiply(BigDecimal.valueOf(km));
    	}
    }
    
    public LocalDate calcolaDataScadenza() {
    	if (flessibile) {
    		return date.plusDays(FLESSIBILE);
    	}else {
    		return date.plusDays(NORMAL);
    	}
    }

}