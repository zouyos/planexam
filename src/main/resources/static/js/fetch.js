async function changeDispo(id,value){
    await fetch("http://localhost:8080/admin/epreuve/jour/"+id+"/"+value, {method: "POST"});
}

function changePonctuel(id,value){
    fetch("http://localhost:8080/admin/etablissement/ponctuel/"+id+"/"+value, {method: "POST"});
}