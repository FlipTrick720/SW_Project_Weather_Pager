package at.qe.skeleton.internal.services;

import at.qe.skeleton.internal.model.Token;
import at.qe.skeleton.internal.model.Userx;
import at.qe.skeleton.internal.repositories.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
@Scope("application")
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;
    public void createVerificationToken (Userx user, String token){
        Token myToken = new Token(token, user);
        tokenRepository.save(myToken);
    }

    public Userx getUserByConfirmationToken(String token) {
        Token tokenEntity =  tokenRepository.findByToken(token);
        if(tokenEntity != null){
            return tokenEntity.getUser();
        } else {
            return null;
        }
    }

    public void deleteToken(Token token){
        tokenRepository.delete(token);
    }

    public Token findByTokenString(String token){
        return tokenRepository.findByToken(token);
    }

    public String generateTokenString() {
        return UUID.randomUUID().toString();
    }
}
