package com.example.xml_processing_exercise.productshop;

import com.example.xml_processing_exercise.productshop.entities.products.ExportProductsInRangeDTO;
import com.example.xml_processing_exercise.productshop.entities.users.ExportSellersDTO;
import com.example.xml_processing_exercise.productshop.entities.users.ExportSellersWithCountsDTO;
import com.example.xml_processing_exercise.productshop.services.ProductsService;
import com.example.xml_processing_exercise.productshop.services.SeedService;
import com.example.xml_processing_exercise.productshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

@Component
public class ProductShopRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final ProductsService productsService;
    private final UserService userService;

    @Autowired
    public ProductShopRunner(SeedService seedService, ProductsService productsService, UserService userService) {
        this.seedService = seedService;
        this.productsService = productsService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
       // this.seedService.seedAll();
       // this.productInRange();
        //this.findUsersWithSoldProducts();

        this.findUsersWithSoldProductsAndCounts();

    }

    private void findUsersWithSoldProductsAndCounts() throws JAXBException {
        ExportSellersWithCountsDTO dtoData = this.userService.findAllWithSoldProductsAndCounts();

        JAXBContext context = JAXBContext.newInstance(ExportSellersWithCountsDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(dtoData, System.out);

    }

    private void findUsersWithSoldProducts() throws JAXBException {
        ExportSellersDTO result = this.userService.findAllWithSoldProducts();

        JAXBContext context = JAXBContext.newInstance(ExportSellersDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(result, System.out);
    }

    private void productInRange() throws JAXBException {
        ExportProductsInRangeDTO inRange = this.productsService.getInRange(500, 1000);
        JAXBContext context = JAXBContext.newInstance(ExportProductsInRangeDTO.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(inRange, System.out);
    }

}
