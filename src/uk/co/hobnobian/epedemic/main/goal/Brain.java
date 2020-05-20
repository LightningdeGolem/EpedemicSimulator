package uk.co.hobnobian.epedemic.main.goal;

import java.util.ArrayList;
import java.util.List;

import uk.co.hobnobian.epedemic.main.Person;
import uk.co.hobnobian.epedemic.main.util.Random;

public class Brain {
    private List<Goal> goals = new ArrayList<Goal>();
    private List<Integer> pri = new ArrayList<Integer>();
    
    public Brain() {
        
    }
    
    private Brain(List<Goal> g, List<Integer> p) {
        goals = g;
        pri = p;
    }
    
    public Brain duplicate() {
        ArrayList<Goal> newgoals = new ArrayList<Goal>();
        ArrayList<Integer> newpri = new ArrayList<Integer>();
        
        for (Goal g : goals) {
            newgoals.add(g.clone());
        }
        for (int p : pri) {
            newpri.add(p);
        }
        
        return new Brain(newgoals, newpri);
    }
    
    public void addGoal(Goal goal, int priority) {
        goals.add(goal);
        pri.add(priority);
    }
    public void clearGoals() {
        goals.clear();
        pri.clear();
    }
    
    private boolean processGoal(Goal g, Person person) {
        return g.run(person);
    }
    
    public void go(Person person) {
        
        ArrayList<Goal> choices = new ArrayList<Goal>();
        for (int i = 0; i< goals.size(); i++) {
            for (int n = 0; n < pri.get(i); n++) {
                choices.add(goals.get(i));
            }
        }

        
        if (choices.size() == 0) {
            while (!processGoal(goals.get(Random.randint(0, goals.size())), person)) {}
            return;
        }
        

        while (true) {
            Goal goal = choices.get(Random.randint(0, choices.size()));
            if (processGoal(goal, person)) {
                break;
            }
            else {
                removeAllFrom(choices, goal);
//                System.out.println(goal + " failed");
            }
        }
    }
    
    private void removeAllFrom(List<Goal> l, Goal go) {
//        l.removeAll(Collections.singleton(go));
        int i = 0;
        while (i < l.size()) {
            if (l.get(i).getClass().equals(go.getClass())) {
                l.remove(i);
            }
            else {
                i++;
            }
        }
    }
}
