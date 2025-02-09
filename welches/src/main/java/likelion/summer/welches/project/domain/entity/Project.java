package likelion.summer.welches.project.domain.entity;


import jakarta.persistence.*;
import likelion.summer.welches.bookMark.domain.entity.BookMark;
import likelion.summer.welches.communityPost.domain.entity.CommunityPost;
import likelion.summer.welches.project.application.dto.ProjectAddDto;
import likelion.summer.welches.projectComment.domain.entity.ProjectComment;
import likelion.summer.welches.projectLike.domain.entity.ProjectLike;
import likelion.summer.welches.user.domain.entity.User;
import likelion.summer.welches.userApplication.domain.entity.UserApplication;
import likelion.summer.welches.userProject.domain.entity.UserProject;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageAddress;
    private String category;
    private String name;
    private String information;
    private String description;
    private Boolean isPublic;
    private Long maximumNumber;
    private Boolean isFinished;
    private Boolean isRecruit;
    private String bigCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<CommunityPost> communityPostList;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectComment> projectCommentList;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectLike> projectLikeList;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<UserApplication> userApplicationList;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<UserProject> userProjectList;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<BookMark> bookMarkList;

    public static Project toAdd(ProjectAddDto dto, String imageAddress, User user) {
        return Project.builder()
                .category(dto.getCategory())
                .bigCategory(dto.getBigCategory())
                .description(dto.getDescription())
                .name(dto.getName())
                .information(dto.getInformation())
                .isPublic(dto.getIsPublic())
                .maximumNumber(dto.getMaximumNumber())
                .isFinished(dto.getIsFinished())
                .imageAddress(imageAddress)
                .user(user)
                .isRecruit(dto.getIsRecruit())
                .build();
    }
}
