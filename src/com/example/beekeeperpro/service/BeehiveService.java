package com.example.beekeeperpro.service;

import com.example.beekeeperpro.model.Beehive;
import com.example.beekeeperpro.repository.BeehiveRepository;

import java.util.List;

public class BeehiveService {

    private final BeehiveRepository beehiveRepository;

    public BeehiveService(BeehiveRepository beehiveRepository) {
        this.beehiveRepository = beehiveRepository;
    }

    public void ensureBeehiveExists(int number) {
        if (!beehiveRepository.existByNumber(number)) {
            throw new IllegalArgumentException("Beehive number " + number + " does not exist");
        }
    }

    public void ensureBeehiveNotExists(int number) {
        if (beehiveRepository.existByNumber(number)) {
            throw new IllegalArgumentException("Beehive number " + number + " already exists");
        }
    }

    public Beehive getBeehiveByNumber(int number) {
        if (!beehiveRepository.existByNumber(number)) {
            throw new IllegalArgumentException("Beehive number " + number + " does not exist");
        }
        return beehiveRepository.findBeehiveByNumber(number);
    }

    public List<Beehive> getBeehivesByApiaryId(int apiaryId) {
        return beehiveRepository.getBeehivesByApiaryId(apiaryId);
    }

    public void insertBeehive(Beehive beehive) {
        ensureBeehiveNotExists(beehive.getNumber());
        beehiveRepository.insertBeehive(beehive);
    }

    public void updateBeehive(Beehive beehive) {
        ensureBeehiveExists(beehive.getNumber());
        beehiveRepository.updateBeehive(beehive);
    }

    public void deleteBeehive(int number) {
        ensureBeehiveExists(number);
        beehiveRepository.deleteBeehive(number);
    }
}
