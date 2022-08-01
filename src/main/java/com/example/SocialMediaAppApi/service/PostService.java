package com.example.SocialMediaAppApi.service;


import com.example.SocialMediaAppApi.model.Post;
import com.example.SocialMediaAppApi.model.User;
import com.example.SocialMediaAppApi.repository.PostsRepository;
import com.example.SocialMediaAppApi.request.PostRequest;
import com.example.SocialMediaAppApi.security.token.Token;
import com.example.SocialMediaAppApi.security.token.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
@AllArgsConstructor
public class PostService {

    private final TokenService tokenService;
    private final PostsRepository postsRepository;

    public String publishPost(PostRequest request){
        Token newToken = tokenService.verifyToken(request.getToken());
        String pictureURL = photoProcessing(request.getPicture(), newToken.getUser().getEmail());

        Post post = new Post(
                pictureURL,
                request.getText(),
                request.getPostDate(),
                newToken.getUser()

        );
        postsRepository.save(post);
        return "post published";

    }


    public String photoProcessing(String photoString, String userEmail){

        String pictureName =encoder(userEmail+System.currentTimeMillis()) ;
        String[] strings = photoString.split(",");
        String extension;
        switch (strings[0]) {//check image's extension
            case "data:image/jpeg;base64":
                extension = "jpeg";
                break;
            case "data:image/png;base64":
                extension = "png";
                break;
            default://should write cases for more images types
                extension = "jpg";
                break;
        }
        //convert base64 string to binary data
        byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
        String path = "C:\\Users\\Eliza\\Desktop\\Licenta\\SocialMediaAppApi\\src\\main\\resources\\Images\\"+ pictureName +"." + extension;
//        String path = "/resources/Images/"+ pictureName +"." + extension;
        File file = new File(path);
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(data);
            System.out.println(path);
            return path;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String encoder(String input)
    {
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/feed")
    List<Post> allPosts() { return postsRepository.findAll(); }

}
