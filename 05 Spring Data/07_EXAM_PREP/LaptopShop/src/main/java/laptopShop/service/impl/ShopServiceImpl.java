package laptopShop.service.impl;

import laptopShop.model.Shop;
import laptopShop.model.Town;
import laptopShop.model.dtos.importShops.ImportShopsDTO;
import laptopShop.model.dtos.importShops.ShopImportDTO;
import laptopShop.repository.ShopRepository;
import laptopShop.repository.TownRepository;
import laptopShop.service.ShopService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
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
public class ShopServiceImpl implements ShopService {
    private static final Path path = Path.of("src" ,"main", "resources", "files", "xml", "shops.xml");
    private final ShopRepository shopRepository;
    private final ModelMapper modelMapper;
    private final Unmarshaller unmarshaller;
    private final Validator validator;
    private final TownRepository townRepository;


    public ShopServiceImpl(ShopRepository shopRepository,
                           TownRepository townRepository) throws JAXBException {
        this.shopRepository = shopRepository;
        this.townRepository = townRepository;
        this.modelMapper = new ModelMapper();

        JAXBContext context = JAXBContext.newInstance(ImportShopsDTO.class);
        this.unmarshaller = context.createUnmarshaller();

        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }

    @Override
    public boolean areImported() {
        return this.shopRepository.count() > 0;
    }

    @Override
    public String readShopsFileContent() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importShops() throws JAXBException, FileNotFoundException {
        ImportShopsDTO importShopsDTO =
                (ImportShopsDTO) unmarshaller
                        .unmarshal(new FileReader(path.toAbsolutePath().toString()));

        List<String> result = new ArrayList<>();

        for (ShopImportDTO shopImportDTO: importShopsDTO.getShops()) {

            Set<ConstraintViolation<ShopImportDTO>> validationErrors = this.validator.validate(shopImportDTO);

            if (validationErrors.isEmpty()) {
                Optional<Shop> optionalShop = this.shopRepository.findByName(shopImportDTO.getName());

                if (optionalShop.isEmpty()){
                    Shop shop = this.modelMapper.map(shopImportDTO, Shop.class);

                    Optional<Town> town = this.townRepository
                            .findByName(shop.getTown().getName());

                    shop.setTown(town);

                    String message = "Successfully imported Shop " + shop.getName() + " - " + shop.getIncome();
                    result.add(message);

                    this.shopRepository.save(shop);
                } else {
                    result.add("Invalid shop");
                }
            } else {
                result.add("Invalid shop");
            }
        }
        return String.join("\n", result);
    }
}
