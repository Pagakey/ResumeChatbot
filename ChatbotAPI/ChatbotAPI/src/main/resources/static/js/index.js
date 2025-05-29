//information mapping to keywords and appropriate responses
function SendPrompt(){
    prompt = document.getElementById("input_window").value;
    document.getElementById("conversation").innerHTML += "<div class='message user-message'>" + prompt + "</div>";
    document.getElementById("input_window").value = "";
}