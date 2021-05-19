import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;

public class MemberCustomers {
    public enum enumCategorieAge {REGULAR, SENIOR, CHILDREN}
    private String categorieAge;
    private String login;
    private String hash;

    public MemberCustomers() {

    }
    public MemberCustomers(String login, String hash) {
        this.login = login;
        this.hash = hash;
    }

    public MemberCustomers(String login, String hash, String categorieAge) {
        this.login = login;
        this.hash = hash;
        this.categorieAge = categorieAge;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getCategorieAge() {
        return categorieAge;
    }


    public void setCategorieAge(String categorieAge) {
        this.categorieAge = categorieAge;
    }
}