const API = "http://localhost:8080/todos";
let alteracoes = {};

async function listar() {
    const res = await fetch(API);
    const data = await res.json();

    data.sort((a, b) => a.prioridade - b.prioridade)
    const lista = document.getElementById("lista");
    lista.innerHTML = "";


    data.forEach(todo => {
        lista.innerHTML += `
      <tr>
      <td>${todo.nome}</td>
      <td>${todo.descricao}</td>
      <td>${todo.prioridade}</td>
      <td>
  <input 
    type="checkbox" 
    ${todo.realizado ? "checked" : ""}
    onchange="marcarAlteracao(${todo.id}, this.checked)"
  >
</td>
      <td>
        <button onclick="deletar(${todo.id})">X</button>
      </td>
    </tr>
    `;
    });
}

async function criar() {
    const nome = document.getElementById("nome").value;
    const descricao = document.getElementById("descricao").value;
    const prioridade = document.getElementById("prioridade").value;

    await fetch(API, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            nome,
            descricao,
            prioridade,
            realizado: false
        })
    });
    limparCampos();
    listar();
    
}

async function deletar(id) {
    await fetch(`${API}/${id}`, { method: "DELETE" });
    listar();
}
function editar(id, nome, descricao, prioridade, realizado) {
  document.getElementById("nome").value = nome;
  document.getElementById("descricao").value = descricao;
  document.getElementById("prioridade").value = prioridade;

  idEditando = id;
}
async function atualizar() {

  const res = await fetch(API);
  const todos = await res.json();

  for (const todo of todos) {
    if (alteracoes[todo.id] !== undefined) {

      await fetch(API, {
        method: "PUT",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
          id: todo.id,
          nome: todo.nome,
          descricao: todo.descricao,
          prioridade: todo.prioridade,
          realizado: alteracoes[todo.id]
        })
      });
    }
  }

  alteracoes = {}; // limpa alterações
  listar();
}

function limparCampos() {
  document.getElementById("nome").value = "";
  document.getElementById("descricao").value = "";
  document.getElementById("prioridade").value = "";
}
function marcarAlteracao(id, realizado) {
  alteracoes[id] = realizado;
}

listar();

