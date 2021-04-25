package fr.kelig.springboot.boilerplate.domain.profile;

import java.util.List;
import java.util.Optional;

public interface ProfileDao {

    List<Profile> findAll();

    Optional<Profile> findById(String id);

    Optional<Profile> findByUsername(String username);

    String save(Profile profile);

    void deleteById(String id);
}
