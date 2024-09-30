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
        <!-- <li><RouterLink to="/about">About</RouterLink></li> -->
        <li><RouterLink to="/request">요청</RouterLink></li>
        <li><RouterLink to="/biz-list">업체 찾기</RouterLink></li>
        <li><RouterLink to="/gatherlist">모임</RouterLink></li>
        <li><RouterLink to="/api/v1/notice">공지</RouterLink></li>
        <!-- 다른 메뉴 항목 추가 가능 -->

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

        <div class="dropdown">
          <img src="@/assets/icon/navigation/setting.png" alt="settings" @click="toggleDropdown" />
          <div v-if="isDropdownOpen" class="dropdown-menu">
            <RouterLink to="/me" @click="closeDropdown">마이페이지</RouterLink>
            <RouterLink v-if="isCompany" to="/biz-mypage" @click="closeDropdown">업체 선택</RouterLink>
          </div>
        </div>
        
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
import { ref, inject, onMounted } from 'vue';
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
  localStorage.removeItem('Roles');
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

const isDropdownOpen = ref(false);
const isCompany = ref(false);

const toggleDropdown = () => {
  isDropdownOpen.value = !isDropdownOpen.value;
};

const closeDropdown = () => {
  isDropdownOpen.value = false;
};

const checkCompanyRole = () => {
  const rolesString = localStorage.getItem('Roles');
  if (rolesString) {
    const roles = rolesString.split(',');
    isCompany.value = roles.includes('COMPANY');
  } else {
    isCompany.value = false;
  }
};

onMounted(() => {
  checkCompanyRole();
});

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

.dropdown {
  position: relative;
  display: inline-block;
}

.dropdown-menu {
  position: absolute;
  right: 0;
  background-color: #f9f9f9;
  min-width: 120px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
  border-radius: 4px;
}

.dropdown-menu a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

.dropdown-menu a:hover {
  background-color: #f1f1f1;
}
</style>
