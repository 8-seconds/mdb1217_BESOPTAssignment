# BE SOPT 28th Android Assignment

[![WrittenBy](https://img.shields.io/badge/Written%20by-mdb1217-white.svg)](https://github.com/mdb1217)

:calendar: **Last Edited : `2021. 06. 09`**

<br>

# :computer: Contents

[:zero: Specification](#zero-specification)<br>
[:one: First Week](#one-first-week)<br>
[:two: Second Week](#two-second-week)<br>[:three: Third Week](#three-third-week)<br>[:four: Seventh Week](#four-seventh-week)<br>[:five: Package Structure](#five-package-structure)<br>

---

## :zero: Specification

<table class="tag">
<tbody>
  <tr>
    <td><b>Architecture</b></td>
    <td>MVVM</td>
  </tr>
<tr>
    <td><b>Design Pattern</b></td>
<td>Repository Pattern</td>
</tr>
<tr>
    <td><b>Jetpack Components</b></td>
<td>DataBinding, LiveData, ViewModel, Lifecycle, Room, ViewPager2, CardView</td>
</tr>
<tr>
  <tr>
    <td><b>Network</b></td>
<td>Retrofit2, OkHttp3</td>
</tr>
  <tr>
    <td><b>Asynchronous Processing</b></td>
<td>Coroutine</td>
</tr>
    <td><b>Dependency Injection</b></td>
<td>Hilt</td>
</tr>
<tr>
    <td><b>Third Party Library</b></td>
<td>Glide, Expandable Layout</td>
</tr>
</tbody>
</table>

<br>

## :one: First Week

<table class="tg">
<tbody>
    <tr>
      <td><b>Sign Up</b></td>
      <td><b>Login Check</b></td>
      <td><b>Auto Login</b></td>
      <td><b>Home</b></td>
    </tr>
  <tr>
    <td><img src="/WIKI/SignUp.gif" width="300px"/></td>
    <td><img src="/WIKI/SignIn.gif"  width="300px"/></td>
    <td><img src="/WIKI/AutoLogin.gif"  width="300px"/></td>
  	<td><img src="/WIKI/Home.png"  width="300px"/></td>
	</tr>
</tbody>
</table>

<br>

#### 1. Level 1 :baby:

- ##### Sign Up

  - **회원가입 버튼 클릭 시 세 개의 EditText가 채워져있는지 확인**

    ```kotlin
    private fun isAllEditTextEmpty() : Boolean {
         return isEtNameEmpty() || isEtIdEmpty() || isEtPassword()
    }
    
    private fun isEtNameEmpty() : Boolean {
        return binding.etName.text.isNullOrEmpty()
    }
    
    private fun isEtIdEmpty() : Boolean {
        return binding.etId.text.isNullOrEmpty()
    }
    
    private fun isEtPassword() : Boolean {
        return binding.etPassword.text.isNullOrEmpty()
    }
    ```

  - **빈 칸 확인 및 데이터 전송을 위한 setResult**

    ```kotlin
    private fun initClickEvent() {
        binding.btnSignUp.setOnClickListener {
            if(isAllEditTextEmpty())
                shortToast(getString(R.string.is_empty))
            else {
                val name = binding.etName.text.toString()
                val id = binding.etId.text.toString()
                val password = binding.etPassword.text.toString()
    
                viewModel.insert(UserData(null, name, id, password))
    
                setResult(Activity.RESULT_OK, Intent().putExtra("name", name)
                    .putExtra("id", id)
                    .putExtra("password", password)
                )
                shortToast(getString(R.string.sign_up_done))
                finish()
            }
        }
    }
    ```

<br>

- ##### Sign In

  - **빈 칸 확인**

    ```kotlin
    private fun isEtIdEmpty() : Boolean {
        return binding.etId.text.isNullOrEmpty()
    }
    
    private fun isEtPasswordEmpty() : Boolean {
        return binding.etPassword.text.isNullOrEmpty()
    }
    ```

  - **registerForActivityResult(데이터 수신 부분)**

    ```kotlin
    private val requestActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
        if(activityResult.resultCode == Activity.RESULT_OK) {
            val intent = activityResult.data
            if (intent != null) {
                binding.etId.setText(intent.extras?.getString("id"))
                binding.etPassword.setText(intent.extras?.getString("password"))
            }
        }
        else {
            binding.etId.text.clear()
            binding.etPassword.text.clear()
        }
    
        when(isEtIdEmpty()) {
            true -> setIdGray()
            false -> setIdSky()
        }
    
        when(isEtPasswordEmpty()) {
            true -> setPasswordGray()
            false -> setPasswordSky()
        }
    }
    ```

  - **Auto Login(SharedPreference 이용)**

    ```kotlin
    private fun autoLogin() {
        if(getAutoLogin()) {
            shortToast(getString(R.string.auto_login_done))
            startActivity(Intent(this@SignInActivity, MainActivity::class.java))
        }
    }
    ```

  - **계정 존재 여부 체크(Room 이용) + Launch SignUp Activity**

    ```kotlin
    private fun initClickEvent() {
        binding.btnLogin.setOnClickListener {
            val id = binding.etId.text.toString()
            val password = binding.etPassword.text.toString()
    
            viewModel.findPasswordById(id, password)
    
            if(isEtIdEmpty() || isEtPasswordEmpty())
                shortToast(getString(R.string.is_empty))
            else {
                if(getIdPasswordExist()) {
                    shortToast(getString(R.string.welcome) + getName() + getString(R.string.sir))
                    setAutoLogin(binding.ibCheckBox.isSelected)
                    startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                }
                else
                    shortToast(getString(R.string.not_match_id_password))
            }
        }
    
        binding.ibCheckBox.setOnClickListener {
            it.isSelected = !it.isSelected
        }
    
        binding.tvSignUp.setOnClickListener {
            requestActivity.launch(Intent(this@SignInActivity, SignUpActivity::class.java))
        }
    }
    ```

<br>

- ##### Home

  - **constraintDimensionRatio(1대1 비율)**

    ```xml
    <ImageView
        android:id="@+id/iv_mint_logo"
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:layout_marginTop="20dp"
        app:layout_constraintDimensionRatio="1:1"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        tools:background="@drawable/ic_person_mint" />
    ```

  - **gif, jpeg 이미지 적용(Glide)**

    ```kotlin
    private fun setImage() {
        Glide.with(this).load(R.raw.gif_move_mint).circleCrop().into(binding.ivMintLogo)
        Glide.with(this).load(R.drawable.img_dabin).circleCrop().into(binding.ivOwner)
    }
    ```

  - **Fade In Animation 적용**

    ```kotlin
    private fun setAnim() {
        binding.ivOwner.startAnimation(setFadeInAnim(R.anim.fade_in_1000))
        binding.tvName.startAnimation(setFadeInAnim(R.anim.fade_in_1000))
        binding.tvGit.startAnimation(setFadeInAnim(R.anim.fade_in_1000))
    
        binding.line.startAnimation(setFadeInAnim(R.anim.fade_in_1500))
    
        binding.tvWelcome.startAnimation(setFadeInAnim((R.anim.fade_in_2000)))
        binding.tvIntroduce.startAnimation(setFadeInAnim((R.anim.fade_in_2000)))
    }
    ```

<br>

- ##### 생명주기 로그 찍기

  - 코드

```kotlin
    class LifeCycleLog(private val lifecycle: Lifecycle, private val className : String) : LifecycleObserver {
        init {
            lifecycle.addObserver(this)
        }
    
        @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
        fun createLog() {
            Log.d(className, lifecycle.currentState.toString())
        }
    }
```

<br>

<table class="tg">
<tbody>
    <tr>
      <td><b>로그1</b></td>
      <td><b>로그2</b></td>
    </tr>
  <tr>
    <td><img src="/WIKI/LifeCycleLog1.png" width="300px"/></td>
    <td><img src="/WIKI/LifeCycleLog2.png"  width="300px"/></td>
	</tr>
</tbody>
</table>


<br>

##### :bulb: 이번 과제를 통해 배운내용

- 프로젝트 기초 세팅 하는 법
- Fade In Animation
- 힐트 아주 조금
- 코루틴 아주 조금
- 패키징, 네이밍 컨벤션

<br>

#### 2. Level 2 👧

- [사용하는 Naming Convention을 정리한 글](https://github.com/8-seconds/WIKI_FOR_8_SECONDS/blob/main/Android/tip/AndroidNamingConvention.md)

- **Nested Scroll View 사용**

  ```xml
  <androidx.core.widget.NestedScrollView
      android:id="@+id/nsv_home"
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
      android:fillViewport="true"
      app:layout_constraintEnd_toEndOf="parent"
      app:layout_constraintStart_toStartOf="parent"
      app:layout_constraintTop_toTopOf="parent">
  ```

<br>

#### 3. Level 3 :woman:

- **Q : ViewBinding 이름의 뜻이 뭘까요?**

  :arrow_right: 뷰 결합의 의미입니다. 뷰와 상호 작용하는 코드를 보다 쉽게 작성할 수 있는 기능입니다. findViewById 메소드를 대체할 수 있습니다.

  :arrow_right: 사용하는 데이터를 뷰에 바인딩하는 방법으로는 **DataBinding** 이 있습니다. 저는 실제로 이번 1주차 과제에 **DataBinding** 을 사용했습니다.

  <br>

- **Q : 객체지향 어느정도 다뤄보셨나요?**

  :arrow_right: 객체란 클래스를 실제 사용할 수 있도록 만든 것으로 메모리 공간을 할당해 놓은 인스턴스라고도 하며 클래스의 동적인 상태를 의미합니다.

  :arrow_right: 안드로이드 또한 클래스(데이터와 메소드 등)를 정의하고 이를 기반한 객체를 생성하여 동작하기 때문에 객체지향 프로그래밍이라고 생각합니다.

  :arrow_right: `Activity`는 앱이 UI를 그리는 창을 제공하고, 사용자의 입력을 처리하는 작업을 합니다. 또한 Context가 인자로도 사용될 수 있습니다.

  <br>

- **Q : 아키텍쳐라고 들어보셨나요?**

  :arrow_right: 제가 아는 아키텍쳐로는 MVVM, MVP, MVC가 있습니다.

  :arrow_right: 저의 이번 과제는 **MVVM** 아키텍쳐로 이루어져있습니다.

  :arrow_right: MVC 아키텍쳐에서 Controller는 사용자의 입력을 처리하고, 데이터 Model과 상호작용하는 작업을 합니다.

  :arrow_right: 서버사이드의 MVC도 M은 모델(데이터 정보), V(화면), C(Controller)아닌가요..? 굳이 따지자면 M쪽은 백엔드에서 관리하고, V는 프론트 엔드에서 관리한다는 말인가요.. 이거는 잘 모르겠네용..

<br>

## :two: Second Week

<table class="tg">
<tbody>
    <tr>
      <td><b>RecyclerView</b></td>
	<td><b>ViewPager + RecyclerAdapter</b></td>
	<td><b>Custom Dialog</b></td>
	<td><b>Custom Dialog2</b></td>
    </tr>
  <tr>
    <td><img src="https://user-images.githubusercontent.com/70698151/116193790-dc571a80-a76a-11eb-9e72-48cbf3950fec.gif" width="200px"/></td>
	<td><img src="https://user-images.githubusercontent.com/70698151/116399564-07716500-a864-11eb-9573-14b6b425a6dc.gif" width="200px"/></td>
	<td><img src="https://user-images.githubusercontent.com/70698151/116403108-24a83280-a868-11eb-9c38-62d6708c01e8.png" width="200px"/></td>
	<td><img src="https://user-images.githubusercontent.com/70698151/116403119-27a32300-a868-11eb-9a62-5522a5c5778f.png" width="200px"/></td>
    </tr>
</tbody>
</table>


<br>

#### 1. Level 1 :baby:

- ##### profile fragment

  - **Recycler Adapter and ViewHolder**

    ```kotlin
    private val _data = mutableListOf<ProfileData>()
    var data : List<ProfileData> = _data
        set(value) {
            _data.clear()
            _data.addAll(value)
            notifyDataSetChanged()
        }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val binding: ItemDetailedProfileBinding = ItemDetailedProfileBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false)
    
        return ProfileViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bind(_data[position])
    }
    
    fun setTodoList(list: List<ProfileData>) {
        data = list.toMutableList()
        notifyDataSetChanged()
    }
    
    override fun getItemCount(): Int = _data.size
    
    class ProfileViewHolder(private val binding: ItemDetailedProfileBinding) :
            RecyclerView.ViewHolder(binding.root) {
    
        fun bind(profileData: ProfileData) {
            binding.profileData = profileData
        }
    }
    ```

 <br>
    

 - **Elipsize and MaxLine 통해 text의 size 조절**

   ```xml
   <TextView
               android:id="@+id/tv_repo_explanation"
               android:layout_width="0dp"
               android:layout_height="wrap_content"
               android:layout_marginStart="3dp"
               android:layout_marginTop="8dp"
               android:layout_marginEnd="60dp"
               android:layout_marginBottom="8dp"
               android:ellipsize="end"
               android:singleLine="true"
               android:text="@{repoData.explanation}"
               android:textColor="@color/ocean_green"
               android:textSize="16sp"
               app:layout_constraintBottom_toBottomOf="parent"
               app:layout_constraintEnd_toStartOf="@+id/iv_type"
               app:layout_constraintStart_toStartOf="@+id/line"
               app:layout_constraintTop_toBottomOf="@+id/line"
               tools:text="@string/content" />
   
   <TextView
           android:id="@+id/tv_title_add"
           android:layout_width="wrap_content"
           android:layout_height="wrap_content"
           android:layout_marginStart="8dp"
           android:layout_marginTop="20dp"
           android:text="@string/title"
           android:textColor="@color/ocean_green"
           android:textSize="16sp"
           android:textStyle="bold"
           app:layout_constraintStart_toStartOf="parent"
           app:layout_constraintTop_toBottomOf="@+id/tv_title" />
   ```

   <br>

 - **ViewPager2 통해서 fragment 띄워주기(Level 1-3 버튼대신 뷰페이저 이용)**


```kotlin
    class MainViewPagerAdapter(activity: MainActivity) : FragmentStateAdapter(activity) {
    	var fragmentList = listOf<Fragment>()

    	override fun getItemCount(): Int {
        	return fragmentList.count()
    	}

    	override fun createFragment(position: Int): Fragment = fragmentList[position]}
```

<br>

#### 2. Level 2 👧

- **Grid Layout**

```xml
    <androidx.recyclerview.widget.RecyclerView
            android:id="@+id/rv_profile_list"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:layoutManager="androidx.recyclerview.widget.GridLayoutManager"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:spanCount="2"
            tools:itemCount="4"
            tools:listitem="@layout/item_detailed_profile" />
```

<br>

- **ItemTouchHealper 이용해서 swipe로 삭제 기능 구현**

```kotlin
    override fun onItemSwiped(position: Int) {
        dataSwipeListener?.invoke(_data[position])
        _data.removeAt(position)
        notifyItemRemoved(position)
    }
```

<br>

## :three: Third Week

<table class="tg">
<tbody>
    <tr>
      <td><b>Sign Up, Sign In</b></td>
      <td><b>GitHub Follower</b></td>
    </tr>
  <tr>
    <td><img src="https://user-images.githubusercontent.com/70698151/118401692-94f4e780-b6a1-11eb-9a4d-65d3da08587a.gif" width="300px"/></td>
    <td><img src="https://user-images.githubusercontent.com/70698151/118401699-a0481300-b6a1-11eb-84da-dca7bf129d02.gif"  width="300px"/></td>
	</tr>
</tbody>
</table>

<br>

#### 1. Level 1 :baby:

- ##### PostMan 테스트

  <table class="tg">
  <tbody>
      <tr>
        <td><b>Sign Up</b></td>
        <td><b>Sign In</b></td>
      </tr>
    <tr>
      <td><img src="https://user-images.githubusercontent.com/70698151/118401499-e8b30100-b6a0-11eb-8518-e24844083d13.png" width="600px"/></td>
<td><img src="https://user-images.githubusercontent.com/70698151/118401490-e3ee4d00-b6a0-11eb-925b-2615ee44e1e7.png" width="600px"/>
	    </td>
  	</tr>
  </tbody>
  </table>

<br>

- ##### Retrofit Interface(hilt로 싱글톤 객체 생성)

  ```kotlin
  @UserRetrofit
  @Provides
  @Singleton
  fun provideUserRetrofitObject(): Retrofit {
      return Retrofit.Builder().baseUrl(USER_URL).client(getOkHttpClient())
              .addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(CoroutineCallAdapterFactory()).build()
  }
  
  @GitRetrofit
  @Provides
  @Singleton
  fun provideGitRetrofitObject(): Retrofit {
      return Retrofit.Builder().baseUrl(GIT_URL).client(getOkHttpClient())
          .addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(CoroutineCallAdapterFactory()).build()
  }
  ```

- ##### UserService

  ```kotlin
  interface UserService {
      //POST 회원가입
      @POST("/login/signup")
      suspend fun postSignUp(
              @Body body: ReqSignUp
      ): ResSignUp
  
      //POST 로그인
      @POST("/login/signin")
      suspend fun postSignIn(
              @Body body: ReqSignIn
      ): ResSignIn
  }
  ```

- ##### Sign In, Sign Up

```kotlin
fun postSignUp(reqSignUp: ReqSignUp) = viewModelScope.launch {
    runCatching { userRepo.postSignUp(reqSignUp) }
        .onSuccess { _signUpEvent.postValue(Event(true)) }
        .onFailure {
            _signUpEvent.postValue(Event(false))
            it.printStackTrace()
        }
}

fun postSignIn(reqSignIn: ReqSignIn) = viewModelScope.launch {
    runCatching { userRepo.postSignIn(reqSignIn) }
        .onSuccess {
            _signInEvent.postValue(Event(true))
            setName(it.data.user_nickname)
        }
        .onFailure {
            _signInEvent.postValue(Event(false))
            it.printStackTrace()
        }
}
```

```kotlin
viewModel.signInEvent.observe(this, EventObserver{
    when(it) {
        true -> {
            shortToast(getString(R.string.welcome) + getName() + getString(R.string.sir))
            setAutoLogin(binding.ibCheckBox.isSelected)
            startMainActivity()
        }
        else -> shortToast(getString(R.string.not_match_id_password))
    }
})
```

```kotlin
viewModel.signUpEvent.observe(this, EventObserver{
    when(it) {
        true -> {
            shortToast(getString(R.string.sign_up_done))
            finish()
        }
        else -> shortToast(getString(R.string.sign_up_fail))
    }
})
```

<br>

##### :bulb: 이번 과제를 통해 배운내용

- suspend 활용법(콜백지옥이 안되서 매우 좋음 ㅎㅎ)

<br>

#### 2. Level 2 👧

- ##### GitService

  ```kotlin
  interface GitService {
      @GET("/users/{username}/followers")
      suspend fun geFollower(
          @Path("username") username:String
      ): List<ResFollower>
  }
  ```

- ##### 뷰모델에서 통신 성공 실패 처리

  ```kotlin
  fun getFollower() = viewModelScope.launch {
      runCatching { gitRepo.getFollower("mdb1217") }
          .onSuccess {
              _followerList.postValue(it)
          }
          .onFailure {
              it.printStackTrace()
          }
  }
  ```

<br>

## :four: Seventh Week

<table class="tg">
<tbody>
    <tr>
      <td><b>Auto Login</b></td>
      <td><b>Logout</b></td>
    </tr>
  <tr>
    <td><img src="/WIKI/auto_login.gif" width="300px"/></td>
    <td><img src="/WIKI/logout.gif"  width="300px"/></td>
	</tr>
</tbody>
</table>


<br>

#### 1. Level 1 :baby:

- ##### 자동로그인 로직

  - **Auto Login**

    `SharedPreference`에서 AutoLogin 여부를 알려주는 boolean 변수를 정의해, 그 값이 **true**이면 자동로그인 처리

  ```kotlin
  private fun autoLogin() {
          if(getAutoLogin()) {
              shortToast(getString(R.string.auto_login_done))
              startMainActivity()
          }
      }
  ```

  - ##### Logout

    `SharedPreference`에 저장된 모든 값들을 **clear**해줘서, logout구현(Auto Login 해제)

  ```kotlin
  binding.clLogout.setOnClickListener {
              clearStorage()
              startSignIn()
              finishAffinity()
          }
  ```

  - ##### SharedPreference(+초기화 코드)

```kotlin
object SOPTSharedPreference {
    private const val AUTO_LOGIN = "AUTO_LOGIN"
    private const val USER_NAME = "USER_NAME"

    lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    fun getAutoLogin(): Boolean = preferences.getBoolean(AUTO_LOGIN, false)

    fun setAutoLogin(value: Boolean) {
        preferences.edit{putBoolean(AUTO_LOGIN, value)}
    }

    fun getName(): String? = preferences.getString(USER_NAME, "")

    fun setName(value: String) {
        preferences.edit{putString(USER_NAME, value)}
    }

    fun clearStorage() {
        preferences.edit { clear() }
    }
}
```

```kotlin
@HiltAndroidApp
class ApplicationController : Application() {
    companion object {
        lateinit var INSTANCE: ApplicationController
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        SOPTSharedPreference.init(applicationContext)
    }
}
```

 <br>

##### :bulb: 이번 과제를 통해 배운내용

- 툴바 활용법
- SharedPreference 활용법
- 확장함수 활용법

<br>

#### 2. Level 2 👧

- ##### Toast Extension

  ```kotlin
  //activity용
  fun Context.shortToast(message: String) {
      Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
  }
  
  //fragment용
  fun shortToastRequireContext(context: Context, message: String) {
      Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
  }
  ```

- ##### Dialog Extension

  ```kotlin
  //Dialog 생성
  fun makeDialog(context : Context) = Dialog(context, R.style.DialogCustom)
  
  //Dialog 스타일 적용
  fun setDialog(dialog : Dialog, view : View) {
      dialog.apply {
          requestWindowFeature(Window.FEATURE_NO_TITLE)
          setCancelable(false)
          setContentView(view)
  
          with(window?.attributes) {
              this?.width = WindowManager.LayoutParams.MATCH_PARENT
              this?.height = WindowManager.LayoutParams.WRAP_CONTENT
              this?.verticalWeight = 1F
          }
      }
  }
  ```

<br>

## :five: Package Structure

```
📦 org.sopt
 ┣ 📂 data
 ┃ ┗ 📂 local
 ┃ ┃ ┣ 📂 dao
 ┃ ┃ ┗ 📂 database
 ┃ ┃ ┗ 📂 entity
 ┃ ┗ 📂 repository
 ┣ 📂 di
 ┣ 📂 ui
 ┃ ┗ 📂 base
 ┃ ┗ 📂 view
 ┃ ┃ ┗ 📂 user
 ┃ ┗ 📂 viewmodel
 ┗ 📂 util
```
