package kz.mn.decisive.repository;

import kz.mn.decisive.model.Decider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeciderRepository extends JpaRepository<Decider, Long> {
    Decider findByUsername(String username);
}
