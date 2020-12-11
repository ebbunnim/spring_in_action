#enum
https://medium.com/webeveloper/enum%EC%9D%84-%EC%82%AC%EC%9A%A9%ED%95%9C-%EC%83%81%EC%88%98%EA%B0%92-%EA%B4%80%EB%A6%AC-a3e3fb73eae1

#new ArrayList<>() vs Arrays.aslist()
https://woowacourse.github.io/javable/2020-05-18/ArrayList-vs-Arrays.asList

#model.addAttribute
- spring에서 model 객체 통해 데이터를 뷰에 전달


#@Data, @RequiredArgsConstructor
https://hyoj.github.io/blog/java/basic/lombok/#equalsandhashcode
final변수의 경우 반드시 초기화되어야 하므로 RequiredArgsConstructor로 생성자 내에서 초기화


#생성자의 개념 
객체 변수에 값을 무조건 설정해야만 객체가 생성될 수 있도록 강제할 수 있는 방법

#@Autowired
스프링이 해당 빈을 ~에 주입(연결)한다. 

#private protected default public
https://luyin.tistory.com/232

#extends, implements
extends : 부모 객체 메서드 등 그대로 사용 가능 (말그대로 상속)
implements : 자식클래스가 부모 클래스 메서드를 반드시 오버라이딩(재정의) 해야 함

https://velog.io/@hkoo9329/%EC%9E%90%EB%B0%94-extends-implements-%EC%B0%A8%EC%9D%B4

#Serializable
https://woowabros.github.io/experience/2017/10/17/java-serialize2.html


#@Bean vs @Component, @Configuration vs @Service,@Controller.. etc




#<jpa>
##@Id + @GeneratedValue(strategy=GenerationType.AUTO)
=>id만 사용하면 직접 할당해야.gen~ 쓰면 데이터베이스에서 자동 생성. AUTO는 Autoincrement 가 데이터베이스 자체 특성 따라 관리되도록 함.

##jpa 이점
인터페이스에 대한 구현클래스 자동 생성되므로 따로 만들지 않아도 됨. (jdbc보다 편리한 점) 
