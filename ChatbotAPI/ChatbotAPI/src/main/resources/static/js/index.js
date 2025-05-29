//information mapping to keywords and appropriate responses
const knowledgeBase = [
    {
        keywords: ["programming languages", "coding", "languages you know", "skills in programming"],
        response: "I have strong foundations in multiple programming languages including C, C++, Java, and Python."
    },
    {
        keywords: ["backend", "server-side", "back-end development"],
        response: "I am passionate about backend development and have a proven ability to master complex technical concepts through self-directed learning."
    },
    {
        keywords: ["university", "studies", "where do you study", "education"],
        response: "I am a fourth-year Computer Science student at the University of Crete."
    },
    {
        keywords: ["github", "projects","project", "code samples"],
        response: "You can check out my personal projects at github.com/Pagakey/Projects."
    },
    {
        keywords: ["soft skills", "core", "skills you have"],
        response: "I am defined by my logical reasoning, problem solving abilities, and collaborative mindset."
    },
    {
        keywords: ["vr", "virtual reality", "vr testing"],
        response: "I have conducted basic VR testing for OramaVR in FORTH, identifying bugs related to user experience and Unity performance."
    },
    {
        keywords: ["hackathon", "hackathons", "hackathon experience", "competitions", "achievements"],
        response: "I achieved 17th place among 70 teams in 'Hellenic University Hack 2024' CTF competition as a key member of a 5-person team."
    },
    {
        keywords: ["linux", "linux experience", "linux skills"],
        response: "I have ease of use with Linux and command line environments."
    },
    {
        keywords: ["languages", "languages you speak", "real life languages you know","foreign languages"],
        response: "I speak Greek natively and have a B2 English level (Michigan ECCE)."
    },
    {
        keywords: ["interests", "hobbies", "free time","hustles","life","personal"],
        response: "My hobbies include playing piano, sim racing, PC building, and participating in Capture the Flag challenges."
    }
    ];

function SendPrompt(){
    prompt = document.getElementById("input_window").value;
    document.getElementById("conversation").innerHTML += "<div class='message user-message'>" + prompt + "</div>";
    document.getElementById("input_window").value = "";
}