function SendPrompt() {
    const inputWindow = document.getElementById("input_window");
    const sendButton = document.getElementById("send_button");
    const conversation = document.getElementById("conversation");

    const userMessage = inputWindow.value.trim();

    // Don't send empty messages
    if (!userMessage) return;

    // Add user message to chat
    conversation.innerHTML += "<div class='message user-message'>" + userMessage + "</div>";

    // Clear input and disable controls
    inputWindow.value = "";
    inputWindow.disabled = true;
    sendButton.disabled = true;
    sendButton.innerHTML = "...";

    // Add typing indicator
    const typingIndicator = "<div class='message bot-message typing-indicator' id='typing'>Thinking...</div>";
    conversation.innerHTML += typingIndicator;

    // Scroll to bottom
    conversation.scrollTop = conversation.scrollHeight;

    // Add 2-second delay to show the waiting/typing effect
    setTimeout(() => {
        // Send request to backend
        fetch('/api/chat', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                user_input: userMessage
            })
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Exoume provlima');
                }
                return response.json();
            })
            .then(data => {
                // Remove "typing" indicator
                const typing = document.getElementById("typing");
                if (typing) {
                    typing.remove();
                }

                // Add chatbot's response to chat
                conversation.innerHTML += "<div class='message bot-message'>" + data.chat_response + "</div>";

                // Scroll to bottom
                conversation.scrollTop = conversation.scrollHeight;
            })
            .catch(error => {
                console.error('Error:', error);

                // Remove typing indicator
                const typing = document.getElementById("typing");
                if (typing) {
                    typing.remove();
                }

                // Add error message
                conversation.innerHTML += "<div class='message bot-message'>I'm just a bot 👉👈</div>";
            })
            .finally(() => {
                // Re-enable controls regardless of success or failure
                inputWindow.disabled = false;
                sendButton.disabled = false;
                sendButton.innerHTML = "Send";
                inputWindow.focus();
            });
    }, 2000); // 2-second delay
}