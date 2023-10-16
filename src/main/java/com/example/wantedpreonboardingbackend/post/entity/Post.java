package com.example.wantedpreonboardingbackend.post.entity;

import com.example.wantedpreonboardingbackend.apply.entity.Apply;
import com.example.wantedpreonboardingbackend.company.entity.Company;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    private Long postId;
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company companyName;
    @Column(name = "position", columnDefinition = "VARCHAR(30)")
    private String position;
    @Column(name = "reward", columnDefinition = "INT")
    private int reward;
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> stacks = new HashSet<>();
    @OneToMany(mappedBy = "post")
    private List<Apply> posts = new ArrayList<>();
    public Post(long postId, Company companyName, String position, int reward, String content, Set<String> stacks) {
        this.postId = postId;
        this.companyName = companyName;
        this.position = position;
        this.reward = reward;
        this.content = content;
        this.stacks = stacks;
    }

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