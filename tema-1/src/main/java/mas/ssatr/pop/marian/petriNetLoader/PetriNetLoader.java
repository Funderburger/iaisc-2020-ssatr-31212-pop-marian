package mas.ssatr.pop.marian.petriNetLoader;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import mas.ssatr.pop.marian.petriNetModel.PetriNetModel;

import java.io.File;
import java.io.IOException;

public class PetriNetLoader {
    private String jsonFile;

    public PetriNetLoader(String jsonFile) throws IOException {
        this.jsonFile = jsonFile;
    }

    public PetriNetModel load() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(MapperFeature.AUTO_DETECT_FIELDS,true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        PetriNetModel petriNetModel = objectMapper.readValue(new File(jsonFile),PetriNetModel.class);
        return petriNetModel;
    }

}
