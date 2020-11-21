package com.simpleSBApps.restboilerlate.repositories;

import com.simpleSBApps.restboilerlate.models.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource(path = "users", collectionResourceRel = "users")
public interface UserRestRepository extends PagingAndSortingRepository<User, UUID> {

    public List<User> findByRole(String role);
}
