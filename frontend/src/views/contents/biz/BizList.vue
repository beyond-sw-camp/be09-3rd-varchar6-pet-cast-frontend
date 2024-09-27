<template>
  <div class="biz-list-container">
    <div v-if="isLoading" class="loading-message">
      페이지가 로딩중입니다...
    </div>
    <template v-else>
      <div class="search-section">
        <input type="text" v-model="searchQuery" placeholder="어떤 업체를 찾고 계세요?">
        <div class="search-options">
          <select v-model="searchType">
            <option value="title">제목</option>
            <option value="content">내용</option>
            <option value="both">제목+내용</option>
          </select>
          <button @click="search" class="search-button">검색</button>
        </div>
      </div>

      <div class="content-wrapper">
        <div class="filter-section">
          <h3>필터</h3>
          <div v-for="(filter, index) in filters" :key="index" class="filter-item">
            <input type="checkbox" :id="filter.id" v-model="filter.checked">
            <label :for="filter.id">{{ filter.label }}</label>
          </div>
          <button @click="applyFilters" class="apply-filters-button">필터 적용</button>
        </div>

        <div class="main-content">
          <div class="business-list">
            <div v-for="business in displayedBusinesses" 
                 :key="business.id" 
                 class="business-item"
                 @click="goToBizDetail(business.id)">
              <img :src="business.image" :alt="business.name" class="business-image">
              <div class="business-info">
                <h2>{{ business.name }}</h2>
                <p>{{ business.description }}</p>
                <div class="rating">★ {{ business.rating.toFixed(1) }}</div>
                <button @click.stop="createRequest(business.id)">요청서 작성하기</button>
                <button @click.stop="viewReviews(business.id)">리뷰 상세 보기</button>
              </div>
            </div>
          </div>

          <button v-if="hasMoreBusinesses" @click="loadMore" class="load-more-btn">
            업체 더보기
          </button>
        </div>
      </div>
    </template>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();

const filters = ref([
  { id: 'birthday', label: '생일파티', checked: false },
  { id: 'group', label: '단체파티', checked: false },
  { id: 'funeral', label: '장례 서비스', checked: false },
  { id: 'invitation', label: '초대장', checked: false },
  { id: 'party', label: '파티 모임', checked: false },
  { id: 'venue', label: '장소 대관', checked: false },
]);

const searchType = ref('title');
const searchQuery = ref('');
const businesses = ref([]);
const displayCount = ref(10);
const isLoading = ref(true);

const displayedBusinesses = computed(() => businesses.value.slice(0, displayCount.value));
const hasMoreBusinesses = computed(() => displayCount.value < businesses.value.length);

const applyFilters = () => {
  fetchBusinesses();
};

const search = () => {
  fetchBusinesses();
};

const loadMore = () => {
  displayCount.value += 10;
};

const fetchBusinesses = async () => {
  isLoading.value = true;
  const queryParams = new URLSearchParams();
  
  filters.value.forEach(filter => {
    if (filter.checked) {
      queryParams.append('filter', filter.id);
    }
  });

  if (searchQuery.value) {
    queryParams.append('searchType', searchType.value);
    queryParams.append('query', searchQuery.value);
  }

  try {
    const url = `http://localhost:8888/biz-list?${queryParams.toString()}`;
    console.log('요청 URL:', url);
    const response = await fetch(url);
    const data = await response.json();
    businesses.value = data.biz || [];
    displayCount.value = 10;
  } catch (error) {
    console.error('업체 목록을 불러오는 중 오류가 발생했습니다:', error);
  } finally {
    isLoading.value = false;
  }
};

const createRequest = (id) => {
  // 요청서 작성 로직
  console.log(`요청서 작성 (업체 ID: ${id})`);
};

const viewReviews = (id) => {
  router.push({ name: 'BizReviews', params: { id: id } });
};

const goToBizDetail = (id) => {
  router.push({ name: 'BizDetail', params: { id: id } });
};

onMounted(() => {
  fetchBusinesses();
});
</script>

<style scoped>
.biz-list-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.search-section {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

.search-section input {
  flex-grow: 1;
  padding: 10px;
  font-size: 16px;
  border: 1px solid #ddd;
  border-radius: 5px 0 0 5px;
}

.search-options {
  display: flex;
}

.search-options select {
  padding: 10px;
  font-size: 16px;
  border: 1px solid #ddd;
  border-left: none;
  background-color: white;
}

.search-button {
  padding: 10px 20px;
  font-size: 16px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 0 5px 5px 0;
  cursor: pointer;
}

.content-wrapper {
  display: flex;
}

.filter-section {
  width: 200px;
  margin-right: 20px;
  background-color: #f0f0f0;
  padding: 15px;
  border-radius: 5px;
}

.filter-item {
  margin-bottom: 10px;
}

.main-content {
  flex-grow: 1;
}

.featured-business {
  position: relative;
  margin-bottom: 20px;
}

.featured-business img {
  width: 100%;
  height: auto;
  border-radius: 10px;
}

.featured-overlay {
  position: absolute;
  bottom: 20px;
  right: 20px;
  text-align: right;
}

.featured-overlay h2 {
  color: white;
  margin-bottom: 10px;
}

.featured-button {
  background-color: white;
  color: black;
  border: none;
  padding: 5px 15px;
  margin-left: 10px;
  border-radius: 5px;
  cursor: pointer;
}

.business-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
  gap: 20px;
}

.business-item {
  border: 1px solid #ddd;
  border-radius: 5px;
  overflow: hidden;
  cursor: pointer;
  transition: box-shadow 0.3s ease;
}

.business-item:hover {
  box-shadow: 0 0 10px rgba(0,0,0,0.1);
}

.business-image {
  width: 100%;
  height: 150px;
  object-fit: cover;
}

.business-info {
  padding: 15px;
}

.business-info h2 {
  margin-top: 0;
}

.business-info button {
  background-color: #4CAF50;
  color: white;
  border: none;
  padding: 5px 10px;
  margin-top: 10px;
  margin-right: 5px;
  border-radius: 3px;
  cursor: pointer;
}

.load-more-btn {
  display: block;
  width: 100%;
  padding: 10px;
  margin-top: 20px;
  margin-bottom: 100px; /* Footer를 고려한 여백 */
  background-color: #f0f0f0;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
  font-weight: bold;
  color: #333;
}

.load-more-btn:hover {
  background-color: #e0e0e0;
}

.rating {
  color: #ffd700;
  font-weight: bold;
  margin-bottom: 5px;
}

.biz-list-container {
  min-height: calc(100vh - 100px); /* Footer 높이를 고려하여 조정 */
}

.loading-message {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  font-size: 1.5rem;
  color: #333;
}

.apply-filters-button {
  margin-top: 15px;
  padding: 8px 15px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 14px;
}

.apply-filters-button:hover {
  background-color: #45a049;
}
</style>
