import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Dog {

    public void run(){
        String allDogsURL = "https://dog.ceo/api/breeds/list/all";
        String urlResponse = "";
        try {
            URI myUri = URI.create(allDogsURL); // creates a URI object from the url string
            HttpRequest request = HttpRequest.newBuilder().uri(myUri).build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            urlResponse = response.body();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        JSONObject jsonObj = new JSONObject(urlResponse);
        JSONObject allDogObj = jsonObj.getJSONObject("message");

        String str = allDogObj.toString();
        String dogs = "";
        ArrayList<String> list = new ArrayList<>();
        int count = 0;
        boolean copy = false;
        boolean start = false;
        for(int i = 0; i < str.length(); i++){
            if(copy && !start){
                dogs += str.substring(i, i + 1);
            }
            if(str.substring(1,i+1).equals("[")){
               start = true;
            }
            if(str.substring(1,i+1).equals("]")){
                start = false;
            }

            if(str.substring(i, i + 1).equals("\"")){

                if(count %2 == 1){
                    copy = false;
                }else {
                    copy = true;
                }
                count++;
            }
        }
        //System.out.println(dogs);

        String dog = "";
        for(int i = 0; i < dogs.length(); i++){
            if(dogs.substring(i, i+1).equals("\"")){
                list.add(dog);
                System.out.println(dog);
                dog = "";
            }else{
                dog += dogs.substring(i, i+1);

            }

        }





    }



}
