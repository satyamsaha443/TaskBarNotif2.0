//package org.example;
//
//import org.apache.http.HttpResponse;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.util.EntityUtils;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class EmailFetcher {
//    private List<Main> emails = new ArrayList<>();
//
//    public List<Main> getEmails() {
//        return emails;
//    }
//
//    public void fetchEmails() {
//        // Your existing fetchEmails() code
//        String accessToken = "...";
//        String url = "https://www.googleapis.com/gmail/v1/users/me/messages?maxResults=15&access_token=" + accessToken;
//
//        HttpClient client = HttpClientBuilder.create().build();
//        HttpGet request = new HttpGet(url);
//        try {
//            HttpResponse response = client.execute(request);
//            if (response.getStatusLine().getStatusCode() == 200) {
//                JSONObject data = new JSONObject(EntityUtils.toString(response.getEntity()));
//                JSONArray emailList = data.optJSONArray("messages");
//
//                if (emailList != null) {
//                    emails.clear();
//                    for (int i = 0; i < emailList.length(); i++) {
//                        JSONObject emailData = emailList.optJSONObject(i);
//                        if (emailData != null) {
//                            String messageId = emailData.optString("id");
//                            fetchMessageDetails(accessToken, messageId);
//                        }
//                    }
//                    updateListView();
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            throw new RuntimeException(e);
//        } catch (ClientProtocolException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void fetchMessageDetails(String accessToken, String id) {
//        // Your existing fetchMessageDetails() code
//        String url = "https://gmail.googleapis.com/gmail/v1/users/me/messages/" + id + "?format=full&access_token=" + accessToken;
//
//        HttpClient client = HttpClientBuilder.create().build();
//        HttpGet request = new HttpGet(url);
//        try {
//            HttpResponse response = client.execute(request);
//            if (response.getStatusLine().getStatusCode() == 200) {
//                JSONObject data = new JSONObject(EntityUtils.toString(response.getEntity()));
//
//                String subject = "";
//                String sender = "";
//
//                if (data.has("payload") && data.getJSONObject("payload").has("headers")) {
//                    JSONArray headers = data.getJSONObject("payload").getJSONArray("headers");
//
//                    for (int i = 0; i < headers.length(); i++) {
//                        JSONObject header = headers.getJSONObject(i);
//                        String name = header.optString("name");
//                        String value = header.optString("value");
//
//                        if ("Subject".equals(name)) {
//                            subject = value;
//                        } else if ("From".equals(name)) {
//                            sender = value;
//                        }
//                    }
//                }
//
//                emails.add(new Main(id, subject, sender, false));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
