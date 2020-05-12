
 
var request = new XMLHttpRequest(); 
var action="listFile";
var currentDir;

function reqReadyStateChange(){ 
    if (request.readyState == 4) { 
        var status = request.status; 
        if (status == 200) {
            var json=JSON.parse(request.responseText);
            
            if (action == "browserFS") {
//                document.getElementById("command").innerHTML ="browserFS";
                fileList(json);}
            else {
//                document.getElementById("command").innerHTML ="playList";
                playList(json);}
        }
            
        else{document.getElementById("command").innerHTML ="нет ответа от сервера";} 
    } 
    }

 function linkList (i, title){
    return "<button type=\"button\"  visibility=\"hidden\" onclick=\"RestPOSTsong('"+i+"')\">"+title+"</button>";
 }

 function playList(List){
     currentDir=List.currentDir;
     playScena();
    var table="<table><tr><td>Номер</td><td>Название</td><td>Исполнитель</td></tr>";
    for (var i in List.playList) {
        
        if (i == List.currentSong) {
            var strongBegin="<strong>";
            var strongEnd ="</strong>";
            console.log("true");}
        else {
            var strongBegin="";
            var strongEnd ="";
         console.log("false");}

        table+="<tr>"+"<td>"+strongBegin+i+strongEnd+"</td><td>"+linkList(i, List.playList[i].metadata.title)+"</td><td>"+List.playList[i].metadata.artist+"</td>"+"</tr>";
    }
    table+="</table>"
    document.getElementById("playingSong").innerHTML ="Проигрывается "+List.song.metadata.title;   
    document.getElementById("playList").innerHTML =table;   
    document.getElementById("fileList").innerHTML =""; 
 }


 function linkFile (i, title){
    return "<button type=\"button\"  onclick=\"RestPOSTfile('"+title.split("\\").join("\/\/")+"', 'false')\">"+title+"</button>";
 }

 function fileList(List){
    document.getElementById("fileList").innerHTML ="генерируем список файлов";
    currentDir=List.file.split("\\").join("\/\/");
    fileScena();
    var strongBegin="<strong>";
    var strongEnd ="</strong>";
    var table="<table><tr><td>Номер</td><td>Путь</td></tr>";
    table+="<tr>"+"<td>"+strongBegin+"*"+strongEnd+"</td><td>"
    +"<button type=\"button\"  onclick=\"RestPOSTfile('"+currentDir+"','true')\">"+".."+"</button>"+"</tr>";
    for (var i in List.listFile) {
        table+="<tr>"+"<td>"+strongBegin+i+strongEnd+"</td><td>"+linkFile(i, List.listFile[i])+"</td>"+"</tr>";
    }
    table+="</table>"
    document.getElementById("playList").innerHTML =""; 
    document.getElementById("fileList").innerHTML =table;   
 }


var urlGlobal="http://"+IP+":8080/";

var RestGET = function(command) {
var url=urlGlobal+"player";
    action="listFile";
request.open("GET", url+"?command="+command, true);
request.onreadystatechange = reqReadyStateChange;
request.send();
}

var RestPOSTsong = function(command) {

var url=urlGlobal+"player";
    action="listFile";
request.open("POST", url+"/"+command, true);
request.onreadystatechange = reqReadyStateChange;
request.send();
}

var RestPOSTfile = function(getPath, getUp) {
var url=urlGlobal+"opendir/";
    action="browserFS";

if (getPath == "") getPath=currentDir;

var folder = { 
    path: getPath,
    up: getUp
};

document.getElementById("fileList").innerHTML =JSON.stringify(folder); 

var json = JSON.stringify(folder);
request.open("POST", url);
request.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
request.onreadystatechange = reqReadyStateChange;
request.send(json);
}

var RestPOSTplay = function(getPath, getUp) {
var url=urlGlobal+"player/";
    // action="browserFS";
    action="playList";
if (getPath == "") getPath=currentDir;

var folder = { 
    path: getPath,
    up: getUp
};

document.getElementById("fileList").innerHTML =JSON.stringify(folder); 

var json = JSON.stringify(folder);
request.open("POST", url);
request.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
request.onreadystatechange = reqReadyStateChange;
request.send(json);
}

var playScena = function(){
    var page ="<h3>FxJava Spring Player</h3>";
    page +="<button type=\"button\" onclick=\"RestGET('stop')\">STOP/PAUSE</button>";
    page +="<button type=\"button\" onclick=\"RestGET('prev')\">PREV</button>";
    page +="<button type=\"button\" onclick=\"RestGET('play')\">PLAY</button>";
    page +="<button type=\"button\" onclick=\"RestGET('next')\">NEXT</button>";
    page +="<button type=\"button\" onclick=\"RestGET('info')\">INFO</button>";
    page +="<button type=\"button\" onclick=\"RestPOSTfile('"+currentDir+"', 'false')\">Open folder</button>";
    document.getElementById("scena").innerHTML =page;
}

var fileScena = function(){
    var page ="<h3>Открыть папку</h3>";

    page +="<button type=\"button\" onclick=\"RestPOSTplay('"+currentDir+"', 'false')\">Воспроизвести папку</button>";
    page +="<button type=\"button\" onclick=\"RestPOSTfile('"+currentDir+"', 'true')\">Наверх</button>";
    page +="<button type=\"button\" onclick=\"RestGET('info')\">Вернуться в проигрыватель</button>";
    document.getElementById("scena").innerHTML =page;
}