package FinalPractice.Services;

import java.util.List;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

@ConfigurationProperties(prefix = "myapp.properties")
@Validated
public class ApplicationProperties {
    @NotBlank
    private String applicationName;
    @NotBlank
    private String version;
    @NotBlank
    private String serverUrl;
    @NotBlank
    private String serverName;
    private List<String> countries;
    @Valid
    private User user = new User();


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

    public String getServerUrl() {
        return this.serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
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
            " applicationName='" + applicationName + "'" +
            ", version='" + version + "'" +
            ", serverUrl='" + serverUrl + "'" +
            ", serverName='" + serverName + "'" +
            ", countries='" + countries + "'" +
            ", user='" + user + "'" +
            "}";
    }
    
    class User{
        @NotBlank
        private String firstname;
        private String lastname;
        @Email
        private String username;
        private String password;

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

        @Override
        public String toString() {
            return "{" +
                " firstname='" + firstname + "'" +
                ", lastname='" + lastname + "'" +
                ", username='" + username + "'" +
                ", password='" + password + "'" +
                "}";
        }
    }
}
