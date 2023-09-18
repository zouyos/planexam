async function changeDispo(id,value){
    await fetch("http://localhost:8080/epreuve/jour/"+id+"/"+value, {method: "POST"});
}

function changePonctuel(id,value){
    fetch("http://localhost:8080/etablissement/ponctuel/"+id+"/"+value, {method: "POST"});
}