#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.

* tomcat의 Container 모듈은 4가지 컴포넌트로 구성된다 (Engine, Host, Context, Wrapper). tomcat의 여러가지 컴포넌트는 Lifecycle 인터페이스의
구현을 통해서 생명주기를 관리하고, 이 Lifecycle의 변경을 감지하는 LifecycleEvent 클래스를 통해서 해당 컴포넌트의 lifecycle 단계 변경에 대한
이벤트를 처리할 수 있다.
* 참고로, 각 모듈은 Pipeline과 Valve라는 특별한 클래스를 통해서 요청이 들어왔을때 일련의 과정을 처리한다. (Filter의 개념과 비슷하다. 실제로 Filter가 
Valve중 하나이다) 이 Valve중 마지막에 수행되는 Valve를 basic valve라고 하는데, Context 컴포넌트의  basic valve는 mapping을 통해서 
알맞은 Wrapper 컴포넌트로 요청을 넘겨주는 것이고, Wrapper의 basic valve는 해당 Wrapper가 wrapping한 servlet의 service(req, res)를 
호출하는 것이다.
* 다시 돌아와서 컨테이너의 초기화는 위 4가지 컴포넌트의 초기화를 의미하는 것이다. 초기화 단계에서 상위 Container의 초기화는 하위 Container의 초기화를
트리거한다. 즉, Context의 초기화는 Wrapper의 초기화를 트리거한다.  
이때 초기화는 BEFORE_START, START, AFTER_START로 나뉘어져 있으며, 하위 컴포넌트의 초기화는 상위 컴포넌트의 BEFORE_START와 START사이에서 
발생한다.
* 문제에서 제시되는 ContextLoaderListener가 정확히 어떤 단계에 속해있는지는 코드에서 확인할 수 없지만, DB 초기화와 관련한 코드고, 
DB는 서블릿 단위가 아닌 context 단위이기 때문에, Context 컴포넌트의 초기화와 관련한 lifecycle listener임을 짐작할 수 있다.
메소드의 이름이 contextInitialized이므로 아마 AFTER_START event가 발생했을때 해당 메소드가 실행되어 DB가 초기화 됨을 추측할 수 있다.
* DispatcherServlet의 경우 loadOnStartup = 1에 의해서 미리 load되어 초기화되는데, 이는 실제 servlet이 초기화 과정에서 servlet의 classloader
만 생성되고, 실제로 load되지 않는 것과는 다르다. 따라서 일반적인 servlet이 실제 요청이되면 초기화되는 것과는 달리, Wrapper의 초기화 과정에서 
load될 것이라고 추측할 수 있다.

#### 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
##### 실제 tomcat 코드의 측면에서 설명하면 다음과 같다.
1. Connector 모듈에서 요청을 처리할 processor 객체를 pool에서 가져온뒤, 해당 요청 socket을 assign한다.
2. processor 컴포넌트에서 Request와 Response 객체를 생성하고, 요청 메시지를 parsing하여 Request객체를 채운뒤, Container 모듈에 넘긴다.
3. Container 모듈의 pipeline들을 통해서 요청이 처리된다. Filter는 servlet하나가 아닌 여러개의 servlet에 대해서 처리하므로, Context 컴포넌트의
valve에 속한다고 추측할 수 있다. 따라서 먼저 '/'(root)로 요청이 들어왔을때, resourceURL이 아니므로, 특별한 처리 없이 Context pipeline의 basic 
valve가 실행되어 mapping을 통해 '/'(root) url과 매치되는 servlet의 Wrapper 컴포넌트를 invoke한다.
4. wrapper 컴포넌트의 pipeline이 진행되어 basic valve가 실행되고, 미리 load된 DispatcherServlet의 service 메소드가 실행된다. 
해당 메소드에서 해당 요청을 처리할 controller를 찾아서 dispatch한다. (비록 책에서는 servlet으로 취급하지만, 
실제로 여기의 controller는 servlet이 아님에 주의하라)
5. controller에서 요청을 처리하여 ModelAndView객체를 생성하여 DispatcherServlet에 반환하고, DispatcherServlet은 view의 render함수를 호출한다.
해당 render함수에서 write가 발생하여 응답메시지를 전송하게 된다.
6. DispatcherServlet의 service 메소드가 끝났으므로, 응답이 완료되었다.
7. 첫번째 응답에서는 home.jsp만 전송하므로, 브라우저에서 추가로 필요한 데이터에 대한 요청을 보낸다.
8. 리소스에 대해서는 Filter에서 default라는 이름의 dispatcher로 포워딩하는데, 이는 이름이 default라는 Wrapper로 Context의 basic valve
가 실행되었다고 볼 수 있으므로, 이후의 동작은 대부분 동일하다.

#### 7. next.web.qna package의 ShowController는 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.
* 다른 사용자로부터 question이 삭제되어 questionId로 찾은 question 객체가 null일 수 있다.

