package likelion.summer.welches.user.application.service;

import likelion.summer.welches.commons.config.ImageUploader;
import likelion.summer.welches.project.application.dto.ProjectUserDto;
import likelion.summer.welches.project.domain.entity.Project;
import likelion.summer.welches.project.domain.repository.ProjectRepository;
import likelion.summer.welches.projectLike.domain.entity.ProjectLike;
import likelion.summer.welches.subscribe.domain.entity.SubscribeUser;
import likelion.summer.welches.subscribe.domain.repository.SubscribeUserRepository;
import likelion.summer.welches.user.application.dto.UserSubscribeDto;
import likelion.summer.welches.user.domain.entity.User;
import likelion.summer.welches.user.domain.repository.UserRepository;
import likelion.summer.welches.user.presentation.request.UserRequest;
import likelion.summer.welches.user.presentation.response.UserGetResponse;
import likelion.summer.welches.userProject.domain.entity.UserProject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ImageUploader imageUploader;
    private final SubscribeUserRepository subscribeUserRepository;

    @Transactional
    public UserGetResponse getUserInfo(String userId) {
        User user = userRepository.findUserByUserId(userId);
        List<SubscribeUser> subscribeUserList = subscribeUserRepository.findSubscribeUserBySubscribeUserId(userId);
        List<UserSubscribeDto> userSubscribeDtoList = subscribeUserList.stream().map((SubscribeUser subscribeUser)
                -> UserSubscribeDto.toResponse(userRepository.findUserByUserId(subscribeUser.getSubscribeUserId()))).toList();


        List<ProjectUserDto> projectUserDtoList = user.getUserProjectList().stream().map((UserProject userProject) -> ProjectUserDto.toResponse(userProject.getProject(), userId)).toList();
        List<ProjectUserDto> progressProjectList = new ArrayList<>();
        List<ProjectUserDto> finishedProjectList = new ArrayList<>();

        for(ProjectUserDto p : projectUserDtoList) {
            if(p.getIsFinished()) {
                finishedProjectList.add(p);
            } else {
                progressProjectList.add(p);
            }
        }

        List<ProjectLike> projectLikeList = user.getProjectLikeList();
        List<Project> projectList = new ArrayList<>();

        for(ProjectLike p : projectLikeList) {
            projectRepository.findById(p.getProject().getId()).ifPresent(project -> projectList.add(projectRepository.findById(p.getProject().getId()).orElse(null)));
        }

        List<ProjectUserDto> likeProjectList = projectList.stream().map((Project project) -> ProjectUserDto.toResponse(project, userId)).toList();




        return UserGetResponse.toResponse(user, userSubscribeDtoList, progressProjectList, finishedProjectList, likeProjectList);
    }

    @Transactional
    public String updateUser(MultipartFile profile, MultipartFile back, UserRequest request, String userId) {
        User user = userRepository.findUserByUserId(userId);

        if(profile != null) {
            String profileImageAddress = imageUploader.toUpload(profile);
            user.setProfileImageAddress(profileImageAddress);
        }

        if(back != null) {
            String backImageAddress = imageUploader.toUpload(back);
            user.setBackgroundImageAddress(backImageAddress);
        }

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setInformation(request.getInformation());

        userRepository.save(user);

        return userId;
    }

    @Transactional
    public UserGetResponse getUserInfoWithId(String userId, String myId) {
        User user = userRepository.findUserByUserId(userId);

        List<SubscribeUser> subscribeUserList = subscribeUserRepository.findSubscribeUserBySubscribeUserId(userId);

        List<UserSubscribeDto> userSubscribeDtoList = subscribeUserList.stream().map((SubscribeUser subscribeUser)
                -> UserSubscribeDto.toResponse(userRepository.findUserByUserId(subscribeUser.getSubscribeUserId()))).toList();

        List<ProjectUserDto> projectUserDtoList = user.getUserProjectList().stream().map((UserProject userProject) -> ProjectUserDto.toResponse(userProject.getProject(), userId)).toList();
        List<ProjectUserDto> progressProjectList = new ArrayList<>();
        List<ProjectUserDto> finishedProjectList = new ArrayList<>();

        for(ProjectUserDto p : projectUserDtoList) {
            if(p.getIsFinished()) {
                finishedProjectList.add(p);
            } else {
                progressProjectList.add(p);
            }
        }

        List<ProjectLike> projectLikeList = user.getProjectLikeList();
        List<Project> projectList = new ArrayList<>();

        for(ProjectLike p : projectLikeList) {
            projectRepository.findById(p.getProject().getId()).ifPresent(project -> projectList.add(projectRepository.findById(p.getProject().getId()).orElse(null)));
        }

        List<ProjectUserDto> likeProjectList = projectList.stream().map((Project project) -> ProjectUserDto.toResponse(project, userId)).toList();

        SubscribeUser subscribeUser = subscribeUserRepository.findSubscribeUserByUserIdAndSubscribeUserId(myId, userId);
        Boolean isSubscribe = Boolean.FALSE;

        if(subscribeUser != null) {
            isSubscribe = Boolean.TRUE;
        }

        return UserGetResponse.toResponse(user, userSubscribeDtoList, progressProjectList, finishedProjectList, likeProjectList, isSubscribe);
    }
}
