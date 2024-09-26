<template>
  <div class="biz-detail" v-if="business && business.length">
    <!-- 비즈니스 헤더 -->
    <div class="biz-header">
      <!-- <img :src="business[0].imageUrl" alt="비즈니스 이미지"> -->
      <img :src="business[0].imageUrl" alt="이미지"  height="200px" width="aspect-ratio:1">
      <div class="biz-info">
        <div class="biz-header">
          <h1>{{ business[0].name }}</h1>
          <p class="location">{{ business[0].location }}</p>
        </div>
        <p class="description">{{ business[0].description }}</p>
        <div class="biz-details">
          <p class="registration">사업자 등록증: {{ business[0].registrationStatus }}</p>
          <p class="categories">
            <span v-for="(category, index) in business[0].categories" :key="index">
              {{ category }}{{ index < business[0].categories.length - 1 ? ', ' : '' }}
            </span>
          </p>
        </div>
      </div>
      <button v-if="isLoggedIn" class="delete-btn" @click="deleteAccount">계정 삭제</button>
    </div>

    <!-- 비즈니스 통계 -->
    <div class="biz-stats">
      <div class="stat-item">
        <h3>리뷰</h3>
        <div class="review-summary">
          <span class="rating">★ {{ business[0].rating }}</span>
          <span class="review-count">({{ business[0].reviewCount }}개)</span>
        </div>
        
      </div>
      <div class="stat-item">
        <h3>경력</h3>
        <p>{{ business[0].experience }}년</p>
      </div>
      <div class="stat-item">
        <h3>직원 수</h3>
        <p>{{ business[0].employeeCount }}명</p>
      </div>
      <div class="stat-item">
        <h3>연락 가능 시간</h3>
        <p>{{ business[0].contactHours }}</p>
      </div>
    </div>

    <!-- 최근 진행한 이벤트 -->
    <div class="recent-events">
      <h2>
        업체가 진행한 이벤트
        <span class="more-link" @click="goToEvents">더보기 ></span>
      </h2>
      <div v-for="event in business[0].events.slice(0, 2)" :key="event.id" class="event-item">
        <img :src="event.imageUrl" :alt="event.title">
        <div class="event-info">
          <div class="event-header">
            <h3 class="event-title">{{ event.title }}</h3>
            <div class="event-meta">
              <span class="event-status">상태: {{ event.status }}</span>
              <span class="event-category">
                {{ event.category.join(', ') }}
              </span>
            </div>
          </div>
          <p>{{ event.description }}</p>
        </div>
      </div>
    </div>

    <!-- 리뷰 -->
    <div class="reviews">
      <h2>
        리뷰
        <span class="more-link" @click="goToReviews">더보기 ></span>
      </h2>
      <div v-for="review in business[0].reviews.slice(0, 2)" :key="review.id" class="review-item">
        <div class="review-header">
          <span class="rating">★ {{ review.rating }}</span>
          <span class="review-title">{{ review.title }}</span>
        </div>
        <p>{{ review.description }}</p>
      </div>
    </div>

    <!-- Q&A -->
    <div class="qa-section">
      <h2>
        Q&A
        <span class="more-link" @click="goToQA">더보기 ></span>
      </h2>
      <div v-for="qa in business[0].qas.slice(0, 3)" :key="qa.id" class="qa-item">
        <span :class="['status', qa.status]">{{ qa.status }}</span>
        <p class="qa-title">{{ qa.title }}</p>
        <p class="qa-date">{{ qa.date }}</p>
      </div>
    </div>
  </div>

  <div v-else>
    <p>로딩 중입니다...</p> <!-- 데이터가 로드되기 전 표시할 내용 -->
  </div>
</template>


<script setup>
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const currentRoute = useRoute();
const router = useRouter();
const business = ref(null);
const isLoggedIn = ref(false); // 로그인 상태를 저장하는 변수

// 비즈니스 세부 정보를 가져오는 함수
const fetchBusinessDetails = async () => {
try {
  console.log('fetchBusinessDetails 실행');
  const response = await fetch('http://localhost:8080/businesses'); // 비즈니스 ID를 지정하여 데이터 가져오기
  if (!response.ok) {
    throw new Error('네트워크 응답이 올바르지 않습니다.');
  }
  business.value = await response.json();
} catch (error) {
  console.error("데이터를 가져오는 중 오류 발생:", error);
}
};

const deleteAccount = () => {
  if (confirm('정말로 계정을 삭제하시겠습니까?')) {
        //   여기에 계정 삭제 API 호출 로직 구현
        alert('계정이 삭제되었습니다.');
        router.push({ name: 'Home' });
    }
};

const checkLoginStatus = () => {
  const rolesString = localStorage.getItem('Roles');
  if (rolesString) {
    const roles = JSON.parse(rolesString);
    isLoggedIn.value = roles.includes('COMPANY');
  } else {
    isLoggedIn.value = false;
  }
};

