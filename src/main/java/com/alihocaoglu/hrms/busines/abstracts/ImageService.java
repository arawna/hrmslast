package com.alihocaoglu.hrms.busines.abstracts;

import com.alihocaoglu.hrms.core.utilities.results.DataResult;
import com.alihocaoglu.hrms.core.utilities.results.Result;
import com.alihocaoglu.hrms.entities.concretes.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    DataResult<List<Image>> getAll();
    Result update(MultipartFile multipartFile,int cvId);
    Result delete(int id);
    DataResult<Image> getById(int id);
    Boolean isExist(int id);
}
