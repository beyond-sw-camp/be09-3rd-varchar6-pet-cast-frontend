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
            placeholder="Enter your ID" 
            required 
          />
        </div>
        
        <div class="form-group">
          <label for="password">비밀번호</label>
          <input 
            type="password" 
            id="password" 
            v-model="password" 
            placeholder="Enter your password" 
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
      <div>|</div>
      <RouterLink to="/register"> 비밀번호 수정 </RouterLink>
      <div>|</div>
      <RouterLink to="/register"> 회원 가입 </RouterLink>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const loginId = ref('');
const password = ref('');

const handleLogin = () => {
  if (loginId.value === 'user' && password.value === 'password') {
    alert('Login Successful!');
    router.push('/');
  } else {
    alert('Invalid login Id or password');
  }
};
</script>

<style scoped>
.login-container {
  width: 650px;
	height: 500px;
  margin: 30px auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 26px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  background-color: #CCEFFF;
	opacity: 68%;
}

h1 {
  text-align: center;
  margin-bottom: 60px;
}

.form {
    text-align: center;
}

.form-group {
  margin-bottom: 15px;
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
  margin-top: 100px;
}

button {
  width: 40%;
  padding: 10px;
  background-color: #4CAF50;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
	justify-content: center;
}

button:hover {
  background-color: #45a049;
}

register-link {
  margin-top: 15px;
  text-align: center;
  justify-content: space-evenly;
}

.register-link a {
  color: #4CAF50;
  text-decoration: none;
}
</style>
