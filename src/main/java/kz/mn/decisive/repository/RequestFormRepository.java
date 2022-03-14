package kz.mn.decisive.repository;

import kz.mn.decisive.model.RequestForm;
import kz.mn.decisive.model.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RequestFormRepository extends JpaRepository<RequestForm, Long> {
    @Query(value = "select * from request_forms where status = 'SUBMIT'", nativeQuery = true)
    List<RequestForm> findAllByStatus();

    @Modifying
    @Query(value = "update request_forms set status = ?2 WHERE id = ?1", nativeQuery = true)
    void changeStatusById(long id, String status);

    RequestForm findById(long id);

    @Query(value = "select * from request_forms where status = 'SUBMIT' and id = ?1", nativeQuery = true)
    RequestForm findSubmittedById(long id);
}
