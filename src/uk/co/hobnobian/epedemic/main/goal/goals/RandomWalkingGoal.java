package uk.co.hobnobian.epedemic.main.goal.goals;

import uk.co.hobnobian.epedemic.main.Person;
import uk.co.hobnobian.epedemic.main.goal.Goal;
import uk.co.hobnobian.epedemic.main.util.Random;

public class RandomWalkingGoal extends Goal {
    private int speed;
    
    private int[] targetPos = null;
    
    public RandomWalkingGoal(int speed) {
        this.speed = speed;
    }
    public RandomWalkingGoal() {
        this(3);
    }

    @Override
    public boolean run(Person p) {
        if (targetPos == null || Random.chance(1, 10000)) {
            targetPos = new int[] {Random.randint(0, p.getMap().getSize()[0]),Random.randint(0, p.getMap().getSize()[1])};
        }
        
        if (p.getPos()[0] > targetPos[0]) {
            p.move(-speed, 0);
        }
        else if (p.getPos()[0] < targetPos[0]){
            p.move(speed, 0);
        }
        
        if (p.getPos()[1] > targetPos[1]) {
            p.move(0, -speed);
        }
        else if (p.getPos()[1] < targetPos[1]){
            p.move(0, speed);
        }
        
        if (targetPos[0] > p.getPos()[0] - speed && targetPos[0] < p.getPos()[0] + speed &&
            targetPos[1] > p.getPos()[1] - speed && targetPos[1] < p.getPos()[1] + speed) {
            targetPos = null;
        }
        
        return true;
//        
//        int xchange = Random.randint(-speed, speed);
//        int ychange = Random.randint(-speed, speed);
//        
//        p.moveTo(p.getPos()[0]+xchange, p.getPos()[1]+ychange);
    }

}
