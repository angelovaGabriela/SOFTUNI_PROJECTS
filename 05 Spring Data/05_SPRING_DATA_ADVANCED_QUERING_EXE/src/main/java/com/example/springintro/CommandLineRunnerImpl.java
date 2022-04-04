package com.example.springintro;

import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.BookSummary;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
       // seedData();

        Scanner scanner = new Scanner(System.in);

       // 11. String title = scanner.nextLine();
       // BookSummary summary =  this.bookService.getInformationForTitle(title);

       // System.out.println(summary.getTitle() + " " + summary.getEditionType() + " " +
       //         " " + summary.getAgeRestriction() + " " + summary.getPrice());



        // 10.this.authorService.getWithTotalCopies()
        //        .forEach(a -> System.out.println(
        //                a.getFirstName() + " " + a.getLastName() +
        //                        " - " + a.getTotalCopies()));


       // 09. int length = Integer.parseInt(scanner.nextLine());
       // int totalBooks = this.bookService.countBooksWithTitleLongerThan(length);
      //  System.out.printf("There are %d books with longer title than %d symbols", totalBooks, length);


      // 08. String search = scanner.nextLine();
      //   this.bookService.findByAuthorLastNameStartsWith(search)
      //          .forEach(book -> System.out.printf("%s (%s %s)%n",
      //                  book.getTitle(), book.getAuthor().getFirstName(),
      //                      book.getAuthor().getLastName()));



      // 07. String search = scanner.nextLine();
      //  this.bookService.findAllTitlesContaining(search)
      //          .forEach(System.out::println);




     // 06. String endsWith = scanner.nextLine();
     //   this.authorService.findByFirstNameEndingWith(endsWith)
     //           .stream()
     //           .map(author -> author.getFirstName() + " " + author.getLastName())
     //            .forEach(System.out::println);



      // 05. String date = scanner.nextLine();
      //  this.bookService.findBooksReleasedBefore(date)
      //          .forEach(book -> System.out.printf("%s %s %.2f%n",
      //                  book.getTitle(), book.getEditionType(), book.getPrice()));



      // 04. this.bookService.findNotReleasedIn(releaseYear)
      //        .forEach(book -> System.out.println(book.getTitle()));

       // 03.this.bookService.findAllWithPriceBetween(5, 40)
       //       .forEach(book -> System.out.println(book.getTitle() + " - " + book.getPrice()));

       //02.  this.bookService.findAllTitlesByEditionAndCopies(EditionType.GOLD, 5000)
       //    .forEach(System.out::println);


       //01. this.bookService.findByAgeRestriction(restriction)
       //   .forEach(System.out::println);

    }



    private void printALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
