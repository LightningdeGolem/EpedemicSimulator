package uk.co.hobnobian.epedemic.main;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private List<Person> people = new ArrayList<Person>();
    
    private List<Person> switchRequests = new ArrayList<Person>();
    
    private int[] size;
    
    public Map(int width, int height) {
        size = new int[] {width, height};
    }
    
    public Map() {
        this(800,600);
    }

    public List<Person> getPeople() {
        return people;
    }
    
    public int getInfectedCount() {
        int total = 0;
        for (Person p : people) {
            if (p.getStatus().infectious()) {
                total ++;
            }
        }
        return total;
    }
    
    public void tick() {
        for (Person p : people) {
            p.tick();
        }
        for (Person p : switchRequests) {
            people.remove(p);
        }
        switchRequests = new ArrayList<Person>();
    }
    
    public void requestLeave(Person p) {
        if (people.contains(p)) {
            if (!switchRequests.contains(p)) {
                switchRequests.add(p);
            }
        }
    }
    
    public void addPerson(Person p) {
        people.add(p);
    }
    
    public int[] getSize() {
        return size;
    }
    
    public List<Person> getPeopleInRadius(int x, int y, int radius){
        ArrayList<Person> inradius = new ArrayList<Person>();
        for (Person p : people) {
            int px = p.getPos()[0];
            int py = p.getPos()[1];
            
            if (px < x+radius && px > x-radius &&
                py < y+radius && py > y-radius) {
                
                double distance = Math.sqrt(Math.pow(px-x, 2) + Math.pow(py-y, 2));
                if (distance < radius) {
                    inradius.add(p);
                }
            }
        }
        return inradius;
    }
}
