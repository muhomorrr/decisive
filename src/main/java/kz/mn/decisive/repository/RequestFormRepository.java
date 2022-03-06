package kz.mn.decisive.repository;

import kz.mn.decisive.model.RequestForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestFormRepository extends JpaRepository<RequestForm, Long> {
}
