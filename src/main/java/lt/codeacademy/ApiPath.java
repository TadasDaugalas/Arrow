package lt.codeacademy;

public interface ApiPath {
    String ID_VARIABLE = "id";

    String PRODUCTS = "/products";
    String FILES = "/files";
    String PRODUCT = "/{" + ID_VARIABLE + "}";
    String BLOBS = "/blobs";
    String GET_BLOB = BLOBS + "/{" + ID_VARIABLE + "}";
    String LOGIN = "/login";
    String USERS = "/users";

}