package com.example.performancecache;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.example.performancecache.dto.Notice;
import com.example.performancecache.mapper.NoticeReadMapper;
import com.example.performancecache.service.NoticeServiceImpl;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NoticeServiceMockTests {
    @Mock
    private NoticeReadMapper noticeReadMapper;

    @InjectMocks
    private NoticeServiceImpl noticeService;

    @Test
    @DisplayName("조회수가 동일한 경우 최근 생성일 기준으로 정렬되어야 한다.")
    public void testSortNoticesByViewsAndCreateDate() {
        // Sample notices data
        Notice notice1 = new Notice();
        notice1.setId(1);
        notice1.setTitle("Title 1");
        notice1.setContent("Content 1");
        notice1.setWho("Admin");
        notice1.setViews(200);
        notice1.setCreateDate(LocalDateTime.of(2024, 4, 1, 10, 0));
        notice1.setUpdateDate(LocalDateTime.of(2024, 4, 1, 10, 0));

        Notice notice2 = new Notice();
        notice2.setId(2);
        notice2.setTitle("Title 2");
        notice2.setContent("Content 2");
        notice2.setWho("Admin");
        notice2.setViews(200);
        notice2.setCreateDate(LocalDateTime.of(2024, 4, 1, 9, 0));
        notice2.setUpdateDate(LocalDateTime.of(2024, 4, 1, 9, 0));

        List<Notice> notices = Arrays.asList(notice1, notice2);

        // Mocking method call
        when(noticeReadMapper.findHotNotices()).thenReturn(notices);

        // Calling the method to be tested
        noticeService.sortNoticesByViewsAndCreateDate();

        // Assertions
        assertThat(notices.get(0)
                          .getId()).isEqualTo(1);
        assertThat(notices.get(1)
                          .getId()).isEqualTo(2);
    }
}
