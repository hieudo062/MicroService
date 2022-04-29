package com.unikom.projectservice.service.impl;

import com.unikom.projectservice.dto.request.Search;
import com.unikom.projectservice.dto.ProjectDTO;
import com.unikom.projectservice.model.Project;
import com.unikom.projectservice.repository.ProjectRepository;
import com.unikom.projectservice.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.social.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ProjectDTO save(ProjectDTO project) {
        Project p = new Project(project);
        Project projectE = projectRepository.save(p);
        return new ProjectDTO(projectE);
    }

    @Override
    public ProjectDTO update(Long id, ProjectDTO project) {
        Project p =  projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project", "Project does not exist"));
        project.setId(id);
        p.update(project);
        projectRepository.save(p);
        return new ProjectDTO(p);
    }

    @Override
    public void deleteById(Long id) {
        Project project = projectRepository.findByIdAndDeletedIsFalse(id).orElseThrow(() -> new ResourceNotFoundException("Project", "Project does not exist"));
        project.setDeleted(true);
        projectRepository.save(project);
    }

    @Override
    public ProjectDTO findById(Long id) {
        return projectRepository.findByIdAndDeletedIsFalse(id).map(ProjectDTO::new).orElseThrow(() -> new ResourceNotFoundException("Project", "Project does not exist"));
    }

    @Override
    public Page<ProjectDTO> search(Search search, int page, int size) {
        StringBuilder hql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        Pageable pageable = PageRequest.of(page - 1, size);

        hql.append("select p ");
        hql.append("from Project p ");
        hql.append("where 1=1 ");
        hql.append("and deleted = 0 ");
        if (search.getCode() != null) {
            hql.append("and p.code like :code ");
            params.put("code", "%" + search.getCode() + "%");
        }
        if (search.getName() != null) {
            hql.append("and p.name like :name");
            params.put("name", "%" + search.getName() + "%");
        }
        Query query = entityManager.createQuery(hql.toString()).setMaxResults(size).setFirstResult((int)pageable.getOffset());
        for (String key : params.keySet()) {
            query.setParameter(key, params.get(key));
        }

        List<ProjectDTO> list = query.getResultList();
        Page<ProjectDTO> page1 = new PageImpl<>(list, pageable, list.size());
        return page1;
    }

    @Override
    public long count(Search search) {
        StringBuilder hql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        hql.append("select count(*) ");
        hql.append("from Project p ");
        hql.append("where 1=1 ");
        hql.append("and deleted = 0 ");
        if (search.getCode() != null) {
            hql.append("and p.code like :code ");
            params.put("code", "%" + search.getCode() + "%");
        }
        if (search.getName() != null) {
            hql.append("and p.name like :name");
            params.put("name", "%" + search.getName() + "%");
        }
        Query query = entityManager.createQuery(hql.toString());
        for (String key : params.keySet()) {
            query.setParameter(key, params.get(key));
        }
        return (long) query.getSingleResult();
    }
}
