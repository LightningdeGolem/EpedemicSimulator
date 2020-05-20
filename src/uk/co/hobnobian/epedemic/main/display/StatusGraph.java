package uk.co.hobnobian.epedemic.main.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JPanel;

import uk.co.hobnobian.epedemic.main.GameConstants;
import uk.co.hobnobian.epedemic.main.Person;
import uk.co.hobnobian.epedemic.main.PersonStatus;

public class StatusGraph extends JPanel {
    private static final long serialVersionUID = -2509094993157492666L;
    
    private ArrayList<HashMap<Color, Integer>> graph = new ArrayList<HashMap<Color, Integer>>();
    
    private int height;
    private int width;
  
    private int totalPeople = 0;
    
    public StatusGraph(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        
        this.width = width;
        this.height = height;
        
    }
    
    public void update(List<Person> people) {
        HashMap<PersonStatus, Integer> totals = new HashMap<PersonStatus, Integer>();
        for (Person per : people) {
            if (totals.containsKey(per.getStatus())) {
                totals.replace(per.getStatus(), totals.get(per.getStatus())+1);
            }else {
                totals.put(per.getStatus(), 1);
            }
        }
        
        totalPeople = people.size();
        
        
        PersonStatus[] order = {
                PersonStatus.JUST_DIED,
                PersonStatus.DEAD,
                PersonStatus.IMMUNE,
                PersonStatus.SUSCEPTIBLE,
                PersonStatus.INFECTED,
                PersonStatus.NO_SYMPTOMS
        };
        HashMap<Color, Integer> mostrecent = new HashMap<Color, Integer>();
        
        for (PersonStatus s : order) {
            Color c = GameConstants.getColourForStatus(s);
            int amount;
            if (totals.containsKey(s)) {
                amount = totals.get(s);
            }else {
                amount = 0;
            }
            mostrecent.put(c, amount);
        }
        
        graph.add(mostrecent);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        width = getSize().width;
        height = getSize().height;
        g.fillRect(0, 0, width, height);
        if (graph.size() == 0) {
            return;
        }
       
        
        int px = 0;
        while (px < width) {
            int rx = px*graph.size()/width;
            HashMap<Color, Integer> column = graph.get(rx);
            
            
            int ry = 0;
            
            for (Color key : column.keySet()) {
                g.setColor(key);
                int py = ry*height/totalPeople;
                
                g.drawLine(px, py, px, (ry+column.get(key))*height/totalPeople);
                ry+=column.get(key);
            }
            
            px++;
        }
    }
    
}
