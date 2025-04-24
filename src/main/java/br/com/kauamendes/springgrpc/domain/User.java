package br.com.kauamendes.springgrpc.domain;

import br.com.kauamendes.v1.user.UserRes;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "tb_user")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    public UserRes toUserRes() {
        return UserRes.newBuilder()
                .setId(id)
                .setName(name)
                .setEmail(email)
                .build();
    }
}
