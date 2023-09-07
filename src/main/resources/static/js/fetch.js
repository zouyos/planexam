function changePonctuel(id,value){
    fetch("http://localhost:8080/admin/etablissement/ccf/"+id+"/"+value);
}