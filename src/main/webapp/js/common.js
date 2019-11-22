function setPage(page){
    var url = new URL(window.location.href);
    var params = new URLSearchParams(url.search);
    setParam(params, "page", page);
    url.search = params.toString();
    window.location.href = url.toString();
}

function setPerPage(perPage){
    var url = new URL(window.location.href);
    var params = new URLSearchParams(url.search);
    setParam(params, "perPage", perPage);
    url.search = params.toString();
    window.location.href = url.toString();
}

function setParam(params, param, value){
    if(params.has(param)){
        params.set(param, value);
    }else{
        params.append(param, value);
    }
}

