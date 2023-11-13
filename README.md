# kotlin + AAC + MVVM 를 적용한 간단 로그인 페이지 예제

# AAC (Android Architecture Components)
Google에서 2017년도에 발표한 5개의 라이브러리로 해당 프로젝트 내에서는 LiveData, ViewModel, Room, Databinding 을 적용하였습니다.

# MVVM (Model - View - ViewModel) 디자인 패턴
View(Activity,Fragment)와 Model(DataBase) 사이에 ViewModel을 두어 이 둘 사이에 의존성을 없앤 디자인 패턴

# model
 - Room Database : SQLite 개체 매핑 라이브러리
 - Entity : 데이터베이스 내에 존재하는 테이블
 - DAO(Data Access Object) : DAO는 앱에서 데이터베이스의 데이터를 추가,삭제,업데이트 작업을 할 수 있는 메소드를 제공해주는 역할, 그외 다양한 쿼리 사용가능

# repository
 - Repository Pattern : ViewModel 에서 직접 Data에 접근하는 방식이 아닌, Data Layer를 캡슐화하여 Repository 내에서 데이터에 접근 후 ViewModel에 반환해주는 개념

           [  Activity / Fragment      ]
                          |
           [  ViewModel (LiveData ... )]
                          |
           [  Repository               ]
                          |
            -----------------------------
            |                           |
       [Model (Room->SQLite)]   [Remote Data Source(Retrofit)]

# utils
 - FirebaseCrashlytics

# view
 - DashBoardActivity
 - LoginActivity
 - MainActivity

# viewModel
 - MainViewModel 
  1) Repository 인스턴스를 갖고 데이터베이스 작업을 수행하는 메서드가 존재
  2) View에서 발생된 이벤트에 대한 처리가 호출 