package com.loadbalance.major;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@RestController
public class MainController {
    RestTemplate restTemplate=new RestTemplate();

    // @GetMapping(value="get-image/{id}",produces=MediaType.IMAGE_JPEG_VALUE)
    // ResponseEntity<?> getImagefromDb(@PathVariable int id) {
    //     // RestTemplate restTemplate = new RestTemplate();
    //     // ResponseEntity<String> response =
    //     // restTemplate.getForEntity("localhost:5000/get_image_from_db/"+id,
    //     // String.class);
    //     // return response;

    //     // retrieve image bytes from the Flask API
    //     byte[] imageBytes = restTemplate.getForObject("http://localhost:5000/get_image_from_db/" + id, byte[].class);
        
    //     try (FileWriter writer = new FileWriter("C:\\deleteit\\delete.txt")) {
    //         BufferedWriter bufferedWriter = new BufferedWriter(writer); // create BufferedWriter object to write efficiently
    //         bufferedWriter.write(imageBytes.toString()); // write to the file
    //         bufferedWriter.close();
    //     } catch (IOException e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //     }
    //         // encode the image bytes as a Base64 string
    //     String encodedImage = Base64.getEncoder().encodeToString(imageBytes);

    //     // build the data URI for the image
    //     String dataURI = "data:image/jpeg;base64," + encodedImage;

    //     // create the response headers
    //     HttpHeaders headers = new HttpHeaders();
    //     headers.setContentType(MediaType.IMAGE_JPEG);

    //     // create the response entity with the data URI and headers
    //     ResponseEntity<String> response = new ResponseEntity<String>(dataURI, headers, HttpStatus.OK);

    //     return response;
    // }

    @GetMapping(value="get-image/{id}",produces=MediaType.IMAGE_JPEG_VALUE)
    ResponseEntity<?> getImagefromDb(@PathVariable int id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://127.0.0.1:51908/get_image_from_db/"+id, String.class);
        String imageData = response.getBody();
        byte[] imageBytes = Base64.getDecoder().decode(imageData);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(imageBytes.length);
        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

    @GetMapping("get-image-count/{id}")
    ResponseEntity<?> getImageCount(@PathVariable int id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate
                .getForEntity("http://127.0.0.1:5010/get_count_of_image/" + id, String.class);
        return response;
    }

    @GetMapping("get-image-crowd-density/{id}")
    ResponseEntity<?> getImageCrowdDensity(@PathVariable int id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate
                .getForEntity("http://127.0.0.1:5010/get_crowd_density/" + id, String.class);
        return response;
    }

    public byte[] getImageBytesById(int id) {
        // create a RestTemplate to call the Flask API
        RestTemplate restTemplate = new RestTemplate();

        // call the Flask API to retrieve the image bytes
        ResponseEntity<byte[]> responseEntity = restTemplate
                .getForEntity("http://localhost:5000/get_image_from_db/" + id, byte[].class);

        // return the image bytes if the response is successful
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        }

        // return null if the response is not successful
        return null;
    }

}
