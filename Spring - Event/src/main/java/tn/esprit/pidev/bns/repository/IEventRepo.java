package tn.esprit.pidev.bns.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev.bns.entity.Event;

@Repository
public interface IEventRepo extends JpaRepository<Event, Integer> {
}
