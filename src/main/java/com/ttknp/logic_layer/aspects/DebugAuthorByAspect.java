package com.ttknp.logic_layer.aspects;

import com.ttknp.logic_layer.entities.Author;
import com.ttknp.logic_layer.entities.EditHistory;
import com.ttknp.logic_layer.services.AuthorService;
import com.ttknp.logic_layer.services.EditHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;

@Aspect
@Component
@Slf4j
public class DebugAuthorByAspect {

    private final EditHistoryService editHistoryService;
    private final AuthorService authorService;

    @Autowired
    public DebugAuthorByAspect(EditHistoryService editHistoryService , AuthorService authorService) {
        this.editHistoryService = editHistoryService;
        this.authorService = authorService;
    }

    // @After annotation work after your method done
    @After("execution(* com.ttknp.logic_layer.services.AuthorService.editAuthor(..))")
    public void logAfterUpdatedAuthor(JoinPoint joinPoint) {
        log.debug("(After) Enter: {} , Method: {} Return: {} ", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        /*
            (After)
            Enter: com.ttknp.logic_layer.services.AuthorService ,
            Method: editAuthor
            Return: [Author(aid=null, fullname=Kevin Slider, age=37, alive=false, editHistories=null), A001]
        */
        /*
            EditHistory editHistory = getEditHistory(joinPoint.getArgs());
            boolean isEditHistoryCreated = this.editHistoryService.createEditHistory(editHistory).isPresent();
            if (isEditHistoryCreated) log.debug("EditHistory is created");
            else log.debug("EditHistory is not created");
        */
    }


    @Before("execution(* com.ttknp.logic_layer.services.AuthorService.editAuthor(..))")
    public void logBeforeUpdatedAuthor(JoinPoint joinPoint) {
        log.debug("(Before) Enter: {} , Method: {} Return: {} ", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        EditHistory editHistory = getEditHistory(joinPoint.getArgs());
        boolean isEditHistoryCreated = this.editHistoryService.createEditHistory(editHistory).isPresent();
        if (isEditHistoryCreated) log.debug("EditHistory is created");
        else log.debug("EditHistory is not created");
        /*
            (Before)
            Enter: com.ttknp.logic_layer.services.AuthorService ,
            Method: editAuthor
            Return: [Author(aid=null, fullname=Kevin Slider, age=37, alive=false, editHistories=null), A001]
        */
    }

    @AfterReturning(value = "execution(* com.ttknp.logic_layer.services.AuthorService.editAuthor(..))", returning = "author")
    public void logAfterReturningUpdatedAuthor(JoinPoint joinPoint, Optional<Author> author) {
        log.debug("(AfterReturning) Enter: {} , Method: {} Return: {} , Optional<Author> : {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()), author);
        log.debug("(AfterReturning) Author : {}", author.get());
        /*
            (AfterReturning)
            Enter: com.ttknp.logic_layer.services.AuthorService ,
            Method: editAuthor
            Return: [Author{aid='null', fullname='Kevin Nash', age=40, alive=true}, A001] ,
            Author : Optional[Author{aid='A001', fullname='Kevin Nash', age=40, alive=true}]
        */
    }

    // ** catch all exceptions on service layer
    @AfterThrowing(value = "execution(* com.ttknp.logic_layer.services.AuthorService.*(..))", throwing = "exception")
    public void logAfterThrowingAuthorService(Exception exception) {
        log.debug("(AfterThrowing) Exception : {}", exception.getMessage());
    }

    private EditHistory getEditHistory(Object[] signatureArgs) {
        // way to convert object array to object of EditHistory class
        // Object signatureArg = signatureArgs[0];
        String aid = (String) signatureArgs[1];
        Author author = authorService.getAuthorById(aid).get();
        Integer unixTimestamp = (int) Instant.now().getEpochSecond();
        EditHistory editHistory = new EditHistory();
        editHistory.setFullname(author.getFullname());
        editHistory.setAge(author.getAge());
        editHistory.setAlive(author.getAlive());
        editHistory.setAuthor(author);
        editHistory.setDatetimeEdit(unixTimestamp);
        return editHistory;
    }
}
