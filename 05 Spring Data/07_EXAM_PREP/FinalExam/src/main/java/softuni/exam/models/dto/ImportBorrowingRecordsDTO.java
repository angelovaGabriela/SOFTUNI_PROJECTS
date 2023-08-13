package softuni.exam.models.dto;

import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "borrowing_record")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportBorrowingRecordsDTO {


    @NotNull
    @XmlElement(name = "borrow_date")
    private String borrowDate;

    @NotNull
    @XmlElement(name = "return_date")
    private String returnDate;

    @NotNull
    @XmlElement
    private BookDTO book;
    @NotNull
    @XmlElement
    private MemberDTO member;

    @XmlElement
    @Size(min = 3, max = 100)
    private String remarks;

    public String getBorrowDate() {
        return borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public BookDTO getBook() {
        return book;
    }

    public MemberDTO getMember() {
        return member;
    }

    public String getRemarks() {
        return remarks;
    }
}
