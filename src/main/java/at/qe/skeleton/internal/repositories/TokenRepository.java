package at.qe.skeleton.internal.repositories;

import at.qe.skeleton.internal.model.Token;
/**
 * Repository for managing {@link Token} entities.
 */
public interface TokenRepository extends AbstractRepository<Token, Long>{

    Token findByToken(String token);
    Token findByUserUsername(String username);
}
