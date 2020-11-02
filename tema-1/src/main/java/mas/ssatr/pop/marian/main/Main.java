package mas.ssatr.pop.marian.main;

import mas.ssatr.pop.marian.petriNetLoader.PetriNetLoader;
import mas.ssatr.pop.marian.simulationLogic.SimulationLogic;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String jsonFile = "E:\\MyFiles\\Scoala\\Masterat\\An I\\Sem I\\SSATR\\Laborator\\iaisc-2020-ssatr-31212-pop-marian\\tema-1\\src\\main\\resources\\PetriNet.json";
        PetriNetLoader petriNetLoader = new PetriNetLoader(jsonFile);
        SimulationLogic simulator = new SimulationLogic(petriNetLoader.load());
        simulator.startSimulation();

    }
}
