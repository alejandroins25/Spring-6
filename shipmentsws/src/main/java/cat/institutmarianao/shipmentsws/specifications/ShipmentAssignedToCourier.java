package cat.institutmarianao.shipmentsws.specifications;

import org.springframework.data.jpa.domain.Specification;

import cat.institutmarianao.shipmentsws.model.Action;
import cat.institutmarianao.shipmentsws.model.Action.Type;
import cat.institutmarianao.shipmentsws.model.Shipment;
import cat.institutmarianao.shipmentsws.model.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ShipmentAssignedToCourier implements Specification<Shipment> {

	private static final long serialVersionUID = 1L;
	private User courier;

	public ShipmentAssignedToCourier(User courier) {
		this.courier = courier;
	}

	@Override
	public Predicate toPredicate(Root<Shipment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if (courier == null) {
			return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
		}

		Join<Shipment, Action> shipmentsActions = root.join("tracking");

		return criteriaBuilder.and(criteriaBuilder.equal(shipmentsActions.get("type"), Type.ASSIGNMENT),
				criteriaBuilder.equal(shipmentsActions.get("courier"), courier));
	}

}
