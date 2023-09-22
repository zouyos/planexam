async function changeDispo(id, value){
    await fetch("http://localhost:8080/api/epreuve/jour/"+id+"/"+value, {method: "GET"});
}

async function changePonctuel(id, value){
    await fetch("http://localhost:8080/api/etablissement/ponctuel/"+id+"/"+value, {method: "GET"});
}

async function changeNbrJurys(id, value){
    await fetch("http://localhost:8080/api/nbrJurys/nbr/"+id+"/"+value, {method: "GET"});
}