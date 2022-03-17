$( document ).ready(function() {
	$("#modal").click(function() {
		$("#modal").toggle();
	});
});

function Modal(fnContinuar, fnFechar) {
	var _fnContinuar = fnContinuar;
	var _fnFechar = fnFechar;
	
	function continuar() {
		var continuar = function () {
			_fnContinuar();
			document.getElementById("modalContinuar").removeEventListener('click', continuar);
			document.getElementById("modal").click();
		};
		document.getElementById("modalContinuar").addEventListener('click', continuar);
	};
	
	function fechar() {
		var fechar = function () {
			_fnFechar();
			document.getElementById("modalFechar").removeEventListener('click', fechar);
			document.getElementById("modal").click();
		}
		document.getElementById("modalFechar").addEventListener('click', fechar);
	};
	
	return {
		open: function _open(msg){
			continuar();
			fechar();
			document.getElementsByClassName("modal-body")[0].innerText = msg;
			document.getElementById("modal").click();
		}
	}
}



