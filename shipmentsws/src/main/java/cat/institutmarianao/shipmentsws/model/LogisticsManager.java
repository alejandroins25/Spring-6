package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* JPA annotations */
@Entity
@DiscriminatorValue(User.LOGISTICS_MANAGER)
/* Lombok */
@Data
@EqualsAndHashCode(callSuper = true)
public class LogisticsManager extends Receptionist implements Serializable {

	private static final long serialVersionUID = 1L;
}
