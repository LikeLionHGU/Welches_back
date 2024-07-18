package likelion.summer.welches.project.application.service;

import likelion.summer.welches.commons.config.ImageUploader;
import likelion.summer.welches.project.application.dto.ProjectAddDto;
import likelion.summer.welches.project.domain.entity.Project;
import likelion.summer.welches.project.domain.repository.ProjectRepository;
import likelion.summer.welches.user.domain.entity.User;
import likelion.summer.welches.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ImageUploader imageUploader;
    private final UserRepository userRepository;

    @Transactional
    public Long addProject(ProjectAddDto dto) {
        String imageUrl = imageUploader.toUpload(dto.getFile());
        User user = userRepository.findUserByUserId(dto.getUserId());

        Project project = projectRepository.save(Project.toAdd(dto, imageUrl, user));
        return project.getId();
    }
}
