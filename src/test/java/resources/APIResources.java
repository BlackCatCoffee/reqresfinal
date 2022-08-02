package resources;

public enum APIResources {
    getListUserAPI("/api/users?page="),
    getSingleUserAPI("/api/users/"),
    getSingleUserNotFoundAPI("/api/users/"),
    getListResourcesAPI("/api/unknown"),
    getSingleResourceAPI("/api/unknown/"),
    getSingleResourceNotFoundAPI("/api/unknown/"),
    postCreateAPI("/api/users"),
    putUpdateAPI("/api/users/"),
    patchUpdateAPI("/api/users/"),
    deleteAPI("/api/users/"),
    postRegisterSuccessfulAPI("/api/register"),
    postRegisterUnsuccessfulAPI("/api/register"),
    postLoginSuccessfulAPI("/api/login"),
    postLoginUnsuccessfulAPI("/api/login"),
    getDelayedResponseAPI("/api/users?delay=");
    private String resource;

    APIResources(String resource){
        this.resource = resource;
    }

    public String getResource(){
        return resource;
    }
}
