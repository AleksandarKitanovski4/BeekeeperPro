package com.example.beekeeperpro.service;

import com.example.beekeeperpro.model.Apiary;
import com.example.beekeeperpro.repository.ApiaryRepository;

import java.util.List;

public class ApiaryService {

    private final ApiaryRepository apiaryRepository;

    public ApiaryService(ApiaryRepository apiaryRepository) {
        this.apiaryRepository = apiaryRepository;
    }

    public void ensureApiaryExists(String apiaryName) {
        if (!apiaryRepository.existByName(apiaryName)) {
            throw new IllegalArgumentException("Apiary " + apiaryName + " does not exist");
        }
    }

    public void ensureApiaryNotExists(String apiaryName) {
        if (apiaryRepository.existByName(apiaryName)) {
            throw new IllegalArgumentException("Apiary " + apiaryName + " already exists");
        }
    }

    public List<Apiary> getAllApiaries() {
        return apiaryRepository.getAllApiaries();
    }

    public void insertApiary(Apiary apiary) {
        ensureApiaryNotExists(apiary.getName());
        apiaryRepository.insertApiary(apiary);
    }

    public List<Apiary> searchApiariesByName(String name) {
        ensureApiaryExists(name);
        return apiaryRepository.searchApiariesByName(name);
    }

    public void updateApiary(Apiary apiary) {
        ensureApiaryExists(apiary.getName());
        apiaryRepository.updateApiary(apiary);
    }

    public void deleteApiary(String apiaryName) {
        ensureApiaryExists(apiaryName);
        apiaryRepository.deleteApiary(apiaryName);
    }
}
