package com.bitfury.pages;

import com.bitfury.utils.common.Email;
import com.bitfury.utils.common.SessionInfo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.*;

public class EmailSteps {

    private String GUEIRRILLAMAIL_URL = "https://api.guerrillamail.com/ajax.php";
    private String PARAMETER = "f";

    public SessionInfo sessionInfo;

    public EmailSteps initialEmail()  {
        sessionInfo = createSession();
        return this;
    }

    private SessionInfo createSession() {
        SessionInfo session = getEmailAddress("en");
        getEmailList(session, 0, 0);
        return session;
    }

    private SessionInfo getEmailAddress(String lang) {
        Map<String, Object> params = new HashMap<>();
        params.put("lang", lang);

        JsonNode responseJson = sendGetRequest(GUEIRRILLAMAIL_URL, "get_email_address", params);
        return new SessionInfo( getText(responseJson, "email_addr"),
                                getLong(responseJson, "email_timestamp"),
                                getText(responseJson, "sid_token"));
    }

    private List<Email> getEmailList(SessionInfo sessionInfo, long offset, long seq) {
        Map<String, Object> params = new HashMap<>();
        params.put("sid_token", sessionInfo.getSid_token());
        params.put("offset", offset);
        params.put("seq", seq);

        JsonNode responseJson = sendGetRequest(GUEIRRILLAMAIL_URL, "get_email_list", params);
        sessionInfo.setSid_token(getText(responseJson, "sid_token"));
        return getEmailList(responseJson);
    }

    private JsonNode sendGetRequest(String url, String functionName, Map<String, Object> params) {
        URIBuilder builder = null;
        try {
            builder = new URIBuilder(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        builder.setParameter(PARAMETER, functionName);
            for (Map.Entry<String, Object> param : params.entrySet()) {
                builder.setParameter(param.getKey(), String.valueOf(param.getValue()));
            }
        HttpGet request = null;
        try {
            request = new HttpGet(builder.build());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return sendGetRequest(request);

    }

    private JsonNode sendGetRequest(HttpGet request) {
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = null;
        try {
            response = client.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parseResponseToJson(response);
    }

    private JsonNode parseResponseToJson(HttpResponse response) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            return mapper.readTree(result.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getText(JsonNode jsonNode, String fieldName) {
        if (jsonNode.findValue(fieldName) != null) {
            return jsonNode.findValue(fieldName).textValue();
        }
        return null;
    }

    private long getLong(JsonNode jsonNode, String fieldName) {
        if (jsonNode.findValue(fieldName) != null) {
            return jsonNode.findValue(fieldName).asLong();
        }
        return 0;
    }

    private List<Email> getEmailList(JsonNode responseJson) {
        List<JsonNode> emailListNode = responseJson.findValues("list");
        List<Email> emailList = new ArrayList<>();
        if (emailListNode == null) {
            return Collections.emptyList();
        }
        for (JsonNode emailNode : emailListNode) {
            Email email = new Email(
                getLong(emailNode, "mail_id"),
                getText(emailNode, "mail_from"),
                getText(emailNode, "mail_subject"),
                getText(emailNode, "mail_excerpt"),
                getLong(emailNode, "mail_timestamp"),
                getLong(emailNode, "mail_read"),
                getText(emailNode, "mail_date"),
                getLong(emailNode, "att"),
                getLong(emailNode, "mail_size")
            );
            emailList.add(email);
        }
        return emailList;
    }

    public boolean forgetMe() {
        Map<String, Object> params = new HashMap<>();
        params.put("sid_token", sessionInfo.getSid_token());
        params.put("email_addr", sessionInfo.getEmail_addr());

        JsonNode responseJson = sendGetRequest(GUEIRRILLAMAIL_URL, "forget_me", params);
        return responseJson.asBoolean();
    }

    public List<Email> checkEmail(SessionInfo sessionInfo, int seq) {
        Map<String, Object> params = new HashMap<>();
        params.put("sid_token", sessionInfo.getSid_token());
        params.put("seq", seq);

        JsonNode responseJson = sendGetRequest(GUEIRRILLAMAIL_URL, "check_email", params);
        sessionInfo.setSid_token(getText(responseJson, "sid_token"));
        return getEmailList(responseJson);
    }

    public Email fetchEmail(SessionInfo sessionInfo, Email email) {
        Map<String, Object> params = new HashMap<>();
        params.put("sid_token", sessionInfo.getSid_token());
        params.put("email_id", email.getMail_id());

        JsonNode responseJson = sendGetRequest(GUEIRRILLAMAIL_URL, "fetch_email", params);
        sessionInfo.setSid_token(getText(responseJson, "sid_token"));
        email.setMail_body(getText(responseJson, "mail_body"));
        return email;
    }
}
