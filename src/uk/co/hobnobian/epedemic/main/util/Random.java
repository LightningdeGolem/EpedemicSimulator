package uk.co.hobnobian.epedemic.main.util;

public class Random {
    public static int randint(int min, int max) {
        double initial = Math.random();
        return (int) Math.floor(((initial*(double)(max-min))+min));
    }
    
    public static boolean chance(int x, int in) {
        return randint(0,in) < x; 
    }
}
