package fr.kelig.springboot.boilerplate.application.profile;

import fr.kelig.springboot.boilerplate.domain.profile.ProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteProfileById {

    private final ProfileDao profileDao;
    private final FindProfileById findProfileById;

    @Autowired
    public DeleteProfileById(ProfileDao profileDao, FindProfileById findProfileById) {
        this.profileDao = profileDao;
        this.findProfileById = findProfileById;
    }

    public void execute(String id) {
        findProfileById.execute(id);
        profileDao.deleteById(id);
    }
}
