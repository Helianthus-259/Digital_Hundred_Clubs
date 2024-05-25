package org.example.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.example.constants.RequestKeyConstants;
import org.example.entity.Student;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtils {

    private static final String SECRET_KEY = "secretKey:123456";
    public static final long TOKEN_EXPIRE_TIME = 7200 * 1000;

    public String createJwt(JwtUser user) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");

        return JWT.create()
                .withHeader(header)
                .withClaim(RequestKeyConstants.ID, user.getId())
                .withClaim(RequestKeyConstants.NAME, user.getName())
                .withClaim(RequestKeyConstants.AUTHORITY, user.getAuthority())
                .withClaim(RequestKeyConstants.Account, user.getAccount())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRE_TIME))
                .sign(algorithm);
    }

    public Map<String, Claim> parseJwt(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaims();
        } catch (Exception e) {
            return null;
        }
    }

    public static class JwtUser {
        private String id;
        private String name;
        private String authority;

        private String account;

        public JwtUser(String id, String name, String authority, String account) {
            this.id = id;
            this.name = name;
            this.authority = authority;
            this.account = account;
        }

        public JwtUser(Student student) {
            this.id = String.valueOf(student.getStudentId());
            this.name = student.getStName();
            this.authority = "student";
            this.account = student.getEmail();
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getAuthority() {
            return authority;
        }

        public String getAccount()  {return account;}
    }

}