package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.BorrowingRecord;
import softuni.exam.models.entity.Genre;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {


    Optional<BorrowingRecord> findByBookTitleAndMemberId(String title, Long id);


    List<BorrowingRecord> findByBookGenreAndBorrowDateBeforeOrderByBorrowDateDesc(Genre genre, LocalDate before);
}
