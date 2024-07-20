package likelion.summer.welches.bookMark.application.service;

import likelion.summer.welches.bookMark.domain.entity.BookMark;
import likelion.summer.welches.bookMark.domain.repository.BookMarkRepository;
import likelion.summer.welches.project.domain.entity.Project;
import likelion.summer.welches.project.domain.repository.ProjectRepository;
import likelion.summer.welches.userBookMark.application.service.UserBookMarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookMarkService {
    private final BookMarkRepository bookMarkRepository;
    private final ProjectRepository projectRepository;
    private final UserBookMarkService userBookMarkService;

    @Transactional
    public Long addBookMark(String userId, Long projectId) {
        // 처음 갈피를 만드는 부분
        // 여기서 자동으로 사용자-갈피 entity 하나를 추가 해줘야함(이후 작업해야하는 부분)
        Project project = projectRepository.findById(projectId).orElse(null);
        BookMark bookMark = bookMarkRepository.save(BookMark.toAdd(project));
        userBookMarkService.addUserBookMark(userId, bookMark.getId());

        return bookMark.getId();
    }
}
