package fr.kelig.springboot.boilerplate.infrastructure.profile.persitence;

import fr.kelig.springboot.boilerplate.domain.profile.Profile;
import fr.kelig.springboot.boilerplate.domain.profile.ProfileDao;
import fr.kelig.springboot.boilerplate.infrastructure.common.mapper.ProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaProfileDao implements ProfileDao {

    private final JpaProfileRepository repository;
    private final ProfileMapper profileMapper;

    @Autowired
    public JpaProfileDao(JpaProfileRepository repository, ProfileMapper profileMapper){
        this.repository = repository;
        this.profileMapper = profileMapper;
    }

    public List<Profile> findAll(){
        return repository.findAll()
                .stream()
                .map(profileMapper::toDomain)
                .collect(Collectors.toList()
                );
    }

    public Optional<Profile> findById(String id){
        return repository.findById(id)
                .map(profileMapper::toDomain);
    }

    public Optional<Profile> findByUsername(String username) {
        return repository.findByUsername(username)
                .map(profileMapper::toDomain);
    }

    public String save(Profile profile){
        JpaProfile jpaProfile = profileMapper.toEntity(profile);
        repository.save(jpaProfile);
        return jpaProfile.getId();
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
