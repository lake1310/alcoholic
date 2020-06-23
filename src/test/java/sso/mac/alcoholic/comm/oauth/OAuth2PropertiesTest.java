package sso.mac.alcoholic.comm.oauth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OAuth2PropertiesTest {

    @Autowired
    OAuth2Properties oAuth2Properties;

    @Test
    public void checkProperties(){
        assertThat(oAuth2Properties.getExpiredPeriod()).isEqualTo(3600);
        assertThat(oAuth2Properties.getResourceId()).isEqualTo("hirit-server");
        assertThat(oAuth2Properties.getPublicKey()).isNotNull();
    }
}