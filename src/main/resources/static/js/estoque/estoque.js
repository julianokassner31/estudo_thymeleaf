$( document ).ready(function() {
	
	$("#resultados").click(function() {
		$(this).empty();
		$(this).hide();
	});
	
	var timeout = undefined;
	$("#nome").keyup(function(event) {
		var host = window.location.origin;
	    var resultados = $("#resultados");
	    var nome = event.currentTarget.value;
	    $("#produto").val("");
	    resultados.empty();
	    if (nome.length > 2) {
			clearTimeout(timeout);
	    	timeout = setTimeout(function(){
			    function reqListener () {
					var produtos = JSON.parse(this.responseText);
					for (var i=0; i < produtos.length; i++) {
						var divResultado = document.createElement("div");
						divResultado.innerText = produtos[i].nome
						divResultado.className = 'resultado';
						divResultado.setAttribute("id", produtos[i].id);
						divResultado.setAttribute("onclick", "selectProduto('"+produtos[i].id+"')")
						resultados.append(divResultado);
						resultados.show();
					}
			    };
			    var oReq = new XMLHttpRequest();
			    oReq.onload = reqListener;
			    oReq.open("get", host + '/produto?nome='+ nome, true);
			    oReq.send();
	    	}, 300);
		}
	});
	
	$('input[name="quantidade"]').blur(function(){
		if ($(this).val() <= 0 ) {
			$(this).val(1);
		}
	});
});

function selectProduto(id) {
	var produto = document.getElementById(id);
	document.getElementById("produto").value = id;
	document.getElementById("nome").value = produto.innerText;
	document.getElementById("resultados").click();
	
	var divEstoqueList = document.getElementsByClassName("divEstoqueList")[0];
	if (divEstoqueList) {
		divEstoqueList.style.display = 'none';
	}
}

function buscarProduto() {
	var idproduto = document.getElementById("produto").value;
	var nomeProduto = document.getElementById("nome").value;
	
	if (idproduto != "" && nomeProduto != "") {
		document.getElementById("formBuscarProduto").submit();
	} else {
		alert("Selecione um produto.")
	}
}
