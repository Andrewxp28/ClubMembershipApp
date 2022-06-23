package com.andrew.member;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MemberControllerImpl implements MemberController {

    // assumes connection connected already outside.
    // uses a service.
    MemberService memberService;

    public MemberControllerImpl() {
        memberService = new MemberServiceImpl();
    }


    @Override
    public void displayMemberOptions() {
        String[] options = {"1: See all members",
                "2: Find a member",
                "3: Add a member",
                "4: Update a member's details",
                "5: Delete a member",
                "6: Return to main menu"};
        for (String option: options) {
            System.out.println(option);
        }
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
                //scanner.next();
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

            // Find a member
            case 2 -> displayFormAndFindOneMember();

            // Add a member
            case 3 -> displayFormAndInsertOneMember();

            // Update a member's details
            case 4 -> displayFormAndUpdateOneMember();

            // Delete a member
            case 5 -> displayFormAndDeleteOneMember();
            // for case 6 we just return to main menu
        }
    }




    @Override
    public void displayAllMembers() {
        // get list of members in db
        List<Member> members = memberService.findAllMembers();
        if (members == null) {
            printOneSpacer();
            System.out.println("There are currently no members...");
            printOneSpacer();
            return;
        }
        // write members to console
        printOneSpacer();
        for (Member member: members) {
            System.out.println(member);
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
            System.out.println("An unexpected error has happened. Please try again.");
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
        System.out.println("newEmail: " + newEmail);
        System.out.println("newFirstName: " + newFirstName);
        System.out.println("newLastName: " + newLastName);
        System.out.println("newPhone: " + newPhone);

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

    private void printOneMember(Member member) {
        System.out.println(
                "Member email: " + member.getEmail() + "\n" +
                "First Name: " + member.getFirstName() + "\n" +
                "Last Name: " + member.getLastName() + "\n" +
                "Phone: " + member.getPhone() + "\n"
        );
    }
    private void printOneSpacer() {
        System.out.println("--------------------------------------------------");
    }
}
