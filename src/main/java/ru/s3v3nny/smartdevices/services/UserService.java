package ru.s3v3nny.smartdevices.services;

import lombok.SneakyThrows;
import ru.s3v3nny.smartdevices.models.User;
import ru.s3v3nny.smartdevices.repositories.DatabaseRepo;

public class UserService {

    private final JsonConverter converter = new JsonConverter();
    DatabaseRepo databaseRepo = new DatabaseRepo();

    @SneakyThrows
    public boolean checkUUID(String uuid) {
        return databaseRepo.checkUuid(uuid);
    }

    @SneakyThrows
    public void addUser(String body) {
        User user = converter.getUser(body);
        databaseRepo.addUser(user);
    }
}
