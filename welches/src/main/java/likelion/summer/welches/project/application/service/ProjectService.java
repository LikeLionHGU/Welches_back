package likelion.summer.welches.project.application.service;

import likelion.summer.welches.bookMark.application.service.BookMarkService;
import likelion.summer.welches.bookMark.domain.entity.BookMark;
import likelion.summer.welches.commons.config.ImageUploader;
import likelion.summer.welches.communityPost.application.service.CommunityPostService;
import likelion.summer.welches.communityPost.domain.entity.CommunityPost;
import likelion.summer.welches.project.application.dto.ProjectAddDto;
import likelion.summer.welches.project.domain.entity.Project;
import likelion.summer.welches.project.domain.repository.ProjectRepository;
import likelion.summer.welches.project.presentation.request.ProjectCategoryRequest;
import likelion.summer.welches.project.presentation.response.ProjectGetResponse;
import likelion.summer.welches.project.presentation.response.ProjectResponse;
import likelion.summer.welches.projectComment.application.service.ProjectCommentService;
import likelion.summer.welches.projectComment.domain.entity.ProjectComment;
import likelion.summer.welches.projectLike.domain.entity.ProjectLike;
import likelion.summer.welches.projectLike.domain.repository.ProjectLikeRepository;
import likelion.summer.welches.user.domain.entity.User;
import likelion.summer.welches.user.domain.repository.UserRepository;
import likelion.summer.welches.userApplication.domain.entity.UserApplication;
import likelion.summer.welches.userApplication.domain.repository.UserApplicationRepository;
import likelion.summer.welches.userProject.application.service.UserProjectService;
import likelion.summer.welches.userProject.domain.entity.UserProject;
import likelion.summer.welches.userProject.domain.repository.UserProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ImageUploader imageUploader;
    private final UserRepository userRepository;
    private final UserProjectService userProjectService;
    private final CommunityPostService communityPostService;
    private final ProjectLikeRepository projectLikeRepository;
    private final UserApplicationRepository userApplicationRepository;
    private final UserProjectRepository userProjectRepository;
    private final BookMarkService bookMarkService;
    private final ProjectCommentService projectCommentService;

    @Transactional
    public Long addProject(ProjectAddDto dto) {
        String imageUrl = null;
        if(dto.getFile() != null) {
            imageUrl = imageUploader.toUpload(dto.getFile());
        }
        User user = userRepository.findUserByUserId(dto.getUserId());

        Project project = projectRepository.save(Project.toAdd(dto, imageUrl, user));
        userProjectService.addProjectUser(dto.getUserId(), project.getId());

        return project.getId();
    }

    @Transactional
    public List<ProjectResponse> getAllList(String userId) {
        List<Project> projectList = projectRepository.findAll();
        List<ProjectResponse> projectResponseList = projectList.stream().map(ProjectResponse::toResponse).toList();
        if(userId != null) {
            for(ProjectResponse p : projectResponseList) {
                p.setIsOwner(p.getOwnerId().equals(userId)); // 현재 접속자가 해당 프로젝트의 owner일 경우에는 true를 넣어서 return
            }
        } else {
            for(ProjectResponse p : projectResponseList) {
                p.setIsOwner(false); // 현재 접속자가 해당 프로젝트의 owner일 경우에는 true를 넣어서 return
            }
        }


        return projectResponseList;
    }

    @Transactional
    public List<ProjectResponse> getFinishedList(String userId) {
        List<Project> projectList = projectRepository.findProjectsByIsFinished(true);
        List<ProjectResponse> projectResponseList = projectList.stream().map(ProjectResponse::toResponse).toList();

        for(ProjectResponse p : projectResponseList) {
            p.setIsOwner(p.getOwnerId().equals(userId)); // 현재 접속자가 해당 프로젝트의 owner일 경우에는 true를 넣어서 return
        }

        return projectResponseList;
    }

    @Transactional
    public List<ProjectResponse> getProjectListWithCategoryAndFinish(String userId, String category, Boolean isFinished) {
        List<Project> projectList = projectRepository.findProjectsByCategoryEqualsAndIsFinished(category, isFinished);
        List<ProjectResponse> projectResponseList = projectList.stream().map(ProjectResponse::toResponse).toList();

        for(ProjectResponse p : projectResponseList) {
            p.setIsOwner(p.getOwnerId().equals(userId)); // 현재 접속자가 해당 프로젝트의 owner일 경우에는 true를 넣어서 return
        }
        return projectResponseList;
    }
    @Transactional
    public List<ProjectResponse> getProjectListWithCategoryAndRecruit(String userId, String category, Boolean isRecruit) {
        List<Project> projectList = projectRepository.findProjectsByCategoryEqualsAndIsRecruit(category, isRecruit);
        List<ProjectResponse> projectResponseList = projectList.stream().map(ProjectResponse::toResponse).toList();

        for(ProjectResponse p : projectResponseList) {
            p.setIsOwner(p.getOwnerId().equals(userId)); // 현재 접속자가 해당 프로젝트의 owner일 경우에는 true를 넣어서 return
        }
        return projectResponseList;
    }

    @Transactional
    public List<ProjectResponse> getProjectListWithBigCategoryAndFinish(String userId, String bigCategory, Boolean isFinished) {
        List<Project> projectList = projectRepository.findProjectsByBigCategoryEqualsAndIsFinished(bigCategory, isFinished);
        List<ProjectResponse> projectResponseList = projectList.stream().map(ProjectResponse::toResponse).toList();

        for(ProjectResponse p : projectResponseList) {
            p.setIsOwner(p.getOwnerId().equals(userId)); // 현재 접속자가 해당 프로젝트의 owner일 경우에는 true를 넣어서 return
        }
        return projectResponseList;
    }

    @Transactional
    public List<ProjectResponse> getProjectListWithBigCategoryAndRecruit(String userId, String bigCategory, Boolean isRecruit) {
        List<Project> projectList = projectRepository.findProjectsByBigCategoryEqualsAndIsRecruit(bigCategory, isRecruit);
        List<ProjectResponse> projectResponseList = projectList.stream().map(ProjectResponse::toResponse).toList();

        for(ProjectResponse p : projectResponseList) {
            p.setIsOwner(p.getOwnerId().equals(userId)); // 현재 접속자가 해당 프로젝트의 owner일 경우에는 true를 넣어서 return
        }
        return projectResponseList;
    }

    @Transactional
    public ProjectGetResponse getProjectInformation(Long projectId, String userId) {
        Project project = projectRepository.findById(projectId).orElse(null);

        if(project == null) {
            return null;
        }

        return ProjectGetResponse.toResponse(project, userId);
    }

    @Transactional
    public Long updateProjectOwner(String userId, Long projectId) {
        User user = userRepository.findUserByUserId(userId);
        Project project = projectRepository.findById(projectId).orElse(null);

        assert project != null;
        project.setUser(user);
        projectRepository.save(project);

        return project.getId();
    }

    @Transactional
    public List<ProjectResponse> getBestProjectList() {
        List<Project> projectList = projectRepository.findAll();

        projectList.sort((p1, p2) -> p2.getProjectLikeList().size() - p1.getProjectLikeList().size());
        return projectList.stream().map(ProjectResponse::toResponse).limit(10).toList();
    }

