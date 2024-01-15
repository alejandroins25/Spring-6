package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;

import cat.institutmarianao.shipmentsws.model.User.Role;
import cat.institutmarianao.shipmentsws.validation.constraints.Performer;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Validation */
@Performer(Role.LOGISTICS_MANAGER)
/* JPA annotations */
@Entity
@DiscriminatorValue(Action.ASSIGNMENT)
/* Lombok */
@Data
@EqualsAndHashCode(callSuper = true)
public class Assignment extends Action implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int MIN_PRIORITAT = 1;
	public static final int MAX_PRIORITAT = 3;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	private Courier courier;

	@NotNull
	@Min(MIN_PRIORITAT)
	@Max(MAX_PRIORITAT)
	private Integer priority;

}
