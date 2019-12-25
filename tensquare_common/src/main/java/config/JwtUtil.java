package config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@ConfigurationProperties("jwt.config")
@Data
public class JwtUtil {
    private String key;
    private long ttl;

    /**
     * @author: zhangyu
     * @date: 2019-12-24
     * @param: [id, subject, roles]
     * @return: java.lang.String
     * 功能描述: 生成Token
     */
    public String createToken(String id, String subject, String roles){
        //获取当前系统时间
        long timeMillis = System.currentTimeMillis();
        //将当前时间转换为Date（）
        Date now = new Date(timeMillis);
        JwtBuilder builder = Jwts.builder()
                .setId(id) //设置id
                .setSubject(subject) //设置主体
                .setIssuedAt(now) //设置当前时间
                .signWith(SignatureAlgorithm.HS256, key) //设置签名算法和签名
                .claim("roles", roles); //设置roles角色，属于自定义claim
        if (ttl > 0){
            builder.setExpiration(new Date(timeMillis+ttl)); //设置过期时间
        }
        //返回token凭证
        return builder.compact();
    }

    /**
     * @author: zhangyu
     * @date: 2019-12-24
     * @param: [token]
     * @return: io.jsonwebtoken.Claims
     * 功能描述: 对token进行
     */
    public Claims parseToken(String token){
        return Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();
    }
}
