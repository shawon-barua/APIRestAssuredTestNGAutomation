package ApiTest.endpoints;

/**
 * The Routes class provides static endpoint URLs for a web service related to user and contact management.
 * This includes endpoints for creating users, user login, adding contacts, and deleting contacts.
 */
public class Routes {
    public static String base_URL = "https://thinking-tester-contact-list.herokuapp.com";
    public static String create_user_endpoint = "/users";
    public static String user_login = "/users/login";
    public static String add_contact = "/contacts";
    public static String delete_contact = "/contacts";
}