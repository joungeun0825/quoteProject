package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Book;
import org.example.dto.AddBookRequest;
import org.example.dto.BookResponse;
import org.example.repository.BookRepository;
import org.example.repository.BookSearch;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public void save(AddBookRequest request){
        bookRepository.save(request.toEntity());
    }
    @Transactional(readOnly = true)
    public List<Book> findAll(){
        return bookRepository.findAll();
    }
    @Transactional(readOnly = true)
    public List<Book> findByTitle(String title){
        return bookRepository.findByTitle(title);
    }
    @Transactional(readOnly = true)
    public List<Book> findByAuthor(String author){
        return bookRepository.findByAuthor(author);
    }
    @Transactional(readOnly = true)
    public List<Book> findByPublisher(String publisher){
        return bookRepository.findByPublisher(publisher);
    }
}
