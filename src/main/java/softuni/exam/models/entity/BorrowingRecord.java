package softuni.exam.models.entity;

import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table (name = "borrowing_records")
@Validated
public class BorrowingRecord extends BaseEntity{
    @Column (name = "borrow_date", nullable = false)
    private LocalDate borrowDate;

    @Column
    private String remarks;

    @Column (name = "return_date", nullable = false)
    private LocalDate returnDate;

    @ManyToOne
    private Book book;

    @ManyToOne
    private LibraryMember member;

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }


    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }


    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LibraryMember getMember() {
        return member;
    }

    public void setMember(LibraryMember member) {
        this.member = member;
    }
}
