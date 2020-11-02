package mas.ssatr.pop.marian.petriNetModel;

import java.util.ArrayList;

public class Transition {
    private String name;
    private ArrayList<Integer> tempo;
    private ArrayList<String> nextP;
    private ArrayList<String> prevP;

    public Transition() {
        super();
    }

    public Transition(String name, ArrayList<Integer> tempo, ArrayList<String> nextP, ArrayList<String> prevP) {
        this.name = name;
        this.tempo = tempo;
        this.nextP = nextP;
        this.prevP = prevP;
    }

    public ArrayList<String> getPrevP() {
        return prevP;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getTempo() {
        return tempo;
    }

    public ArrayList<String> getNextP() {
        return nextP;
    }

}
