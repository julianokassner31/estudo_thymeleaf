function matchSenha() {
	var senha = window.document.getElementById("senha").value;
	var repita = window.document.getElementById("repitaSenha").value;
	window.document.getElementsByClassName("senha-invalida")[0].style.display = repita != senha ? 'block' : 'none';
}

function disableCheckBoxesPermissoes() {
	var roles = document.getElementsByName("roles");
	
	for (var i = 0; i < roles.length; i++) {
		
		if (roles[i].checked === true && roles[i].value === "1") {
			roles.forEach(role => role.checked = false);
			roles[i].checked = true;
			break;
		}
	}
}

function deleteUsuario(idUsuario) {
	
	var form = document.getElementById("formDeleteUsuario"+idUsuario);
	
	var modal = new Modal(
			function(){form.submit()}, 
			function(){}
		);
	
	modal.open("Deseja realmente excluir o usuário?");
}