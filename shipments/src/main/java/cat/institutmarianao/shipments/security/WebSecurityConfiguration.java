package cat.institutmarianao.shipments.security;

import org.apache.hc.client5.http.classic.HttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.DispatcherType;

@Configuration
public class WebSecurityConfiguration {
	protected static final String LOGIN_URL = "/login";
	protected static final String LOGIN_FAIL_URL = "/loginfailed";
	protected static final String LOGOUT_URL = "/logout";

	protected static final String DEFAULT_SUCCESS_URL = "/";

	protected static final String[] ENDPOINTS_WHITELIST = { "/css/**", LOGIN_URL, LOGIN_FAIL_URL };

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http, AuthenticationProvider authenticationProvider)
			throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class).authenticationProvider(authenticationProvider)
				.build();
	}

	@Bean
	HttpClient httpClient(CustomHttpClientFactory customHttpClientFactory) throws Exception {
		return customHttpClientFactory.getObject();
	}

	@Bean
	RestTemplate restTemplate(HttpClient httpClient) {
		return new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(authorizeHttpRequest -> authorizeHttpRequest
						.dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
						.requestMatchers(ENDPOINTS_WHITELIST).permitAll().anyRequest().authenticated())
				.formLogin(formLogin -> formLogin.loginPage(LOGIN_URL).failureUrl(LOGIN_FAIL_URL))
				.logout(logout -> logout.logoutUrl(LOGOUT_URL).deleteCookies("JSESSIONID"))
				.exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedPage(LOGIN_URL)).build();
	}
}
