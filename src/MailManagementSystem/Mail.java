package MailManagementSystem;
import java.util.*;

public class Mail {
    // Declaring variables
    private String sender;
    private String receiver;
    private String subject;
    private String content;
    private List<String> tags;
    private boolean isSpam;

    // Constructor with all fields
    public Mail(String sender, String receiver, String subject, String content, List<String> tags, boolean isSpam) {
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.content = content;
        this.tags = tags;
        this.isSpam = isSpam;
    }

    // Constructor without tags and spam flag
    public Mail(String sender, String receiver, String subject, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.content = content;
        this.tags = new ArrayList<>();
        this.isSpam = false;
    }

    // Getters and Setters
    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }

    public String getReceiver() { return receiver; }
    public void setReceiver(String receiver) { this.receiver = receiver; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }

    public boolean isSpam() { return isSpam; }
    public void setSpam(boolean spam) { isSpam = spam; }

    // Add tag if not present
    public void tag(String tag) {
        if (!tags.contains(tag)) {
            tags.add(tag);
        }
    }

    // Remove tag
    public void removeTag(String tag) {
        tags.remove(tag);
    }

    // Alternate tag adding method
    public void addTag(String tag) {
        tag(tag);
    }

    @Override
    public String toString() {
        return "From: " + sender + "\n" +
                "To: " + receiver + "\n" +
                "Subject: " + subject + "\n" +
                "Content: " + content + "\n" +
                "Tags: " + tags + "\n" +
                "Spam: " + isSpam;
    }
}
