package com.example.xml_processing_exercise.productshop.entities.categoties;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesImportDTO {
    @XmlElement(name = "category")
    private List<CategoryNameDTO> categories;

    public CategoriesImportDTO() {}

    public List<CategoryNameDTO> getCategories() {
        return categories;
    }
}
