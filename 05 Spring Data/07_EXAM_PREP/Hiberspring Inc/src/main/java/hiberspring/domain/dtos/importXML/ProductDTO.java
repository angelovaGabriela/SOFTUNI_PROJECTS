package hiberspring.domain.dtos.importXML;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ProductDTO {


    @XmlAttribute
    @NotNull
    private String name;

    @XmlAttribute
    @NotNull
    private int clients;

    @XmlElement
    private String branch;

    public String getName() {
        return name;
    }

    public int getClients() {
        return clients;
    }

    public String getBranch() {
        return branch;
    }
}
