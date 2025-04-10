import java.util.*;

public class MoodMusicSuggester {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, List<String>> moodSongs = new HashMap<>();

        moodSongs.put("happy", Arrays.asList("Happy - Pharrell", "Best Day of My Life - American Authors", "On Top of the World - Imagine Dragons"));
        moodSongs.put("sad", Arrays.asList("Someone Like You - Adele", "Let Her Go - Passenger", "Fix You - Coldplay"));
        moodSongs.put("energetic", Arrays.asList("Eye of the Tiger - Survivor", "Stronger - Kanye West", "Can't Hold Us - Macklemore"));
        moodSongs.put("relaxed", Arrays.asList("Sunflower - Post Malone", "Perfect - Ed Sheeran", "Stay With Me - Sam Smith"));

        System.out.println("Welcome to Mood-Based Music Suggester!");
        System.out.print("How are you feeling? (happy/sad/energetic/relaxed): ");
        String mood = scanner.nextLine().toLowerCase();

        if (moodSongs.containsKey(mood)) {
            List<String> songs = moodSongs.get(mood);
            Random random = new Random();
            String suggestedSong = songs.get(random.nextInt(songs.size()));
            System.out.println("You might enjoy this song: " + suggestedSong);
        } else {
            System.out.println("Sorry, I don't have suggestions for that mood.");
        }
    }
}

