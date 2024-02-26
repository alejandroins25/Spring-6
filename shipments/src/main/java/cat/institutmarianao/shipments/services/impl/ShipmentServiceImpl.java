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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import cat.institutmarianao.shipments.model.Action;
import cat.institutmarianao.shipments.model.Assignment;
import cat.institutmarianao.shipments.model.Delivery;
import cat.institutmarianao.shipments.model.Shipment;
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
		final String uri = webServiceHost + ":" + webServicePort + "/shipments/find/all/{shipment-status}";
		UriComponentsBuilder uriTemplate = UriComponentsBuilder.fromHttpUrl(uri);
		
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("shipment-status", filter.getStatus().name());
		
		if (filter.getReceptionist() != null) {
	        uriVariables.put("receptionist", filter.getReceptionist());
	    } else if (filter.getCourierAssigned() != null) {
	        uriVariables.put("courierAssigned", filter.getCourierAssigned());
	    }
		
		ResponseEntity<Shipment[]> response = restTemplate.getForEntity(uriTemplate.buildAndExpand(uriVariables).toString() , Shipment[].class);
		
		return Arrays.asList(response.getBody());
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
		final String uri = webServiceHost + ":" + webServicePort + "/shipments/save/action";

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    HttpEntity<Action> request = new HttpEntity<>(action, headers);

	    return restTemplate.postForObject(uri, request, Action.class);
	}

}
