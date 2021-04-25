package fr.kelig.springboot.boilerplate.application.profile;

import fr.kelig.springboot.boilerplate.domain.profile.Profile;
import fr.kelig.springboot.boilerplate.domain.profile.ProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllProfiles {

    private final ProfileDao profileDao;

    @Autowired
    public FindAllProfiles(ProfileDao profileDao){
        this.profileDao = profileDao;
    }

    public List<Profile> execute(){
        return profileDao.findAll();
    }
}
