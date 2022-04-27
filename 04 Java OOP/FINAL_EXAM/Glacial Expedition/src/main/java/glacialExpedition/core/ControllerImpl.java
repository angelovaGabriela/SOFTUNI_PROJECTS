package glacialExpedition.core;

import glacialExpedition.common.ConstantMessages;
import glacialExpedition.common.ExceptionMessages;
import glacialExpedition.models.explorers.AnimalExplorer;
import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.explorers.GlacierExplorer;
import glacialExpedition.models.explorers.NaturalExplorer;
import glacialExpedition.models.states.State;
import glacialExpedition.models.states.StateImpl;
import glacialExpedition.repositories.ExplorerRepository;
import glacialExpedition.repositories.Repository;
import glacialExpedition.repositories.StateRepository;

import java.util.Collection;
import java.util.Collections;

public class ControllerImpl implements Controller {

    private Repository<Explorer> explorerRepository;
    private Repository<State> stateRepository;


    public ControllerImpl () {
        this.explorerRepository = new ExplorerRepository();
        this.stateRepository = new StateRepository();

    }

    @Override
    public String addExplorer(String type, String explorerName) {
        Explorer explorer = null;

        switch (type) {
            case "AnimalExplorer":
                explorer = new AnimalExplorer(explorerName);
                break;
            case "GlacierExplorer":
                explorer = new GlacierExplorer(explorerName);
                break;
            case "NaturalExplorer":
                explorer = new NaturalExplorer(explorerName);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.EXPLORER_INVALID_TYPE);
        }
        this.explorerRepository.add(explorer);

        return String.format(ConstantMessages.EXPLORER_ADDED, type, explorerName);

    }

    @Override
    public String addState(String stateName, String... exhibits) {
        State state = new StateImpl(stateName);
        Collection<String> stateExhibits = state.getExhibits();
        Collections.addAll(stateExhibits, exhibits);
        this.stateRepository.add(state);

        return String.format(ConstantMessages.STATE_ADDED, stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {
        Explorer explorer = explorerRepository.byName(explorerName);

        if (explorer == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.EXPLORER_DOES_NOT_EXIST, explorerName));
        }
        this.explorerRepository.remove(explorer);
        return String.format(ConstantMessages.EXPLORER_RETIRED, explorerName);
    }

    @Override
    public String exploreState(String stateName) {
        // Дали имаме изследователи с повече от 50 енергия
        // Ако нямаме - > Exception
       return null;

        // Ако имаме, започваме мисията
        // Да върнем, колко изследователи са се изтощили

    }

    @Override
    public String finalResult() {
        return null;
    }
}
