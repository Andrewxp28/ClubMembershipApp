package com.andrew;

import com.andrew.db.SQLDB;
import com.andrew.db.SqliteSQLDBImpl;
import com.andrew.member.MemberController;
import com.andrew.member.MemberControllerImpl;
import com.andrew.membership.MembershipController;
import com.andrew.membership.MembershipControllerImpl;

import java.util.InputMismatchException;
import java.util.Scanner;


public class ConsoleApp {
    public static void main(String[] args) {
        /*
        String[] mainMenuOptions =
                {"1: Member Management",
                 "2: Membership Management",
                 "3: exit"
                };
         */
        // runs console application
        run();
    }

    public static void run() {

        String[] mainMenuOptions =
                {       "1: Member Management",
                        "2: Membership Management",
                        "3: Exit"
                };
        // setup db
        SQLDB db = new SqliteSQLDBImpl();
        db.initialiseDb("ClubDb7.db");
        // setup up controllers
        MemberController memberController = new MemberControllerImpl();
        MembershipController membershipController = new MembershipControllerImpl();

        Scanner scanner = new Scanner(System.in);
        int option = 0;
        while (option != 3) {
            printMainMenu(mainMenuOptions);
            try {
                    option = scanner.nextInt();
            } catch (InputMismatchException ex){
                System.out.println("Please enter an integer value between 1 and " + mainMenuOptions.length);
                scanner.next();
            } catch (Exception ex) {
                System.out.println("An unexpected error happened. Please try again.");
                //scanner.next();
            }

            // activate specific action based on option value

            // user wants to exit
            if (option == 3) continue;

            // Member Management option
            if (option == 1) {
                memberController.displayMemberOptions();
            } else if (option == 2) {
                // use display membership options method here with its controller
            }
            /*
                we can separate these options into two categories, members and membership options.
                we need options for:
                - seeing all members
                - finding one member
                - adding a member
                - updating a member
                - deleting a member

                - seeing active members?
                // steps for above.
                // need to select memberships where end_date is greater than current date.
                // option 1: make new method that does a join on member and membership where email = member_email.

                // option 2: get all active memberships, then find and return members with matching emails.
                // use set, no duplicates.

                - seeing all memberships
                - finding memberships of one member
                - seeing all active memberships
                - adding a membership
                - deleting a membership
                - updating a membership

             */
        }
        scanner.close();
    }
    public static void printMainMenu(String[] options) {
        for (String option: options) {
            System.out.println(option);
        }
        System.out.print("Choose an option: ");
    }
}
