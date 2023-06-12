package com.mahroz.identity.service.service;

import com.mahroz.identity.service.config.CustomUserDetails;
import com.mahroz.identity.service.dto.RefreshTokenDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Value("${jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    private final List<CustomUserDetails> userList = List.of(new CustomUserDetails("Alex","$2a$10$cZg4tVooPvf1C3kNjU./X.e.TUAE6km0G2oP4xI7xQfGjvB47wwFi"));
    private final List<RefreshTokenDto> refreshTokenList = new ArrayList<>();

    public RefreshTokenDto createRefreshToken(String userName){

     var user=  userList
             .stream()
             .filter(u->u.getUsername().equalsIgnoreCase(userName))
             .findFirst()
             .orElseThrow(()-> new RuntimeException("USER NOT FOUND!"));

        RefreshTokenDto refreshToken = new RefreshTokenDto(user.getUsername(), UUID.randomUUID().toString(), Instant.now().plusMillis(refreshTokenDurationMs));
        refreshTokenList.add(refreshToken); // save it in db for prod or redis

        return refreshToken;
    }

    public Optional<RefreshTokenDto> findByRefreshToken(String token){
        var refreshToken = refreshTokenList
                .stream()
                .filter(t->t.refreshToken().equals(token)).findFirst();
        return refreshToken;
    }

    public RefreshTokenDto validateRefreshToken(RefreshTokenDto refreshTokenDto){
        if(refreshTokenDto.expiryDate().compareTo(Instant.now()) >0 ){
            // delete refresh token
            refreshTokenList.removeIf(s->s.userName().equalsIgnoreCase(refreshTokenDto.userName()));

            throw new RuntimeException("TOKEN WAS EXPIRED!!");
        }else {
            return refreshTokenDto;
        }
    }


}
