package fr.kelig.springboot.boilerplate.application.profile;

import fr.kelig.springboot.boilerplate.domain.profile.Profile;
import fr.kelig.springboot.boilerplate.domain.profile.ProfileDao;
import fr.kelig.springboot.boilerplate.infrastructure.common.exception.ProfileWithUsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FindProfileByUsername {

    private final ProfileDao profileDao;

    public FindProfileByUsername(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    public Profile execute(String username){
        return profileDao.findByUsername(username)
                .orElseThrow(() -> new ProfileWithUsernameNotFoundException(username));
    }
}
