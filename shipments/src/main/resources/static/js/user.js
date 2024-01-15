function roleFields() {
	var role = document.getElementById('role');
	var receptionistFields = document.getElementById('receptionistFields');
	var courierFields = document.getElementById('courierFields');

	var roleValue = role.value;

	if (roleValue == 'COURIER') {
		courierFields.style.removeProperty('display');
		receptionistFields.style.display = 'none';
	} else {
		courierFields.style.display = 'none';
		receptionistFields.style.removeProperty('display');
	}
}