package fr.kelig.springboot.boilerplate.infrastructure.profile.persitence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaProfileRepository extends JpaRepository<JpaProfile, String> {
    List<JpaProfile> findAll();

    Optional<JpaProfile> findById(String id);

    Optional<JpaProfile> findByUsername(String username);

    void deleteById(String id);
}
