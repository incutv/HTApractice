
<div align=center>
	<h3>📚 "HTA 멘토링을 위한 코드입니다." 📚</h3>
</div>
<br><br>

# 📖 실습 환경
* java 17
* ehcache 2.x
* h2
* Maven
* intellij

## 첫번째 미션
* notice 테이블에는 조회수 필드가 있습니다.
* 조회수를 기반으로 HOT 게시글 10개를 출력하고자 합니다. ( 커뮤니티에 핫글이라고 생각해주시면 됩니다.)
  * HOT 게시글 10개애 대한 데이터를 캐싱 처리하고자 합니다.( ehcache 활용)
    * [new] 조회수가 동일한경우에는 최근에 생성한 공지사항 게시글순으로 나올수있게 개선해주세요. - (단, java 문법을 활용하여 정렬 처리 해주세요.)

* [new] spring boot gmail 발송 기능을 추가해주세요.
  * from(noreplay 고정) , to(본인 이메일) , message( notice 테이블에 content를)
* [new] spring boot scheduled를 활용하여 매일 18:00에 HOT 게시글 10개를 본인 이메일에 발송되게 스케줄링 설정


         






# HTApractice
