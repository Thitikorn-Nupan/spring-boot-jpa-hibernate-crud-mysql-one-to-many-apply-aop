package com.ttknp.logic_layer.services;

import com.ttknp.logic_layer.entities.Author;
import com.ttknp.logic_layer.repositories.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AuthorService {

    private final AuthorRepo authorRepo;

    @Autowired
    public AuthorService(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    public Iterable<Author> getAllAuthors() {
        return authorRepo.findAll();
    }

    public Optional<Author> getAuthorById(String aid) {
        return authorRepo.findById(aid);
    }

    public Optional<Author> saveAuthor(Author author) {
        return Optional.of(authorRepo.save(author));
    }

    // i'll catch this err by aop ** AfterThrowing()
    public Optional<Author> editAuthor(Author author, String aid)  throws RuntimeException {
        return Optional.ofNullable(authorRepo.findById(aid).map(searchAuthor -> {
            searchAuthor.setFullname(author.getFullname());
            searchAuthor.setAge(author.getAge());
            searchAuthor.setAlive(author.getAlive());
            return authorRepo.save(searchAuthor);
            // if not found will do orElseThrow(...) block
        }).orElseThrow(() -> new RuntimeException("May not found author id : " + aid)));
    }

    public Boolean deleteAuthor(String aid) {
        Optional<Author> author = authorRepo.findById(aid);
        if (author.isPresent()) {
            if (author.get().getEditHistories().isEmpty()) {
                authorRepo.delete(author.get());
                return true;
            }
        }
        return false;
    }
}
