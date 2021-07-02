package com.alihocaoglu.hrms.busines.concretes;

import com.alihocaoglu.hrms.busines.abstracts.ImageService;
import com.alihocaoglu.hrms.core.services.CloudinaryService;
import com.alihocaoglu.hrms.core.utilities.results.*;
import com.alihocaoglu.hrms.dataAccess.abstracts.CvDao;
import com.alihocaoglu.hrms.dataAccess.abstracts.ImageDao;
import com.alihocaoglu.hrms.entities.concretes.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ImageManager implements ImageService {

    private ImageDao imageDao;
    private CloudinaryService cloudinaryService;
    private CvDao cvDao;

    @Autowired
    public ImageManager(ImageDao imageDao,CloudinaryService cloudinaryService,CvDao cvDao) {
        this.imageDao = imageDao;
        this.cloudinaryService=cloudinaryService;
        this.cvDao=cvDao;
    }

    @Override
    public DataResult<List<Image>> getAll() {
        return new SuccessDataResult<List<Image>>(this.imageDao.findByOrderById(),"Data listelendi");
    }

    @Override
    public Result update(MultipartFile multipartFile, int cvId) {
        try {
            BufferedImage bufferedImage= ImageIO.read(multipartFile.getInputStream());
            if(bufferedImage==null){
                return new ErrorResult("Resim validasyonu başarısız");
            }
        }catch (IOException exception){
            return new ErrorResult("Resim validasyonu başarısız");
        }
        Image image=this.imageDao.findByCvId(cvId);
        if(image.getImageId()==null){
            try {
                Map result=cloudinaryService.upload(multipartFile);
                image.setName((String)result.get("original_filename"));
                image.setImageUrl((String)result.get("url"));
                image.setImageId((String)result.get("public_id"));
                this.imageDao.save(image);
                return new SuccessResult("Başarıyla eklendi");
            }catch (IOException exception){
                return new ErrorResult("Resim yükleme aşamasında bir sorun oldu");
            }
        }else if(image.getImageId()!=null){
            //claudanry silme
            try {
                Map result=cloudinaryService.delete(image.getImageId());
                Map result2=cloudinaryService.upload(multipartFile);
                image.setName((String)result2.get("original_filename"));
                image.setImageUrl((String)result2.get("url"));
                image.setImageId((String)result2.get("public_id"));
                this.imageDao.save(image);
                return new SuccessResult("Başarıyla yüklendi");
            }catch (IOException exception){
                return new ErrorResult("Resim yükleme aşamasında bir sorun oldu");
            }
        }else{
            return new ErrorResult("Bir sorun var lütfen iletişime mail at");
        }
    }

    @Override
    public Result delete(int id) {
        if(!this.imageDao.existsById(id)){
            return new ErrorResult("Böyle bir resim bulunamadı");
        }
        try {
            Image image=this.imageDao.getById(id);
            Map result=cloudinaryService.delete(image.getImageId());
            image.setName(null);
            image.setImageId(null);
            image.setImageUrl("https://t4.ftcdn.net/jpg/00/64/67/63/360_F_64676383_LdbmhiNM6Ypzb3FM4PPuFP9rHe7ri8Ju.jpg");
            this.imageDao.save(image);
            return new SuccessResult("Resim başarıyla silindi");
        }catch (IOException exception){
            return new ErrorResult("Bir hata olştu");
        }
    }

    @Override
    public DataResult<Image> getById(int id) {
        if(!this.imageDao.existsById(id)){
            return new ErrorDataResult<Image>("Bu idye ait resim bulunamadı");
        }
        return new SuccessDataResult<Image>(this.imageDao.getById(id),"Verilen id ye ait resim listelendi");
    }

    @Override
    public Boolean isExist(int id) {
        return this.imageDao.existsById(id);
    }
}
