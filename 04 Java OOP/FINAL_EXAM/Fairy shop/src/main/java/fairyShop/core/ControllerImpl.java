package fairyShop.core;

import fairyShop.common.ConstantMessages;
import fairyShop.common.ExceptionMessages;
import fairyShop.models.Helper;
import fairyShop.models.Instrument;
import fairyShop.models.Present;
import fairyShop.models.Shop;
import fairyShop.models.entities.*;
import fairyShop.repositories.HelperRepository;
import fairyShop.repositories.PresentRepository;

import java.util.ArrayList;
import java.util.List;


public class ControllerImpl implements Controller {

    private final HelperRepository helpers;
    private final PresentRepository presents;

    public ControllerImpl() {
        this.helpers = new HelperRepository();
        this.presents = new PresentRepository();
    }

    @Override
    public String addHelper(String type, String helperName) {
        Helper helper;

        if (type.equals("Happy")) {
            helper = new Happy(helperName);
        } else if (type.equals("Sleepy")) {
            helper = new Sleepy(helperName);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.HELPER_TYPE_DOESNT_EXIST);
        }
        this.helpers.add(helper);
        return String.format(ConstantMessages.ADDED_HELPER, type, helperName);
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {
        Instrument instrument = new InstrumentImpl(power);

        if (this.helpers.findByName(helperName) == null) {
            throw new IllegalArgumentException(ExceptionMessages.HELPER_DOESNT_EXIST);
        }

        for (Helper helper : this.helpers.getModels()) {
            if (helper.getName().equals(helperName)) {
                helper.addInstrument(instrument);
            }
        }

        return String.format(
                ConstantMessages.SUCCESSFULLY_ADDED_INSTRUMENT_TO_HELPER,
                power, helperName);
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {
        Present present = new PresentImpl(presentName, energyRequired);
        this.presents.add(present);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_PRESENT, presentName);
    }

    @Override
    public String craftPresent(String presentName) {

        //•	The helpers that you should select are the ones with energy above 50 units.
        List<Helper> helpersToCraft = new ArrayList<>();
        for (Helper helper : helpers.getModels()) {
            if (helper.getEnergy() > 50) {
                helpersToCraft.add(helper);
            }
        }

        //•	If no helpers are ready, throw IllegalArgumentException with the following message:
        //"There is no helper ready to start crafting!"
        if (helpersToCraft.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.NO_HELPER_READY);
        }

        // the suitable helpers start working on a gift

        Shop shop = new ShopImpl();
        Present present = null;
        int instrumentsCount = 0;

        // for each gift of the repository
        for (Present gift : this.presents.getModels()) {
            if (gift.getName().equals(presentName)) {
                present = gift;
        // for each helper of the suitable ones
                for (Helper helperToCraft : helpersToCraft) {
        // gift crafting
                    shop.craft(gift, helperToCraft);
                }
        // for each suitable helper
                for (Helper helperToCraft : helpersToCraft) {
        // for each instrument of helpers instruments
                    for (Instrument instrument : helperToCraft.getInstruments()) {
        // count the broken ones for the final report
                        if (instrument.isBroken()) {
                            instrumentsCount++;
                        }
                    }
                }
            }
        }
        //if present == null here is the bug
        assert present != null;
        // present crafted
        if (present.isDone()) {
            return String.format(ConstantMessages.PRESENT_DONE, presentName, "done") + String.format(ConstantMessages.COUNT_BROKEN_INSTRUMENTS, instrumentsCount);
        }
        // present not crafted
        return String.format(ConstantMessages.PRESENT_DONE, presentName, "not done") + String.format(ConstantMessages.COUNT_BROKEN_INSTRUMENTS, instrumentsCount);

    }
        @Override
        public String report () {

            StringBuilder stringBuilder = new StringBuilder();
            int countPresents = 0;
            int countInstruments = 0;

            for (Present model : this.presents.getModels()) {
                if(model.isDone()){
                    countPresents++;
                }
            }
            stringBuilder.append(String.format("%d presents are done!", countPresents)).append(System.lineSeparator())
                    .append("Helpers info:").append(System.lineSeparator());

            for (Helper model : this.helpers.getModels()) {

                stringBuilder.append(String.format("Name: %s", model.getName())).append(System.lineSeparator())
                        .append(String.format("Energy: %d", model.getEnergy())).append(System.lineSeparator());

                for (Instrument instrument : model.getInstruments()) {
                    if(!instrument.isBroken()){
                        countInstruments++;
                    }
                }
                stringBuilder.append(String.format("Instruments: %d not broken left", countInstruments))
                        .append(System.lineSeparator());
                countInstruments = 0;
            }
            return stringBuilder.toString().trim();
        }
    }



