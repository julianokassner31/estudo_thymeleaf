$(function() {
	$("select[name='size']").change(function() {
    	$("input[name='page']").val("0");
	});
	
	$("input[name='query']").blur(function() {
    	$("input[name='page']").val("0");
	});
});

function formatValor() {
	var valorView = window.document.getElementById("valorView");
	var valorReal = window.document.getElementById("valor");
	
	var valor = valorView.value
		.replace("R$", "")
		.replace(/\s+/,"")
		.replace(/[a-zA-Z]/, "")
		.replace(",",".");
	
	valorReal.value = valor;
	
	valorView.value = new Intl.NumberFormat("pt-BR", {
		    style: "currency",
		    currency: "BRL",
  		}).format(parseFloat(valor));
}

function buscarProdutosPagina(page) {
	var form = window.document.getElementById("formBuscarProduto");
	document.getElementsByName("page")[0].value = page;
	form.submit(); 
}

function buscarProdutoPrevNext(type){
	var page = document.getElementsByName("page")[0];
	var totalPaginas = document.getElementsByName("totalPaginas")[0];
	var form = window.document.getElementById("formBuscarProduto");
	var pageValue = parseInt(page.value);
	
	if (type === 'Prev') {
		if (pageValue -1 >= 0) {
			page.value = pageValue - 1 
			form.submit();	
		}
	} else if (pageValue + 1 < parseInt(totalPaginas.value)) {
		page.value = pageValue + 1
		form.submit(); 
	}
}

function editarProduto(id){
	var form = window.document.getElementById("formEditar");
	form.action = window.location.origin + '/produto/editar/' + id;
	form.submit();
}




