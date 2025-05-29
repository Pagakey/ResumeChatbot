package com.example.ChatbotAPI.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Knowledge base and matching logic
@Service
public class ChatService {

    static class KnowledgeEntry {
        private String[] keywords;
        private String response;

        public void setKeywords(String[] keywords) {
            this.keywords = keywords;
        }
        public void setResponse(String response) {
            this.response = response;
        }
        public String[] getKeywords() {
            return keywords;
        }
        public String getResponse() {
            return response;
        }
        public KnowledgeEntry(String[] keywords, String response) {
            setKeywords(keywords);
            setResponse(response);
        }
    }

    private final List<KnowledgeEntry> knowledgeBase = new ArrayList<>();


    // In case of multiple matches where the question "what languages do you know"
    // can refer to programming languages but also natural/speaking languages,
    // the bot should be able to match all entries appropriate and return a combined response.
    public String processMessage(String user_input) {
        String lowerInput = user_input.toLowerCase();
        List<KnowledgeEntry> bestMatches = new ArrayList<>();
        int highestScore = 0;

        // Find all entries with the highest match score
        for (KnowledgeEntry entry : knowledgeBase) {
            int matchCount = countMatches(lowerInput, entry.getKeywords());
            if (matchCount > highestScore) {
                highestScore = matchCount;
                bestMatches.clear();
                bestMatches.add(entry);
            } else if (matchCount == highestScore && matchCount > 0) {
                bestMatches.add(entry);
            }
        }

        if (!bestMatches.isEmpty()) {
            if (bestMatches.size() == 1) {
                // Single best match
                return bestMatches.get(0).getResponse();
            } else {
                // If multiple keywords match, combine responses
                StringBuilder combinedResponse = new StringBuilder();
                for (int i = 0; i < bestMatches.size(); i++) {
                    combinedResponse.append(bestMatches.get(i).getResponse());
                    if (i < bestMatches.size() - 1) {
                        combinedResponse.append("\nAlso, ");
                    }
                }
                return combinedResponse.toString();
            }
        } else {
            return "I'm sorry, I don't have information about that. You can ask me about my programming skills, education, projects, or personal interests!";
        }
    }

    private int countMatches(String lowerInput, String[] keywords) {
        int count = 0;
        for (String keyword : keywords) {
            if (lowerInput.contains(keyword.toLowerCase())) {
                count++;
            }
        }
        return count;
    }

    public ChatService() {
        // Add each knowledge entry from your JavaScript knowledge base
        knowledgeBase.add(new KnowledgeEntry(
                new String[]{"programming languages", "coding", "languages you know", "skills in programming"},
                "I have strong foundations in multiple programming languages including C, C++, Java, and Python."
        ));

        knowledgeBase.add(new KnowledgeEntry(
                new String[]{"backend", "server-side", "back-end development"},
                "I am passionate about backend development and have a proven ability to master complex technical concepts through self-directed learning."
        ));

        knowledgeBase.add(new KnowledgeEntry(
                new String[]{"university", "studies", "where do you study", "education"},
                "I am a fourth-year Computer Science student at the University of Crete."
        ));

        knowledgeBase.add(new KnowledgeEntry(
                new String[]{"github", "projects", "project", "code samples"},
                "You can check out my personal projects at github.com/Pagakey/Projects."
        ));

        knowledgeBase.add(new KnowledgeEntry(
                new String[]{"soft skills", "core", "skills you have"},
                "I am defined by my logical reasoning, problem solving abilities, and collaborative mindset."
        ));

        knowledgeBase.add(new KnowledgeEntry(
                new String[]{"vr", "virtual reality", "vr testing"},
                "I have conducted basic VR testing for OramaVR in FORTH, identifying bugs related to user experience and Unity performance."
        ));

        knowledgeBase.add(new KnowledgeEntry(
                new String[]{"hackathon", "hackathons", "hackathon experience", "competitions", "achievements"},
                "I achieved 17th place among 70 teams in 'Hellenic University Hack 2024' CTF competition as a key member of a 5-person team."
        ));

        knowledgeBase.add(new KnowledgeEntry(
                new String[]{"linux", "linux experience", "linux skills"},
                "I have ease of use with Linux and command line environments."
        ));

        knowledgeBase.add(new KnowledgeEntry(
                new String[]{"languages", "languages you speak", "real life languages you know", "foreign languages"},
                "I speak Greek natively and have a B2 English level (Michigan ECCE)."
        ));

        knowledgeBase.add(new KnowledgeEntry(
                new String[]{"interests", "hobbies", "free time", "hustles", "life", "personal"},
                "My hobbies include playing piano, sim racing, PC building, and participating in Capture the Flag challenges."
        ));
    }
}