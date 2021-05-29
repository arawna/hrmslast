package com.alihocaoglu.hrms.busines.abstracts;

import com.alihocaoglu.hrms.core.utilities.results.DataResult;
import com.alihocaoglu.hrms.core.utilities.results.Result;
import com.alihocaoglu.hrms.entities.concretes.JobAd;
import com.alihocaoglu.hrms.entities.concretes.JobAdDto;

import java.util.List;

public interface JobAdService {
    Result create(JobAdDto jobAdDto);
    Result setPasssive(int jobAdId);
    Result setActive(int jobAdId);
    DataResult<List<JobAd>> getAll();
    DataResult<List<JobAd>> getActiveAds();
    DataResult<List<JobAd>> getActiveAndOrderLastDate();
    DataResult<List<JobAd>> getActiveAndCompanyId(int id);
}
