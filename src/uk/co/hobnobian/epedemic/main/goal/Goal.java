package uk.co.hobnobian.epedemic.main.goal;

import uk.co.hobnobian.epedemic.main.Person;

public abstract class Goal implements Cloneable{
    public abstract boolean run(Person p);
    
    public Goal clone() {
        try {
            return (Goal) super.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
