package mas.ssatr.pop.marian.petriNetModel;

import java.util.ArrayList;

public class PetriNetModel {
    private ArrayList<Place> places;
    private ArrayList<Transition> transitions;

    public PetriNetModel() {
        super();
    }

    public PetriNetModel(ArrayList<Place> places, ArrayList<Transition> transitions) {
        this.places = places;
        this.transitions = transitions;
    }

    public ArrayList<Place> getPlaces() {
        return places;
    }

    public ArrayList<Transition> getTransitions() {
        return transitions;
    }

}
