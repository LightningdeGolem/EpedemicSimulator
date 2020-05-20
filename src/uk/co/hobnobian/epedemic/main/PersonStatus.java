package uk.co.hobnobian.epedemic.main;

public enum PersonStatus {
    INFECTED,
    SUSCEPTIBLE,
    IMMUNE,
    NO_SYMPTOMS,
    JUST_DIED,
    DEAD;
    
    public boolean infectious() {
        if (this == INFECTED || this == NO_SYMPTOMS) {
            return true;
        }
        return false;
    }
    
    public boolean dead() {
        return this == DEAD || this == JUST_DIED;
    }
    
    public boolean beenInfected() {
        return dead() || infectious() || this == IMMUNE; 
    }
}
