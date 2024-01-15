package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;

import cat.institutmarianao.shipmentsws.model.User.Role;
import cat.institutmarianao.shipmentsws.validation.constraints.Performer;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Validation */
@Performer({ Role.RECEPTIONIST, Role.LOGISTICS_MANAGER })
/* JPA annotations */
@Entity
@DiscriminatorValue(Action.RECEPTION)
/* Lombok */
@Data
@EqualsAndHashCode(callSuper = true)
public class Reception extends Action implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer trackingNumber;
}
