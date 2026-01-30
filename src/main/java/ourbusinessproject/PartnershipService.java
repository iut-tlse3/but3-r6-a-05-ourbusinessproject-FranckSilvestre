package ourbusinessproject;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ourbusinessproject.repositories.PartnerRepository;

import java.util.List;


/**
 * Service to manage PartnerShip entities
 */
@Service
public class PartnershipService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PartnerRepository partnerRepository;

    /**
     * Create a new partnership
     *
     * @param project the project of the partnership
     * @param enterprise the enterprise of the partnership
     * @return the new partnership
     */
    @Transactional
    public Partnership newPartnership(Project project, Enterprise enterprise) {
        Partnership partnership = new Partnership();
        partnership.setProject(project);
        partnership.setEnterprise(enterprise);
        entityManager.persist(partnership);
        return partnership;
    }

    /**
     * Find partnership by id
     *
     * @param id the id of the partnership
     * @return the found partnership,or null
     */
    public Partnership findPartnershipById(Long id) {
        return entityManager.find(Partnership.class,id);
    }

    /**
     * Remove a partnership
     *
     * @param partnership the partnership to remove
     */
    @Transactional
    public void remove(Partnership partnership) {
        entityManager.remove(partnership);
    }

    /**
     * Search engine or partnerships
     *
     * @param projectTitle the project title of the searched partnerships
     * @param enterpriseName the enterprise name of the searched partnerships
     * @return the list of found partnership
     */
    public List<Partnership> search(String projectTitle, String enterpriseName) {
        String jpqlQuery = null;
        TypedQuery<Partnership> query = null;
        if (projectTitle != null && !projectTitle.isBlank()) {
            if (enterpriseName != null && !enterpriseName.isBlank()) {
                jpqlQuery = "select part from Partnership part where part.project.title like :projectTitle and part.enterprise.name like :enterpriseName order by part.project.title";
                query = entityManager.createQuery(jpqlQuery,Partnership.class);
                query.setParameter("projectTitle", projectTitle);
                query.setParameter("enterpriseName", enterpriseName);
            } else {
                jpqlQuery = "select part from Partnership part where part.project.title like :projectTitle order by part.project.title";
                query = entityManager.createQuery(jpqlQuery,Partnership.class);
                query.setParameter("projectTitle", projectTitle);
            }
        } else if (enterpriseName != null && !enterpriseName.isBlank()) {
            jpqlQuery = "select part from Partnership part where part.enterprise.name like :enterpriseName order by part.project.title";
            query = entityManager.createQuery(jpqlQuery, Partnership.class);
            query.setParameter("enterpriseName", enterpriseName);
        } else {
            jpqlQuery = "select part from Partnership part order by part.project.title";
            query = entityManager.createQuery(jpqlQuery,Partnership.class);
        }
        return query.getResultList();
    }


    /**
     * Search engine or partnerships
     * @param partnershipExample the Example of partnership used to perform the query
     * @param pageable the object descriping page and ordering
     * @return the list of found partnership
     */
    public Page<Partnership> search(Example<Partnership> partnershipExample, Pageable pageable) {
        return partnerRepository.findAll(partnershipExample,pageable);
    }
}
