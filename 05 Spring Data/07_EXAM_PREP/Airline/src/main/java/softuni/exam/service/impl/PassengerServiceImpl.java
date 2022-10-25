package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.importJSON.PassengerImportDTO;
import softuni.exam.models.entities.Passenger;
import softuni.exam.models.entities.Town;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.PassengerService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PassengerServiceImpl implements PassengerService {
    private final static Path path = Path.of("src","main", "resources", "files", "json", "passengers.json");

    private final PassengerRepository passengerRepository;
    private final TownRepository townRepository;
    private final Gson gson;
    private final Validator validator;
    private final ModelMapper modelMapper;


    @Autowired
    public PassengerServiceImpl(PassengerRepository passengerRepository,
                                TownRepository townRepository, Gson gson,
                                Validator validator,
                                ModelMapper modelMapper) {
        this.passengerRepository = passengerRepository;
        this.townRepository = townRepository;
        this.gson = gson;
        this.validator = validator;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return this.passengerRepository.count() > 0;
    }

    @Override
    public String readPassengersFileContent() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importPassengers() throws IOException {
        String json = this.readPassengersFileContent();
        PassengerImportDTO[] passengers = this.gson.fromJson(json, PassengerImportDTO[].class);

        List<String> result = new ArrayList<>();
        for (PassengerImportDTO importPassenger : passengers) {

            Set<ConstraintViolation<PassengerImportDTO>> validationErrors
                    = this.validator.validate(importPassenger);
            if (validationErrors.isEmpty()) {
                Optional<Passenger> optionalPassenger
                        = this.passengerRepository
                        .findByEmail(importPassenger.getEmail());

                if (optionalPassenger.isPresent()) {
                    result.add("Invalid Passenger");
                } else {
                    Passenger passenger = this.modelMapper.map(importPassenger, Passenger.class);

                    Optional<Town> town = this.townRepository.findByName(importPassenger.getTown());
                    passenger.setTown(town.get());

                    this.passengerRepository.save(passenger);
                    String message = String.format("Successfully imported Passenger %s - %s",
                            passenger.getLastName(), passenger.getEmail());
                    result.add(message);
                }
            } else {
                result.add("Invalid Passenger");
            }
        }
        return String.join("\n", result);
    }

    @Override
    public String getPassengersOrderByTicketsCountDescendingThenByEmail() {
        List<Passenger> passengers
                = this.passengerRepository
                .getPassengersOrderByTicketsDescThenByEmail();

       return passengers.stream()
                .map(Passenger::toString)
                .collect(Collectors.joining("\n"));
    }
}
