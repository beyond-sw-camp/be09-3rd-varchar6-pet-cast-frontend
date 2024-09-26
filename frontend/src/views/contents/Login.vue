<template>
  <div class="login-container">
    <h1>로그인</h1>
    <div class="login-form-container">
      <form @submit.prevent="handleLogin">
        <div class="form-group">
          <label for="id">아이디</label>
          <input 
            type="text" 
            id="id" 
            v-model="loginId" 
            placeholder="아이디를 입력해주세요" 
            required
          />
        </div>
        
        <div class="form-group">
          <label for="password">비밀번호</label>
          <input 
            type="password" 
            id="password" 
            v-model="password" 
            placeholder="비밀번호를 입력해주세요" 
            required 
          />
        </div>

        <div class="button-container">
          <button type="submit">로그인</button>
        </div>
      </form>
    </div>
    <div class="register-link">
      <RouterLink to="/register"> 아이디 찾기 </RouterLink>
      <span class="divider">|</span>
      <RouterLink to="/register"> 비밀번호 수정 </RouterLink>
      <span class="divider">|</span>
      <RouterLink to="/register"> 회원 가입 </RouterLink>
    </div>
    <div class="oauth-login-container">
      <div class="oauth-button" @click="handleKakaoLogin">
        <img src="../../assets/icon/kakao-icon.png" alt="Kakao" class="oauth-icon" />
      </div>
      
      <div class="oauth-button" @click="handleNaverLogin">
        <img src="../../assets/icon/naver-icon.png" alt="Naver" class="oauth-icon" />
      </div>
      
      <div class="oauth-button" @click="handleGoogleLogin">
        <img src="../../assets/icon/google-icon.png" alt="Google" class="oauth-icon" />
      </div>
    </div>

    <Modal :isVisible="showModal" @update:isVisible="showModal = $event" title="로그인 오류" :message="errorMessage" />
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';

const router = useRouter();
const loginId = ref('');
const password = ref('');
const showModal = ref(false);
const errorMessage = ref('');
const handleKakaoLogin = async() => {
  // const response = await axios.get('http://localhost:8081/oauth2/authorization/kakao?redirect_uri=http://localhost:5173&mode=login');
  window.location.href = `http://localhost:8081/oauth2/authorization/kakao?redirect_uri=http://localhost:5173&mode=login`;
  alert('Kakao 로그인 버튼 클릭됨');
  // 실제 로그인 로직
};

const handleNaverLogin = async() => {
  window.location.href = `http://localhost:8081/oauth2/authorization/naver?redirect_uri=http://localhost:5173&mode=login`;
  alert('Naver 로그인 버튼 클릭됨');
  // 실제 로그인 로직
};

const handleGoogleLogin = async() => {
  window.location.href = `http://localhost:8081/oauth2/authorization/google?redirect_uri=http://localhost:5173&mode=login`;
  alert('Google 로그인 버튼 클릭됨');
  // 실제 로그인 로직
};

const handleLogin = async() => {
  try {
    const response = await axios.post(`http://localhost:8081/login`, {
      "loginId": loginId.value,
      "password": password.value
    });
    console.log("response123124: ", response);
    const accessToken = response.headers['access-token'];
    const refreshToken = response.headers['refresh-token'];
    if (accessToken === undefined || refreshToken === undefined) {
      console.log("login failed");
      throw new Error("에러발생! ");
    }
    localStorage.setItem("accessToken", accessToken);
    localStorage.setItem("refreshToken", refreshToken);
    router.push('/'); // 로그인 성공 후 리디렉션
  } catch (error) {
    console.error("msg: ", error);
    showModal.value = true; // 모달 열기
    errorMessage.value = "아이디 또는 비밀번호가 잘못되었습니다."; // 오류 메시지 설정
  }
};
</script>

<style scoped>
.login-container {
  width: 500px;
  height: 550px;
  margin: 30px auto;
  padding: 30px;
  border: 1px solid #ccc;
  border-radius: 26px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  background-color: #CCEFFF;
  opacity: 68%;
}

h1 {
  text-align: center;
  margin-bottom: 40px;
}

.form {
    text-align: center;
}

.form-group {
  margin-bottom: 20px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

.form-group input {
  width: 95%;
  height: 30px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 18px;
}

.button-container {
  display: flex;
  justify-content: center;
  margin-top: 30px;
}

button {
  width: 20%;
  padding: 10px;
  background-color: white;
  color: #49D5FF;
  border-color: #49D5FF;
  border-radius: 14px;
  cursor: pointer;
  font-size: 20px;
  justify-content: center;
}

button:hover {
  background-color: #9fe0ff;
  color: white;
}

.register-link {
  display: flex;
  justify-content: center;
  text-align: center;
  margin-top: 15px;
  padding: 10px;
}

.register-link a {
  text-decoration: none;
  color: black;
}

.divider {
  margin: 0 10px;
  color: #999;
}

.oauth-login-container {
  display: flex;
  justify-content: center;
}

img {
  width: 100%;
  height: auto;
}

.oauth-button {
  width: 75px; /* 버튼 크기 조절 */
  height: 75px; /* 버튼 크기 조절 */
  margin-inline: 15px;
}

.oauth-button:hover {
  cursor: pointer;
}

</style>
