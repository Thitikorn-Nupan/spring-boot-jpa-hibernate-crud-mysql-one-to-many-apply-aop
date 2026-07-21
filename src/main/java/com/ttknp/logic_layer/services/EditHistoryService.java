package com.ttknp.logic_layer.services;

import com.ttknp.logic_layer.entities.EditHistory;
import com.ttknp.logic_layer.repositories.EditHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class EditHistoryService {
    private final EditHistoryRepo editHistoryRepo;

    @Autowired
    public EditHistoryService(EditHistoryRepo editHistoryRepo) {
        this.editHistoryRepo = editHistoryRepo;
    }

    public Optional<EditHistory> createEditHistory(EditHistory editHistory) {
        return Optional.of(editHistoryRepo.save(editHistory));
    }
}
