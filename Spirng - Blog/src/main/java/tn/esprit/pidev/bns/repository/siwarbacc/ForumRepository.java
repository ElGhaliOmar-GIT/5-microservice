package tn.esprit.pidev.bns.repository.siwarbacc;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import tn.esprit.pidev.bns.entity.siwarbacc.Forum;

import java.util.List;

public interface ForumRepository extends CrudRepository<Forum, Integer> {
    @Query("SELECT f FROM Forum f WHERE f.content LIKE %?1% OR f.title LIKE %?1%")
    List<Forum> search(String keyword);

    List<Forum> findByContentOrTitleLike(String content, String title);





}
