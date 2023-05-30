package com.mahroz.identity.service.controller;


import com.mahroz.identity.service.config.CustomUserDetails;
import com.mahroz.identity.service.config.UserDetailsServiceImpl;
import com.mahroz.identity.service.dto.AuthRequest;
import com.mahroz.identity.service.dto.RefreshTokenDto;
import com.mahroz.identity.service.dto.UserInfoDto;
import com.mahroz.identity.service.service.JwtService;
import com.mahroz.identity.service.service.RefreshTokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.server.Cookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
//@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private JwtService jwtService;

    private AuthenticationManager authenticationManager;

    private RefreshTokenService refreshTokenService;

    public AuthController(JwtService jwtService, AuthenticationManager authenticationManager, RefreshTokenService refreshTokenService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.refreshTokenService = refreshTokenService;
    }

    @GetMapping("/currentUser")
    public String userDetails(@CookieValue(name="JSESSIONID") String JSESSIONID) {

        return JSESSIONID;
    }

    @PostMapping("/signin")
    public ResponseEntity<UserInfoDto> signin(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtService.generateJwtCookie(userDetails);

        RefreshTokenDto refreshToken = refreshTokenService.createRefreshToken(userDetails.getUsername());

        ResponseCookie jwtRefreshCookie = jwtService.generateRefreshJwtCookie(refreshToken.refreshToken());

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .header(HttpHeaders.SET_COOKIE ,jwtRefreshCookie.toString())
                .body(new UserInfoDto(userDetails.getUsername(),"xyz@org.com"));
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        jwtService.validateToken(token);
        return "Token is valid";
    }


    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principle.toString() != "anonymousUser") {
            // Delete Refresh Token From Db
        }

        ResponseCookie jwtCookie = jwtService.getCleanJwtCookie();
        ResponseCookie jwtRefreshCookie = jwtService.getCleanJwtRefreshCookie();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
                .body("You've been signed out!");
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(HttpServletRequest request) {
        String refreshToken = jwtService.getJwtRefreshFromCookies(request);

        if ((refreshToken != null) && (refreshToken.length() > 0)) {
            return refreshTokenService.findByRefreshToken(refreshToken)
                    .map(refreshTokenService::validateRefreshToken)
                    .map(t-> new CustomUserDetails(t.userName(),""))
                    .map(token -> {
                        ResponseCookie jwtCookie = jwtService.generateJwtCookie(token);

                        return ResponseEntity.ok()
                                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                                .body("Token is refreshed successfully!");
                    })
                    .orElseThrow(() -> new RuntimeException("Refresh token is not in database!"));
        }

        return ResponseEntity.badRequest().body("Refresh Token is empty!");
    }
}

