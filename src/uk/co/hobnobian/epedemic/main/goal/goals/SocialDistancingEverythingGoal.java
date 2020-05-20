package uk.co.hobnobian.epedemic.main.goal.goals;

import uk.co.hobnobian.epedemic.main.Person;

public class SocialDistancingEverythingGoal extends SocialDistancingGoal {
    
    public SocialDistancingEverythingGoal(int speed, int radius, boolean noactivity) {
        this.speed = speed;
        this.radius = radius;
        this.defaultReturn = noactivity;
    }
    public SocialDistancingEverythingGoal() {
        this(10, 40, false);
    }

    @Override
    public boolean checkPlayerValid(Person p) {
        return !p.getStatus().dead();
    }
    

}
