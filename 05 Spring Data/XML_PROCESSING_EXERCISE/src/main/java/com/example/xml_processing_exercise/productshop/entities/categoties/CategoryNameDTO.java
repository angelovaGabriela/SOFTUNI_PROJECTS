package com.example.xml_processing_exercise.productshop.entities.categoties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryNameDTO {

    @XmlElement
    private String name;

    public CategoryNameDTO() {}

    public String getName() {
        return name;
    }
}
