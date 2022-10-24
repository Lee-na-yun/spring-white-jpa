package site.metacoding.white.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // pk는 보통 long(8bit)로 만듬
    private String title;
    @Column(length = 1000)
    private String content;

    // FK가 만들어짐 = user_id
    @ManyToOne(fetch = FetchType.EAGER)
    private User user; // fk
}
