# BE SOPT 28th Android Assignment

[![WrittenBy](https://img.shields.io/badge/Written%20by-mdb1217-white.svg)](https://github.com/mdb1217)

:calendar: **Last Edited : `2021. 04. 25`**

<br>

# :computer: Contents

[:zero: Specification](#zero-specification)<br>
[:one: First Week](#one-first-week)<br>
[:two: Second Week](#two-second-week)<br>
[:three: Package Structure](#three-package-structure)<br>

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
<td>DataBinding, LiveData, ViewModel, Lifecycle, Room</td>
</tr>
<tr>
  <tr>
    <td><b>Asynchronous Processing</b></td>
<td>Coroutine</td>
</tr>
    <td><b>Dependency Injection</b></td>
<td>Hilt</td>
</tr>
<tr>
    <td><b>Third Party Library</b></td>
<td>Glide</td>
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

#### 2. Level 2 👧​

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
    </tr>
  <tr>
    <td><img src="https://user-images.githubusercontent.com/70698151/115997831-93df1600-a61f-11eb-879e-6864f3acb8b1.png" width="200px"/></td>
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

#### 2. Level 2 :baby:

**onItemTouchHealper 이용해서 swipe 와 move 구현**
```kotlin
    override fun onItemMoved(from: Int, to: Int) {
        if (from == to) {
            return
        }

        val fromItem = data.removeAt(from)
        data.add(to, fromItem)
        notifyItemMoved(from, to)
    }

    override fun onItemSwiped(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }
    ```

## :three: Package Structure

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
