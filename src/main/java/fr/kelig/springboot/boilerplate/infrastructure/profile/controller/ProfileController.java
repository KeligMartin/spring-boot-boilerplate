package fr.kelig.springboot.boilerplate.infrastructure.profile.controller;

import fr.kelig.springboot.boilerplate.application.profile.DeleteProfileById;
import fr.kelig.springboot.boilerplate.application.profile.FindAllProfiles;
import fr.kelig.springboot.boilerplate.application.profile.FindProfileById;
import fr.kelig.springboot.boilerplate.domain.profile.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final FindAllProfiles findAllProfiles;
    private final FindProfileById findProfileById;
    private final DeleteProfileById deleteProfileById;

    @Autowired
    public ProfileController(FindAllProfiles findAllProfiles,
                             FindProfileById findProfileById,
                             DeleteProfileById deleteProfileById) {
        this.findAllProfiles = findAllProfiles;
        this.findProfileById = findProfileById;
        this.deleteProfileById = deleteProfileById;
    }

    @GetMapping
    public ResponseEntity<List<Profile>> findAllProfiles(){
        return new ResponseEntity<>(findAllProfiles.execute(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> findProfileById(@PathVariable String id) {
        return new ResponseEntity<>(findProfileById.execute(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfileById(@PathVariable String id) {
        deleteProfileById.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
