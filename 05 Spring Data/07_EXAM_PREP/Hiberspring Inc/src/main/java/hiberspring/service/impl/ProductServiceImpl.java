package hiberspring.service.impl;

import hiberspring.common.Constants;
import hiberspring.domain.dtos.importXML.ProductDTO;
import hiberspring.domain.dtos.importXML.RootProductsDTO;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Product;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.ProductRepository;
import hiberspring.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {
    private final static Path path = Path.of("src","main", "resources", "files", "products.xml");

    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;
    private final Validator validator;
    private final ModelMapper modelMapper;
    private final Unmarshaller unmarshaller;

    public ProductServiceImpl(ProductRepository productRepository,
                              BranchRepository branchRepository,
                              Validator validator,
                              ModelMapper modelMapper) throws JAXBException {
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
        this.validator = validator;
        this.modelMapper = modelMapper;

        JAXBContext context = JAXBContext.newInstance(RootProductsDTO.class);
        this.unmarshaller = context.createUnmarshaller();
    }

    @Override
    public Boolean productsAreImported() {
        return this.productRepository.count() > 0;
    }

    @Override
    public String readProductsXmlFile() throws IOException {
        return Files.readString(path);
    }

    @Override
    public String importProducts() throws JAXBException {
        RootProductsDTO root = (RootProductsDTO) this.unmarshaller
                .unmarshal(new File(path.toAbsolutePath().toString()));


        List<String> result = new ArrayList<>();
        for (ProductDTO product: root.getProducts()) {
            Set<ConstraintViolation<ProductDTO>> validationErrors
                    = this.validator.validate(product);

            if(validationErrors.isEmpty()) {
                Optional<Product> optionalProduct
                        = this.productRepository.findByName(product.getName());

                if (optionalProduct.isPresent()) {
                    result.add(Constants.INCORRECT_DATA_MESSAGE);
                } else {
                    Product productToSave = this.modelMapper.map(product, Product.class);
                    Optional<Branch> branch = this.branchRepository.findByName(product.getBranch());

                    productToSave.setBranch(branch.get());

                    this.productRepository.save(productToSave);

                    result.add(String.format("Successfully imported Product %s.", productToSave.getName()));
                }

            } else {
                result.add(Constants.INCORRECT_DATA_MESSAGE);
            }
        }


        return String.join("\n", result);
    }
}
