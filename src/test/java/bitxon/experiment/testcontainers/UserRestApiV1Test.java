package bitxon.experiment.testcontainers;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

public class UserRestApiV1Test extends AbstractTestcontainersV1Test {

    @Autowired
    public TestRestTemplate restTemplate;

    @Test
    public void contextLoads() {
        User userToCreate = new User(null, "Bob");
        User userCreated = restTemplate.postForObject("/users", userToCreate, User.class);
        String userId = userCreated.getId();
        Assert.assertNotNull("ID should not be null", userId);

        User userFounded = restTemplate.getForObject("/users/{id}", User.class, userId);
        Assertions.assertEquals("Bob", userFounded.getName());
    }

}
