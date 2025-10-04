package ru.itis.repository.User;

import ru.itis.models.User;

import java.util.List;

public interface UserRepository {

    void saveUserByFullInfo(String firstName, String lastName, int age);

    List<User> findByCar();

    List<User> findByCountry();

    List<User> findBySalary();


}
