
import com.xt.study.spring.redis.model.Person;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisTemplateTest extends BaseTest {

  @Autowired
  RedisTemplate<String, Object> redisTemplate;

  @Test
  public void redisTest() {
    redisTemplate.opsForValue().set("key1", String.valueOf(10));
    String value1 = (String) redisTemplate.opsForValue().get("key1");
    System.out.println(value1);

    BoundValueOperations boundValueOperations = redisTemplate.boundValueOps("key2");
    long value2 = boundValueOperations.increment(10);
    System.out.println(value2);
    value2 = (Integer) boundValueOperations.get();
    System.out.println(value2);

    Person person1 = new Person("Super Thanos", 1001, "Mars");
    redisTemplate.opsForValue().set("person1", person1);
    Person value3 = (Person) redisTemplate.opsForValue().get("person1");
    System.out.println(value3);
  }
}
