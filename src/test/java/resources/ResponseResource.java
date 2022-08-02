package resources;

public enum ResponseResource {
    SINGLE_USER_ID("data.id"),
    USER_CREATED("id"),
    USER_UPDATE("updatedAt"),
    USER_LOGIN("token"),
    GET_LIST_PAGE_NUMBER("page");

    private String resource;

    ResponseResource(String resource){this.resource = resource;}

    public  String getResource(){return resource;}

}
