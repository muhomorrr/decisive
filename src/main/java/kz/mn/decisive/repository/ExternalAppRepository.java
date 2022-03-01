package kz.mn.decisive.repository;

import kz.mn.decisive.model.ExternalApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalAppRepository extends JpaRepository<ExternalApp, Long> {
}
