package com.simpleSBApps.restboilerlate.repositories;

import com.simpleSBApps.restboilerlate.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {

    public List<User> findByRole(String role);
    public List<User> findByName(String name);
}
