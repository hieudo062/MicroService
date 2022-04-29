package com.unikom.projectservice.service;

import com.unikom.projectservice.dto.request.Search;
import com.unikom.projectservice.dto.ProjectDTO;
import org.springframework.data.domain.Page;

public interface ProjectService {

    ProjectDTO save(ProjectDTO project);
    ProjectDTO update(Long id, ProjectDTO project);
    void deleteById(Long id);
    ProjectDTO findById(Long id);
    Page<ProjectDTO> search(Search search, int page, int size);
    long count(Search search);

}
