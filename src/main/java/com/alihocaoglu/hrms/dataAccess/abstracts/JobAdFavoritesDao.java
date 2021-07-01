package com.alihocaoglu.hrms.dataAccess.abstracts;

import com.alihocaoglu.hrms.entities.concretes.JobAdFavorites;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobAdFavoritesDao extends JpaRepository<JobAdFavorites,Integer> {
    List<JobAdFavorites> findByCandidateId(int id);
    boolean existsByCandidate_IdAndJobAd_Id(int candidateId,int JobAdId);
}
