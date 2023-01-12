package ds.project1task2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.google.gson.Gson;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import java.net.URL;
import java.net.URLEncoder;
import java.net.HttpURLConnection;
import javax.net.ssl.*;
import java.io.*;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.awt.image.BufferedImage;
import java.awt.image.*;
public class StateInfoModel {

    /**
     * Get nickname for input state in https://www.britannica.com/topic/List-of-nicknames-of-U-S-States-2130544.
     * @param state
     * @return nickname string
     */
    public String getStateNickname(String state) {
        String nickNameURL = "https://www.britannica.com/topic/List-of-nicknames-of-U-S-States-2130544";
        String response = fetch(nickNameURL, "TLSV1.3"); // SSL

        String nickname = "";
        Document doc = Jsoup.parse(response);
        // get the nickname
        Element table = doc.select("table").get(0); //select the first table.
        Elements rows = table.select("tr");

        //first row is the col names so skip it.
        for (int i = 1; i < rows.size(); i++) {
            Element row = rows.get(i);
            Elements cols = row.select("td");
            String key = cols.get(0).text();
            String value = cols.get(1).text();
            if (key.equalsIgnoreCase(state)) {
                nickname = value;
                break;
            }
        }
        return nickname;
    }

    /**
     * Get capital for input state by iterating through all elements in the url.
     * @param state
     * @return capital string
     */
    public String getStateCapital(String state) {
        String URL = "https://gisgeography.com/united-states-map-with-capitals/";
        String response = fetch(URL, "TLSV1.3"); // SSL

        String capital = "";
        Document doc = Jsoup.parse(response);
        // go through all elements to get capital for input state
        Elements divs = doc.getElementsByClass("kt-inside-inner-col");
        for (int index = 2; index < 4; index++){
            Element element1 = divs.get(index);
            Elements p = element1.select("p");
            String[] textSplitResult = p.first().html().split("<br>");
            for (String text:textSplitResult) {
                String key = text.substring(0,text.indexOf("(")-1);
                String value = text.substring(text.indexOf("(")+1, text.indexOf(")"));
                if (key.equalsIgnoreCase(state)) {
                    capital = value;
                    break;
                }
            }
        }
        return capital;
    }

    /**
     * Get state song via https://50states.com/songs/
     * @param state
     * @return song string
     */
    public String getStateSong(String state) {
        String URL = "https://50states.com/songs/";
        String response = fetch(URL, "TLSV1.3"); // SSL

        String song = "";
        Document doc = Jsoup.parse(response);
        // go through all elements to get capital for input state
        Element table = doc.select("table").get(0); //select the first table.
        Elements lists = table.select("li");
        // here start from index = 0, pay attention to nickname part.
        for (int i = 0; i < lists.size(); i++) { //first row is the col names so skip it.
            Element list = lists.get(i);
            String key = list.select("dt").text();
            String value = list.select("dd").text();
            if (key.equalsIgnoreCase(state)) {
                song = value;
                break;
            }
        }
        return song;
    }

    /**
     * Get population via API https://api.census.gov/data/2020/dec/pl?get=NAME,P1_001N&for=state: [put the state FIPS code here]&key=[put your API key here]
     * and my key here is : 16ae7ef40a91c0902a387b0d94a7b73e89cded96
     * @param state
     * @return population string
     */
    public String getStatePopulation(String state) {
        String code = getStateCode(state);
        String URL = "https://api.census.gov/data/2020/dec/pl?get=NAME,P1_001N&for=state:"+code+"&key=16ae7ef40a91c0902a387b0d94a7b73e89cded96";
        String response = fetch(URL, "TLSV1.3"); // SSL

        String population = response.split(",")[4];
        population = population.replaceAll("^\"|\"$", "");
        return population;
    }

    /**
     * Get image of state flower from https://statesymbolsusa.org/categories/flower.
     * @param state
     * @return url of flower image with proper size
     */
    public String getStateFlowerImage(String state) {
        String URL = "https://statesymbolsusa.org/categories/flower";
        String response = fetch(URL, "TLSV1.3"); // SSL

        String imgURL = "";
        Document doc = Jsoup.parse(response);
        // go through all elements to get capital for input state
        Elements imageElements = doc.select("img");
        Elements elements = doc.select("div.views-field.views-field-title-1 > span.field-content");
        for (int i = 0; i < elements.size();i++){
            String absoluteUrl = imageElements.get(i+2).absUrl("src");  //absolute URL on src
            String key = elements.get(i).text();
            if (key.equalsIgnoreCase(state)){
                imgURL = absoluteUrl;
                break;
            }
        }
        return imgURL;
    }


    /**
     * Get image url of state flag from https://states101.com/flags
     * @param state
     * @return url of flag image with proper size
     */
    public String getStateFlagImage(String state) {
        String URL = "https://states101.com/flags";
        String response = fetch(URL, "TLSV1.3"); // SSL

        String imgURL = "";
        Document doc = Jsoup.parse(response);
        // go through all elements to get flag image
        Elements imageElements = doc.select("img");
        Elements elements = doc.select("div.col-md-3.col-sm-4.col-xs-6 > a");
        for (int i = 0; i < elements.size();i++){
            String absoluteUrl = imageElements.get(i+2).attr("src");  //absolute URL on src
            String key = elements.get(i).text();
            if (key.equalsIgnoreCase(state)){
                imgURL = "https://www.states101.com"+absoluteUrl;
                break;
            }
        }
        //imgURL = pictureSize(imgURL, picSize);
        return imgURL;
    }

