package fr.kelig.springboot.boilerplate.application.profile;

import fr.kelig.springboot.boilerplate.domain.profile.Profile;
import fr.kelig.springboot.boilerplate.domain.profile.ProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddProfile {

    private final ProfileDao profileDao;

    @Autowired
    public AddProfile(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    public String execute(Profile profile) {
        return profileDao.save(profile);
    }
}
