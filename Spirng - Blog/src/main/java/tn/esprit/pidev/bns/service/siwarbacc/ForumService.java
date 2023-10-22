package tn.esprit.pidev.bns.service.siwarbacc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.bns.entity.siwarbacc.Forum;
import tn.esprit.pidev.bns.repository.siwarbacc.ForumRepository;
import tn.esprit.pidev.bns.serviceInterface.siwarbacc.IForumService;

import java.util.List;

@Slf4j
@Service
public class ForumService implements IForumService {
    @Autowired
    ForumRepository forumRepository;

    public List<Forum> retrieveAllForums() {
        return (List<Forum>) forumRepository.findAll();
    }

    public Forum updateForum(Forum f) {
        return forumRepository.save(f);
    }

    public Forum addForum(Forum f) {
        return forumRepository.save(f);
    }

    public Forum retrieveForum(Integer idForum) {
        return forumRepository.findById(idForum).orElse(null);
    }

    public void removeForum(Integer idForum) {
        Forum f = retrieveForum(idForum);
        forumRepository.delete(f);
    }

    public List<Forum> searchForums(String keyword) {
        return forumRepository.findByContentOrTitleLike(keyword,keyword);
    }



}
