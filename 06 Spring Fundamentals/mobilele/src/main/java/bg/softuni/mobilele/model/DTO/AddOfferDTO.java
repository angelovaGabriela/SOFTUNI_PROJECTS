package bg.softuni.mobilele.model.DTO;

import bg.softuni.mobilele.model.enums.EngineEnum;
import com.sun.istack.NotNull;

public class AddOfferDTO {

    @NotNull
    private EngineEnum engine;

    public EngineEnum getEngine() {
        return engine;
    }

    public AddOfferDTO setEngine(EngineEnum engine) {
        this.engine = engine;
        return this;
    }
}
