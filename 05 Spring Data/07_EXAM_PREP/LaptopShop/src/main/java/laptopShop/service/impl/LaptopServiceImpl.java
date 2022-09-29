package laptopShop.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import laptopShop.model.Laptop;
import laptopShop.model.Shop;
import laptopShop.model.dtos.importLaptops.LaptopsImportDTO;
import laptopShop.repository.LaptopRepository;
import laptopShop.repository.ShopRepository;
import laptopShop.service.LaptopService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class LaptopServiceImpl implements LaptopService {
    private final static Path path = Path.of("src", "main", "resources", "files", "json", "laptops.json");

    private final LaptopRepository laptopRepository;
    private final ShopRepository shopRepository;

    private final Gson gson;
    private final ModelMapper modelMapper;
    private final Validator validator;



    @Autowired
    public LaptopServiceImpl(LaptopRepository laptopRepository, ShopRepository shopRepository) {
        this.laptopRepository = laptopRepository;
        this.shopRepository = shopRepository;

        this.modelMapper = new ModelMapper();
        this.gson = new GsonBuilder().create();
        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }

    @Override
    public boolean areImported() {
        return this.laptopRepository.count() > 0;
    }

    @Override
    public String readLaptopsFileContent() throws IOException {
        return Files.readString(path);
    }

    @Override
    @Transactional
    public String importLaptops() throws IOException {
        String json = readLaptopsFileContent();

        LaptopsImportDTO[] importDTOs = this.gson.fromJson(json, LaptopsImportDTO[].class);

        List<String> result = new ArrayList<>();
        for (LaptopsImportDTO laptopImport : importDTOs) {

            Set<ConstraintViolation<LaptopsImportDTO>> validationErrors
                    = this.validator.validate(laptopImport);

            if (validationErrors.isEmpty()){
                Optional<Laptop> optionalLaptop = this.laptopRepository.findByMacAddress(laptopImport.getMacAddress());

                if (optionalLaptop.isEmpty()) {
                    Laptop laptop = this.modelMapper.map(laptopImport, Laptop.class);

                    Optional<Shop> shop = this.shopRepository.findByName(laptopImport.getShop().getName());
                    laptop.setShop(shop.get());

                    this.laptopRepository.save(laptop);

                    String message = String.format("Successfully imported Laptop %s – %.2f – %d - %d",
                            laptop.getMacAddress(), laptop.getCpuSpeed(), laptop.getRam(), laptop.getStorage());

                    result.add(message);

                } else {
                    result.add("Invalid Laptop");
                }

            } else {
                result.add("Invalid Laptop");
            }
        }

       return String.join("\n", result);
    }

    @Override
    public String exportBestLaptops() {
        return null;
    }
}
