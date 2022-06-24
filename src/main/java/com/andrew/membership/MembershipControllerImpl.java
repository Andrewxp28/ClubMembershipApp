package com.andrew.membership;

import com.andrew.member.Member;
import com.andrew.member.MemberService;
import com.andrew.member.MemberServiceImpl;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * A concrete implementation of MembershipController.
 */
public class MembershipControllerImpl implements MembershipController {

    private MembershipService membershipService;
    private MemberService memberService;
    public MembershipControllerImpl() {
        membershipService = new MembershipServiceImpl();
        memberService = new MemberServiceImpl();
    }

    @Override
    public void displayMembershipOptions() {
        String[] options = {
                "1: See all memberships",
                "2: See all active memberships",
                "3: Find all memberships of a member",
                "4: Find one membership",
                "5: Insert one membership",
                "6: Delete one membership",
                "7: Delete ALL memberships",
                "8: Return to main menu"
        };
        for (String option: options) {
            System.out.println(option);
        }
        System.out.print("Choose an option: ");
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        boolean picked = false;
        while (!picked) {
            try {
                option = scanner.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Please enter an integer value between 1 and " + options.length);
                scanner.next();
            } catch (Exception ex) {
                System.out.println("An unexpected error has happened. Please try again.");
                scanner.next();
            }
            // checking option is within boundaries
            if (option > 0 && option <= options.length) {
                // within boundaries
                picked = true;
                executeMembershipOption(option);
            } else {
                System.out.println("Please enter an integer value between 1 and " + options.length);
                scanner.next();
            }
        }
    }

    @Override
    public void executeMembershipOption(int option) {
        // option already validated.
        // run method corresponding to option number
        switch (option) {
            case 1 -> displayAllMemberships();
            case 2 -> displayAllActiveMemberships();
            case 3 -> displayFormAndFindMembershipsOfOneMember();
            case 4 -> displayFormAndFindOneMembership();
            case 5 -> displayFormAndInsertOneMembership();
            case 6 -> displayFormAndDeleteOneMembership();
            case 7 -> displayAndDeleteAllMemberships();
            // for other cases we return to main menu.
        }
    }


    @Override
    public void displayAllMemberships() {
        printOneSpacer();
        // getting list of memberships from db
        List<Membership> membershipList = membershipService.findAllMemberships();
        if (membershipList == null) {
            System.out.println("There are currently no memberships...");
        } else {
            // print memberships
            for (Membership membership: membershipList) {
                System.out.println(membership);
            }
        }
        printOneSpacer();
    }

    @Override
    public void displayAllActiveMemberships() {
        printOneSpacer();
        List<Membership> activeMemberships = membershipService.findAllActiveMemberships();
        if (activeMemberships == null) {
            System.out.println("There are currently no active memberships");
        } else {
            // print active memberships
            for (Membership membership: activeMemberships) {
                System.out.println(membership);
            }
        }
        printOneSpacer();
    }

    @Override
    public void displayFormAndFindMembershipsOfOneMember() {
        String memberEmail;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter in member email address: ");
        try {
            memberEmail = scanner.next();
        } catch (Exception ex) {
            printOneSpacer();
            System.out.println("An unexpected error has happened. Please try again.");
            printOneSpacer();
            return;
        }
        printOneSpacer();
        // now we search db for memberships with memberEmail.
        List<Membership> rsMemberships = membershipService.findAllMembershipsOfOneMember(memberEmail);
        if (rsMemberships == null) {
            System.out.println("The member with the email " + memberEmail + "does not have any memberships");
        } else {
            // print the memberships
            for (Membership membership: rsMemberships) {
                System.out.println(membership);
            }
        }
        printOneSpacer();
    }

