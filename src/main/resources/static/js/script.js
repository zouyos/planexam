async function changeDispo(id, value){
    await fetch("http://localhost:8080/api/epreuve/jour/"+id+"/"+value);
}

async function changePonctuel(id, value){
    await fetch("http://localhost:8080/api/etablissement/ponctuel/"+id+"/"+value);
}

async function changeNbrJuries(jourId, etabEpreuveId, nbrJury){
    await fetch("http://localhost:8080/api/epreuve/nbr-juries/"+jourId+"/"+etabEpreuveId+"/"+nbrJury);
}

let inputs = document.querySelectorAll("tbody tr input")
for (const input of inputs) {
    input.addEventListener("change", (e) => {
        let tr = e.target.parentNode.parentNode
        let resultat = 0
        for (const element of tr.querySelectorAll("input")) {
            resultat += parseInt(element.value)
        }
        tr.lastElementChild.textContent = resultat
    })
}