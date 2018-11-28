package com.patrushev.stackoverflowsearcherwebappspringboot.services;

import com.patrushev.stackoverflowsearcherwebappspringboot.utils.ParameterStringBuilder;
import com.patrushev.stackoverflowsearcherwebappspringboot.model.QuestionResult;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.GZIPInputStream;

public class StackExchangeService {
    private static StackExchangeService ourInstance = new StackExchangeService();


    public static StackExchangeService getInstance() {
        return ourInstance;
    }

    private StackExchangeService() {
    }

    public List<QuestionResult> getQuestionsList(String question) throws IOException {
        List<QuestionResult> results = new ArrayList<>();
        StringBuilder inline = new StringBuilder();

        Map<String, String> parametres = new HashMap<>();
        parametres.put("site", "stackoverflow");
        parametres.put("intitle", question);
        parametres.put("sort", "relevance");
        parametres.put("order", "desc");

        URL url = new URL("http://api.stackexchange.com/2.2/search?" + ParameterStringBuilder.getParamsString(parametres));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.connect();
        int responsecode = con.getResponseCode();

        if (responsecode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responsecode);
        } else {
            BufferedReader in = new BufferedReader(new InputStreamReader(new GZIPInputStream(con.getInputStream())));

            String inputLine;
            while ((inputLine = in.readLine()) != null)
                inline.append(inputLine);
            in.close();
        }

        JSONParser parse = new JSONParser();
        JSONObject jobj = null;
        try {
            jobj = (JSONObject) parse.parse(inline.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONArray jsonarr_1 = null;
        if (jobj != null) {
            jsonarr_1 = (JSONArray) jobj.get("items");
        }

        for (Object aJsonarr_1 : jsonarr_1) {
            JSONObject jsonobj_1 = (JSONObject) aJsonarr_1;
            //date
            Date d = new Date(((long) jsonobj_1.get("creation_date")) * 1000);
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String date = df.format(d);
            //title
            String title = (String) jsonobj_1.get("title");
            //author
            String author;
            JSONObject jsonobj_2 = (JSONObject) jsonobj_1.get("owner");
            author = (String) jsonobj_2.get("display_name");
            //ref
            String ref = (String) jsonobj_1.get("link");
            //isSolved
            boolean isSolved = (boolean) jsonobj_1.get("is_answered");
            results.add(new QuestionResult(date, title, author, ref, isSolved));
        }

        return results;
    }
}
