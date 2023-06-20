package com.uep.wap.service;

import com.uep.wap.dto.BuyerProjectDTO;
import com.uep.wap.model.Buyer;
import com.uep.wap.model.Project;
import com.uep.wap.repository.BuyerRepository;
import com.uep.wap.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyerService {

    private final BuyerRepository buyerRepository;
    private final ProjectRepository projectRepository;

    public BuyerService(BuyerRepository buyerRepository, ProjectRepository projectRepository) {
        this.buyerRepository = buyerRepository;
        this.projectRepository = projectRepository;
    }

    public List<Buyer> getAllBuyersWithProjectsAndUsers() {
        return buyerRepository.findAllWithProjectsAndUsers();
    }

    public Buyer addBuyerWithProject(BuyerProjectDTO buyerProjectDTO) {
        Buyer buyer = new Buyer();
        buyer.setName(buyerProjectDTO.getBuyerName());

        Project project = new Project();
        project.setTopic(buyerProjectDTO.getProjectTopic());
        project.setDescription(buyerProjectDTO.getProjectDescription());
        project.setBuyer(buyer);

        buyer.getProjects().add(project);

        buyerRepository.save(buyer);
        projectRepository.save(project);

        return buyer;
    }

    public List<Buyer> getAllBuyers() {
        return (List<Buyer>) buyerRepository.findAll();
    }
}
