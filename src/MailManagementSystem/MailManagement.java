package MailManagementSystem;
import java.util.*;
import java.util.stream.Collectors;

public class MailManagement {
    // Declaring Variables
    private List<Mail> mails;
    private Set<String> spamWords;
    private Scanner scanner;

    public MailManagement() {
        mails = new ArrayList<>();
        spamWords = new HashSet<>(Arrays.asList("lottery", "winner", "prize", "free", "congratulations"));
        scanner = new Scanner(System.in);
    }

    // Store Mail
    public void storeMail() {
        System.out.println("Enter Sender Mail: ");
        String sender = scanner.nextLine();
        System.out.println("Enter Receiver Mail: ");
        String receiver = scanner.nextLine();
        System.out.println("Enter Subject: ");
        String subject = scanner.nextLine();
        System.out.println("Enter the Content: ");
        String content = scanner.nextLine();

        Mail mail = new Mail(sender, receiver, subject, content);
        checkSpam(mail);
        mails.add(mail);
        System.out.println("Mail Stored Successfully!");
    }

    // Delete Mail
    public void deleteMail() {
        displayAllMails();
        System.out.println("Enter Mail index to Delete: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        if (index >= 0 && index < mails.size()) {
            mails.remove(index);
            System.out.println("Mail deleted Successfully!");
        } else {
            System.out.println("<<<Invalid Mail Index>>>");
        }
    }

    // Add Tag
    public void addTag() {
        displayAllMails();
        System.out.println("Enter Mail index: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        if (index >= 0 && index < mails.size()) {
            System.out.println("Enter a Tag: ");
            String tags = scanner.nextLine();
            mails.get(index).addTag(tags);
            System.out.println("Tag Added Successfully...");
        } else {
            System.out.println("<<<Invalid Mail Index>>>");
        }
    }

    // Show Mail Stats
    public void showStats() {
        System.out.println("\nMail Statistics");
        System.out.println("Total Mails: " + mails.size());
        System.out.println("\nEnter Number of Mails to Display: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nRecent " + n + " mails");
        mails.stream()
                .skip(Math.max(0, mails.size() - n))
                .forEach(System.out::println);
    }

    // Check if Mail is Spam
    public void checkSpam(Mail mail) {
        String content = mail.getContent().toLowerCase();
        for (String word : spamWords) {
            if (content.contains(word)) {
                mail.setSpam(true);
                break;
            }
        }
    }

    // Search Mail by keyword
    public void search() {
        System.out.println("Enter Query to Search: ");
        String query = scanner.nextLine().toLowerCase();
        List<Mail> results = mails.stream()
                .filter(mail ->
                        mail.getSender().toLowerCase().contains(query) ||
                                mail.getReceiver().toLowerCase().contains(query) ||
                                mail.getSubject().toLowerCase().contains(query) ||
                                mail.getContent().toLowerCase().contains(query) ||
                                mail.getTags().stream().anyMatch(tag -> tag.toLowerCase().contains(query)))
                .collect(Collectors.toList());
        if (results.isEmpty()) {
            System.out.println("No Mails Found Matching the Query!!");
        } else {
            System.out.println("\nSearch Results");
            results.forEach(System.out::println);
        }
    }

    // Wildcard Search
    public void wildSearch() {
        System.out.println("Enter Wild Search Query Pattern (use * for any Characters): ");
        final String pattern = scanner.nextLine().toLowerCase().replace("*", ".*");
        List<Mail> results = mails.stream()
                .filter(mail ->
                        mail.getSender().toLowerCase().matches(pattern) ||
                                mail.getReceiver().toLowerCase().matches(pattern) ||
                                mail.getSubject().toLowerCase().matches(pattern) ||
                                mail.getContent().toLowerCase().matches(pattern) ||
                                mail.getTags().stream().anyMatch(tag -> tag.toLowerCase().matches(pattern)))
                .collect(Collectors.toList());
        if (results.isEmpty()) {
            System.out.println("No Mails Found Matching the Query!!");
        } else {
            System.out.println("\nSearch Results");
            results.forEach(System.out::println);
        }
    }

    // Display all mails with index
    private void displayAllMails() {
        if (mails.isEmpty()) {
            System.out.println("No mails available.");
            return;
        }
        for (int i = 0; i < mails.size(); i++) {
            System.out.println("Index " + i + ": " + mails.get(i).getSubject() +
                    " (From: " + mails.get(i).getSender() + ")");
        }
    }

    // Menu
    public void displayMenu() {
        System.out.println("\n----- Mail Management System -----");
        System.out.println("1. Store Mail");
        System.out.println("2. Delete Mail");
        System.out.println("3. Add Tag");
        System.out.println("4. Show Statistics");
        System.out.println("5. Search");
        System.out.println("6. WildCard Search");
        System.out.println("7. Exit");
        System.out.println("Enter Your Choice: ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MailManagement mailManagement = new MailManagement();
        int choice;

        do {
            mailManagement.displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> mailManagement.storeMail();
                case 2 -> mailManagement.deleteMail();
                case 3 -> mailManagement.addTag();
                case 4 -> mailManagement.showStats();
                case 5 -> mailManagement.search();
                case 6 -> mailManagement.wildSearch();
                case 7 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid Choice...");
            }
        } while (choice != 7);
    }
}
