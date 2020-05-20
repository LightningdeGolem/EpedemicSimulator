package uk.co.hobnobian.epedemic.main.game;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;

import uk.co.hobnobian.epedemic.main.Map;
import uk.co.hobnobian.epedemic.main.Person;
import uk.co.hobnobian.epedemic.main.PersonStatus;
import uk.co.hobnobian.epedemic.main.display.MapDisplayer;
import uk.co.hobnobian.epedemic.main.display.StatusGraph;
import uk.co.hobnobian.epedemic.main.goal.Brain;
import uk.co.hobnobian.epedemic.main.util.Random;

public class Game {
    private List<Map> maps = new ArrayList<Map>();
    
    private List<MapDisplayer> displayers;
    private StatusGraph statusGraph;
    
    private int tick = 0;
    
    private HashMap<Integer, GameEvent> timedevents = new HashMap<Integer, GameEvent>();
    private HashMap<EnforcingRule, GameEvent> customRuleEvents = new HashMap<EnforcingRule, GameEvent>();
    
    public List<Person> getAllPlayers() {
        ArrayList<Person> people = new ArrayList<Person>();
        for (Map m : maps) {
            for (Person p : m.getPeople()) {
                people.add(p);
            }
        }
        return people;
    }
    
    public void addTimeEvent(int time, GameEvent event) {
        timedevents.put(time, event);
    }
    public void addCustomeEvent(EnforcingRule rule, GameEvent event) {
        customRuleEvents.put(rule, event);
    }
    
    private JFrame mapwindow;
    private JFrame statuswindow;
    
    public Map getMap(int mapno) {
        return maps.get(mapno);
    }
    
    public Game(int[] mapsize, int numberofmaps) {
        for (int i = 0; i<numberofmaps;i++) {
            maps.add(new Map(mapsize[0], mapsize[1]));
        }
    }
    
    public void spawnInMap(int mapno, int people, Brain brain) {
        Map map = maps.get(mapno);
        for (int i = 0; i<people;i++) {
            map.addPerson(new Person(Random.randint(0, map.getSize()[0]),Random.randint(0, map.getSize()[1]), map, brain.duplicate()));
        }
    }
    
    public void addPeopleToAllMaps(int peoplepermap, Brain brain) {
        for (Map map : maps) {
            for (int i = 0; i<peoplepermap;i++) {
                map.addPerson(new Person(Random.randint(0, map.getSize()[0]),Random.randint(0, map.getSize()[1]), map, brain.duplicate()));
            }
        }
    }
    
    private void initDisplayers() {
        displayers = new ArrayList<MapDisplayer>();
        for (Map m : maps) {
            displayers.add(new MapDisplayer(m));
        }
    }
    
    public void go() {
        mapwindow = new JFrame();
        mapwindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mapwindow.setLayout(new FlowLayout());
        
        statuswindow = new JFrame();
        statuswindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        initDisplayers();
        
        for (MapDisplayer d : displayers) {
            mapwindow.add(d);
        }
        
        statusGraph = new StatusGraph(300, 400);
        statuswindow.add(statusGraph);
        
        mapwindow.pack();
        mapwindow.setVisible(true);
        statuswindow.pack();
        statuswindow.setVisible(true);
        
        loop();
    }
    
    public int getTick() {
        return tick;
    }
    
    private void loop() {

        while (true) {
            boolean gameended = true;
            for (Person p :this.getAllPlayers()) {
                if (p.getStatus().infectious()) {
                    gameended = false;
                    break;
                }
            }
            
            if (gameended) {
                break;
            }
            
            for(Map m : maps) {
                m.tick();
            }
            for (MapDisplayer d : displayers) {
                d.repaint();
            }
            statusGraph.update(getAllPlayers());
            statusGraph.repaint();
            
            for (int key : timedevents.keySet()) {
                if (key == tick) {
                    timedevents.get(key).onEvent(this);
                }
            }
            
            for (EnforcingRule key : customRuleEvents.keySet()) {
                if (key.shouldEnforce(this)) {
                    customRuleEvents.get(key).onEvent(this);
                }
            }
            
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            tick++;
        }
    }
    
    public void infectOne() {
        List<Person> peeps = this.getAllPlayers();
        peeps.get(Random.randint(0, peeps.size())).setStatus(PersonStatus.INFECTED);
    }
}
