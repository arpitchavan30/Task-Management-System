package com.example.Task.Management.Repository;

import com.example.Task.Management.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    @Query(value = "SELECT MAX(CAST(SUBSTRING(user_id, 4) AS SIGNED)) FROM USER WHERE user_id LIKE 'usr%'", nativeQuery = true)
    Long findLatestUserSequenceNumber();

    @Query(value = "SELECT MAX(CAST(SUBSTRING(user_id, 4) AS SIGNED)) FROM USER WHERE user_id LIKE 'adm%'", nativeQuery = true)
    Long findLatestAdminSequenceNumber();


    Optional<User> findByUserId(String userId);
}
