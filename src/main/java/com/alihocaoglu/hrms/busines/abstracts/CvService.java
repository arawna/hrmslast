package com.alihocaoglu.hrms.busines.abstracts;

import com.alihocaoglu.hrms.core.utilities.results.DataResult;
import com.alihocaoglu.hrms.core.utilities.results.Result;
import com.alihocaoglu.hrms.entities.concretes.Cv;
import com.alihocaoglu.hrms.entities.dtos.CvForSetDto;

import java.util.List;

public interface CvService {
    public Result add(int candidateId);
    public DataResult<List<Cv>> getAll();

    public DataResult<Cv> getByCvId(int cvId);
    public DataResult<Cv> getByCandidateId(int candidateId);

    public Result updateGithub(String githublink, int cvId);
    public Result deleteGithub(int cvId);

    public Result updateLinkedin(String linkedinlink, int cvId);
    public Result deleteLinkedin(int cvId);

    public Result updateBiography(String biography, int cvId);
    public Result deleteBiography(int cvId);
}
