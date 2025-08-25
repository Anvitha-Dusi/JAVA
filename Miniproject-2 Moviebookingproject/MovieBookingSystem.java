import java.util.*;

class Movie {
    String title;
    String description;
    String showTime;
    String date;
    boolean[] seats = new boolean[100]; // true = booked

    Movie(String title, String description, String showTime, String date) {
        this.title = title;
        this.description = description;
        this.showTime = showTime;
        this.date = date;
        Arrays.fill(seats, false); // All seats unbooked initially
    }
}

class Booking {
    String userEmail;
    int movieIndex;
    int[] seatNumbers;

    Booking(String userEmail, int movieIndex, int[] seatNumbers) {
        this.userEmail = userEmail;
        this.movieIndex = movieIndex;
        this.seatNumbers = seatNumbers;
    }
}

public class MovieBookingSystem {
    static Scanner sc = new Scanner(System.in);
    static Movie[] movies = new Movie[10];
    static int movieCount = 0;

    static String[][] users = new String[20][3]; 
    static int userCount = 0;

    static Booking[] bookings = new Booking[100];
    static int bookingCount = 0;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Admin Login\n2. User Login\n3. User Register\n4. Exit");
            System.out.print("Choose option: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> adminLogin();
                case 2 -> userLogin();
                case 3 -> userRegister();
                case 4 -> System.exit(0);
                default -> System.out.println("Invalid Option.");
            }
        }
    }

    static void userRegister() {
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();
        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();
        users[userCount][0] = email;
        users[userCount][1] = password;
        users[userCount][2] = phone;
        userCount++;
        System.out.println("Registered Successfully!");
    }

    static void userLogin() {
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();
        for (int i = 0; i < userCount; i++) {
            if (users[i][0].equals(email) && users[i][1].equals(password)) {
                userMenu(email);
                return;
            }
        }
        System.out.println("Login Failed!");
    }

    static void userMenu(String email) {
        while (true) {
            System.out.println("\nUser Menu");
            System.out.println("1. View Movies");
            System.out.println("2. Check Movie Details");
            System.out.println("3. Book Ticket");
            System.out.println("4. Cancel Ticket");
            System.out.println("5. Logout");
            System.out.print("Choose option: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> listMovies();
                case 2 -> viewMovieDetails();
                case 3 -> bookTicket(email);
                case 4 -> cancelTicket(email);
                case 5 -> { return; }
                default -> System.out.println("Invalid Option");
            }
        }
    }

    static void listMovies() {
        System.out.println("Movies Running:");
        for (int i = 0; i < movieCount; i++) {
            System.out.println((i + 1) + ". " + movies[i].title);
        }
    }

    static void viewMovieDetails() {
        listMovies();
        System.out.print("Enter movie number: ");
        int idx = sc.nextInt() - 1;
        if (idx >= 0 && idx < movieCount) {
            Movie m = movies[idx];
            System.out.println("Title: " + m.title);
            System.out.println("Description: " + m.description);
            System.out.println("Show Time: " + m.showTime);
            System.out.println("Date: " + m.date);
            showSeatLayout(m);
        } else {
            System.out.println("Invalid choice.");
        }
    }

   
    static void showSeatLayout(Movie m) {
    System.out.println("Seat Layout (B = Booked, A = Available):");
    for (int row = 0; row < 10; row++) {
        char rowLabel = (char) ('A' + row);
        for (int col = 0; col < 10; col++) {
            int seatIndex = row * 10 + col;
            String seatCode = rowLabel + Integer.toString(col + 1);
            System.out.print(seatCode + "(" + (m.seats[seatIndex] ? "B" : "A") + ") ");
        }
        System.out.println();
    }
}

    static void bookTicket(String email) {
        listMovies();
        System.out.print("Select movie: ");
        int idx = sc.nextInt() - 1;
        if (idx < 0 || idx >= movieCount) {
            System.out.println("Invalid movie.");
            return;
        }
        Movie m = movies[idx];
        showSeatLayout(m);

        System.out.print("Enter number of seats to book (max 4): ");
        int n = sc.nextInt();
        if (n > 4 || n < 1) {
            System.out.println("Invalid number.");
            return;
        }
        int[] seats = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter seat number (0-99): ");
            seats[i] = sc.nextInt();
            if (seats[i] < 0 || seats[i] > 99 || m.seats[seats[i]]) {
                System.out.println("Seat already booked or invalid.");
                return;
            }
        }
        for (int s : seats) m.seats[s] = true;

        bookings[bookingCount++] = new Booking(email, idx, seats);
        System.out.println("Ticket booked successfully!");
    }

    static void cancelTicket(String email) {
        for (int i = 0; i < bookingCount; i++) {
            if (bookings[i].userEmail.equals(email)) {
                Booking b = bookings[i];
                System.out.print("Cancel booking for movie: " + movies[b.movieIndex].title + "? (y/n): ");
                char c = sc.next().charAt(0);
                if (c == 'y') {
                    for (int seat : b.seatNumbers) {
                        movies[b.movieIndex].seats[seat] = false;
                    }
                    bookings[i] = bookings[--bookingCount];
                    System.out.println("Booking cancelled.");
                    return;
                }
            }
        }
        System.out.println("No bookings found.");
    }

    static void adminLogin() {
        System.out.print("Enter Admin Password: ");
        String pwd = sc.nextLine();
        if (pwd.equals("admin123")) {
            adminMenu();
        } else {
            System.out.println("Wrong password.");
        }
    }

    static void adminMenu() {
        while (true) {
            System.out.println("\nAdmin Menu");
            System.out.println("1. Add Movie");
            System.out.println("2. Update Movie");
            System.out.println("3. Delete Movie");
            System.out.println("4. View All Bookings");
            System.out.println("5. Logout");
            System.out.print("Choose option: ");
            int ch = sc.nextInt();
            sc.nextLine();
            switch (ch) {
                case 1 -> addMovie();
                case 2 -> updateMovie();
                case 3 -> deleteMovie();
                case 4 -> viewAllBookings();
                case 5 -> { return; }
                default -> System.out.println("Invalid Option");
            }
        }
    }

    static void addMovie() {
        System.out.print("Title: ");
        String t = sc.nextLine();
        System.out.print("Description: ");
        String d = sc.nextLine();
        System.out.print("Show Time: ");
        String s = sc.nextLine();
        System.out.print("Date: ");
        String dt = sc.nextLine();
        movies[movieCount++] = new Movie(t, d, s, dt);
        System.out.println("Movie added.");
    }

    static void updateMovie() {
        listMovies();
        System.out.print("Select movie to update: ");
        int idx = sc.nextInt() - 1;
        sc.nextLine();
        if (idx >= 0 && idx < movieCount) {
            System.out.print("New Title: ");
            movies[idx].title = sc.nextLine();
            System.out.print("New Description: ");
            movies[idx].description = sc.nextLine();
            System.out.print("New Show Time: ");
            movies[idx].showTime = sc.nextLine();
            System.out.print("New Date: ");
            movies[idx].date = sc.nextLine();
            System.out.println("Updated.");
        } else {
            System.out.println("Invalid movie.");
        }
    }

    static void deleteMovie() {
        listMovies();
        System.out.print("Select movie to delete: ");
        int idx = sc.nextInt() - 1;
        if (idx >= 0 && idx < movieCount) {
            for (int i = idx; i < movieCount - 1; i++) {
                movies[i] = movies[i + 1];
            }
            movieCount--;
            System.out.println("Deleted.");
        } else {
            System.out.println("Invalid.");
        }
    }

    static void viewAllBookings() {
        for (int i = 0; i < bookingCount; i++) {
            Booking b = bookings[i];
            System.out.print("User: " + b.userEmail + ", Movie: " + movies[b.movieIndex].title + ", Seats: ");
            for (int s : b.seatNumbers) System.out.print(s + " ");
            System.out.println();
        }
    }
}
