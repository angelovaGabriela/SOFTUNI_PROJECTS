package goldDigger.core;

import goldDigger.common.ConstantMessages;
import goldDigger.common.ExceptionMessages;
import goldDigger.models.discoverer.Anthropologist;
import goldDigger.models.discoverer.Archaeologist;
import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.discoverer.Geologist;
import goldDigger.models.operation.Operation;
import goldDigger.models.operation.OperationImpl;
import goldDigger.models.spot.Spot;
import goldDigger.models.spot.SpotImpl;
import goldDigger.repositories.DiscovererRepository;
import goldDigger.repositories.SpotRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class
 ControllerImpl implements Controller {
    private final DiscovererRepository discovererRepository;
    private final SpotRepository spotRepository;

    private final Operation operation;



    public ControllerImpl() {
        this.discovererRepository = new DiscovererRepository();
        this.spotRepository = new SpotRepository();
        this.operation = new OperationImpl();
    }
// Archaeologist
//Anthropologist
//Geologist
    @Override
    public String addDiscoverer(String kind, String discovererName) {
        Discoverer discoverer;
        switch (kind) {
            case "Archaeologist":
                discoverer = new Archaeologist(discovererName);
                break;
            case "Anthropologist":
                discoverer = new Anthropologist(discovererName);
                break;
            case "Geologist":
                discoverer = new Geologist(discovererName);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.DISCOVERER_INVALID_KIND);
        }
        this.discovererRepository.add(discoverer);
        return String.format(ConstantMessages.DISCOVERER_ADDED, kind, discovererName);
    }

    @Override
    public String addSpot(String spotName, String... exhibits) {
        Spot spot = new SpotImpl(spotName);
        spot.getExhibits().add(Arrays.toString(exhibits));
        this.spotRepository.add(spot);
        return String.format(ConstantMessages.SPOT_ADDED, spotName);
    }

    @Override
    public String excludeDiscoverer(String discovererName) {
        Discoverer discoverer = this.discovererRepository.byName(discovererName);
        if (discoverer == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DISCOVERER_DOES_NOT_EXIST, discovererName));
        }
        this.discovererRepository.remove(discoverer);
        return String.format(ConstantMessages.DISCOVERER_EXCLUDE, discovererName);
    }

    @Override
    public String inspectSpot(String spotName) {
        int excludedDiscoverers = 0;
        List<Discoverer> suitable = new ArrayList<>();
        for (Discoverer discoverer : this.discovererRepository.getCollection()) {
            if (discoverer.getEnergy() > 45) {
                suitable.add(discoverer);
            } else {
               this.excludeDiscoverer(discoverer.getName());
               excludedDiscoverers++;
            }
            if (suitable.isEmpty()) {
                throw new IllegalArgumentException(ExceptionMessages.SPOT_DISCOVERERS_DOES_NOT_EXISTS);
            }

        }
        Spot spot = this.spotRepository.byName(spotName);
        this.operation.startOperation(spot, suitable);

        return String.format(ConstantMessages.INSPECT_SPOT, spotName, excludedDiscoverers);

        // TODO: You call each of the discoverers and pick only the ones that have energy above 45 units.
        //• If you don't have any suitable discoverers, throw an IllegalArgumentException with the following message: "You must have at least one discoverer to inspect the spot."
        //• After a mission, you must return the following message with the name of the inspected spot and the count of the discoverers that had excluded on the mission:
        //"The spot {spotName} was inspected. {excludedDiscoverers} discoverers have been excluded on this operation."
    }

    @Override
    public String getStatistics() {

        StringBuilder stringBuilder = new StringBuilder();
        int inspectedSpotCount = this.spotRepository.getCollection().size();

        stringBuilder.append(String.format("%d spots were inspected.", inspectedSpotCount)).append(System.lineSeparator());
        stringBuilder.append("Information for the discoverers:").append(System.lineSeparator());
        for (Discoverer discoverer : this.discovererRepository.getCollection()) {
            stringBuilder.append(discoverer.toString()).append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }
}
