package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;

import cat.institutmarianao.shipmentsws.model.User.Role;
import cat.institutmarianao.shipmentsws.validation.constraints.Performer;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Validation */
@Performer(Role.COURIER)
/* JPA annotations */
@Entity
@DiscriminatorValue(Action.DELIVERY)
/* Lombok */
@Data
@EqualsAndHashCode(callSuper = true)
public class Delivery extends Action implements Serializable {

	private static final long serialVersionUID = 1L;
}
