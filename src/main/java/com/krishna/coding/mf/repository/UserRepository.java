package com.krishna.coding.mf.repository;

import com.krishna.coding.mf.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {

   Optional<UserInfo> findByUsername(String username);

}
