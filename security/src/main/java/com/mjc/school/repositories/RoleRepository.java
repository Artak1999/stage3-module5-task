package com.mjc.school.repositories;

import com.mjc.school.enumiration.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role,Integer> {
}
