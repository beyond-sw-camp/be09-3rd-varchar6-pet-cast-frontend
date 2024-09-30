<template>
  <div class="home">
    <div class="top-section">
      <!-- 상단 카테고리 -->
      <div class="categories-container">
        <div class="categories">
          <div v-for="category in mainData.categories" :key="category.id" class="category">
            <img :src="category.icon" :alt="category.name">
            <span>{{ category.name }}</span>
          </div>
        </div>
        
        <!-- 새로운 배너 섹션 -->
        <div class="banner-container" v-if="mainData.banners && mainData.banners.length">
          <div class="banner-slider">
            <div v-for="banner in mainData.banners" :key="banner.id" class="banner-slide">
              <img :src="banner.image" :alt="banner.title">
              <div class="banner-content">
                <h2>{{ banner.title }}</h2>
                <p>{{ banner.description }}</p>
              </div>
            </div>
          </div>
        </div>
        <div v-else>배너 데이터가 없습니다.</div>
      </div>

      <!-- 우측 프로필 및 메뉴 수정 -->
      <div class="profile-menu">
        <div class="profile">
          <img :src="mainData.user.profileImage" :alt="mainData.user.nickname">
          <span>{{ mainData.user.nickname }}</span>
        </div>
        <div class="menu-buttons">
          <button @click="goToEstimates">받은 <br> 견적서</button>
          <button @click="goToMyMeetings">내 모임</button>
        </div>
      </div>
    </div>

    <!-- 하단 섹션 -->
    <div class="bottom-section">
      <div class="notice">
        <h3>공지사항</h3>
        <ul>
          <li v-for="notice in mainData.notices" :key="notice.id">{{ notice.title }}</li>
        </ul>
      </div>
      <div class="recent-reviews">
        <h3>최근 후기</h3>
        <ul>
          <li v-for="review in mainData.recentReviews" :key="review.id">{{ review.content }}</li>
        </ul>
      </div>
      <div class="popular-meetings">
        <h3>인기 모임</h3>
        <ul>
          <li v-for="meeting in mainData.popularMeetings" :key="meeting.id">{{ meeting.title }}</li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const mainData = ref({
  categories: [],
  bannerGroups: [],
  user: {},
  notices: [],
  recentReviews: [],
  popularMeetings: [],
  banners: []
});

onMounted(async () => {
  try {
    const response = await fetch('http://localhost:8888/main');
    if (!response.ok) {
      throw new Error('네트워크 응답이 올바르지 않습니다');
    }
    const data = await response.json();
    mainData.value = { ...mainData.value, ...data };
    console.log('전체 데이터:', mainData.value);
    console.log('배너 데이터:', mainData.value.banners);
  } catch (error) {
    console.error('데이터를 가져오는 중 오류가 발생했습니다:', error);
  }
});

const goToEstimates = () => {
  router.push('/estimates');
};

const goToMyMeetings = () => {
  router.push('/my-meetings');
};
</script>

<style scoped>
.home {
  display: flex;
  flex-direction: column;
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.top-section {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
}

.categories-container {
  width: calc(100% - 220px); /* 프로필 메뉴 너비만큼 공간 확보 */
}

.categories {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  gap: 40px;
}

.category {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  width: 100px;
}

.category img {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  object-fit: cover;
  margin-bottom: 15px;
}

.profile-menu {
  width: 200px;
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #f0f0f0;
  padding: 15px;
  border-radius: 10px;
}

.profile {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 15px;
}

.profile img {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  object-fit: cover;
  margin-bottom: 10px;
}

.profile span {
  font-weight: bold;
  margin-bottom: 10px;
}

.menu-buttons {
  display: flex;
  justify-content: space-between;
  width: 100%;
}

.menu-buttons button {
  padding: 8px 12px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 12px;
  flex: 1;
  margin: 0 5px;
}

.menu-buttons button:first-child {
  margin-left: 0;
}

.menu-buttons button:last-child {
  margin-right: 0;
}

.bottom-section {
  display: flex;
  justify-content: space-between;
}

.bottom-section > div {
  flex: 1;
  margin: 0 10px;
  background-color: #f0f0f0;
  padding: 15px;
  border-radius: 10px;
}

.bottom-section h3 {
  margin-bottom: 10px;
}

.bottom-section ul {
  list-style-type: none;
  padding: 0;
}

.bottom-section li {
  margin-bottom: 5px;
}

.banner-container {
  width: 100%;
  margin-top: 20px;
  overflow: hidden;
  height: 300px; /* 배너의 높이 설정 */
}

.banner-slider {
  display: flex;
  transition: transform 0.5s ease;
  height: 100%;
}

.banner-slide {
  flex: 0 0 100%;
  position: relative;
  height: 100%;
}

.banner-slide img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.banner-content {
  position: absolute;
  bottom: 20px;
  left: 20px;
  color: white;
  text-shadow: 1px 1px 2px rgba(0,0,0,0.7);
}

.banner-content h2 {
  font-size: 24px;
  margin-bottom: 10px;
}

.banner-content p {
  font-size: 16px;
}
</style>