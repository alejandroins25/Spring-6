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

public class ShipmentReceivedBy implements Specification<Shipment> {

	private static final long serialVersionUID = 1L;
	private User receptionist;

	public ShipmentReceivedBy(User performer) {
		receptionist = performer;
	}

	@Override
	public Predicate toPredicate(Root<Shipment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		if (receptionist == null) {
			return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
		}

		Join<Shipment, Action> shipmentsActions = root.join("tracking");

		return criteriaBuilder.and(criteriaBuilder.equal(shipmentsActions.get("type"), Type.RECEPTION),
				criteriaBuilder.equal(shipmentsActions.get("performer"), receptionist));
	}

}
