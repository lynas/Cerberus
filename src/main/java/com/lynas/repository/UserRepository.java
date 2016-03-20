package com.lynas.repository;

import com.lynas.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {

  public AppUser findByUsername(String username);

}