//    @Transactional
//    public ProjectResponse getProject(String userId, Long projectId) {
//        Project project = projectRepository.findById(projectId).orElse(null);
//        ProjectResponse projectResponse
//    }

    @Transactional
    public Long deleteProject(Long projectId) {
        Project project = projectRepository.findById(projectId).orElse(null);

        List<CommunityPost> communityPostList = project.getCommunityPostList();
        for(CommunityPost p : communityPostList) { // community post 삭제 / 삭제 시에 달려있는 댓글과 좋아요는 자동적으로 모두 삭제됨
            communityPostService.deleteCommunityPost(p.getId());
        }

        List<ProjectLike> projectLikeList = project.getProjectLikeList(); // 프로젝트 좋아요 모두 삭제
        projectLikeRepository.deleteAll(projectLikeList);

        List<UserApplication> userApplicationList = project.getUserApplicationList();
        userApplicationRepository.deleteAll(userApplicationList); // 신청자 정보 모두 삭제

        List<UserProject> userProjectList = project.getUserProjectList();
        userProjectRepository.deleteAll(userProjectList); // 프로젝트 참여자 모두 삭제

        List<BookMark> bookMarkList = project.getBookMarkList();
        for(BookMark b : bookMarkList) {
            bookMarkService.deleteBookMark(b.getId());
        } // 갈피 모두 삭제

        List<ProjectComment> projectCommentList = project.getProjectCommentList();
        for(ProjectComment p : projectCommentList) {
            projectCommentService.deleteProjectComment(p.getId());
        } // 프로젝트 댓글 모두 삭제

        projectRepository.delete(project);
        return projectId;
    }
}
