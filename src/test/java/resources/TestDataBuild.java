package resources;

import com.globallogic.reqresfinal.pojos.Data;
import io.restassured.response.Response;
import stepdefinitions.StepDefinition;

import static resources.Utils.req;

public class TestDataBuild {

    public Data createUser(String first_name,String job){
        Data newUser = new Data();
        newUser.setFirst_name(first_name);
        newUser.setJob(job);
        return newUser;
    }

    public Data registerUser(String email,String password){
        Data regUser = new Data();
        regUser.setUserName(email);
        regUser.setPassword(password);
        return regUser;
    }

    public Data registerUserNoPassword(String email){
        Data regUser = new Data();
        regUser.setEmail(email);
        return regUser;
    }


}
