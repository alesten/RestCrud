package utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Person;
import java.util.List;

public class JSONConverter {

    public static Person getPersonFromJson(String js) {
        JsonObject jPerson = new JsonParser().parse(js).getAsJsonObject();

        Person p = new Person();

        if(jPerson.has("id"))
            p.setId(jPerson.get("id").getAsLong());
        p.setfName(jPerson.get("fName").getAsString());
        p.setlName(jPerson.get("lName").getAsString());
        p.setPhone(jPerson.get("phone").getAsString());

        return p;
    }

    public static String getJSONFromPerson(Person p) {

        JsonObject jsonOut = new JsonObject();
        jsonOut.addProperty("fName", p.getfName());
        jsonOut.addProperty("lName", p.getlName());
        jsonOut.addProperty("phone", p.getPhone());
        jsonOut.addProperty("id", p.getId());

        String jsonResponse = new Gson().toJson(jsonOut);

        return jsonResponse;
    }

    public static String getJSONFromPerson(List<Person> persons) {
        String json = "";
        for (Person person : persons) {
            json += "," + getJSONFromPerson(person);
        }
        
        json = "["+ json.substring(1) + "]";
        
        return json;
    }

}
