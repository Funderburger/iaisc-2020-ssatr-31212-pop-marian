package mas.ssatr.pop.marian.simulationLogic;

import mas.ssatr.pop.marian.petriNetModel.PetriNetModel;
import mas.ssatr.pop.marian.petriNetModel.Place;
import mas.ssatr.pop.marian.petriNetModel.Transition;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SimulationLogic {
    private PetriNetModel petriNetModel;
    private int time = 0;
    private Map<String, Integer> placesWithTokens = new HashMap<>();
    private Map<String, Integer> transitionsWithTimes = new HashMap<>();
    private boolean isPetriNetActive = true;
    private ArrayList<String> nameOfTransitionsInExecution = new ArrayList<>();
    private int noTransitionInExecution = 0;

    public SimulationLogic(PetriNetModel petriNetModel) {
        this.petriNetModel = petriNetModel;
    }

    public void startSimulation() {
        while (isPetriNetActive) {
            this.simulate();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time++;
        }
    }


    private void simulate() {
        isPetriNetActive = true;


        for (Place p : petriNetModel.getPlaces()) {
            if (p.getTokens() > 0) {
                placesWithTokens.put(p.getName(), p.getTokens());
            }
        }

        for (Transition t : petriNetModel.getTransitions()) {
            if (isTransitionActive(t)) {
                nameOfTransitionsInExecution.add(t.getName());
                transitionsWithTimes.put(t.getName(), time + randomTime(t));
                for (String placeName : t.getPrevP()) {
                    placesWithTokens.put(placeName, placesWithTokens.get(placeName) - 1);
                    if (placesWithTokens.get(placeName) == 0) {
                        placesWithTokens.remove(placeName);
                    }
                }
            }


            if (transitionsWithTimes.containsKey(t.getName())) {
                if (transitionsWithTimes.get(t.getName()) == time) {
                    nameOfTransitionsInExecution.remove(t.getName());
                    for (String placeName : t.getNextP()) {
                        if (!placesWithTokens.containsKey(placeName)) {
                            placesWithTokens.put(placeName, 1);
                        } else {
                            placesWithTokens.put(placeName, placesWithTokens.get(placeName) + 1);
                        }
                    }

                }
            }

        }

        if (nameOfTransitionsInExecution.size() == 0) {
            if (noTransitionInExecution == 1) {
                isPetriNetActive = false;
            }
            noTransitionInExecution++;
        } else {
            noTransitionInExecution = 0;
        }

        updatePetriNet();
        if(!isPetriNetActive){
            for (Place p : petriNetModel.getPlaces()) {
                writeOutput("Place name: " + p.getName() + " No of tokens: " + p.getTokens() + " At time: " + time);
            }
            writeOutput("#########################################################################################");
        }

    }

    private int randomTime(Transition t) {
        if (t.getTempo().size() > 1) {
            return t.getTempo().get(0) + (int) (Math.random() * (t.getTempo().get(1) - t.getTempo().get(0) + 1));
        }
        return t.getTempo().get(0);
    }

    private void writeOutput(String text) {
        try {
            FileWriter fw = new FileWriter("Output.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(text);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void updatePetriNet() {
        for (Place p : petriNetModel.getPlaces()) {
            if (placesWithTokens.containsKey(p.getName())) {
                p.setTokens(placesWithTokens.get(p.getName()));
            } else {
                p.setTokens(0);
            }
        }

    }

    private boolean isTransitionActive(Transition t) {
        int activationCounter = 0;
        for (String placeName : t.getPrevP()) {
            if (placesWithTokens.containsKey(placeName)) {
                activationCounter++;
            }
        }
        if (activationCounter == t.getPrevP().size()) {
            return true;
        }
        return false;
    }
}
