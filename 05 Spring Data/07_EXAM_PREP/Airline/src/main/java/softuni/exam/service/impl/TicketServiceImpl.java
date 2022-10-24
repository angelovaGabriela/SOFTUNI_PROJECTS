package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.importXML.tickets.RootImportTicketDTO;
import softuni.exam.models.dtos.importXML.tickets.TicketImportDTO;
import softuni.exam.models.entities.Passenger;
import softuni.exam.models.entities.Plane;
import softuni.exam.models.entities.Ticket;
import softuni.exam.models.entities.Town;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.repository.TicketRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TicketService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TicketServiceImpl implements TicketService {
    private static final Path path = Path.of("src" ,"main", "resources", "files", "xml", "tickets.xml");
    private final TicketRepository ticketRepository;
    private final TownRepository townRepository;
    private final PassengerRepository passengerRepository;
    private final PlaneRepository planeRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;


    private final Unmarshaller unmarshaller;

    public TicketServiceImpl(TicketRepository ticketRepository,
                             TownRepository townRepository,
                             PassengerRepository passengerRepository,
                             PlaneRepository planeRepository,
                             ModelMapper modelMapper,
                             Validator validator) throws JAXBException {
        this.ticketRepository = ticketRepository;
        this.townRepository = townRepository;
        this.passengerRepository = passengerRepository;
        this.planeRepository = planeRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;

        JAXBContext context = JAXBContext.newInstance(RootImportTicketDTO.class);
        this.unmarshaller = context.createUnmarshaller();
    }


    @Override
    public boolean areImported() {
        return this.ticketRepository.count() > 0;
    }

    @Override
    public String readTicketsFileContent() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importTickets() throws FileNotFoundException, JAXBException {
        RootImportTicketDTO tickets = (RootImportTicketDTO) this.unmarshaller.unmarshal(new FileReader(path.toAbsolutePath().toString()));

        List<String> result = new ArrayList<>();

        for (TicketImportDTO importTicket : tickets.getTickets()) {

            Set<ConstraintViolation<TicketImportDTO>> validationErrors
                    = this.validator.validate(importTicket);
            if (validationErrors.isEmpty()) {
                Optional<Ticket> optionalTicket =
                        this.ticketRepository.findBySerialNumber(importTicket.getSerialNumber());
                if (optionalTicket.isEmpty()){
                    Ticket ticket = this.modelMapper.map(importTicket, Ticket.class);

                    Optional<Town> fromTown = this.townRepository.findByName(importTicket.getFromTown().getName());
                    Optional<Town> toTown = this.townRepository.findByName(importTicket.getToTown().getName());

                    Optional<Passenger> passenger = this.passengerRepository.findByEmail(importTicket.getPassenger().getEmail());
                    Optional<Plane> plane = this.planeRepository.findByRegisterNumber(importTicket.getPlane().getRegisterNumber());

                    ticket.setFromTown(fromTown.get());
                    ticket.setToTown(toTown.get());
                    ticket.setPassenger(passenger.get());
                    ticket.setPlane(plane.get());

                    this.ticketRepository.save(ticket);

                    String message = String.format("Successfully imported Ticket %s - %s",
                            ticket.getFromTown().getName(), ticket.getToTown().getName());
                    result.add(message);

                } else {
                    result.add("Invalid Ticket");
                }
            } else {
                result.add("Invalid Ticket");
            }

        }
        return String.join("\n", result);
    }
}
