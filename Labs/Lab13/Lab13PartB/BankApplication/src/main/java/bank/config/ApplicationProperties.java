package bank.config;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@ConfigurationProperties(prefix="myapp.properties")
@Validated
public class ApplicationProperties {
    @NotBlank
    private String applicationName;
    @NotBlank
    private String version;
    @NotBlank
    private String serverURL;
    private String serverName;
    private List<String> countries;
    private User user = new User();

    public ApplicationProperties() {
    }

    public ApplicationProperties(String applicationName, String version, String serverURL, String serverName, List<String> countries, User user) {
        this.applicationName = applicationName;
        this.version = version;
        this.serverURL = serverURL;
        this.serverName = serverName;
        this.countries = countries;
        this.user = user;
    }

    public String getApplicationName() {
        return this.applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getServerURL() {
        return this.serverURL;
    }

    public void setServerURL(String serverURL) {
        this.serverURL = serverURL;
    }

    public String getServerName() {
        return this.serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public List<String> getCountries() {
        return this.countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "{" +
            " applicationName='" + getApplicationName() + "'" +
            ", version='" + getVersion() + "'" +
            ", serverURL='" + getServerURL() + "'" +
            ", serverName='" + getServerName() + "'" +
            ", countries='" + getCountries() + "'" +
            ", user='" + getUser() + "'" +
            "}";
    }

    public  class User {
        private String firstname;
        private String lastname;
        @NotBlank
        @Size(min=8, max=15)
        private String username;
        @NotBlank
        @Size(min=8, max=15)
        private String password;

        public String getFirstname() {
            return firstname;
        }
        public String getLastname() {
            return lastname;
        }
        public String getPassword() {
            return password;
        }
        public String getUsername() {
            return username;
        }
        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }
        public void setLastname(String lastname) {
            this.lastname = lastname;
        }
        public void setPassword(String password) {
            this.password = password;
        }
        public void setUsername(String username) {
            this.username = username;
        }
    }

}
