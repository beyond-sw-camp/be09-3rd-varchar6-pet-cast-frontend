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
        <li><RouterLink to="/about">About</RouterLink></li>
        <!-- 다른 메뉴 항목 추가 가능 -->
      </ul>
    </div>

    <div class="nav-right">
      <!-- 사용자 아이디 및 프로필 이미지 -->
      <span class="user-id">User123</span>
      <img src="../../../assets/icon/navigation/profile_48x48.png" alt="Profile" class="profile-image" />

      <!-- 아이콘들 (예시: 알림, 설정 등) -->
      <img src="../../../assets/icon/navigation/alarm.png" alt="alarm"/>
      <img src="../../../assets/icon/navigation/setting.png" alt="settings"/>
    </div>
  </nav>
</template>

<script setup>
import { RouterLink } from 'vue-router';
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
