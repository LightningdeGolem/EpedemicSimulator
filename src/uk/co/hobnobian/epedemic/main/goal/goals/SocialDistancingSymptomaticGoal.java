package uk.co.hobnobian.epedemic.main.goal.goals;

import uk.co.hobnobian.epedemic.main.Person;
import uk.co.hobnobian.epedemic.main.PersonStatus;

public class SocialDistancingSymptomaticGoal extends SocialDistancingGoal{
    
    public SocialDistancingSymptomaticGoal(int speed) {
        this.speed = speed;
        this.radius = 40;
    }
    public SocialDistancingSymptomaticGoal() {
        this(10);
    }
    
    @Override
    public boolean checkPlayerValid(Person p) {
        return p.getStatus() == PersonStatus.INFECTED;
    }
    
}
