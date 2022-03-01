package kz.mn.decisive.repository;

import kz.mn.decisive.model.Requester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequesterRepository extends JpaRepository<Requester, Long> {
}
