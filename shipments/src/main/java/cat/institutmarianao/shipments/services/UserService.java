package cat.institutmarianao.shipments.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import cat.institutmarianao.shipments.model.Courier;
import cat.institutmarianao.shipments.model.LogisticsManager;
import cat.institutmarianao.shipments.model.Receptionist;
import cat.institutmarianao.shipments.model.User;

public interface UserService extends UserDetailsService {
	List<User> getAllUsers();

	List<Receptionist> getAllReceptionist();

	List<LogisticsManager> getAllLogisticsManager();

	List<Courier> getAllCourier();

	User add(User user);

	void update(User user);

	void remove(String username);
}
