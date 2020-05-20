package uk.co.hobnobian.epedemic.main.goal.goals;

import uk.co.hobnobian.epedemic.main.Person;
import uk.co.hobnobian.epedemic.main.PersonStatus;
import uk.co.hobnobian.epedemic.main.game.Game;
import uk.co.hobnobian.epedemic.main.goal.Goal;

public class GoToMapWhenInfectedGoal extends Goal {
    private int mapno;
    private int cooldown;
    private boolean done = false;
    
    private Game game;
    
    public GoToMapWhenInfectedGoal(int mapno, int cooldownticks, Game g) {
        this.mapno = mapno;
        this.cooldown = cooldownticks;
        this.game = g;
    }
    @Override
    public boolean run(Person p) {
        if (p.getStatus() == PersonStatus.INFECTED) {
            if (cooldown == 0 && !done) {
                done = true;
                p.switchMap(game.getMap(mapno));
                return true;
            }else {
                cooldown--;
            }
        }
        return false;
    }

}
