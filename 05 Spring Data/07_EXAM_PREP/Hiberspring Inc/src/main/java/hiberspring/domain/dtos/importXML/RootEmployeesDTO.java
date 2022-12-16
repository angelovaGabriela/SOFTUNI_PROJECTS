package hiberspring.domain.dtos.importXML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootEmployeesDTO {

    @XmlElement(name = "employee")
    private Set<EmployeeDTO> employees;

    public Set<EmployeeDTO> getEmployees() {
        return employees;
    }
}
