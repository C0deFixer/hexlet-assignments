package exercise.controller;

import exercise.dto.AuthorDTO;
import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.mapper.AuthorMapper;
import exercise.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorMapper authorMapper;

    // BEGIN
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AuthorDTO>> index() {
        List<AuthorDTO> authors = authorService.getAll();
        return ResponseEntity.ok()
                .header("X-Total-count", String.valueOf(authors.size()))
                .body(authors);

    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthorDTO> show(@PathVariable Long id) {

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(authorService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthorDTO> create(@RequestBody @Valid AuthorCreateDTO authorCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authorService.create(authorCreateDTO));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthorDTO> update(@PathVariable Long id,
                                            @RequestBody @Valid AuthorUpdateDTO authorUpdateDTO) {
        return ResponseEntity.ok().body(authorService.update(authorUpdateDTO, id));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        authorService.delete(id);
    }
    // END
}
