package pilot.instagram.domain.post.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import pilot.instagram.domain.post.dto.response.PostPagingResponse;

import java.util.List;
import java.util.Optional;

import static ch.qos.logback.core.util.StringUtil.isNullOrEmpty;
import static pilot.instagram.domain.post.entity.QPost.post;

@RequiredArgsConstructor
public class PostCustomRepositoryImpl implements PostCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<PostPagingResponse> findPostByUserIdWithPaged(String userId, Pageable pageable) {
        List<PostPagingResponse> fetchResult = jpaQueryFactory
                .select(Projections.fields(PostPagingResponse.class,
                        post.id.as("id"),
                        post.content.as("content"),
                        post.user.id.as("userId")))
                .from(post)
                .where(userIdEq(userId))
                .orderBy(post.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = Optional.ofNullable(
                        jpaQueryFactory
                                .select(post.count())
                                .from(post)
                                .where(userIdEq(userId))
                                .fetchOne())
                .orElse(0L);

        return PageableExecutionUtils.getPage(fetchResult, pageable, () -> total);
    }

    private BooleanExpression userIdEq(String userId) {
        return isNullOrEmpty(userId) ? null : post.user.id.eq(userId);
    }
}
