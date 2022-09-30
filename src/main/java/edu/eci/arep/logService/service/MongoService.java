package edu.eci.arep.logService.service;

import edu.eci.arep.logService.persistence.Repository;

public class MongoService {

    private final Repository mongoRepository;

    {
        mongoRepository = new Repository();
    }

    public String uploadAStringAndGetLastStrings(String newString) {
        mongoRepository.saveNewString(newString);
        return mongoRepository.getLastStrings(10);
    }
}
