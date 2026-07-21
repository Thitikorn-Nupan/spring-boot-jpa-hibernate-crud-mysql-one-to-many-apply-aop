package com.ttknp.logic_layer.controllers;

import com.ttknp.logic_layer.entities.Author;
import com.ttknp.logic_layer.services.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// import java.time.Instant;
@CrossOrigin(origins = "*", allowedHeaders = "*") // ** way to set allow all cross origins
@RestController
@RequestMapping(value = "/api/author")
@Slf4j
public class AuthorControl {

    private final AuthorService authorService;

    @Autowired
    public AuthorControl(AuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping(value = {"/",""},method = RequestMethod.GET)
    private ResponseEntity checkServer() {
        return ResponseEntity
                .ok()
                .body("hello world");
    }

    @GetMapping(value = "/reads")
    private ResponseEntity getAllAuthors() {
        return ResponseEntity
                .ok()
                .body(authorService.getAllAuthors());
    }

    @GetMapping(value = "/read")
    private ResponseEntity getAuthorById(@RequestParam String aid) {
        return ResponseEntity
                .ok()
                .body(authorService.getAuthorById(aid));
    }

    /** For Web App */
    @PostMapping(value = "/create")
    private ResponseEntity saveAuthor(@RequestBody Author author) {
        return ResponseEntity.ok().body(authorService.saveAuthor(author));
    }
    @PutMapping(value = "/update")
    private ResponseEntity updateAuthor(@RequestBody Author author, @RequestParam String aid) throws RuntimeException {
        return ResponseEntity.status(202).body(authorService.editAuthor(author, aid));
    }

    /**
    // For Android App
    // In kotlin I found this way for passing params data to api and map it by @RequestParam
    // why I don't pass json
    // because StringRequest() method I used can't pass Json
    @PostMapping(value = "/create")
    private ResponseEntity saveAuthorForAndroid(@RequestParam Map<String,String> body) {
        Author author = new Author();
        author.setFullname(body.get("fullname"));
        author.setAge(Short.valueOf(body.get("age")));
        author.setAid(body.get("aid"));
        author.setAlive(Boolean.valueOf(body.get("alive")));
        log.debug("(saveAuthor) author : {}", author); // (saveAuthor) author : Author{aid='A003', fullname='Don Don', age=43, alive=false}
        return ResponseEntity.ok().body(authorService.saveAuthor(author));
    }
    @PutMapping(value = "/update")
    private ResponseEntity updateAuthorForAndroid(@RequestParam Map<String,String> body,@RequestParam String aid) {
        Author author = new Author();
        author.setFullname(body.get("fullname"));
        author.setAge(Short.valueOf(body.get("age")));
        author.setAlive(Boolean.valueOf(body.get("alive")));
        log.debug("(editAuthor) author : {}", author); // (saveAuthor) author : Author{aid='A003', fullname='Don Don', age=43, alive=false}
        return ResponseEntity.ok().body(authorService.editAuthor(author,aid));
    }
    */

    /**  For Android App */
    @DeleteMapping(value = "/delete")
    private ResponseEntity removeAuthor(@RequestParam String aid) {
        return ResponseEntity.ok().body(authorService.deleteAuthor(aid));
    }

}
