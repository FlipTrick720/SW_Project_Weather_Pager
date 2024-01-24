package at.qe.skeleton.internal.repositories;

import at.qe.skeleton.internal.model.RolChangeLog;
import at.qe.skeleton.internal.model.Userx;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface RolChangeLogRepository extends AbstractRepository <RolChangeLog, Long>, Serializable {

    public RolChangeLog findAllByUser (Userx userx);

    @Query("SELECT DISTINCT rcl.user FROM RolChangeLog rcl")
    public List<Userx> findAllUsers();
}