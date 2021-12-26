package lt.codeacademy;

public interface ApiPath {
    String ID_VARIABLE="id";
    String NAME_VARIABLE="name";

    String ROOT = "/arrow";
    String PRODUCTS = "/products";
    String FILE_BY_NAME="/{"+NAME_VARIABLE+"}";
    String FILES ="/files";
    String PRODUCT ="/{" + ID_VARIABLE + "}";
    String SEARCH ="/search";
    String BLOBS="/blobs";
    String GET_BLOB=BLOBS + "/{" + ID_VARIABLE + "}";
    String LOGIN = "/login";

}