package com.example.SocialMediaAppApi.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;


@Entity
@Table(name="Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User{

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;
    private LocalDate birthDate;
    private LocalDate joinedDate = LocalDate.now();
    private String age = calculateAge(birthDate);



public User( String firstName, String lastName,String email,String password, String gender,LocalDate birthDate,LocalDate joinedDate) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.gender = gender;
    this.birthDate = birthDate;
    this.joinedDate = joinedDate;
}
    public User( String firstName, String lastName,String email,String password ,LocalDate birthDate,LocalDate joinedDate, String gender) {

    }

    public User(String firstName, String lastName, String email, String password, String gender, LocalDate birthDate, LocalDate joinedDate, String age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.birthDate = birthDate;
        this.joinedDate = joinedDate;
        this.age = age;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public static String calculateAge(LocalDate birthDate)
    {

        LocalDate currentDate = LocalDate.now();
        if ((birthDate != null) && (currentDate != null))
        {
            Period period = Period.between(birthDate, currentDate);
            if(period.isZero()) return period.getMonths()+" months";
            else

            return period.getYears()+" years and " + period.getMonths() + " months";
        }
        else
        {
            return null;
        }
    }




    public String getFullName() {
        return firstName + " " + lastName;
    }


    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return email;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return false;
    }

    public boolean isEnabled() {
        return false;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", birthDate=" + birthDate +
                ", joinedDate=" + joinedDate +
                '}';
    }
}
//public class User implements UserDetails{
//    @Id
//    @SequenceGenerator(
//            name = "user_sequence",
//            sequenceName = "user_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "user_sequence"
//    )
//    private Long id;
//    private String firstName;
//    private String lastName;
//    private String email;
//    private String password;
//    private String gender;
//    private LocalDate birthDate;
//    private LocalDate joinedDate;
//
//

//    public User(String firstName, String lastName, String email, String password, LocalDate birthDate, LocalDate joinedDate, String gender) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.password = password;
//        this.birthDate = birthDate;
//        this.joinedDate = joinedDate;
//        this.gender = gender;
//
//    }
//
//
//        @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public String getFullName() {
//        return firstName + " " + lastName;
//    }
//
//    public LocalDate getBirthDate() {
//        return birthDate;
//    }
//
//    public LocalDate getJoinedDate() {
//        return joinedDate;
//    }
//    public String getGender(){
//        return gender;
//    }
//
//   @Override
//    public String getPassword() {
//        return password;
//    }
//
//
//    @Override
//    public String getUsername() {
//        return email;
//    }
//
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
//}