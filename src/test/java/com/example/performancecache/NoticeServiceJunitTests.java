package com.example.performancecache;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.performancecache.dto.Notice;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class NoticeServiceJunitTests {

    @Test
    @DisplayName("조회수가 동일한 경우 최근 생성일 기준으로 정렬되어야 한다.")
    public void testSortByViewsAndCreationDate() {
        // given
        Notice notice1 = new Notice();
        notice1.setId(1);
        notice1.setTitle("Title 1");
        notice1.setContent("Content 1");
        notice1.setWho("Admin");
        notice1.setViews(200);
        notice1.setCreateDate(LocalDateTime.of(2024, 4, 1, 3, 0));
        notice1.setUpdateDate(LocalDateTime.of(2024, 4, 1, 4, 0));

        Notice notice2 = new Notice();
        notice2.setId(2);
        notice2.setTitle("Title 2");
        notice2.setContent("Content 2");
        notice2.setWho("Admin");
        notice2.setViews(200);
        notice2.setCreateDate(LocalDateTime.of(2024, 4, 1, 2, 0));
        notice2.setUpdateDate(LocalDateTime.of(2024, 4, 1, 5, 0));

        Notice notice3 = new Notice();
        notice3.setId(3);
        notice3.setTitle("Title 3");
        notice3.setContent("Content 3");
        notice3.setWho("Admin");
        notice3.setViews(200);
        notice3.setCreateDate(LocalDateTime.of(2024, 4, 1, 1, 0));
        notice3.setUpdateDate(LocalDateTime.of(2024, 4, 1, 6, 0));

        // when
        List<Notice> notices = new ArrayList<>();
        notices.add(notice1);
        notices.add(notice2);
        notices.add(notice3);

        Collections.sort(notices);

        // then
        assertThat(notices.get(0)
                          .getId()).isEqualTo(1);
        assertThat(notices.get(1)
                          .getId()).isEqualTo(2);
        assertThat(notices.get(2)
                          .getId()).isEqualTo(3);
    }
}
