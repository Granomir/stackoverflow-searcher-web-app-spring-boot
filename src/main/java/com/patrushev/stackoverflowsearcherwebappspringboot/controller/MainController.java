package com.patrushev.stackoverflowsearcherwebappspringboot.controller;

import com.patrushev.stackoverflowsearcherwebappspringboot.form.QueryForm;
import com.patrushev.stackoverflowsearcherwebappspringboot.model.QuestionResult;
import com.patrushev.stackoverflowsearcherwebappspringboot.model.SOAPIConnector;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.List;

@Controller
public class MainController {

    //в ответ на переход по localhost выдается страница index:
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String showIndex(Model model) {
        //видимо создается объект, свойства которого будут заполняться на странице из формы запроса
        QueryForm queryForm = new QueryForm();
        model.addAttribute("queryForm", queryForm);

        return "index";
    }

    //в ответ на запрос по /index с методов POST выполняется обновление index:
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.POST)
    public String doQuery(Model model, @ModelAttribute("queryForm") QueryForm queryForm) throws IOException {
        List<QuestionResult> questions;
        String query = queryForm.getQuery();
        //проверка на пустой запрос
        if (query != null && query.length() > 0) {
            //запрос в API и создание коллекции из ответов
            questions = SOAPIConnector.getInstance().getQuestionsList(query);
            //проверка пустой коллекции ответов
            if (questions == null || questions.isEmpty()) {
                model.addAttribute("errorNoResult", "Nothing found by the request\"" + query + "\"");
                return "index";
            }
            model.addAttribute("questions", questions);
            return "index";
        }

        model.addAttribute("errorEmptyQuery", "Your request is empty!");
        return "index";
    }
}
