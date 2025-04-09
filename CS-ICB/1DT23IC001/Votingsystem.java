import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VotingSystemWithInput {

    private Map<String, Integer> candidates;
    private Map<String, String> votes; // voterId -> candidateId
    private List<String> voters;
    private Scanner scanner;

    public VotingSystemWithInput() {
        this.candidates = new HashMap<>();
        this.votes = new HashMap<>();
        this.voters = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public boolean registerCandidate(String candidateName) {
        if (candidates.containsKey(candidateName)) {
            System.out.println(candidateName + " is already a registered candidate.");
            return false;
        }
        candidates.put(candidateName, 0);
        System.out.println(candidateName + " has been registered as a candidate.");
        return true;
    }

    public boolean registerVoter(String voterId) {
        if (voters.contains(voterId)) {
            System.out.println("Voter with ID " + voterId + " is already registered.");
            return false;
        }
        voters.add(voterId);
        System.out.println("Voter with ID " + voterId + " has been registered.");
        return true;
    }

    public boolean vote(String voterId, String candidateName) {
        if (!voters.contains(voterId)) {
            System.out.println("Voter with ID " + voterId + " is not registered.");
            return false;
        }
        if (!candidates.containsKey(candidateName)) {
            System.out.println("Candidate " + candidateName + " is not registered.");
            return false;
        }
        if (votes.containsKey(voterId)) {
            System.out.println("Voter with ID " + voterId + " has already voted for " + votes.get(voterId));
            return false;
        }

        votes.put(voterId, candidateName);
        candidates.put(candidateName, candidates.get(candidateName) + 1);
        System.out.println("Voter " + voterId + " has voted for " + candidateName + ".");
        return true;
    }

    public int getVotes(String candidateName) {
        if (candidates.containsKey(candidateName)) {
            return candidates.get(candidateName);
        } else {
            System.out.println("Candidate " + candidateName + " is not registered.");
            return 0;
        }
    }

    public String getWinner() {
        if (candidates.isEmpty()) {
            return "No candidates registered.";
        }

        String winner = null;
        int maxVotes = -1;

        for (Map.Entry<String, Integer> entry : candidates.entrySet()) {
            if (entry.getValue() > maxVotes) {
                maxVotes = entry.getValue();
                winner = entry.getKey();
            } else if (entry.getValue() == maxVotes) {
                if (winner != null) {
                    winner = "It's a tie between " + winner + " and " + entry.getKey();
                } else {
                    winner = entry.getKey(); // In case the first two have the same votes
                }
            } else if (winner != null && winner.startsWith("It's a tie") && entry.getValue() == maxVotes) {
                winner += " and " + entry.getKey();
            }
        }
        return winner != null ? "The winner is: " + winner : "No votes have been cast yet.";
    }

    public void runElection() {
        int choice;
        do {
            System.out.println("\nVoting System Menu:");
            System.out.println("1. Register Candidate");
            System.out.println("2. Register Voter");
            System.out.println("3. Cast Vote");
            System.out.println("4. View Vote Counts");
            System.out.println("5. Declare Winner");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter candidate name: ");
                    String newCandidate = scanner.nextLine();
                    registerCandidate(newCandidate);
                    break;
                case 2:
                    System.out.print("Enter voter ID: ");
                    String newVoterId = scanner.nextLine();
                    registerVoter(newVoterId);
                    break;
                case 3:
                    System.out.print("Enter your voter ID: ");
                    String voterId = scanner.nextLine();
                    System.out.print("Enter the name of the candidate you want to vote for: ");
                    String candidateName = scanner.nextLine();
                    vote(voterId, candidateName);
                    break;
                case 4:
                    System.out.println("\nCurrent Vote Counts:");
                    for (String candidate : candidates.keySet()) {
                        System.out.println(candidate + ": " + getVotes(candidate));
                    }
                    break;
                case 5:
                    System.out.println("\nElection Result:");
                    System.out.println(getWinner());
                    break;
                case 0:
                    System.out.println("Exiting Voting System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
        scanner.close();
    }

    public static void main(String[] args) {
        VotingSystemWithInput election = new VotingSystemWithInput();
        election.runElection();
    }
}
