package cat.institutmarianao.shipments.services.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import cat.institutmarianao.shipments.model.Courier;
import cat.institutmarianao.shipments.model.LogisticsManager;
import cat.institutmarianao.shipments.model.Receptionist;
import cat.institutmarianao.shipments.model.User;
import cat.institutmarianao.shipments.model.User.Role;
import cat.institutmarianao.shipments.model.forms.UsersFilter;
import cat.institutmarianao.shipments.services.UserService;

@Service
@PropertySource("classpath:application.properties")
public class UserServiceImpl implements UserService {

	@Value("${webService.host}")
	private String webServiceHost;

	@Value("${webService.port}")
	private String webServicePort;

	@Autowired
	private RestTemplate restTemplate;

	@Override /* Spring security */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final String baseUri = webServiceHost + ":" + webServicePort + "/users/get/by/username/{username}";

		UriComponentsBuilder uriTemplate = UriComponentsBuilder.fromHttpUrl(baseUri);

		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("username", username);

		User response = restTemplate.getForObject(uriTemplate.buildAndExpand(uriVariables).toUriString(), User.class);
		return response;
	}

	@Override
	public List<User> getAllUsers() {
		final String uri = webServiceHost + ":" + webServicePort + "/users/find/all";

		ResponseEntity<User[]> response = restTemplate.getForEntity(uri, User[].class);
		return Arrays.asList(response.getBody());
	}

	@Override
	public List<Receptionist> getAllReceptionist() {
		final String baseUri = webServiceHost + ":" + webServicePort + "/users/find/all";

		UriComponentsBuilder uriTemplate = UriComponentsBuilder.fromHttpUrl(baseUri);

		uriTemplate.queryParam("roles", Role.RECEPTIONIST.name());

		ResponseEntity<Receptionist[]> response = restTemplate.getForEntity(uriTemplate.encode().toUriString(),
				Receptionist[].class);
		return Arrays.asList(response.getBody());
	}

	@Override
	public List<LogisticsManager> getAllLogisticsManager() {
		final String baseUri = webServiceHost + ":" + webServicePort + "/users/find/all";

		UriComponentsBuilder uriTemplate = UriComponentsBuilder.fromHttpUrl(baseUri);

		uriTemplate.queryParam("roles", Role.RECEPTIONIST.name());
		uriTemplate.queryParam("roles", Role.LOGISTICS_MANAGER.name());

		ResponseEntity<LogisticsManager[]> response = restTemplate.getForEntity(uriTemplate.encode().toUriString(),
				LogisticsManager[].class);
		return Arrays.asList(response.getBody());
	}

	@Override
	public List<Courier> getAllCourier() {
		final String baseUri = webServiceHost + ":" + webServicePort + "/users/find/all";

		UriComponentsBuilder uriTemplate = UriComponentsBuilder.fromHttpUrl(baseUri);

		uriTemplate.queryParam("roles", Role.COURIER.name());

		ResponseEntity<Courier[]> response = restTemplate.getForEntity(uriTemplate.encode().toUriString(),
				Courier[].class);
		return Arrays.asList(response.getBody());
	}

	@Override
	public User add(User user) {
		final String uri = webServiceHost + ":" + webServicePort + "/users/save";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<User> request = new HttpEntity<>(user, headers);

		return restTemplate.postForObject(uri, request, User.class);
	}

	@Override
	public void update(User user) {
		final String uri = webServiceHost + ":" + webServicePort + "/users/update";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<User> request = new HttpEntity<>(user, headers);

		restTemplate.put(uri, request);
	}

	@Override
	public void remove(String username) {
		final String baseUri = webServiceHost + ":" + webServicePort + "/users/delete/by/username/{username}";

		UriComponentsBuilder uriTemplate = UriComponentsBuilder.fromHttpUrl(baseUri);

		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("username", username);

		restTemplate.delete(uriTemplate.buildAndExpand(uriVariables).toUriString(), User.class);
	}

}
