package com.mao.infocrawler.controller;

import com.mao.infocrawler.model.entity.Item;
import com.mao.infocrawler.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by mao on 2016/4/21.
 */


@Controller
@RequestMapping("/")
public class AppController {

    @Autowired
    private AppService service;

    /**
     * test
     * @param model
     * @return
     */
    @RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
    public String listItems(ModelMap model) {

        List<Item> itemList = service.findAll();
        model.put("itemList", itemList);
        model.addAttribute("message","list");
        service.redis2DB();
        System.out.println(itemList);
        return "list";
    }

}
