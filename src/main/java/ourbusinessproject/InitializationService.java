package ourbusinessproject;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ourbusinessproject.*;

/**
 * This service is responsible for initializing the project data.
 */
@Service
public class InitializationService {
    private Project project1E1;
    private Project project1E2;
    private Project project2E1;
    private Enterprise enterprise1;
    private Enterprise enterprise2;

    /**
     * This method initializes the project data.
     */
    @Autowired
    private EnterpriseProjectService enterpriseProjectService;
    @Autowired
    private PartnershipService partnershipService;
    private Partnership partnershipP1E1WithE2;
    private Partnership partnershipP1E2WithE1;
    private Partnership partnershipP2E1WithE2;

    /**
     * Initialization of the initial list of projects
     */
    @Transactional
    public void initProjects() {
        initEnterprise();
        project1E1 = enterpriseProjectService.newProject("p1E1","P1E1 desc",enterprise1);
        project1E2 = enterpriseProjectService.newProject("p1E2","P1E2 desc",enterprise2);
        project2E1 = enterpriseProjectService.newProject("p2E1","P2E1 desc",enterprise1);
    }

    private void initEnterprise() {
        // enterprise 1
        enterprise1 = enterpriseProjectService.newEnterprise(
                "MyComp1",
                "My comp1 description",
                "comp1 contact name",
                "comp1@com.com");
        // enterprise 2
        enterprise2 = enterpriseProjectService.newEnterprise(
                "MyComp2",
                "My comp2 description",
                "comp2 contact name",
                "comp2@com.com");
    }

    @Transactional
    public void initPartnerships() {
        partnershipP1E1WithE2 = partnershipService.newPartnership(project1E1, enterprise2);
        partnershipP1E2WithE1 = partnershipService.newPartnership(project1E2, enterprise1);
        partnershipP2E1WithE2 = partnershipService.newPartnership(project2E1, enterprise2);
    }

    /**
     * @return the project1E1
     */
    public Project getProject1E1() {
        return project1E1;
    }

    /**
     * @return the project1E2
     */
    public Project getProject1E2() {
        return project1E2;
    }

    /**
     * @return the project2E1
     */
    public Project getProject2E1() {
        return project2E1;
    }

    /**
     * @return the enterprise1
     */
    public Enterprise getEnterprise1() {
        return enterprise1;
    }

    /**
     * @return the enterprise2
     */
    public Enterprise getEnterprise2() {
        return enterprise2;
    }

    /**
     * @return the partnershipP1E1WithE2
     */
    public Partnership getPartnershipP1E1WithE2() {
        return partnershipP1E1WithE2;
    }

    /**
     * @return the partnershipP1E2WithE1
     */
    public Partnership getPartnershipP1E2WithE1() {
        return partnershipP1E2WithE1;
    }

    /**
     * @return the partnershipP2E1WithE2
     */
    public Partnership getPartnershipP2E1WithE2() {
        return partnershipP2E1WithE2;
    }
}
