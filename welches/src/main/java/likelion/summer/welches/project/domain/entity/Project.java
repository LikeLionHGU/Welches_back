package likelion.summer.welches.project.domain.entity;


import jakarta.persistence.*;
import likelion.summer.welches.project.application.dto.ProjectAddDto;
import likelion.summer.welches.projectComment.domain.entity.ProjectComment;
import likelion.summer.welches.user.domain.entity.User;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageAddress;
    private String category;
    private String name;
    private String information;
    private Boolean isPublic;
    private Long maximumNumber;
    private Boolean isFinished;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectComment> projectCommentList;

    public static Project toAdd(ProjectAddDto dto, String imageAddress, User user) {
        return Project.builder()
                .category(dto.getCategory())
                .name(dto.getName())
                .information(dto.getInformation())
                .isPublic(dto.getIsPublic())
                .maximumNumber(dto.getMaximumNumber())
                .isFinished(dto.getIsFinished())
                .imageAddress(imageAddress)
                .user(user)
                .build();
    }
}
