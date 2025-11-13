package com.mjc813.band_jpa.band.vo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "BandEntity")
@Table(name = "band_tbl")
public class BandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //자동증가PK

    @Column(unique = true, nullable = false, length = 30)
    @Size(min = 6, max = 10)
    private String name;    //밴드명

    @Column(unique = true, nullable = false, length = 20)
    @NotNull
    private String leader;    //리더 이름

    @NotNull
    private String guitarFirst;    //기타연주자이름

    private String guitarSecond;    //기타연주자이름

    private String drum;    //드럼 연주자 이름

    private String bass;    //베이스 연주자 이름

    private String keyboard;    //키보드연주자 이름

    private String vocal;    //보컬이름

    @Email
    private String email;
}
