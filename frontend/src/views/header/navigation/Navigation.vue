<template>
  <nav class="navigation">
    <div class="nav-left">
      <!-- 프로젝트 대표이미지 -->
      <RouterLink to="/">
        <img src="../../../assets/image/profile/logo.png" alt="Project Logo" class="project-logo"/>
      </RouterLink>

      <!-- 메뉴 리스트 -->
      <ul class="menu-list">
        <li><RouterLink to="/">Home</RouterLink></li>
        <li><RouterLink to="/about">About</RouterLink></li>>
      </ul>
    </div>

    <div class="nav-right">
      <!-- 로그인 상태에 따라 다르게 표시 -->
      <template v-if="isLoggedIn">
        <!-- 로그인된 사용자 정보 및 아이콘 -->
        <span class="user-id">{{ userId }}님 안녕하세요</span>
        <RouterLink to="/me">
          <img src="@/assets/icon/navigation/profile_48x48.png" alt="Profile" class="profile-image" />
        </RouterLink>
        <img src="@/assets/icon/navigation/alarm.png" alt="alarm" />
        <img src="@/assets/icon/navigation/setting.png" alt="settings" />
        <button @click="handleLogout">Logout</button>
      </template>
      <template v-else>
        <!-- 로그인되지 않은 경우 로그인 버튼 표시 -->
        <RouterLink to="/login">
          <button class="login-button">Login</button>
        </RouterLink>
      </template>
    </div>
    <Modal :isVisible="isModalVisible" :title="modalTitle" :message="modalMessage" @update:isVisible="handleModalClose" />
  </nav>
</template>

<script setup>
import { ref, inject } from 'vue';
import { RouterLink, useRouter } from 'vue-router';
import Modal from '../../../components/Modal.vue';

const router = useRouter();
const modalTitle = ref('');
const modalMessage = ref('');
const isModalVisible = ref(false);
const isLoggedIn = inject('isLoggedIn');
const setIsLoggedIn = inject('setIsLoggedIn');
const userId = inject('userId');

// 로그아웃 처리
const handleLogout = () => {
  localStorage.removeItem('accessToken');
  localStorage.removeItem('refreshToken');
  localStorage.removeItem('userId'); // 아이디 정보 제거

  setIsLoggedIn(false);
  userId.value = '';

  modalTitle.value = '로그아웃 완료';
  modalMessage.value = '성공적으로 로그아웃 되었습니다.';
  isModalVisible.value = true;
};

const handleModalClose = () => {
  isModalVisible.value = false;
  if (modalTitle.value === '로그아웃 완료') {
    router.push('/login'); // 로그아웃 후 로그인 페이지로 이동
  }
};

</script>

<style scoped>
.navigation {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 20px;
  background-color: #f8f9fa;
  border-bottom: 1px solid #e0e0e0;
}

.nav-left {
  display: flex;
  align-items: center;
}

.project-logo {
  height: 40px;
  margin-right: 20px;
}

.menu-list {
  list-style: none;
  display: flex;
  gap: 15px;
}

.menu-list li {
  display: inline;
}

.menu-list li a {
  text-decoration: none;
  color: #333;
  padding: 5px 10px;
}

.menu-list li a.router-link-exact-active {
  font-weight: bold;
  color: #007bff;
}

.nav-right {
  display: flex;
  align-items: center;
  gap: 15px;
}

.user-id {
  font-weight: bold;
}

.profile-image {
  width: 30px;
  height: 30px;
  border-radius: 50%;
}

.icon {
  cursor: pointer;
  font-size: 20px;
}
</style>
