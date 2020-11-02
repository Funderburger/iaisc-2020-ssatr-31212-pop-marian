package mas.ssatr.pop.marian.petriNetModel;

import java.util.ArrayList;

public class Place {
    private String name;
    private int tokens;
    private ArrayList<String> nextT;

    public Place(){
        super();
    }

    public Place(String name, int tokens, ArrayList<String> nextT){
        this.name = name;
        this.tokens = tokens;
        this.nextT = nextT;
    }

    public String getName() {
        return name;
    }


    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    public ArrayList<String> getNextT() {
        return nextT;
    }

}
