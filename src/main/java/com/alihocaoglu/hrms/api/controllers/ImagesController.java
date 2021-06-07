package com.alihocaoglu.hrms.api.controllers;

import com.alihocaoglu.hrms.busines.abstracts.CvService;
import com.alihocaoglu.hrms.busines.abstracts.ImageService;
import com.alihocaoglu.hrms.core.services.CloudinaryService;
import com.alihocaoglu.hrms.core.utilities.results.ErrorResult;
import com.alihocaoglu.hrms.core.utilities.results.SuccessResult;
import com.alihocaoglu.hrms.dataAccess.abstracts.CvDao;
import com.alihocaoglu.hrms.entities.concretes.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/images")
@CrossOrigin
public class ImagesController {

    private CloudinaryService cloudinaryService;
    private ImageService imageService;
    private CvDao cvDao;

    @Autowired
    public ImagesController(CloudinaryService cloudinaryService, ImageService imageService,CvDao cvDao) {
        this.cloudinaryService = cloudinaryService;
        this.imageService = imageService;
        this.cvDao=cvDao;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(this.imageService.getAll());
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile ,@RequestParam int cvId){
        try {
            BufferedImage bufferedImage= ImageIO.read(multipartFile.getInputStream());
            if(bufferedImage==null){
                return ResponseEntity.badRequest().body(new ErrorResult("Resim validasyonu başarısız"));
            }
        }catch (IOException exception){
            return ResponseEntity.badRequest().body(new ErrorResult("Resim validasyonu başarısız"));
        }

        try {
            Map result= cloudinaryService.upload(multipartFile);
            Image image=new Image();
            image.setName((String)result.get("original_filename"));
            image.setImageUrl((String)result.get("url"));
            image.setImageId((String)result.get("public_id"));
            image.setCv(this.cvDao.getById(cvId));
            return ResponseEntity.ok(this.imageService.add(image));
        }catch (IOException exception){
            return ResponseEntity.badRequest().body(new ErrorResult("Resim yukleme aşamasında bir sorun oluştu"));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam int id){
        if(!this.imageService.isExist(id)){
            return ResponseEntity.badRequest().body(new ErrorResult("Böyle bir resim bulunamadı"));
        }

        try {
            Image image=this.imageService.getById(id).getData();
            Map result=cloudinaryService.delete(image.getImageId());
            this.imageService.delete(id);
            return ResponseEntity.ok(new SuccessResult("Resim başarıyla silindi"));
        }catch (IOException exception){
            return ResponseEntity.badRequest().body(new ErrorResult("Bir hata oluştu"));
        }



    }
}
