package tn.esprit.pidev.bns.serviceInterface.siwarbacc;

import tn.esprit.pidev.bns.entity.siwarbacc.Forum;

import java.util.List;

public interface IForumService {

     Forum retrieveForum(Integer idForum);
    Forum updateForum (Forum f);
     Forum addForum (Forum f);

     void removeForum(Integer idForum);

    List<Forum> retrieveAllForums();



    public List<Forum> searchForums(String keyword);
}
