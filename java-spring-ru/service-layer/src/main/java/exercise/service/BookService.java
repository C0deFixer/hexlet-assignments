package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.dto.BookDTO;
import exercise.exception.ResourceNotFoundException;

import exercise.mapper.BookMapper;
import exercise.repository.BookRepository;
import exercise.validator.UtilValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    UtilValidator validator;

    public List<BookDTO> getAll() {
        var books = bookRepository.findAll();
        return books.stream().map(bookMapper::map).toList();
    }

    public BookDTO getById(Long id) {
        var book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Book with id %s Not found", id)));
        return bookMapper.map(book);

    }

    public BookDTO create(BookCreateDTO bookCreateDTO) {
        var bookToCreate = bookMapper.map(bookCreateDTO);
        validator.validate(bookToCreate);
        var book = bookRepository.save(bookToCreate);
        return bookMapper.map(book);
    }

    public BookDTO update(BookUpdateDTO bookUpdateDTO, Long id) {
        var bookToUpdate = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Book with id %s Not found", id)));
        bookMapper.update(bookUpdateDTO, bookToUpdate);
        validator.validate(bookToUpdate);
        var book = bookRepository.save(bookToUpdate);
        return bookMapper.map(book);

    }

    public void delete(Long id) {
        var book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Book with id %s Not found", id)));
        bookRepository.deleteById(id);
    }
    // END
}
