package kz.mn.decisive.repository;

import kz.mn.decisive.model.RequestForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestFromRepository extends JpaRepository<RequestForm, Long> {
}
