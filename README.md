## Spring file upload
스프링에서는 파일 업로드를 할 수 있는 기능을 제공해준다(HTML form에서 multipart/form-data 형식으로 데이터가 넘어오는 경우를 처리해준다)
##
스프링에서는 [@RequestParam MultipartFile 이름]의 형식에서 간단하게 파라미터 정보를 받을 수 있다 또한 받은 업로드 파일을 특정 경로에 저장할 때에는
이름.transferTo(new File(경로)) 이런식으로 지정하면 된다
