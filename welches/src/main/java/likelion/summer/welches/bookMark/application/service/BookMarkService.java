package likelion.summer.welches.bookMark.application.service;

import likelion.summer.welches.bookMark.domain.entity.BookMark;
import likelion.summer.welches.bookMark.domain.repository.BookMarkRepository;
import likelion.summer.welches.bookMark.presentation.request.BookMarkAddRequest;
import likelion.summer.welches.bookMark.presentation.request.BookMarkUpdateRequest;
import likelion.summer.welches.bookMark.presentation.response.BookMarkResponse;
import likelion.summer.welches.post.domain.entity.Post;
import likelion.summer.welches.post.domain.repository.PostRepository;
import likelion.summer.welches.post.presentation.response.PostGetResponse;
import likelion.summer.welches.project.domain.entity.Project;
import likelion.summer.welches.project.domain.repository.ProjectRepository;
import likelion.summer.welches.temporaryPost.domain.entity.TemporaryPost;
import likelion.summer.welches.temporaryPost.domain.repository.TemporaryPostRepository;
import likelion.summer.welches.userBookMark.application.service.UserBookMarkService;
import likelion.summer.welches.userBookMark.domain.entity.UserBookMark;
import likelion.summer.welches.userBookMark.domain.repository.UserBookMarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookMarkService {
    private final BookMarkRepository bookMarkRepository;
    private final ProjectRepository projectRepository;
    private final UserBookMarkService userBookMarkService;
    private final UserBookMarkRepository userBookMarkRepository;
    private final TemporaryPostRepository temporaryPostRepository;
    private final PostRepository postRepository;

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

    @Transactional
    public PostGetResponse getDefaultPost(Long bookmarkId) {
        BookMark bookMark = bookMarkRepository.findById(bookmarkId).orElse(null);
        List<Post> postList = bookMark.getPostList();

        if(!postList.isEmpty()) {
            Post response = postList.stream()
                    .filter(post -> post.getIsConfirmed() && post.getIsAllowed()) // 컨펌 완료 및 승인
                    .max(Comparator.comparing(Post::getUpdatedDate)).orElse(null);

            if(response != null) {
                return PostGetResponse.toResponse(response);
            } else {
                return null;
            }

        }
        return null;
    }

    @Transactional
    public Long deleteBookMark(Long bookmarkId) {
        BookMark bookMark = bookMarkRepository.findById(bookmarkId).orElse(null);


        List<UserBookMark> userBookMarkList = bookMark.getUserBookMarkList();
        userBookMarkRepository.deleteAll(userBookMarkList); // 갈피에 권한이 있는 사용자 모두 삭제

        List<TemporaryPost> temporaryPostList = bookMark.getTemporaryPostList();
        temporaryPostRepository.deleteAll(temporaryPostList); // 임시 저장된 게시물 모두 삭제

        List<Post> postList = bookMark.getPostList();
        postRepository.deleteAll(postList); // 현재 갈피의 게시물 모두 삭제

        bookMarkRepository.delete(bookMark);

        return bookmarkId;
    }
}
