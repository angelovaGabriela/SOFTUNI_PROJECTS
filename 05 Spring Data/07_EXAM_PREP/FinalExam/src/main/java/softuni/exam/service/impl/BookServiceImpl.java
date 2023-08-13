package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportBooksDTO;
import softuni.exam.models.entity.Book;
import softuni.exam.repository.BookRepository;
import softuni.exam.service.BookService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {
    private static final String BOOKS_FILE_PATH = "src/main/resources/files/json/books.json";

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final Validator validator;

    public BookServiceImpl(BookRepository bookRepository,
                           ModelMapper modelMapper,
                           Gson gson,
                           Validator validator) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
    }


    @Override
    public boolean areImported() {
        return this.bookRepository.count() > 0;
    }

    @Override
    public String readBooksFromFile() throws IOException {
        return Files.readString(Path.of(BOOKS_FILE_PATH));
    }

    @Override
    public String importBooks() throws IOException {
        String json = this.readBooksFromFile();
        ImportBooksDTO[] importBooksDTOS = this.gson.fromJson(json, ImportBooksDTO[].class);

        List<String> result = new ArrayList<>();

        for (ImportBooksDTO importDTO : importBooksDTOS) {
            Set<ConstraintViolation<ImportBooksDTO>> validationErrors =
                    this.validator.validate(importDTO);

            if (validationErrors.isEmpty()) {
                Optional<Book> optionalBook = this.bookRepository.findByTitle(importDTO.getTitle());

                if (optionalBook.isPresent()) {
                    result.add("Invalid book");
                } else {
                    Book book = this.modelMapper.map(importDTO, Book.class);
                    this.bookRepository.save(book);

                    String message = String.format("Successfully imported book %s - %s", book.getAuthor(), book.getTitle());
                    result.add(message);
                }
            } else {
                result.add("Invalid book");
            }
        }

        return String.join("\n", result);
    }
}
