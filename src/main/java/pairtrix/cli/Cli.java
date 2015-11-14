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
        InMemoryTeamMembersRepository teamRepository = new InMemoryTeamMembersRepository();

        Application application = new Application(teamRepository);
        Cli cli = new Cli();

        Integer count = cli.promptTeamMemberCount();
        System.out.println("Please list their names");

        for (int i = 0; i < count; i++) {
            String teamMemberName = cli.promptTeamMemberName();
            application.addTeamMember(teamMemberName);
        }

        while (true) {
            application = new Application(teamRepository);
            System.out.println("Pairings:");
            System.out.println(application.pairings());
            System.out.println("Hit enter for more pairings");
            cli.waitForEnterKey();
        }
    }

    private String promptTeamMemberName() {
        return scanner.nextLine();
    }

    private Integer promptTeamMemberCount() {
        System.out.println("How many team members do you have?");
        return scanner.nextInt();
    }

    private void waitForEnterKey() {
        scanner.nextLine();
    }
}
