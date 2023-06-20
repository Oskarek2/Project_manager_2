package com.uep.wap.controller;

import com.uep.wap.dto.BuyerProjectDTO;
import com.uep.wap.model.Buyer;
import com.uep.wap.service.BuyerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buyers")
public class BuyerController {

    private final BuyerService buyerService;

    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }


    @GetMapping
    public List<Buyer> getAllBuyersWithProjectsAndUsers() {
        return buyerService.getAllBuyersWithProjectsAndUsers();
    }

    @PostMapping("/addBuyerWithProject")
    public Buyer addBuyerWithProject(@RequestBody BuyerProjectDTO buyerProjectDTO) {
        return buyerService.addBuyerWithProject(buyerProjectDTO);
    }

}
