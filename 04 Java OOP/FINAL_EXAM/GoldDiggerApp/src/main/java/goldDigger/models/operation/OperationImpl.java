package goldDigger.models.operation;

import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.spot.Spot;

import java.util.Collection;


public class OperationImpl implements Operation {
    public OperationImpl() {
    }

    @Override
    public void startOperation(Spot spot, Collection<Discoverer> discoverers) {
//if energy = 0 exclude
        Collection<String> exhibits = spot.getExhibits();

        while (!discoverers.isEmpty() && !exhibits.isEmpty()) {

            for (Discoverer discoverer : discoverers) {
//TODO: https://stackoverflow.com/questions/26479394/concurrentmodificationexception-checkforcomodificationarraylist-java
                    for (String exhibit : exhibits) {
                        while (discoverer.canDig() && !exhibits.isEmpty()) {
                            if (!exhibit.isEmpty()) {
                                discoverer.dig();
                                discoverer.getMuseum().getExhibits().add(exhibit);
                                exhibits.remove(exhibit);

                                }

                            }
                        if (!exhibits.isEmpty()) {
                            break;
                        }
                    }
                }

            }
        }

        //TODO: Discoverers cannot go on expeditions if their energy is below 0.
        //• They leave the base and start collecting exhibits one by one.
        //• If they find an exhibit, their energy is decreased.
        //• They add the exhibit to their museum. The exhibit should then be removed from the state.
        //• Discoverers cannot continue collecting exhibits if their energy drops to 0.
        //o If their energy drops to 0, the next discoverer starts inspecting
    }

