package uk.co.hobnobian.epedemic.main;

import java.awt.Color;

public class GameConstants {
//  000000    00000    00    00   00000
//  0        0     0   0 0  0 0   0
//  0        0000000   0  00  0   0
//  0  000   0     0   0      0   0000
//  0   0    0     0   0      0   0
//  00000    0     0   0      0   00000
    
    
    //The number of pixels a person must be away from an infected person to risk getting the disease
    public static final int INFECTION_RADIUS = 20;
    //If somebody is in radius to become infected, they have an x in 10000 chance of becoming infected
    public static final int INFECTION_CHANCE = 1000;
    //When somebody gets infected, they have an x in 10000 chance of showing no symptoms
    public static final int NO_SYMPTOMS_CHANCE = 100;
    
    //This number of ticks must have passed since infection for this person to risk dying
    public static final int INFECTION_DEATH_COOLDOWN = 150;
    //After the INFECTION_DEATH_COOLDOWN ticks, the person has a x in 10000 chance of dying every tick
    public static final int DEATH_CHANCE = 5;
    
    //This number of ticks must have passed since infection for this person to start trying to become immune
    public static final int INFECTION_IMMUNE_COOLDOWN  = 200;
    //After the INFECTION_IMMUNE_COOLDOWN ticks, the person has a x in 10000 chance of becoming immune every tick
    public static final int IMMUNITY_CHANCE = 10;
    
    
    
//  000    00000   00000   00000   0       00000   0   0
//  0  0     0    0        0    0  0      0     0   0 0
//  0   0    0     00000   0    0  0      0000000    0
//  0   0    0          0  00000   0      0     0    0
//  0  0     0          0  0       0      0     0    0
//  000    00000   00000   0       00000  0     0    0
    
    public static final Color getColourForStatus(PersonStatus s) {
        switch(s) {
            case INFECTED:
                return Color.RED;
            case SUSCEPTIBLE:
                return Color.BLUE;
            case IMMUNE:
                return Color.GREEN;
            case DEAD:
                return Color.DARK_GRAY;
            case NO_SYMPTOMS:
                return Color.YELLOW;
            case JUST_DIED:
                return Color.WHITE;
        }
        return Color.GRAY;   
    }
    
    //The radius of the circles normally
    public static final int NORMAL_SIZE = 20;
    //The radius of the circles when they need emphasis (e.g they have just died)
    public static final int EMPHASIS_SIZE = 40;

}
