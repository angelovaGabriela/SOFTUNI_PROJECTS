package hiberspring.domain.dtos.importXML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootProductsDTO {

    @XmlElement(name = "product")
    private List<ProductDTO> products;

    public List<ProductDTO> getProducts() {
        return products;
    }
}
