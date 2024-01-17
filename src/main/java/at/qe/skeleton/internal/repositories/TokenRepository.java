package at.qe.skeleton.internal.repositories;

import at.qe.skeleton.internal.model.Token;

import java.io.Serializable;

/**
 * Repository for managing {@link Token} entities.
 */
public interface TokenRepository extends AbstractRepository<Token, Long>, Serializable {

    Token findByToken(String token);
    Token findByUserUsername(String username);
}
