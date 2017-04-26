/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newscrawler;

/**
 *
 * @author Dhruba
 */
import static com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolver.length;
import database.DBConnector;
import database.News;
import database.PerspectiveDB;
  import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.net.ssl.HttpsURLConnection;

public class HttpURLConnectionExample {

            public static  int CD=0,LA=0,TOI=0, id=0;
             public static double CDs=0.0,LAs=0.0,TOIs=0.0;
	private final static String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception {

		HttpURLConnectionExample http = new HttpURLConnectionExample();

		System.out.println("Testing 1 - Send Http GET request");
		http.sendGet();

	}

	// HTTP GET request
	public static void sendGet() throws Exception {
                          ArrayList<News> al = new ArrayList();
//                                al.add("Bangladesh's Supreme Court on Wednesday upheld the death sentences imposed on two opposition leaders for war crimes committed during the nation’s 1971 war of independence.\n" +
//"\n" +
//"The court rejected the appeal of Ali Ahsan Mohammad Mujahid of the Jamaat-e-Islami party and Salauddin Quader Chowdhury of the Bangladesh Nationalist Party, who were convicted in 2013 of various charges that included crimes against humanity.\n" +
//"\n" +
//"FROM OUR PARTNERS:\n" +
//"U.S. Surgeon General Steps Down\n" +
//"\n" +
//"\n" +
//"\n" +
//"The two men face death by hanging, though their principal attorney, Khandker Mahbub Hossain, said the government “can pardon the convicts if it wants or the convicts can seek presidential clemency.”\n" +
//"\n" +
//"Jamaat-e-Islami called for a 24-hour strike on Thursday to protest the decision on Mujahid's appeal. The government ordered that websites such as Facebook, WhatsApp and Viber be blocked in an apparent attempt to tamp down protests.\n" +
//"");
            
                               DBConnector dbc = new DBConnector();
		String Key = "https://access.alchemyapi.com/calls/text/TextGetTextSentiment?apikey=8c5f9df6-18c1-44a9-9ca7-374f83ec793e&";
        
                                    for(News list : dbc.getData()){
                                       
                                         TOI++;
                                        int as=list.getNews().length();
                                        String data = list.getNews();
                                          System.out.println(data.length());
                                        if(as>6000)
                                     data = list.getNews().substring(0, 6000);
                                      
                                        String text = "text="+ URLEncoder.encode(data) + "&outputMode=json";
                                        String url=Key+text;
		URL obj = new URL(url);
                                        System.out.println(obj);
                        
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
                                    
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
                                    
                String JsonRes = response.toString();
                                        System.out.println(JsonRes);
                 StringTokenizer tokenisedInput = new StringTokenizer(JsonRes);
                 while(tokenisedInput.hasMoreTokens())
                {
                  
                    String word = tokenisedInput.nextToken();
                     
                      String sc = "\"score\":";
                   if(word.equals(sc)){
                       String Preoutput = tokenisedInput.nextToken();
                       String result = Preoutput.replaceAll("[\",]","");
                      TOIs= Double.parseDouble(result);
                      id = list.getId();
                      PerspectiveDB pers = new PerspectiveDB();
                      pers.insert();
                   }
                   }
               
                                
                }
	           System.out.println("LA: "+LAs+" TIO: " + TOIs + " CD: "+CDs);
        }

	}




