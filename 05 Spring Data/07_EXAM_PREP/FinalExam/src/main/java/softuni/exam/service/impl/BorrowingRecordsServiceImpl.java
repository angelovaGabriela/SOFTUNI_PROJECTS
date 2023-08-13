package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportBorrowingRecordsDTO;
import softuni.exam.models.dto.RootBorrowingRecordsDTO;
import softuni.exam.models.entity.Book;
import softuni.exam.models.entity.BorrowingRecord;
import softuni.exam.models.entity.Genre;
import softuni.exam.models.entity.LibraryMember;
import softuni.exam.repository.BookRepository;
import softuni.exam.repository.BorrowingRecordRepository;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.BorrowingRecordsService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BorrowingRecordsServiceImpl implements BorrowingRecordsService {

    private static final String RECORDS_FILE_PATH = "src/main/resources/files/xml/borrowing-records.xml";

    private final BorrowingRecordRepository borrowingRecordRepository;
    private final BookRepository bookRepository;
    private final LibraryMemberRepository libraryMemberRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;
    private final Unmarshaller unmarshaller;

    public BorrowingRecordsServiceImpl(BorrowingRecordRepository borrowingRecordRepository, BookRepository bookRepository, LibraryMemberRepository libraryMemberRepository, ModelMapper modelMapper, Validator validator) throws JAXBException {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.bookRepository = bookRepository;
        this.libraryMemberRepository = libraryMemberRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        JAXBContext context = JAXBContext.newInstance(RootBorrowingRecordsDTO.class);
        this.unmarshaller = context.createUnmarshaller();
    }

    @Override
    public boolean areImported() {
        return this.borrowingRecordRepository.count() > 0;
    }

    @Override
    public String readBorrowingRecordsFromFile() throws IOException {
        return Files.readString(Path.of(RECORDS_FILE_PATH));
    }

    @Override
    public String importBorrowingRecords() throws IOException, JAXBException {

        RootBorrowingRecordsDTO importRecordsDTO = (RootBorrowingRecordsDTO) this.unmarshaller.unmarshal(new FileReader(RECORDS_FILE_PATH));
        List<String> result = new ArrayList<>();


        for (ImportBorrowingRecordsDTO importDTO : importRecordsDTO.getBorrowingRecords())  {
            Set<ConstraintViolation<ImportBorrowingRecordsDTO>> validationErrors = this.validator.validate(importDTO);

            if (validationErrors.isEmpty()) {
//
                    Optional<Book> book = this.bookRepository.findByTitle(importDTO.getBook().getTitle());
                    Optional<LibraryMember> member = this.libraryMemberRepository.findById(importDTO.getMember().getId());


                    if (book.isEmpty() || member.isEmpty()) {
                        result.add("Invalid borrowing record");
                        continue;
                    }


//
                    BorrowingRecord record = this.modelMapper.map(importDTO, BorrowingRecord.class);

                    record.setBook(book.get());
                    record.setMember(member.get());



                this.borrowingRecordRepository.save(record);

                    String message = String.format("Successfully imported borrowing record %s - %s", record.getBook().getTitle(), record.getBorrowDate());
                    result.add(message);


            } else {
                result.add("Invalid borrowing record");
            }
        }
        return String.join("\n", result);
    }

    @Override
    public String exportBorrowingRecords() {

        LocalDate before = LocalDate.of(2021, 9, 10);

        Genre genre = Genre.valueOf("SCIENCE_FICTION");

       List<BorrowingRecord> records = this.borrowingRecordRepository.findByBookGenreAndBorrowDateBeforeOrderByBorrowDateDesc(genre, before);


       return records.stream()
               .map(BorrowingRecord::toString)
               .collect(Collectors.joining("\n"));

    }
}
