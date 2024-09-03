package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.repository.AuthorRepository;
import exercise.validator.UtilValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    // BEGIN
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    @Autowired
    UtilValidator validator;

    public List<AuthorDTO> getAll() {
        var authors = authorRepository.findAll();
        return authors.stream().map(authorMapper::map).toList();
    }

    public AuthorDTO getById(Long id) {
        var author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Author with id %s Not found", id)));
        return authorMapper.map(author);

    }

    public AuthorDTO create(AuthorCreateDTO authorCreateDTO) {
        var authorToCreate = authorMapper.map(authorCreateDTO);
        validator.validate(authorToCreate);
        var author = authorRepository.save(authorToCreate);
        return authorMapper.map(author);
    }

    public AuthorDTO update(AuthorUpdateDTO authorUpdateDTO, Long id) {
        var authorToUpdate = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Author with id %s Not found", id)));
        authorMapper.update(authorUpdateDTO, authorToUpdate);
        validator.validate(authorToUpdate);
        var author = authorRepository.save(authorToUpdate);
        return authorMapper.map(author);

    }

    public void delete(Long id) {
        var author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Author with id %s Not found", id)));
        authorRepository.deleteById(id);
    }
    // END
}
