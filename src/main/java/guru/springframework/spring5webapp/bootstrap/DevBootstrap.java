package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    private void initData() {
        Author eric = new Author("Eric", "Evans");
        Book book1 = new Book("Domain Driver Design", "1234");
        Publisher harper = new Publisher("Harper Collins", "123 Adams st. cali");
        eric.getBooks().add(book1);
        book1.getAuthors().add(eric);
        book1.setPublisher(harper);

        publisherRepository.save(harper);
        authorRepository.save(eric);
        bookRepository.save(book1);

        Author elik = new Author("Elik", "Katzav");
        Book book2 = new Book("Haunted Blood", "23456");
        Publisher workx = new Publisher("Worx", "567 Moshe st. Petach Tikva");

        elik.getBooks().add(book2);
        book2.getAuthors().add(elik);
        book2.setPublisher(workx);

        publisherRepository.save(workx);
        authorRepository.save(elik);
        bookRepository.save(book2);
    }

}
