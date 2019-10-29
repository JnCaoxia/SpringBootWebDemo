package com.caox.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import com.caox.model.UserInfo.CHECK;

import java.io.Serializable;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2019/6/5 14:27
 */
@Data
@Setter
@Getter
@ScriptAssert(lang="javascript",script="com.caox.model.UserInfo.checkParams(_this.username,_this.age,_this.classes)",
        groups=CHECK.class)
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 8660354316408237192L;
    @NotBlank(groups=NAME.class)
    private String username;
    //    private Date birthDate;
    @Min(value=3,groups=AGE.class)
    private Integer age;
    private String classes;
    public interface CHECK{};
    private Integer userId;

    public interface NAME{};

    public interface AGE{};

    //注意进行校验的方法要写成静态方法，否则会出现
    //TypeError: xxx is not a function 的错误
    public static boolean checkParams(String username, int age, String classes) {
        if (username != null && age > 8 && classes != null) {
            return true;
        } else {
            return false;
        }
    }

//
//    public UserInfo(Integer userId, String username, Date birthDate, Integer age, float fRate, char ch) {
//        this.userId = userId;
//        this.username = username;
//        this.birthDate = birthDate;
//        this.age = age;
//        this.fRate = fRate;
//        this.ch = ch;
//    }
//
//    public UserInfo(Integer userId, String username) {
//        this.userId = userId;
//        this.username = username;
//    }
//
//    public UserInfo(Integer userId, String username, Integer age) {
//        this.userId = userId;
//        this.username = username;
//        this.age = age;
//    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInfo userInfo = (UserInfo) o;

        if (!userId.equals(userInfo.userId)) return false;
        return username.equals(userInfo.username);

    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + username.hashCode();
        return result;
    }
}
