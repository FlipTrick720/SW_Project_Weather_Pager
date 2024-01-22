package at.qe.skeleton.internal.repositories;

import at.qe.skeleton.internal.model.RollChangeLog;
import at.qe.skeleton.internal.model.Userx;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface RollChangeLogRepository extends AbstractRepository <RollChangeLog, Long>, Serializable {

    public RollChangeLog findAllByUser (Userx userx);
}
