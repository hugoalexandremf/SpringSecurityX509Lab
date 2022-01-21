package SpringSecurityX509Lab.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfiguration {

     private static final Logger LOG = LoggerFactory.getLogger(SecurityConfiguration.class);

     @Configuration
     @Order(1)
     public static class X509ClientAuthConfigurationAdmin extends WebSecurityConfigurerAdapter {

          @Autowired
          private X509CustomUserDetailsService x509CustomUserDetailsService;

          @Override
          protected void configure(AuthenticationManagerBuilder auth) throws Exception {

          }

          @Override
          protected void configure(HttpSecurity http) throws Exception {
               http
                    .antMatcher("/alwaysController/**")
                    .csrf().disable()
                    .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .x509()
                    .authenticationUserDetailsService(x509CustomUserDetailsService)
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
          }

     }

     @Configuration
     public static class X509ClientAuthConfigurationStateless extends WebSecurityConfigurerAdapter {

          @Autowired
          private X509CustomUserDetailsService x509CustomUserDetailsService;

          @Override
          protected void configure(AuthenticationManagerBuilder auth) throws Exception {

          }

          @Override
          protected void configure(HttpSecurity http) throws Exception {
               http
                    .antMatcher("/**")
                    .csrf().disable()
                    .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .x509()
                    .authenticationUserDetailsService(x509CustomUserDetailsService)
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
          }

          @Override
          @Bean
          public AuthenticationManager authenticationManagerBean() throws Exception {
               return super.authenticationManagerBean();
          }

     }

}
