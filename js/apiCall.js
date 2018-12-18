
function updateInfo(id) {
    var value = $('#'+id).is(":checked") ? 1 : 0;
    console.log(value);
    $.ajax({
        url: "http://192.168.1.82:8080/update",
        context: document.body,
        data: {
            id: id,
            value: value
        },
        success: function(){
            console.log("Ok");
        }
    });
}
function updateInfoValue(id, value) {
    console.log(value);
    $.ajax({
        url: "http://192.168.1.82:8080/update",
        context: document.body,
        data: {
            id: id,
            value: value
        },
        success: function(){
            console.log("Ok");
        }
    });
}
function getInfo() {
    $.ajax({
        url: "http://192.168.1.82:8080/options",
        context: document.body,
        success: function(data){
            console.log(data);
        }
    });
}