package likelion.summer.welches.bookMark.application.service;

import likelion.summer.welches.bookMark.domain.entity.BookMark;
import likelion.summer.welches.bookMark.domain.repository.BookMarkRepository;
import likelion.summer.welches.bookMark.presentation.request.BookMarkAddRequest;
import likelion.summer.welches.bookMark.presentation.request.BookMarkUpdateRequest;
import likelion.summer.welches.bookMark.presentation.response.BookMarkResponse;
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
    public Long addBookMark(String userId, BookMarkAddRequest request) {
        // 처음 갈피를 만드는 부분
        // 여기서 자동으로 사용자-갈피 entity 하나를 추가 해줘야함(이후 작업해야하는 부분)
        Project project = projectRepository.findById(request.getProjectId()).orElse(null);
        BookMark bookMark = bookMarkRepository.save(BookMark.toAdd(project, request));
        userBookMarkService.addUserBookMark(userId, bookMark.getId());

        return bookMark.getId();
    }

    @Transactional
    public BookMarkResponse getBookMark(Long id) {
        BookMark bookMark = bookMarkRepository.findById(id).orElse(null);

        if(bookMark != null) {
            return BookMarkResponse.toResponse(bookMark);
        }

        return null;
    }

    @Transactional
    public Long updateBookMark(Long id, BookMarkUpdateRequest request) {
        BookMark bookMark = bookMarkRepository.findById(id).orElse(null);

        if(bookMark == null) {
            return null;
        }

        bookMark.setName(request.getName());
        bookMark.setIsSameTime(request.getIsSameTime());
        bookMark.setIsShared(request.getIsShared());

        bookMarkRepository.save(bookMark);
        return bookMark.getId();
    }
}
