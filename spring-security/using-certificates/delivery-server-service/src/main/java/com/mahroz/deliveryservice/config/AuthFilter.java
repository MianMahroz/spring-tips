package com.mahroz.deliveryservice.config;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class AuthFilter extends OncePerRequestFilter {

    /**
     * This method filters the request and gathers the DN. So it can be verified in this
     * filter.
     *
     * @param request - the http request
     * @param response - the response to be handed over to the next filter
     * @param filterChain - the filter chain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        X509Certificate[] certs = (X509Certificate[]) request
                .getAttribute("jakarta.servlet.request.X509Certificate");
        if (certs != null && certs.length > 0) {
            log.info("X.509 client authentication certificate: {}", certs[0]);
        }
        String dn = certs[0].getSubjectX500Principal().getName();
        log.info(gatherSubjectMap(dn).get("CN"));
        // do the auth logic allow or throw 401
        doFilter(request, response, filterChain);
    }

    /**
     * This code gets the map of LDAP name
     * @param dn
     * @return map - of cn,subject, city, etc,.
     */
    public Map<String, String> gatherSubjectMap(String dn) {
        Map<String, String> dnMap = new HashMap<>();
        if (dn != null) {
            dnMap = Arrays.stream(dn.split(",")).sequential().map(item -> item.split("="))
                    .collect(Collectors.toMap(part -> part[0], part -> part[1]));
        }
        return dnMap;
    }

}
