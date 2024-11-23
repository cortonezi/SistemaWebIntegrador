function validarFormulario() {
    var nome = document.getElementById("nome").value;
    var email = document.getElementById("email").value;

    if (nome === "") {
        alert("Por favor, preencha o campo Nome.");
        return false;
    }

    if (email === "") {
        alert("Por favor, preencha o campo E-mail.");
        return false;
    }

    return true;
}
