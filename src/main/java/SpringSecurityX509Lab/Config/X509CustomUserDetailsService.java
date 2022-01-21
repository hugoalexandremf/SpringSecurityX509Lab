package SpringSecurityX509Lab.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

@Service
public class X509CustomUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

     private static final Logger LOG = LoggerFactory.getLogger(X509CustomUserDetailsService.class);

     @Override
     public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {

          X509Certificate cert = (X509Certificate) token.getCredentials();
          LOG.info("DN:" + cert.getSubjectDN());
          String serialNumber_16 = cert.getSerialNumber().toString(16);
          LOG.info("SERIAL_16: " + serialNumber_16);

          return new org.springframework.security.core.userdetails.User(cert.getSubjectDN().getName(), "N/A", true,
               true, true, true, getGrantedAuthorities());
     }

     private List<GrantedAuthority> getGrantedAuthorities() {

          List<GrantedAuthority> authorities = new ArrayList<>();

          authorities.add(new SimpleGrantedAuthority("testList"));
          authorities.add(new SimpleGrantedAuthority("testList2"));

          LOG.info("Authorities: " + authorities);
          return authorities;
     }

}
