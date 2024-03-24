package softuni.exam.models.dto.Xmls;

import softuni.exam.util.LocalDateAdapter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;

@XmlRootElement(name = "borrowing_record")
@XmlAccessorType(XmlAccessType.FIELD)
public class BorrowingRecordsSeedDto implements Serializable {

    @XmlElement(name = "borrow_date")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @NotNull
    private LocalDate borrowDate;

    @XmlElement(name = "return_date")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)

    private LocalDate returnDate;

    @XmlElement(name = "book")
    private BookDto book;

    @XmlElement(name = "member")
    private MemberDto member;

    @XmlElement(name = "remarks")
    @Size (min = 3, max = 100)
    @NotNull
    private String remarks;

    public BorrowingRecordsSeedDto(@Valid BookDto book, MemberDto member) {
        this.book = book;
        this.member = member;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public BorrowingRecordsSeedDto() {
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public BookDto getBook() {
        return book;
    }

    public void setBook(BookDto book) {
        this.book = book;
    }

    public MemberDto getMember() {
        return member;
    }

    public void setMember(MemberDto member) {
        this.member = member;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