    @Override
    public void displayFormAndFindOneMembership() {
        // need to show two options:
        // find membership by details or by id.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Search by membership details or membership id?");
        System.out.println("1: Membership details");
        System.out.println("2: Membership ID");
        System.out.print("Choose an option: ");
        int option = 0;
        boolean picked = false;
        while (!picked) {
            try {
                option = scanner.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Please enter an integer value of 1 or 2");
                scanner.next();
            } catch (Exception ex) {
                System.out.println("An unexpected error has happened. Please try again.");
                scanner.next();
            }
            if (option == 1 || option == 2) {
                picked = true;
            }
        }
        // now search db based on option.
        if (option == 1) {
            // membership details method
            displayFormForMembershipDetailsAndSearch();
        } else {
            // membership id details
            displayFormForMembershipAndSearchById();
        }
    }
    private void displayFormForMembershipDetailsAndSearch() {
        String memberEmail;
        String startDate;
        String endDate;
        String type;
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter in member email: ");
            memberEmail = scanner.next();
            System.out.print("Enter membership start date (YYYY-MM-DD): ");
            startDate = scanner.next();
            System.out.print("Enter membership end date (YYYY-MM-DD): ");
            endDate = scanner.next();
            System.out.print("Enter membership type " + Arrays.toString(MembershipType.values()) + ": ");
            type = scanner.next();
        } catch (Exception ex) {
            printOneSpacer();
            System.out.println("An unexpected error has happened. Please try again.");
            printOneSpacer();
            return;
        }
        printOneSpacer();
        // need to change dates into LocalDate format
        // need to change type to MembershipType
        LocalDate startDate2 = null;
        LocalDate endDate2 = null;
        MembershipType type1 = null;
        /*
        This is so ugly but I don't have time to refactor.
        The try catch blocks are used to validate formats of start and end date and membership type.
         */
        try {
            startDate2 = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeException ex) {
            System.out.println("Start date is not in the correct format. Please try again.");
            printOneSpacer();
            return;
        } catch (Exception ex) {
            System.out.println("Unexpected error. Please try again.");
            printOneSpacer();
            return;
        }
        try {
            endDate2 = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeException ex) {
            System.out.println("End date is not in the correct format. Please try again.");
            printOneSpacer();
            return;
        } catch (Exception ex) {
            System.out.println("Unexpected error. Please try again.");
            printOneSpacer();
            return;
        }
        try {
            type1 = MembershipType.valueOf(type.toUpperCase());

        } catch (IllegalArgumentException ex) {
            System.out.println("Not a valid membership type. Please try again.");
            printOneSpacer();
            return;
        } catch (Exception ex) {
            System.out.println("Unexpected error. Please try again.");
            printOneSpacer();
            return;
        }
        // Start and end dates are corrected formatted, Membership type is also formatted correctly.
        // search by the details.
        Membership membership = membershipService.findOneMembership(
                new Membership(startDate2,endDate2, memberEmail, type1));
        if (membership == null) {
            System.out.println("Membership with details: \n" +
                    "START_DATE: " + startDate2 + "\n" +
                    "END_DATE: " + endDate2 + "\n" +
                    "MEMBER_EMAIL: " + memberEmail + "\n" +
                    "TYPE: " + type1 + "\n" +
                    " was not found.");
        } else {
            // found the membership, so we print
            printOneMembership(membership);
        }
        printOneSpacer();
    }

    private Membership displayFormForMembershipAndSearchById() {
        int membershipId;
        Membership membership = null;
        Scanner scanner = new Scanner(System.in);
        printOneSpacer();
        System.out.print("Enter membership id (Greater than 0): ");
        try {
            membershipId = scanner.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("Please enter an integer value. Please try again.");
            printOneSpacer();
            return null;
        } catch (Exception ex) {
            System.out.println("An unexpected error has happened. Please try again.");
            printOneSpacer();
            return null;
        }
        // check if membershipId > 0
        if (membershipId > 0 && membershipId < Integer.MAX_VALUE) {
            // search db for membership with membershipId
            membership = membershipService.findOneMembership(membershipId);
            if (membership == null) {
                System.out.println("Membership with membership id " + membershipId + " was not found.");
            } else {
                printOneMembership(membership);
            }
        } else {
            System.out.println("Please enter an integer value from 1 and " + Integer.MAX_VALUE + ". Please try again.");
            return null;
        }
        printOneSpacer();
        return membership;
    }

    @Override
    public void displayFormAndInsertOneMembership() {
        String startDate;
        String endDate;
        String memberEmail;
        String type;
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter in member email: ");
            memberEmail = scanner.next();
            System.out.print("Enter new membership start date (YYYY-MM-DD): ");
            startDate = scanner.next();
            System.out.print("Enter new membership end date (YYYY-MM-DD): ");
            endDate = scanner.next();
            System.out.print("Enter new membership type " + Arrays.toString(MembershipType.values()) + ": ");
            type = scanner.next();
        } catch (Exception ex) {
            printOneSpacer();
            System.out.println("An unexpected error has happened. Please try again.");
            printOneSpacer();
            return;
        }
        printOneSpacer();
        LocalDate startDate2 = null;
        LocalDate endDate2 = null;
        MembershipType type1 = null;
        try {
            startDate2 = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
            endDate2 = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);
            type1 = MembershipType.valueOf(type.toUpperCase());
        } catch (DateTimeException ex) {
            if (startDate2 == null) {
                System.out.println("Start date is not in the correct format. Please try again.");
            } else {
                System.out.println("End date is not in the correct format. Please try again.");
            }
            printOneSpacer();
            return;
        } catch (IllegalArgumentException ex) {
            System.out.println("Not a valid membership type. Please try again.");
            printOneSpacer();
            return;
        } catch (Exception ex) {
            System.out.println("An unexpected error has happened. Please try again.");
            printOneSpacer();
            return;
        }
        // need to make sure a member with memberEmail exists.
        Member member = memberService.findOneMember(memberEmail);
        if (member == null) {
            // no member with this email exists.
            System.out.println("Member with email: " + memberEmail + " does not exists. Please make a member before" +
                    " making a membership for the member.");
        } else {
            // make membership object and insert into db.
            Membership membership = new Membership(startDate2, endDate2, memberEmail, type1);
            if (membershipService.insertOneMembership(membership)) {
                System.out.println("Membership with details: \n" +
                        "START_DATE: " + startDate2 + "\n" +
                        "END_DATE: " + endDate2 + "\n" +
                        "MEMBER_EMAIL: " + memberEmail + "\n" +
                        "TYPE: " + type1 + "\n" +
                        "was successfully added.");
            } else {
                System.out.println("Unexpected error. Please try again.");
            }
        }
        printOneSpacer();
    }

    @Override
    public void displayFormAndDeleteOneMembership() {
        Membership membership = null;
        membership = displayFormForMembershipAndSearchById();
        if (membership == null) return;
        // warning message asking if we are sure we want to delete member
        System.out.print("Warning: This will delete the above membership from the database" +
                ". Do you wish to continue? (y/n): ");
        String ans;
        Scanner scanner = new Scanner(System.in);
        try {
            ans = scanner.next();
            ans = ans.toLowerCase();
        } catch (Exception ex) {
            System.out.println("An unexpected error has happened. Please try again.");
            printOneSpacer();
            return;
        }
        if (ans.equals("y")) {
            // delete members from db
            if (membershipService.deleteOneMembership(membership.getMembershipId())) {
                System.out.println("Deleted membership successfully");
            } else {
                System.out.println("Unexpected error. Please try again.");
            }
        }
        printOneSpacer();
        // otherwise just return to main menu.
    }

    @Override
    public void displayAndDeleteAllMemberships() {
        printOneSpacer();
        System.out.print("Warning: This will delete all memberships from the database" +
                ". Do you wish to continue? (y/n): ");
        String ans;
        Scanner scanner = new Scanner(System.in);
        try {
            ans = scanner.next();
            ans = ans.toLowerCase();
        } catch (Exception ex) {
            System.out.println("An unexpected error has happened. Please try again.");
            return;
        }
        if (ans.equals("y")) {
            // delete members from db
            if (membershipService.deleteAllMemberships()) {
                System.out.println("Deleted all successfully");
            } else {
                System.out.println("Unexpected error. Please try again.");
            }
        }
        // else we just return to main menu;
        printOneSpacer();
    }
    /**
     * Prints a membership in a user-friendly way.
     * @param membership Membership instance to be printed.
     */
    private void printOneMembership(Membership membership) {
        System.out.print(
                "MEMBERSHIP ID: " + membership.getMembershipId() + "\n" +
                        "START DATE: " + membership.getStartDate() + "\n" +
                        "END DATE: " + membership.getEndDate() + "\n" +
                        "MEMBER_EMAIL: " + membership.getMemberEmail() + "\n" +
                        "TYPE: " + membership.getType() + "\n"
        );
    }

    /**
     * Adds formatting to the console output.
     */
    private void printOneSpacer() {
        System.out.println("--------------------------------------------------");
    }
}
