package glacialExpedition.models.mission;

import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;

import java.util.Collection;
import java.util.List;

public class MissionImpl implements Mission {
    @Override
    public void explore(State state, Collection<Explorer> explorers) {
        Collection<String> stateExhibits = state.getExhibits();
        for (Explorer explorer : explorers) {
            while (explorer.canSearch() && stateExhibits.iterator().hasNext()) {
                String currentExhibit = stateExhibits.iterator().next(); // get the next element from the Collection ;)
                explorer.getSuitcase().getExhibits().add(currentExhibit);
                stateExhibits.remove(currentExhibit);
                explorer.search(); // reduces the energy of the explorer after searching
            }
        }
    }
}
