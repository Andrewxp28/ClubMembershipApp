package com.andrew.member;

import com.andrew.membership.Membership;
import com.andrew.membership.MembershipService;
import com.andrew.membership.MembershipServiceImpl;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * A concrete implementation of MemberController interface. Controls and executes commands given from the application.
 * Displays using console.
 */
public class MemberControllerImpl implements MemberController {

    // assumes connection connected already outside.
    // uses a service.
    private MemberService memberService;
    private MembershipService membershipService;

    public MemberControllerImpl() {
        memberService = new MemberServiceImpl();
        membershipService = new MembershipServiceImpl();
    }


    @Override
    public void displayMemberOptions() {
        String[] options = {"1: See all members",
                "2: See all active members",
                "3: Find a member",
                "4: Add a member",
                "5: Update a member's details",
                "6: Delete a member",
                "7: Delete ALL members",
                "8: Return to main menu"};
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
                executeMemberOption(option);
            } else {
                System.out.println("Please enter an integer value between 1 and " + options.length);
                scanner.next();
            }
        }
    }

    @Override
    public void executeMemberOption(int option) {
        // already validated option argument.
        // run method corresponding to option number
        switch (option) {
            // See all members
            case 1 -> displayAllMembers();
            // See all active members
            case 2 -> displayAllActiveMembers();
            // Find a member
            case 3 -> displayFormAndFindOneMember();
            // Add a member
            case 4 -> displayFormAndInsertOneMember();
            // Update a member's details
            case 5 -> displayFormAndUpdateOneMember();
            // Delete a member
            case 6 -> displayFormAndDeleteOneMember();
            // for case 7
            case 7 -> displayAndDeleteAllMembers();
            // and for other cases we just return to main menu
        }
    }




    @Override
    public void displayAllMembers() {
        printOneSpacer();
        // get list of members in db
        List<Member> members = memberService.findAllMembers();
        if (members == null) {
            System.out.println("There are currently no members...");
        } else {
            // write members to console
            for (Member member: members) {
                System.out.println(member);
            }
        }
        printOneSpacer();
        // return.
    }

    @Override
    public void displayFormAndFindOneMember() {

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
        // now we search the db for member with that email
        Member rsMember = memberService.findOneMember(memberEmail);
        printOneSpacer();
        if (rsMember != null) {
            // found member
            printOneMember(rsMember);
        } else {
            System.out.println("Member with email: " + memberEmail + "was not found.");
        }
        printOneSpacer();
    }

    @Override
    public void displayFormAndInsertOneMember() {
        String memberEmail;
        String firstName;
        String lastName;
        String phone;
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter in new member's email: ");
            memberEmail = scanner.next();
            System.out.print("Enter in new member's first name: ");
            firstName = scanner.next();
            System.out.print("Enter in new member's last name: ");
            lastName = scanner.next();
            System.out.print("Enter in new member's phone number: ");
            phone = scanner.next();
        } catch (Exception ex) {
            System.out.println("An unexpected error has happened. Please try again.");
            return;
        }
        // build member object and insert into db.
        Member member = new Member(memberEmail, firstName, lastName, phone);
        printOneSpacer();
        if (memberService.insertOneMember(member)) {
            // inserted successfully
            System.out.println("Inserted member successfully");
        }
        printOneSpacer();
    }

    @Override
    public void displayFormAndUpdateOneMember() {
        String memberEmail;
        String newEmail;
        String newFirstName;
        String newLastName;
        String newPhone;
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter in member's email: ");
            memberEmail = scanner.next();
            System.out.print("Enter in updated email (enter \"*\" if no change required): ");
            newEmail = scanner.next();
            System.out.print("Enter in updated first name (enter \"*\" if no change required): ");
            newFirstName = scanner.next();
            System.out.print("Enter in updated last name (enter \"*\" if no change required): ");
            newLastName = scanner.next();
            System.out.print("Enter in updated phone number (enter \"*\" if no change required): ");
            newPhone = scanner.next();
        } catch (Exception ex) {
            System.out.println("An unexpected error has happened. Please try again.");
            return;
        }
        printOneSpacer();
        // now we need to first check if this member with memberEmail exists.
        Member rsMember = memberService.findOneMember(memberEmail);
        if (rsMember == null) {
            System.out.println("Member with email: " + memberEmail + "does not exist. Please try again.");
            printOneSpacer();
            return;
        }
        // now we need to check the empty fields.
        if (newEmail.equals("*")) newEmail = rsMember.getEmail();
        if (newFirstName.equals("*")) newFirstName = rsMember.getFirstName();
        if (newLastName.equals("*")) newLastName = rsMember.getLastName();
        if (newPhone.equals("*")) newPhone = rsMember.getPhone();

        // make a member object and update the db using memberService.
        Member updatedMember = new Member(newEmail, newFirstName, newLastName, newPhone);
        if (memberService.updateOneMember(memberEmail, updatedMember)) {
            System.out.println("Updated member details with email " + memberEmail);
        } else {
            System.out.println("An unexpected error has happened. Please try again.");
        }
        printOneSpacer();
    }

    @Override
    public void displayFormAndDeleteOneMember() {
        String memberEmail;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter in email address of member to be deleted: ");
        try {
            memberEmail = scanner.next();
        } catch (Exception ex) {
            System.out.println("An unexpected error has happened. Please try again.");
            return;
        }
        printOneSpacer();
        if (memberService.deleteOneMember(memberEmail)) {
            // successfully deleted
            System.out.println("Member with email: " + memberEmail + " was successfully deleted.");
        }
        printOneSpacer();
    }

    @Override
    public void displayAllActiveMembers() {
        printOneSpacer();
        // Sets only hold unique values.
        Set<String> activeMemberEmails;
        List<Membership> activeMemberships = membershipService.findAllActiveMemberships();
        if (activeMemberships == null) {
            System.out.println("There are currently no active members");
        } else {
            // there are active memberships, so we find all active member emails.
            activeMemberEmails = new HashSet<>();
            for (Membership membership: activeMemberships) {
                activeMemberEmails.add(membership.getMemberEmail());
            }
            // now we search db for member details for each member email from the set activeMemberEmails.
            List<Member> activeMembers = new ArrayList<>();
            for (String email: activeMemberEmails) {
                // no need to worry about finding null members, sql db has proper constraints.
                Member member = memberService.findOneMember(email);
                activeMembers.add(member);
            }
            // now we loop and print members
            for (Member member: activeMembers) {
                System.out.println(member);
            }

        }


        printOneSpacer();
    }

    @Override
    public void displayAndDeleteAllMembers() {
        printOneSpacer();
        System.out.print("Warning: This will delete all members and their corresponding memberships from the database" +
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
            if (memberService.deleteAllMembers()) {
                System.out.println("Deleted all members and their corresponding memberships successfully");
            } else {
                System.out.println("Unexpected error. Please try again.");
            }
        }
        // else we just return to main menu;
        printOneSpacer();
    }

    /**
     * Prints a member in a user-friendly way.
     * @param member Member instance to be printed.
     */
    private void printOneMember(Member member) {
        System.out.println(
                "Member email: " + member.getEmail() + "\n" +
                "First Name: " + member.getFirstName() + "\n" +
                "Last Name: " + member.getLastName() + "\n" +
                "Phone: " + member.getPhone() + "\n"
        );
    }

    /**
     * Adds formatting to the console output.
     */
    private void printOneSpacer() {
        System.out.println("--------------------------------------------------");
    }
}
