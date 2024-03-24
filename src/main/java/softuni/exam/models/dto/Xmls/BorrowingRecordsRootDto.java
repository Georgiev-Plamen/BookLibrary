package softuni.exam.models.dto.Xmls;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "borrowing_records")
@XmlAccessorType(XmlAccessType.FIELD)
public class BorrowingRecordsRootDto {

    @XmlElement(name = "borrowing_record")
    List<BorrowingRecordsSeedDto>  borrowingRecordsSeedDtos;

    public BorrowingRecordsRootDto() {
    }

    public BorrowingRecordsRootDto(List<BorrowingRecordsSeedDto> borrowingRecordsSeedDtos) {
        this.borrowingRecordsSeedDtos = borrowingRecordsSeedDtos;
    }

    public List<BorrowingRecordsSeedDto> getBorrowingRecordsSeedDtos() {
        return borrowingRecordsSeedDtos;
    }

    public BorrowingRecordsRootDto setBorrowingRecordsSeedDtos(List<BorrowingRecordsSeedDto> borrowingRecordsSeedDtos) {
        this.borrowingRecordsSeedDtos = borrowingRecordsSeedDtos;
        return this;
    }
}
