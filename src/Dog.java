
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Dog {

    private ArrayList<String> dogList = importAllDogs();
    private String chosenDog;

    public ArrayList<String> getDogList() {
        return dogList;
    }

    public String getChosenDog(){
        return chosenDog;
    }


    public ArrayList<String> importAllDogs(){
        String allDogsURL = "https://dog.ceo/api/breeds/list/all";
        String urlResponse = "";
        try {
            URI myUri = URI.create(allDogsURL);
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
        ArrayList<String> dogList = new ArrayList<>();
        int count = 0;
        boolean copy = false;
        boolean start = false;
        for(int i = 0; i < str.length(); i++){
            if(copy && !start){
                dogs += str.substring(i, i + 1);
            }
            if(str.substring(i,i + 1).equals("[")){
               start = true;
            }
            if(str.substring(i, i + 1).equals("]")){
                start = false;
            }

            if(str.substring(i, i + 1).equals("\"") && !start){

                if(count %2 == 1){
                    copy = false;
                }else {
                    copy = true;
                }
                count++;
            }
        }


        String dog = "";
        for(int i = 0; i < dogs.length(); i++){
            if(dogs.substring(i, i+1).equals("\"")){
                dogList.add(dog);
                dog = "";
            }else{
                dog += dogs.substring(i, i+1);

            }

        }

        return dogList;
    }

    public String changeImageURL(){
        int random = (int)(Math.random() * dogList.size());
        String breed = dogList.get(random);
        chosenDog = breed;

        String imagesURL = "https://dog.ceo/api/breed/" + breed + "/images";
        String urlResponse = "";
        try {
            URI myUri = URI.create(imagesURL);
            HttpRequest request = HttpRequest.newBuilder().uri(myUri).build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            urlResponse = response.body();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        JSONObject jsonObj = new JSONObject(urlResponse);
        JSONArray images = jsonObj.getJSONArray("message");
        int random2 = (int)(Math.random() * images.length());
        return (String) images.get(random2);
    }






}
