package com.example.demo.service.jwt;

import com.example.demo.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtServiceTest {
    @InjectMocks
    private JwtServiceImpl jwtServiceImpl;
    @Mock
    private User userDetails;
    @Mock
    private Claims claims;
    @Value("${jwt.secretKey}")
    public String secretKey;
    @Value("${jwt.validityTime}")
    public long validityTime;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(jwtServiceImpl, "SECRET_KEY", secretKey);
        ReflectionTestUtils.setField(jwtServiceImpl, "JWT_TOKEN_VALIDITY", validityTime);
    }

    @Test
    public void getUserNameFromToken_Successful() {
        when(userDetails.getUsername()).thenReturn("testUser");
        String token = jwtServiceImpl.getToken(userDetails);
        String username = jwtServiceImpl.getUserNameFromToken(token);

        assertNotNull(token);
        assertNotNull(username);
        assertEquals("testUser", username);
    }

    @Test
    public void isTokenValid_ValidToken() {
        when(userDetails.getUsername()).thenReturn("testUser");
        String token = jwtServiceImpl.getToken(userDetails);

        boolean isValid = jwtServiceImpl.isTokenValid(token, userDetails);

        assertNotNull(token);
        assertTrue(isValid);
    }

    @Test(expected = ExpiredJwtException.class)
    public void isTokenValid_ExpiredToken() {
        String invalidToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBaWx5biBGZXJyYWRhIiwiaWF0IjoxNzAwMTk5MzE3LCJleHAiOjE3MDAyMDA3NTd9.q_kjnBnIm5Mdx55ImEnR7l05RsQGrtgm1Ihr_O_PXRI";
        jwtServiceImpl.isTokenValid(invalidToken, userDetails);
    }

    @Test
    public void getExpiration_Successful() {
        String token = jwtServiceImpl.getToken(userDetails);

        Date expiration = jwtServiceImpl.getExpiration(token);

        assertNotNull(expiration);
    }

}
