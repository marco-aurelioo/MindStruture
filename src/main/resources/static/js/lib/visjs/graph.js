//criar grafo de relacionamento entre tecnologias (languages) com usuarios do github
//nos do grafo   {id: 1, shape: 'circularImage', label: "login", circularImage:"urlImg"},
var nodes = new vis.DataSet([
]);

//relacionamento entre os nós
var edges = new vis.DataSet([
]);

var isLoaded =  false;

//Criar conteudo do grafo dentro do elemento
function createNetWork(element){
    if(!isLoaded){
        var container = element;
        var data = {
            nodes: this.nodes,
            edges: this.edges
        };
        var options = {
        };
        var network = new vis.Network(container, data, options);
        isLoaded = true;
    }
}

function addToNetWork(data){
    createNodeUser(data);
}

//cria node user de relacionamento no formato user { login:XXX, avatar_url:YYYY }
function createNodeUser(data){
    var name = data.user.name;
    var avatar = data.user.avatarUrl;
    var id = data.user.id;
    createNode(id,name,avatar);
    for (var key in data.friends) {
       if (data.friends.hasOwnProperty(key)) {
         var nameF = data.friends[key].name;
         var avatarF = data.friends[key].avatarUrl;
         var idF =  data.friends[key].id;
         createNode(idF,nameF,avatarF);
         this.edges.add( {from: id, to: idF});
       }
    }
}

function createNode(idF,nameF,avatarF){
    if(avatarF){
        this.nodes.add({id: idF, shape: 'image', label: nameF, image: avatarF})
    }else{
        this.nodes.add({id: idF, shape: 'hexagon', label: nameF, image: avatarF})
    }
}