    /**
     * Map state with its code as is shown in fips.csv.
     * @param state
     * @return state code as an integer
     */
    private String getStateCode(String state){
        HashMap<String, String> stateMap = new HashMap<>();
        stateMap.put("Alabama".toLowerCase(), "01");
        stateMap.put("Alaska".toLowerCase(), "02");
        stateMap.put("Arizona".toLowerCase(), "04");
        stateMap.put("Arkansas".toLowerCase(),"05");
        stateMap.put("California".toLowerCase(),"06");
        stateMap.put("Colorado".toLowerCase(),"08");
        stateMap.put("Connecticut".toLowerCase(),"09");
        stateMap.put("Delaware".toLowerCase(), "10");
        stateMap.put("Florida".toLowerCase(),"12");
        stateMap.put("Georgia".toLowerCase(), "13");
        stateMap.put("Hawaii".toLowerCase(),"15");
        stateMap.put("Idaho".toLowerCase(),"16");
        stateMap.put("Illinois".toLowerCase(),"17");
        stateMap.put("Indiana".toLowerCase(), "18");
        stateMap.put("Iowa".toLowerCase(),"19");
        stateMap.put("Kansas".toLowerCase(),"20");
        stateMap.put("Kentucky".toLowerCase(),"21");
        stateMap.put("Louisiana".toLowerCase(),"22");
        stateMap.put("Maine".toLowerCase(),"23");
        stateMap.put("Maryland".toLowerCase(),"24");
        stateMap.put("Massachusetts".toLowerCase(),"25");
        stateMap.put("Michigan".toLowerCase(),"26");
        stateMap.put("Minnesota".toLowerCase(),"27");
        stateMap.put("Mississippi".toLowerCase(),"28");
        stateMap.put("Missouri".toLowerCase(),"29");
        stateMap.put("Montana".toLowerCase(), "30");
        stateMap.put("Nebraska".toLowerCase(),"31");
        stateMap.put("Nevada".toLowerCase(),"32");
        stateMap.put("New Hampshire".toLowerCase(),"33");
        stateMap.put("New Jersey".toLowerCase(),"34");
        stateMap.put("New Mexico".toLowerCase(),"35");
        stateMap.put("New York".toLowerCase(),"36");
        stateMap.put("North Carolina".toLowerCase(),"37");
        stateMap.put("North Dakota".toLowerCase(),"38");
        stateMap.put("Ohio".toLowerCase(),"39");
        stateMap.put("Oklahoma".toLowerCase(),"40");
        stateMap.put("Oregon".toLowerCase(),"41");
        stateMap.put("Pennsylvania".toLowerCase(),"42");
        stateMap.put("Rhode Island".toLowerCase(),"44");
        stateMap.put("South Carolina".toLowerCase(),"45");
        stateMap.put("South Dakota".toLowerCase(),"46");
        stateMap.put("Tennessee".toLowerCase(),"47");
        stateMap.put("Texas".toLowerCase(),"48");
        stateMap.put("Utah".toLowerCase(),"49");
        stateMap.put("Vermont".toLowerCase(),"50");
        stateMap.put("Virginia".toLowerCase(),"51");
        stateMap.put("Washington".toLowerCase(),"53");
        stateMap.put("West Virginia".toLowerCase(),"54");
        stateMap.put("Wisconsin".toLowerCase(),"55");
        stateMap.put("Wyoming".toLowerCase(),"56");

        return stateMap.get(state);
    }


    /**
     * Open url without SSLHandshakeException.
     * @param searchURL
     * @param certType
     * @return response from the url
     */
    private String fetch(String searchURL, String certType) {
        try {
            // Create trust manager, which lets you ignore SSLHandshakeExceptions
            createTrustManager(certType);
        } catch (KeyManagementException ex) {
            System.out.println("Shouldn't come here: ");
            ex.printStackTrace();
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Shouldn't come here: ");
            ex.printStackTrace();
        }

        String response = "";
        try {
            URL url = new URL(searchURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Read all the text returned by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String str;
            // Read each line of "in" until done, adding each to "response"
            while ((str = in.readLine()) != null) {
                // str is one line of text readLine() strips newline characters
                response += str;
            }
            in.close();
        } catch (IOException e) {
            System.err.println("Something wrong with URL");
            return null;
        }
        return response;
    }

    private void createTrustManager(String certType) throws KeyManagementException, NoSuchAlgorithmException{
        /**
         * Annoying SSLHandShakeException. After trying several methods, finally this
         * seemed to work.
         * Taken from: http://www.nakov.com/blog/2009/07/16/disable-certificate-validation-in-java-ssl-connections/
         */
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }
            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }
        };

        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance(certType);
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    }

}
