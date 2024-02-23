package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Book;
import org.example.dto.AddBookRequest;
import org.example.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Book save(AddBookRequest request){
        return bookRepository.save(request.toEntity());
    }
}