const goToEvents = () => {
router.push({ name: 'BusinessEvents', params: { id: business.value[0].id } });
};

const goToReviews = () => {
router.push({ name: 'BusinessReviews', params: { id: business.value[0].id } });
};

const goToQA = () => {
// router.push({ name: 'BusinessQA', params: { id: business.value[0].id } });
};

onMounted(() => {
fetchBusinessDetails();
checkLoginStatus();
});
</script>



<style scoped>
.biz-detail {
max-width: 800px;
margin: 0 auto;
padding: 20px;
font-family: Arial, sans-serif;
}

.biz-header {
display: flex;
align-items: center;
margin-bottom: 20px;
}

.biz-header img {
width: 100px;
height: 100px;
border-radius: 50%;
margin-right: 20px;
}

.edit-btn {
margin-left: auto;
padding: 5px 10px;
background-color: #f0f0f0;
border: none;
border-radius: 5px;
}

.biz-stats {
display: flex;
justify-content: space-between;
background-color: #f9f9f9;
padding: 15px;
border-radius: 8px;
margin-bottom: 20px;
}

.stat-item {
text-align: center;
}

.recent-events, .reviews, .qa-section {
margin-bottom: 20px;
}

.more-link {
cursor: pointer;
color: #0066cc;
text-decoration: underline;
font-size: 0.8em;
}

.event-item, .review-item, .qa-item {
background-color: #e6f3ff;
padding: 10px;
margin-bottom: 10px;
border-radius: 5px;
}

.event-item img {
width: 100px;
height: 100px;
object-fit: cover;
margin-right: 10px;
}

.review-header {
display: flex;
align-items: center;
margin-bottom: 5px;
}

.status {
display: inline-block;
padding: 2px 5px;
border-radius: 3px;
font-size: 0.8em;
margin-bottom: 5px;
}

.status.답변완료 {
background-color: #e6f3ff;
color: #0066cc;
}

.status.미답변 {
background-color: #ffe6e6;
color: #cc0000;
}

.qa-title {
margin: 5px 0;
}

.qa-date {
font-size: 0.8em;
color: #888;
}

.delete-btn {
margin-left: auto;
padding: 5px 10px;
background-color: #ff4444;
color: white;
border: none;
border-radius: 5px;
cursor: pointer;
}

.delete-btn:hover {
background-color: #cc0000;
}

.event-header {
display: flex;
justify-content: space-between;
align-items: center;
margin-bottom: 10px;
}

.event-title {
margin: 0;
font-size: 1.2em;
}

.event-meta {
display: flex;
align-items: center;
}

.event-status, .event-category {
padding: 2px 5px;
border-radius: 3px;
font-size: 0.8em;
margin-left: 5px;
}

.biz-info {
margin-bottom: 20px;
}

.biz-header h1 {
margin: 0;
margin-right: 15px;
}

.location {
font-size: 0.9em;
color: #666;
}

.description {
margin-bottom: 10px;
}

.biz-details {
display: flex;
font-size: 0.9em;
}

.registration {
margin-right: 15px;
}

.categories span {
background-color: #f0f0f0;
padding: 2px 5px;
border-radius: 3px;
margin-right: 5px;
}

.review-summary {
  display: flex;
  align-items: center;
}

.rating {
  color: #ffd700;
  font-weight: bold;
  margin-right: 5px;
}

.review-count {
  color: #666;
  font-size: 0.9em;
}

.recent-events h2,
.reviews h2,
.qa-section h2 {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.more-link {
  font-size: 0.8em;
  color: #0066cc;
  cursor: pointer;
}

.event-item {
  display: flex;
  background-color: #e6f3ff;
  padding: 10px;
  border-radius: 5px;
  overflow: hidden;
  position: relative;
  margin-bottom: 20px;
}

.event-item img {
  width: 100px;
  height: 100px;
  object-fit: cover;
  margin-right: 10px;
}

.event-info {
  flex-grow: 1;
}

.event-header {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  margin-bottom: 5px;
}

.event-title {
  margin: 0;
  font-size: 1.2em;
}

.event-meta {
  display: flex;
  align-items: center;
  margin-bottom: 5px;
}

.event-status {
  font-size: 0.8em;
  color: #666;
  margin-right: 10px;
}

.event-category {
  font-size: 0.9em;
  color: #0066cc;
  background-color: #e6f3ff;
  padding: 2px 5px;
  border-radius: 3px;
}

.edit-btn-container {
  display: flex;
  justify-content: flex-end;
}

.edit-btn {
  padding: 5px 10px;
  background-color: #f0f0f0;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 0.9em;
}

.edit-btn:hover {
  background-color: #e0e0e0;
}

</style>
