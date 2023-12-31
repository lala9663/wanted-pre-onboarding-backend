package com.example.wantedpreonboardingbackend.post.entity;

import com.example.wantedpreonboardingbackend.company.entity.Company;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "post")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", columnDefinition = "BIGINT")
    private Long id;
    @Column(name = "position", columnDefinition = "VARCHAR(30)")
    private String position;
    @Column(name = "reward", columnDefinition = "INT")
    private int reward;
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> stacks = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;


    public void updateFrom(Post other) {
        if (other.getPosition() != null) {
            this.position = other.getPosition();
        }
        if (other.getReward() != 0) {
            this.reward = other.getReward();
        }
        if (other.getContent() != null) {
            this.content = other.getContent();
        }
        if (other.getStacks() != null) {
            this.stacks = other.getStacks();
        }
    }
}
