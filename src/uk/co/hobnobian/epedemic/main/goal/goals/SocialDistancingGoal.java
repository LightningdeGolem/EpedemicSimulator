package uk.co.hobnobian.epedemic.main.goal.goals;

import java.util.List;

import uk.co.hobnobian.epedemic.main.Person;
import uk.co.hobnobian.epedemic.main.goal.Goal;

public abstract class SocialDistancingGoal extends Goal {
    protected int radius = 40;
    protected int speed = 3;
    
    protected boolean defaultReturn = false;
    
    public abstract boolean checkPlayerValid(Person p);
    
    @Override
    public final boolean run(Person p) {
        List<Person> choices = p.getMap().getPeopleInRadius(p.getPos()[0], p.getPos()[1], radius);
        for (Person p1 : choices) {
            if (p1.equals(p)) {
                continue;
            }
            if (!checkPlayerValid(p)) {
                continue;
            }
            
            int xchange = p1.getPos()[0] - p.getPos()[0];
            int ychange = p1.getPos()[1] - p.getPos()[1];
            double direction = Math.atan2(ychange, xchange);
            
            direction+=Math.PI;
            
            
            int movex = (int) Math.floor(speed * Math.cos(direction));
            int movey = (int) Math.floor(speed * Math.sin(direction));
            
            p.move(movex, movey);
        }
        return defaultReturn;
    }
    

}
