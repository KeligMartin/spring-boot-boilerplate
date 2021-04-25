package fr.kelig.springboot.boilerplate.application.profile;

import fr.kelig.springboot.boilerplate.domain.profile.Profile;
import fr.kelig.springboot.boilerplate.domain.profile.ProfileDao;
import fr.kelig.springboot.boilerplate.infrastructure.common.exception.ResourceWithIdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindProfileById {

    private final ProfileDao profileDao;

    @Autowired
    public FindProfileById(ProfileDao profileDao){
        this.profileDao = profileDao;
    }

    public Profile execute(String id){
        return profileDao.findById(id)
                .orElseThrow(() -> new ResourceWithIdNotFoundException("profile", id));
    }
}
