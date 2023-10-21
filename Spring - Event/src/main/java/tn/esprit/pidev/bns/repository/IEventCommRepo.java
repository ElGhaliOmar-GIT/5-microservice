package tn.esprit.pidev.bns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.bns.entity.EventComment;

@Repository
public interface IEventCommRepo extends JpaRepository<EventComment, Integer> {
}
