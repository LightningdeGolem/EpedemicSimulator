package uk.co.hobnobian.epedemic.main.goal.goals;

import uk.co.hobnobian.epedemic.main.Person;
import uk.co.hobnobian.epedemic.main.goal.Goal;

public class DoNothingGoal extends Goal{
    @Override
    public boolean run(Person p) {
        return true;
    }
}
