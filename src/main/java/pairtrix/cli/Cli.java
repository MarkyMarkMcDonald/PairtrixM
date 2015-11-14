package pairtrix.cli;

import pairtrix.domain.Application;

import java.util.Scanner;

/* Use this for acceptance testing, toss is once we start building out a more convenient client */
public class Cli {

    private final Scanner scanner;

    public Cli() {
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Application application = new Application();

        Cli cli = new Cli();
        Integer count = cli.promptTeamMemberCount();

        System.out.println("Please list their names");

        for (int i = 0; i < count; i++) {
            String teamMemberName = cli.promptTeamMemberName();
            application.addTeamMember(teamMemberName);
        }

        System.out.println("Pairings:");
        System.out.println(application.pairings());
    }

    private String promptTeamMemberName() {
        return scanner.nextLine();
    }

    private Integer promptTeamMemberCount() {
        System.out.println("How many team members do you have?");
        return scanner.nextInt();
    }

}
