package es.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true,jsr250Enabled=true,prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		.antMatcher("/spring/**/*.xhtml")
		    .exceptionHandling().authenticationEntryPoint(new AccessDenyEntryPoint())
		.and()
		.authorizeRequests()
			.antMatchers("/spring/resources/**","/spring/login","/spring/signup",
					"/spring/main","/spring/error","/spring/group").permitAll()
			.antMatchers("/spring/myprofile").hasRole("USER")
			.antMatchers("/spring/profilegroup").hasRole("MEMBER")
			.antMatchers("/spring/admin").hasRole("ADMIN")
			.antMatchers("/spring/**/*.xhtml").denyAll()
			.anyRequest().authenticated()
		.and()       
        .formLogin()
            .loginPage("/spring/login")
            .usernameParameter("loginUser").passwordParameter("passwordUser")
            .defaultSuccessUrl("/spring/main",true)
            .failureUrl("/spring/login?login_error=1")
        .and()
        .logout()
            .logoutSuccessUrl("/spring/home")
            .deleteCookies("JSESSIONID")
        .and()
        /*.sessionManagement()
        	.invalidSessionUrl("/spring/main")
        	.maximumSessions(1)
        	.expiredUrl("/spring/error?error_code=2")
        	.maxSessionsPreventsLogin(true)
        .and()*/
        .exceptionHandling().accessDeniedPage("/spring/error?error_code=1")
        .and()


        // Disable CSRF (won't work with JSF) but ensure last HTTP POST request is saved
        // See https://jira.springsource.org/browse/SEC-2498

        .csrf().disable()
        .requestCache()
            .requestCache(new HttpSessionRequestCache());

	}

	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.passwordEncoder(new Md5PasswordEncoder())
			.withUser("keith").password("417c7382b16c395bc25b5da1398cf076").roles("USER", "SUPERVISOR").and()
			.withUser("erwin").password("12430911a8af075c6f41c6976af22b09").roles("USER", "SUPERVISOR").and()
			.withUser("jeremy").password("57c6cbff0d421449be820763f03139eb").roles("USER").and()
			.withUser("scott").password("942f2339bf50796de535a384f0d1af3e").roles("USER");
	}

}
