# CIDERPAY_LOCAL_TEST
- 본 프로그램은 사이다페이(ciderpay.com) 의 결제 연동 테스트를 할 때 사용 하는 프로그램입니다.
- 로컬 환경에서 작업시 결제 완료 피드백을 받을 수가 없기 때문에 제대로 된 테스트 진행이 불가능합니다. 해당 프로그램을 이용하여 피드백을 테스트 해주시면 됩니다.
- 해당 프로그램의 문의 사항이나 버그 신고는 purred@udid.co.kr 으로 문의 주시기 바랍니다.
- 사이다페이 결제 연동은 다음 URL 을 참고하시기 바랍니다. http://uddocs.net/v/ciderpay

# 실행법

```hash
$ cd server
$ gradlew bootRun

$ cd ../client
$ yarn install
$ yarn serve
```

- localhost:3000 으로 접속하시면 요청 된 내역을 확인 할 수 있는 간략한 관리자 페이지가 나옵니다.
- 사이다페이 API 의 REST URL 주소 - https://api.ciderpay.com/ 를 http://localhost:8080/ 으로 변경하여 테스트 하시면 됩니다.

# 주의 사항
- 현재는 결제 API만 지원됩니다.
- 해당 프로그램은 테스트 프로그램이기 때문에 실제로 결제가 일어나지 않습니다. 실제 결제는 실서버에서 실제 API 를 이용하여 테스트 해주시기 바랍니다.