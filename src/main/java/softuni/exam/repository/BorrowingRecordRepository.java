package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.BorrowingRecord;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Repository
public interface BorrowingRecordRepository extends JpaRepository <BorrowingRecord, Long> {


    Set<BorrowingRecord> findAllBorrowingRecordsByBorrowDateBefore(LocalDate parse);
}
