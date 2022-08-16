package com.example.SocialMediaAppApi.controller;

import com.example.SocialMediaAppApi.model.Details;
import com.example.SocialMediaAppApi.model.User;
import com.example.SocialMediaAppApi.repository.UserDetailsRepository;
import com.example.SocialMediaAppApi.request.UserDetailsRequest;
import com.example.SocialMediaAppApi.service.UserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserDetailsController {

    private final UserDetailsRepository userDetailsRepository;
    private final  UserDetailsService userDetailsService;

    @PostMapping("/addDetails")
    public String addUserDetails(@RequestBody UserDetailsRequest request){
        return userDetailsService.addDetails(request);


    }
//
//    @GetMapping("/allDetails")
//    List<Details> allUsers() {
//        return userDetailsRepository.findAll();
//    }
//
////
//    @GetMapping("/details/{user_id}")
//    Details oneUserDetails(@PathVariable("user_id") Long user_id) {
//
//        return userDetailsService.getDetailsForUser(user_id);
////                .orElseThrow(() -> new UserNotFoundException(id));
//    }



//    @GetMapping("/details/{user_id}")
//    Details oneUserDetails(@PathVariable("user_id") Long user_id) {
//
//        return userDetailsRepository.getDetailsForUser(user_id);
////                .orElseThrow(() -> new UserNotFoundException(id));
//    }

    @PutMapping("/editProfile/{user_id}/{id}")
    Details editUser(@RequestBody Details updatedUser, @PathVariable Long user_id,@PathVariable Long id) {

        return userDetailsRepository.findById(id)
                .map(details -> {
                    details.setDescription(updatedUser.getDescription());
                    details.setPetsNumber(updatedUser.getPetsNumber());
                    details.setOccupation(updatedUser.getOccupation());
                    details.setWorkPlace(updatedUser.getWorkPlace());
                    details.setProfilePicture(updatedUser.getProfilePicture());
                    details.setBirthPlace(updatedUser.getBirthPlace());
                    details.setLivingCity(updatedUser.getLivingCity());
                    details.setStudies(updatedUser.getStudies());
                    return userDetailsRepository.save(details);
                })
                .orElseGet(() -> {
//                    newEmployee.setId(id);
//                    return repository.save(newEmployee);
                    return null;
                });
    }


}
