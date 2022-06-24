package com.andrew.member;

/** An entity used to represent a Member. This class is used to interact with the system and the database.
 *  A member is defined by having an email address, first name, last name, and phone number.
 */
public class Member implements Comparable<Member> {
    private String email;
    private String firstName;
    private String lastName;
    private String phone;

    public Member(String email, String firstName, String lastName, String phone) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }


    @Override
    public String toString() {
        return "{EMAIL: " + email +
                ", FIRST_NAME: " + firstName +
                ", LAST_NAME: " + lastName +
                ", PHONE: " + phone + "}";
    }
    @Override
    public final boolean equals(Object obj) {

        if (this == obj) return true;
        // obj == null is covered with instanceof.
        // this method allows for sub types of Members to be compared.
        // however has to be final, because otherwise breaks rule, transitivity.
        // so this equals function will be used by all subtypes to determine equality.
        if (!(obj instanceof Member)) return false;
        Member member = (Member) obj;
        return (email.equals(member.getEmail()));
    }

    @Override
    public int compareTo(Member o) {
        return email.compareTo(o.getEmail());
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

}
