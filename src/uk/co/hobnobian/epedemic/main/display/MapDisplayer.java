package uk.co.hobnobian.epedemic.main.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import uk.co.hobnobian.epedemic.main.GameConstants;
import uk.co.hobnobian.epedemic.main.Map;
import uk.co.hobnobian.epedemic.main.Person;
import uk.co.hobnobian.epedemic.main.PersonStatus;

public class MapDisplayer extends JPanel{
    private static final long serialVersionUID = 4751640332409061418L;
    private Map map;

    public MapDisplayer(Map m) {
        map = m;
        super.setPreferredSize(new Dimension(map.getSize()[0], map.getSize()[1]));
    }
    
    
    
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, map.getSize()[0], map.getSize()[1]);
        
        for (Person p : map.getPeople()) {
//            if (p.isDead()) {
//                continue;
//            }
            Color c = GameConstants.getColourForStatus(p.getStatus());
            
            g.setColor(c);
            if (p.getStatus() == PersonStatus.JUST_DIED) {
                g.fillOval(p.getPos()[0], p.getPos()[1], GameConstants.EMPHASIS_SIZE, GameConstants.EMPHASIS_SIZE);
            }
            else {
                g.fillOval(p.getPos()[0], p.getPos()[1], GameConstants.NORMAL_SIZE, GameConstants.NORMAL_SIZE);
            }
            
        }
    }
}
