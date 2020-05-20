package uk.co.hobnobian.epedemic.main;

import java.util.List;

import uk.co.hobnobian.epedemic.main.goal.Brain;
import uk.co.hobnobian.epedemic.main.util.Random;

public class Person implements Cloneable{
    private PersonStatus status = PersonStatus.SUSCEPTIBLE;
    private int x;
    private int y;
    private Map map;
    
    private int infectedFor = 0;
    
    private Brain brain;
    
    public void setStatus(PersonStatus s) {
        status = s;
    }
    
    public void moveTo(int x, int y) {
        if (x < 0 || x > map.getSize()[0] ||
            y < 0 || y > map.getSize()[1]    ) {
            return;
        }
        this.x = x;
        this.y = y;
    }
    
    public void move(int xchange, int ychange) {
        moveTo(x+xchange, y+ychange);
    }
    
    
    public Person(int x, int y, Map m) {
        this(x,y,m,new Brain());
    }
    
    public Person(int x, int y, Map m, Brain b) {
        this.x = x;
        this.y = y;
        setMap(m);
        
        brain = b;
    }
    
    public Brain getBrain() {
        return brain;
    }
    
    public int[] getPos() {
        return new int[] {x,y};
    }
    
    public boolean isDead() {
        return status == PersonStatus.DEAD || status == PersonStatus.JUST_DIED;
    }
    
    public void kill() {
        status = PersonStatus.JUST_DIED;
    }
    
    public void tick() {
        if (status == PersonStatus.JUST_DIED) {
            status = PersonStatus.DEAD;
        }
        if (status == PersonStatus.DEAD) {
            return;
        }
        
        brain.go(this);
        
        if (status.infectious()) {
            infectedFor++;
        }
        
        if (status == PersonStatus.SUSCEPTIBLE) {
            List<Person> couldinfectme = map.getPeopleInRadius(x, y, GameConstants.INFECTION_RADIUS);
            
            for (Person p : couldinfectme) {
                if (p.getStatus() == PersonStatus.INFECTED || p.getStatus() == PersonStatus.NO_SYMPTOMS) {
                    if (Random.chance(GameConstants.INFECTION_CHANCE, 10000)) {
                        if (Random.chance(GameConstants.NO_SYMPTOMS_CHANCE, 10000)) {
                            status = PersonStatus.NO_SYMPTOMS;
                        }
                        else {
                            status = PersonStatus.INFECTED;
                        }
                        
                    }
                }
            }
        }
        else if (status.infectious()) {
            if (Random.chance(GameConstants.IMMUNITY_CHANCE, 10000) && infectedFor >= GameConstants.INFECTION_IMMUNE_COOLDOWN) {
                status = PersonStatus.IMMUNE;
            }
            else if (Random.chance(GameConstants.DEATH_CHANCE, 10000) && infectedFor >= GameConstants.INFECTION_DEATH_COOLDOWN && status == PersonStatus.INFECTED){
                kill();
            }
        }
        
    }
    
    public PersonStatus getStatus() {
        return status;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
    
    public void switchMap(Map map) {
        if (this.map.equals(map)) {
            return;
        }
        this.map.requestLeave(this);;
        setMap(map);
        map.addPerson(this);
        
    }
}
