package com.patrushev.stackoverflowsearcherwebappspringboot.controller;

import com.patrushev.stackoverflowsearcherwebappspringboot.form.QueryForm;
import com.patrushev.stackoverflowsearcherwebappspringboot.model.QuestionResult;
import com.patrushev.stackoverflowsearcherwebappspringboot.services.StackExchangeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.List;

@Controller
public class MainController {

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String showIndex(Model model) {
        QueryForm queryForm = new QueryForm();
        model.addAttribute("queryForm", queryForm);

        return "index";
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.POST)
    public String doQuery(Model model, @ModelAttribute("queryForm") QueryForm queryForm) throws IOException {
        List<QuestionResult> questions;
        String query = queryForm.getQuery();
        if (query != null && query.length() > 0) {
            questions = StackExchangeService.getInstance().getQuestionsList(query);
            if (questions == null || questions.isEmpty()) {
                model.addAttribute("errorNoResult", "Nothing found by the request \"" + query + "\"");
                return "index";
            }
            model.addAttribute("questions", questions);
            return "index";
        }

        model.addAttribute("errorEmptyQuery", "Your request is empty!");
        return "index";
    }
}
