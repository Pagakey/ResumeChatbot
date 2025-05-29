
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
        List<KnowledgeEntry> relevantMatches = new ArrayList<>();
        int highestScore = 0;

        // Find all entries and their match scores
        for (KnowledgeEntry entry : knowledgeBase) {
            int matchCount = countMatches(lowerInput, entry.getKeywords());
            //debugging
            //System.out.println("Word: " + lowerInput + " Count: " + matchCount + " in knowledge base: " + knowledgeBase.indexOf(entry));

            if (matchCount > 0) {
                if (matchCount > highestScore) {
                    highestScore = matchCount;
                }
            }
        }

        // Collect all responses that meet the relevance threshold
        // Include responses with at least 50% of the highest score, minimum of 1 match
        int threshold = Math.max(1, highestScore / 2);

        for (KnowledgeEntry entry : knowledgeBase) {
            int matchCount = countMatches(lowerInput, entry.getKeywords());
            if (matchCount >= threshold) {
                relevantMatches.add(entry);
            }
        }

        if (!relevantMatches.isEmpty()) {
            if (relevantMatches.size() == 1) {
                // Single relevant response
                return relevantMatches.get(0).getResponse();
            } else {
                // Multiple relevant responses -> combine them
                StringBuilder combinedResponse = new StringBuilder();
                for (int i = 0; i < relevantMatches.size(); i++) {
                    combinedResponse.append(relevantMatches.get(i).getResponse());
                    if (i < relevantMatches.size() - 1) {
                        combinedResponse.append("<br>Also, ");
                    }
                }
                return combinedResponse.toString();
            }
        } else {
            return "I'm sorry, I don't have information about that. You can ask me about my programming skills, education, projects, or personal interests!";
        }
    }

    // Bidirectional(?) matching for flexibility (simulates word "similarity"
    private int countMatches(String lowerInput, String[] keywords) {
        int count = 0;
        for (String keyword : keywords) {
            String lowerKeyword = keyword.toLowerCase();
            if (lowerInput.contains(lowerKeyword) || lowerKeyword.contains(lowerInput)) {
                count++;
            }
        }
        return count;
    }

    public ChatService() {
        knowledgeBase.add(new KnowledgeEntry(
                new String[]{"programming languages", "coding", "languages you know", "skills in programming","program","C","C++","Java","Python"},
                "I have strong foundations in multiple programming languages including C, C++, Java, and Python."
        ));
        //backend development
        knowledgeBase.add(new KnowledgeEntry(
                new String[]{"backend", "server-side", "backend development","web development","sites"},
                "I am passionate about backend development and have a proven ability to master complex technical concepts through self-directed learning."
        ));
        //education, studies
        knowledgeBase.add(new KnowledgeEntry(
                new String[]{"university", "studies", "study", "education","computer", "science" ,"department"},
                "I am a fourth-year Computer Science student at the University of Crete."
        ));
        //projects
        knowledgeBase.add(new KnowledgeEntry(
                new String[]{"hackathon", "hackathon experience", "competitions", "achievements","hack", "CTF","capture the flag"},
                "I achieved 17th place among 70 teams in 'Hellenic University Hack 2024' CTF competition as a key member of a 5-person team."
        ));

        knowledgeBase.add(new KnowledgeEntry(
                new String[]{"linux", "linux experience", "linux skills","command line", "cmd", "command prompt", "wsl","subsystem"},
                "I have ease of use with Linux and command line environments."
        ));

        knowledgeBase.add(new KnowledgeEntry(
                new String[]{"languages", "languages you speak", "foreign", "native", "English","Greek","slang"},
                "I speak Greek natively and have a B2 English level (Michigan ECCE)."
        ));

        knowledgeBase.add(new KnowledgeEntry(
                new String[]{"interests", "hobbies", "free time", "hustles", "life", "personal"},
                "My hobbies include playing piano, sim racing, PC building, and participating in Capture the Flag challenges."
        ));
        knowledgeBase.add(new KnowledgeEntry(
                new String[]{"github", "projects", "code samples"},
                "You can check out my personal projects at github.com/Pagakey/Projects."
        ));

        knowledgeBase.add(new KnowledgeEntry(
                new String[]{"soft skills", "core", "skills you have","character", "abilities", "mindset","logic","brain","work"},
                "I am defined by my logical reasoning, problem solving abilities, and collaborative mindset."
        ));

        knowledgeBase.add(new KnowledgeEntry(
                new String[]{"vr", "virtual reality", "vr testing","testing","FORTH","Orama","Unity"},
                "I have conducted basic VR testing for OramaVR in FORTH, identifying bugs related to user experience and Unity performance."
        ));

    }
}