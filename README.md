# Spring Boot Backend App
## 기능 명세서
### 🧑🏻‍💻[201211306](https://github.com/201211306) 기능
  - [X] Post 생성(+) 버튼 클릭 `{uri}/post/{id}`
    - 요청 :
      ```markdown
      HTTP Method = POST
      * Body 값 없이, 경로 파라미터 값을 입력하여 요청을 한다.
      ex) {uri}/post/2
      {id} 부분에 memberId(고유 식별 번호)를 입력하여 요청한다.
      ```
    - 응답 :
      ```json
      {
        "success": true,
        "response": {
          "eventNames": [
            "행사1",
            "행사2",
            "행사3"
          ],
          "relationNames": [
            "관계1",
            "관계2",
            "관계3",
            "관계4"
          ]
        },
        "error": null
      }
      ```
  - [X] Post 생성 `{uri}/post`
    - 요청 :
      ```json
      HTTP Method = POST
      {
        "name": "홍길동",
        "phoneNumber": "010-1234-1234",
        "isSent": "true",
        "sentAt": "2022-05-10",
        "memo": "메모메모메모",
        "amount": "50000",
        "memberId": "2",
        "eventId": "1",
        "relationId": "1"
      }
      ```
    - 응답 :
      ```json
      {
        "success": true,
        "response": {
          "name": "홍길동",
          "phoneNumber": "010-1234-1234",
          "amount": 50000,
          "isSent": true,
          "sentAt": "2022-05-10",
          "memo": "메모메모메모",
          "eventName": "행사1",
          "relationName": "관계1"
        },
        "error": null
      }
      ```
  - [X] Event 생성 `{uri}/event`
    - 요청 :
      ```json
      HTTP Method = POST
      {
        "eventName": "행사2",
        "memberId": "2"
      }
      ```
    - 응답 :
      ```json
      {
        "success": true,
        "response": {
          "eventNames": [
            "행사1", "행사2"
          ]
        },
        "error": null
      }
      ```
  - [X] Relation 생성 `{uri}/relation`
    - 요청 :
      ```json
      HTTP Method = POST
      {
        "relationName": "관계1",
        "memberId": "3"
      }
      ```
    - 응답 :
      ```json
      {
        "success": true,
        "response": {
          "relationNames": [
            "관계1"
          ]
        },
        "error": null
      }
      ```
  - [X] Event 삭제 `{uri}/event`
    - 요청
      ```json
      HTTP Method = DELETE
      {
        "eventName": "행1",
        "memberId": "2"
      }
      ```
    - 응답
      ```json
      {
        "success": true,
        "response": {
          "eventNames": [
            "행사2"
          ]
        },
        "error": null
      }
      ```
  - [X] Relation 삭제 `{uri}/relation`
    - 요청
      ```json
      HTTP Method = DELETE
      {
        "relationName": "관계1",
        "memberId": "4"
      }
      ```
    - 응답
      ```json
      {
        "success": true,
        "response": {
          "relationNames": [
          
          ]
        },
        "error": null
      }
      ```