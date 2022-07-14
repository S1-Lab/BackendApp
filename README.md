# Spring Boot Backend App
## 기능 명세서
### 🧑🏻‍💻[201211306](https://github.com/201211306) 기능
- [X] Post 생성(+) 버튼 클릭
  - Input : member_id
  - Output : event_list, relation_list 
- [X] Post 생성
  - Input : member_id, name, phone_number, event_id, relation_id, amount, memo, sent_at, is_sent 
  - Output : member_id, name, phone_number, event_name, relation_name, amount, memo, sent_at, is_sent
- [X] Event 생성
  - Input : event_name, member_id
  - Output : evnt_id, event_name
- [X] Relation 생성
  - Input : relation_name, member_id
  - Output : relation_id, relation_name
- [ ] Event 삭제
- [ ] Relation 삭제