package cat.institutmarianao.shipments.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cat.institutmarianao.shipments.model.Action;
import cat.institutmarianao.shipments.model.Shipment;
import cat.institutmarianao.shipments.model.User;
import cat.institutmarianao.shipments.model.forms.ShipmentsFilter;
import cat.institutmarianao.shipments.services.ShipmentService;

@Service
@PropertySource("classpath:application.properties")
public class ShipmentServiceImpl implements ShipmentService {

	@Value("${webService.host}")
	private String webServiceHost;

	@Value("${webService.port}")
	private String webServicePort;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Shipment> filterShipments(ShipmentsFilter filter) {
		final String uri = webServiceHost + ":" + webServicePort + "/shipments/filter";

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    HttpEntity<ShipmentsFilter> request = new HttpEntity<>(filter, headers);
	    
	    return restTemplate.postForObject(uri, request, new ParameterizedTypeReference<List<Shipment>>() {});
	}

	@Override
	public Shipment add(Shipment ticket) {
		final String uri = webServiceHost + ":" + webServicePort + "/shipments/save";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Shipment> request = new HttpEntity<>(ticket, headers);

		return restTemplate.postForObject(uri, request, Shipment.class);
	}

	@Override
	public Action tracking(Action action) {
		final String uri = webServiceHost + ":" + webServicePort + "/actions/track";

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    HttpEntity<Action> request = new HttpEntity<>(action, headers);

	    return restTemplate.postForObject(uri, request, Action.class);
	}
}
